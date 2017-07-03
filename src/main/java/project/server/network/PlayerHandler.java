package project.server.network;

import project.controller.cardsfactory.*;
import project.controller.checkfunctions.AllCheckFunctions;
import project.controller.checkfunctions.BasicCheckFunctions;
import project.controller.Constants;
import project.controller.effects.effectsfactory.LeaderCardRequirements;
import project.messages.*;
import project.messages.updatesmessages.Updates;
import project.model.*;
import project.server.GameActions;
import project.server.Room;
import project.server.network.exception.*;

import java.util.ArrayList;
import java.util.List;

public abstract class PlayerHandler extends Player {

    private transient Room room;
    private transient AllCheckFunctions checkFunctions;
    final static String NO_AbCTION_CAN_BE_DONE = "no action can be done";
    private LeaderCardRequirements leaderCardRequirements;
    boolean callPray;


    protected PlayerHandler(){
        super();
        callPray = false;
        leaderCardRequirements = new LeaderCardRequirements();
        checkFunctions = new BasicCheckFunctions();
    }

    private GameActions gameActions(){
        return room.getGameActions();
    }
    /**
     * controllare se si puo fare una check functions che si chiamauguale CheckIfCanTakeCard che prendere come parametr una volta buildingCard una volta TerritoryCard e cosi via
     * @param towerColor
     * @param floor
     * @param familyM
     */
    protected void clientTakeDevelopmentCard(String towerColor, int floor, FamilyMember familyM) throws CantDoActionException{
        Position[] tower;
        DevelopmentCard card;
        int diceCost;
        int diceValueOfFamiliar;
        Tower zone;
        boolean canPlaceFamiliar;
        boolean towerOccupied;

        tower = getPosition(towerColor);
        card = getCard(towerColor,floor);
        diceCost = getDiceCost(towerColor,floor);
        diceValueOfFamiliar = familyM.getMyValue();
        zone = getZone(towerColor,floor);
        canPlaceFamiliar = checkFunctions.checkPosition(floor, tower, familyM);
        towerOccupied = checkFunctions.checkTowerOccupied((Tower[])tower);

        if (towerColor.equals(Constants.COLOUR_OF_TOWER_WITH_VENTURES_CARD)) {
            int paymentChosen = checkOnVenturesCost(card, this, towerOccupied,diceCost,diceValueOfFamiliar);
            gameActions().takeVenturesCard(zone, familyM, this, towerOccupied, paymentChosen);
        }
        else {
            boolean canPayCard = checkFunctions.checkCardCost(card, this, towerOccupied,diceCost,diceValueOfFamiliar);
            System.out.println("canPayCard: " + canPayCard + "  postion: " + canPlaceFamiliar);
            if (canPayCard && canPlaceFamiliar) {
                gameActions().takeNoVenturesCard(zone, familyM, this, towerOccupied);
            }
            else {
                throw new CantDoActionException();
            }
        }

    }

    private int checkOnVenturesCost(DevelopmentCard card, PlayerHandler playerHandler, boolean towerOccupied, int diceCost, int diceValueOfFamiliar) throws CantDoActionException {
        int canTakeVenturesCard = checkFunctions.checkCardCostVentures((VenturesCard) card, playerHandler,towerOccupied,diceCost,diceValueOfFamiliar);

        if (canTakeVenturesCard == Constants.CANT_USE_ANY_PAYMENT) {
            throw new CantDoActionException();
        }
        else if (canTakeVenturesCard == Constants.CAN_USE_BOTH_PAYMENT_METHOD){
            return canUseBothPaymentMethod();
        }
        else
            return canTakeVenturesCard - 1; //indice del costo pagabile
    }

    protected void clientTakeBonusDevelopementCard(String towerColour, int floor, TowerAction returnFromEffect) throws CantDoActionException {
        System.out.println("SONO IN CLIENT TAKE DEV CARD");
        if (!(returnFromEffect.getKindOfCard().equals(Constants.ALL_COLOURS) || returnFromEffect.getKindOfCard().equals(towerColour)))
            throw new CantDoActionException();

        DevelopmentCard card = getCard(towerColour,floor);
        Cost realCost = card.getCost();
        Cost costDiscounted = realCost.copyOf();
        costDiscounted = discountCost(costDiscounted, returnFromEffect.getDiscountedResource1(), returnFromEffect.getQuantityDiscounted1());
        costDiscounted = discountCost(costDiscounted, returnFromEffect.getDiscountedResource2(), returnFromEffect.getQuantityDiscounted2());
        card.setCost(costDiscounted);

        int diceValueOfFamiliar = returnFromEffect.getNewCardDicevalue();
        int diceCost = getDiceCost(towerColour,floor);
        Position[] tower = getPosition(towerColour);
        Tower zone = getZone(towerColour,floor);
        boolean towerOccupied = checkFunctions.checkTowerOccupied((Tower[])tower);

        if (towerColour.equals(Constants.COLOUR_OF_TOWER_WITH_VENTURES_CARD)) {
            int paymentChosen = 0;
            try {
                paymentChosen = checkOnVenturesCost(card, this, towerOccupied,diceCost,diceValueOfFamiliar);
            } catch (CantDoActionException e) {
                card.setCost(realCost);
                throw new CantDoActionException();
            }
            gameActions().takeVenturesCard(zone, this, towerOccupied, paymentChosen, diceValueOfFamiliar);
        }
        else {
            boolean canPayCard = checkFunctions.checkCardCost(card, this, towerOccupied,diceCost,diceValueOfFamiliar);
            if (!canPayCard) {
                card.setCost(realCost);
                throw new CantDoActionException();
            }
            else {
                System.out.println("eseguo takeNoVenturesCard");
                gameActions().takeNoVenturesCard(zone, this, towerOccupied, diceValueOfFamiliar);
            }
        }

    }

    private Cost discountCost(Cost costDiscounted, String discountedResource, int quantityDiscounted) {
        switch (discountedResource){
            case Constants.WOOD:{
                costDiscounted.addWood(-quantityDiscounted);
                break;
            }
            case Constants.COIN:{
                costDiscounted.addCoin(-quantityDiscounted);
                break;
            }
            case Constants.STONE:{
                costDiscounted.addStone(-quantityDiscounted);
                break;
            }
        }
        return costDiscounted;
    }

    /**
     * this method is used when the system ask to the client which of VenturesCard payment he wants to use
     * @param position
     * @param familyMember
     * @param paymentChoosen
     */
    protected void clientChosenPaymentForVenturesCard(int position, FamilyMember familyMember, int paymentChoosen){
        Position[] tower;
        Tower zone;
        boolean towerOccupied;

        tower = getPosition(Constants.COLOUR_OF_TOWER_WITH_VENTURES_CARD);
        zone = getZone(Constants.COLOUR_OF_TOWER_WITH_VENTURES_CARD,position);
        towerOccupied = checkFunctions.checkTowerOccupied((Tower[])tower);

        gameActions().takeVenturesCard(zone, familyMember, this, towerOccupied, paymentChoosen);
    }

    /**
     *  @param familyM
     * @param servantsNumber
     */

    protected void harvester(FamilyMember familyM, int servantsNumber) throws CantDoActionException {
        List<Harvester> harvesterZone = room.getBoard().getHarvesterZone();
        boolean canTakeCard;
        int position = firstFreePosition(harvesterZone, familyM.getFamilyColour());

        gameActions().harvester(position,familyM,servantsNumber,this);
    }

    private int firstFreePosition(List<? extends Position> zone, String familyColour) throws CantDoActionException {
        for (Position p: zone){
            if (p.getFamiliarOnThisPosition().getFamilyColour().equals(familyColour))
                throw new CantDoActionException();
        }
        if (room.numberOfPlayerOn() > 2 && zone.size() > 0)
            throw new CantDoActionException();

        return zone.size();
    }

    /**
     * check function on bonus harvester action
     * @param returnFromEffect
     * @param intServantsNumber
     * @throws CantDoActionException
     */
    protected void doBonusHarv(BonusProductionOrHarvesterAction returnFromEffect, int intServantsNumber) throws CantDoActionException {
        int harvesterValue = returnFromEffect.getDiceValue() + getPersonalBoardReference().getBonusOnActions().getHarvesterBonus() + intServantsNumber;

        gameActions().harvesterBonus(harvesterValue, intServantsNumber,this);
    }

    /**
     * @param familyM
     * @param cardToProduct
     * @return
     */
    protected void production(FamilyMember familyM, List<BuildingCard> cardToProduct) throws CantDoActionException {
        int maxValueOfProduction;
        List<Production> productionZone = room.getBoard().getProductionZone();
        boolean canTakeCards;
        int position;

        if ( productionZone.isEmpty() )
            position = 0;
        else
            position = firstFreePosition(productionZone,familyM.getFamilyColour());

        System.out.println("fM: " + familyM.getMyValue());
        System.out.println("personalBoard bonus on action: " + getPersonalBoardReference().getBonusOnActions());
        System.out.println("production Bonus: " + getPersonalBoardReference().getBonusOnActions().getProductionBonus());

        maxValueOfProduction = familyM.getMyValue() + getPersonalBoardReference().getBonusOnActions().getProductionBonus();


        if (position > 0)
            maxValueOfProduction = maxValueOfProduction - Constants.MALUS_PROD_HARV;
        int servantsToPay = checkAvaiabiltyToProduct(cardToProduct, maxValueOfProduction);

        gameActions().production(position,familyM,cardToProduct,servantsToPay,this);
    }


    protected void doBonusProduct(BonusProductionOrHarvesterAction returnFromEffect, ArrayList<BuildingCard> cards) throws CantDoActionException {
        int maxValueOfProduction;
        maxValueOfProduction =  returnFromEffect.getDiceValue() + getPersonalBoardReference().getBonusOnActions().getProductionBonus() + checkFunctions.getServants(this);
        int servantsToPay = checkAvaiabiltyToProduct(cards,maxValueOfProduction);
        gameActions().productionBonus(cards,servantsToPay,this);
    }


    private int checkAvaiabiltyToProduct(List<BuildingCard> cardToProduct, int maxValueOfProduction) throws CantDoActionException {
        BuildingCost totalCardCosts = new BuildingCost();
        int servantsMax = 0;
        for (BuildingCard b: cardToProduct){
            if (b.getCost().getDiceCost() > maxValueOfProduction + checkFunctions.getServants(this))
                throw new CantDoActionException();
            int servnatsToPay = b.getCost().getDiceCost() - maxValueOfProduction;
            if (servnatsToPay > servantsMax)
                servantsMax = servnatsToPay;
        }
        //todo qua in teoria mancano dei controlli se hai abbastanza risorse per fare produzione con scambio
        return servantsMax;
    }

    /**
     * @param position
     * @param familyM
     * @return
     */
    protected void goToMarket(int position, FamilyMember familyM) throws CantDoActionException {
        Position[] marketZone = room.getBoard().getMarketZone();
        boolean canGoToMarket = checkFunctions.checkPosition(position,marketZone,familyM);
        canGoToMarket = canGoToMarket && ((familyM.getMyValue() + checkFunctions.getServants(this)) >0);
        System.out.println("posso andare li? : " + canGoToMarket );
        if (canGoToMarket)
            gameActions().goToMarket(position,familyM,this);
        else
            throw new CantDoActionException();
    }

    /**
     * @return
     */
    public void jumpTurn(){
        gameActions().nextTurn(this);
    }

    /**
     * @param leaderName
     * @return
     */
    protected void playLeaderCard(String leaderName) throws CantDoActionException {
        if (leaderCardRequirements.checkRequirements(leaderName,this))
            gameActions().playLeaderCard(leaderName,this);
        else
            throw new CantDoActionException();
    }

    /**
     * @param leaderName
     * @return
     */
    protected void discardLeaderCard(String leaderName) throws CantDoActionException {
        for (LeaderCard l: getPersonalBoardReference().getMyLeaderCard()){
            if (l.getName().equals(leaderName)){
                gameActions().discardLeaderCard(leaderName,this);
            }
        }
        throw new CantDoActionException();
    }

    /**
     * @return
     */
    public void rollDices(){
        //non faccio controlli perchè presumo che se è arrivata questa chiamata vuol dire che è il seve che ha fatto la richiesta al giocatore
        //tirare i dadi
        gameActions().rollDice();
    }

    /**
     *
     * @param privelgeNumber
     * @param familyMember
     */
    protected void goToCouncilPalace(int privelgeNumber, FamilyMember familyMember){
        // ho supposto che posso andare nel palazzo del consiglio anche se c'è gia un altro del mio colore
        if (familyMember.getMyValue() + checkFunctions.getServants(this) > 1)
            gameActions().goToCouncilPalace(privelgeNumber,familyMember,this);
    }

    /**
     * @param privilegeNumber
     */
    protected void takePrivilege(int privilegeNumber){
        gameActions().takeCouncilPrivilege(privilegeNumber, this);
    }

    public void pray(){
        gameActions().pray(this);
    }

    public void dontPray(){
        gameActions().takeExcommunication(this);
    }




    /**
     * qua ci sono i metodi ausialiari
     * @return
     */
    public AllCheckFunctions getCheckFunctions() {
        return checkFunctions;
    }

    public void setCheckFunctions(AllCheckFunctions checkFunctions) {
        this.checkFunctions = checkFunctions;
    }

    public Room getRoom() {
        return room;
    }


    private Tower[] getPosition(String towerColor) {
        return room.getBoard().getTrueArrayList(towerColor);
    }


    private Tower getZone(String towerColor, int floor) {
        return room.getBoard().getTrueArrayList(towerColor)[floor];
    }

    private int getDiceCost(String towerColor, int floor) {
        return room.getBoard().getTrueArrayList(towerColor)[floor].getDiceValueOfThisFloor();
    }

    private DevelopmentCard getCard(String towerColor, int floor) throws CantDoActionException {
        DevelopmentCard card =  room.getBoard().getTrueArrayList(towerColor)[floor].getCardOnThisFloor();
        if (card == null)
            throw new CantDoActionException();
        return card;
    }

    public void reconnectClient() {
        this.setOn(true);
        System.out.println("Client reconnexted!");
    }


    /**
     * da qua iniziano a comparire i metodi di ritorno al client. che poi potrebbero essere anche lo stesso dove cambia solo il coso che mandi
     * in rmi però è più comodo avere metodi diversi
     * penso anche in socket cosi sai gia che se devo mandare dal metodo chiamato possoUsareEntrambi... so che il parametro da passare è quella
     * stringa BOTH_COST_CAN_BE_SATISFIED
     */



    public abstract void cantDoAction();

    protected abstract int canUseBothPaymentMethod() ;

    public abstract void itsMyTurn(); //non saprei che parametri passare

    /**
     * manda al client la richiesta se vuole pregare o meno. il client o manderà la richiest di pregare o si rimetterà in ascolto
     * @param playerTurn
     */
    public abstract int sendAskForPraying(List<PlayerHandler> playerTurn); //

    public abstract void sendString( String message );

    public abstract void sendAnswer(Object returnFromEffect);

    public abstract void sendNotification(Notify notifications);

    public abstract void sendUpdates(Updates updates);

    public abstract int sendPossibleChoice(String kindOfChoice);

    public abstract void sendBonusTowerAction(TowerAction returnFromEffect);

    protected FamilyMember findFamilyMember(String colour) {
        for (FamilyMember familyMember : getAllFamilyMembers())
            if (familyMember.getMyColour().equals(colour))
                return familyMember;
        System.out.println("ho ritornato un familiar null");
        return null;
    }


    public abstract void sendBonusProdOrHarv(BonusProductionOrHarvesterAction returnFromEffect);

    public abstract void sendRequestForPriviledges(TakePrivilegesAction returnFromEffect);

    public abstract void sendActionOk();

    //todo implement

    public abstract void timerTurnDelayed();

    public abstract void nicknameAlredyUsed();

    public abstract void loginSucceded();

    public void skipTurn(){
        this.waitForYourTurn();
        room.setLastPlayer(this);
        room.getGameActions().nextTurn(this);
    }

    protected abstract void waitForYourTurn();

    public void setRoom(Room room) {
        this.room = room;
    }

    /**
     * this method is used for the draft of leader cards
     * @param leaders list of leader cards in which the player can choose
     * @return
     */
    public abstract String leaderCardChosen(List<LeaderCard> leaders);

    /**
     * this method notify the players that the match is started.
     * @param roomPlayers number of player in the room
     * @param familyColour colour of the player
     */
    public abstract void matchStarted(int roomPlayers, String familyColour);

    public abstract int chooseTile(ArrayList<Tile> tiles);

    public abstract void showStatistics();

    public boolean isCallPray() {
        return callPray;
    }

    public void setCallPray(boolean callPray) {
        this.callPray = callPray;
    }
}
