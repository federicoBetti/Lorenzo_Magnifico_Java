package project.server.network;

import project.controller.cardsfactory.*;
import project.controller.checkfunctions.AllCheckFunctions;
import project.controller.checkfunctions.BasicCheckFunctions;
import project.controller.Constants;
import project.controller.effects.effectsfactory.LeaderCardRequirements;
import project.messages.*;
import project.messages.updatesmessages.Updates;
import project.model.*;
import project.server.Room;
import project.server.network.exception.*;

import java.util.ArrayList;
import java.util.List;

public abstract class PlayerHandler extends Player {

    private Room room;
    private AllCheckFunctions checkFunctions;
    final static String NO_ACTION_CAN_BE_DONE = "no action can be done";

    public PlayerHandler(){
        super();
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

            if (canPayCard && canPlaceFamiliar) {
                gameActions().takeNoVenturesCard(zone, familyM, this, towerOccupied);
                gameActions().broadcastNotifications(new Notify(getName() + " has taken " + card.getName()));
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
            return canTakeVenturesCard;
    }

    protected void clientTakeBonusDevelopementCard(String towerColour, int floor, TowerAction returnFromEffect) throws CantDoActionException {
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
                gameActions().takeNoVenturesCard(zone, this, towerOccupied, diceValueOfFamiliar);
                gameActions().broadcastNotifications(new Notify(getName() + " has taken " + card.getName()));
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

    //todo rifare il metodo senza position
    protected void harvester(FamilyMember familyM, int servantsNumber) throws CantDoActionException {
        List<Harvester> harvesterZone = room.getBoard().getHarvesterZone();
        boolean canTakeCard;
        int position = firstFreePosition(harvesterZone, familyM.getFamilyColour());

        gameActions().harvester(position,familyM,servantsNumber,this);
    }

    private int firstFreePosition(List<? extends Position> harvesterZone, String familyColour) throws CantDoActionException {
        for (Position p: harvesterZone){
            if (p.getFamiliarOnThisPosition().getFamilyColour().equals(familyColour))
                throw new CantDoActionException();
        }
        if (room.numberOfPlayerOn() > 2 && harvesterZone.size() > 0)
            throw new CantDoActionException();

        return harvesterZone.size();
    }


    /**
     * @param familyM
     * @param cardToProduct
     * @return
     */

    //todo rifare metodo senza posizione
    public void production(FamilyMember familyM, List<BuildingCard> cardToProduct) throws CantDoActionException {
        int maxValueOfProduction;
        List<Production> productionZone = room.getBoard().getProductionZone();
        boolean canTakeCards;
        int position = firstFreePosition(productionZone,familyM.getFamilyColour());

        maxValueOfProduction = familyM.getMyValue() + getPersonalBoardReference().getBonusOnActions().getProductionBonus() + checkFunctions.getServants(this);

        if (position > 0)
            maxValueOfProduction = maxValueOfProduction - Constants.MALUS_PROD_HARV;
        checkAvaiabiltyToProduct(cardToProduct, maxValueOfProduction);

        gameActions().production(position,familyM,cardToProduct,this);
    }


    public void checkAvaiabiltyToProduct(List<BuildingCard> cardToProduct, int maxValueOfProduction) throws CantDoActionException {
        BuildingCost totalCardCosts = new BuildingCost();
        for (BuildingCard b: cardToProduct){
            if (b.getCost().getDiceCost() > maxValueOfProduction)
                throw new CantDoActionException();
        }
        //todo qua in teoria mancano dei controlli se hai abbastanza risorse per fare produzione con scambio
        return ;
    }

    /**
     * @param position
     * @param familyM
     * @return
     */
    protected void goToMarket(int position, FamilyMember familyM) throws CantDoActionException {
        Position[] marketZone = getPosition(Constants.MARKET);
        boolean canGoToMarket = checkFunctions.checkPosition(position,marketZone,familyM);

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
        if (LeaderCardRequirements.checkRequirements(leaderName,this))
            gameActions().playLeaderCard(leaderName,this);
        else
            throw new CantDoActionException();
    }

    /**
     * @param leaderName
     * @return
     */
    public void discardLeaderCard(String leaderName) throws CantDoActionException {
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


    private Position[] getPosition(String towerColor) {
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



    /**
     * da qua iniziano a comparire i metodi di ritorno al client. che poi potrebbero essere anche lo stesso dove cambia solo il coso che mandi
     * in rmi però è più comodo avere metodi diversi
     * penso anche in socket cosi sai gia che se devo mandare dal metodo chiamato possoUsareEntrambi... so che il parametro da passare è quella
     * stringa BOTH_COST_CAN_BE_SATISFIED
     */



    public abstract void cantDoAction();

    public abstract int canUseBothPaymentMethod() ;

    public abstract void itsMyTurn(); //non saprei che parametri passare

    /**
     * manda al client la richiesta se vuole pregare o meno. il client o manderà la richiest di pregare o si rimetterà in ascolto
     */
    public abstract void sendAskForPraying(); //

    public abstract void sendString( String message );

    public abstract void sendAnswer(Object returnFromEffect);

    public abstract void sendNotification(Notify notifications);

    public abstract void sendUpdates(Updates updates);

    public abstract int sendPossibleChoice(String kindOfChoice);

    public abstract void sendBonusTowerAction(TowerAction returnFromEffect);

    protected FamilyMember findFamilyMember(String colour) {
        for (FamilyMember familyMember : getAllFamilyMembers())
            if (familyMember.getMyColour().equals(colour)) return familyMember;
        return null;
    }

    public void doBonusProduct(BonusProductionOrHarvesterAction returnFromEffect, int intServantsNumber, ArrayList<BuildingCard> cards) throws CantDoActionException {
    }
    public void doBonusHarv(BonusProductionOrHarvesterAction returnFromEffect, int intServantsNumber) throws CantDoActionException {
    }

    public abstract void sendBonusProdOrHarv(BonusProductionOrHarvesterAction returnFromEffect);

    public abstract void sendRequestForPriviledges(TakePrivilegesAction returnFromEffect);

    public abstract void takePriviledgesInArow(TakePrivilegesAction returnFromEffect);

    public abstract void sendActionOk();

    protected void clientTakeDevelopementCard(String towerColour, int floor, FamilyMember familyMember) throws CantDoActionException, CanUseBothPaymentMethodException{
    }
}
