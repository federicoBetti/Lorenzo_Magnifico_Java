package project.server.network;

import project.controller.cardsfactory.BuildingCard;
import project.controller.cardsfactory.BuildingCost;
import project.controller.cardsfactory.LeaderCard;
import project.controller.cardsfactory.VenturesCard;
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

        if (towerColor == Constants.COLOUR_OF_TOWER_WITH_VENTURES_CARD) {
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

    protected void clientTakeBonusDevelopementCard(String towerColour, int floor) throws CantDoActionException{
        //todo ricordarsi il caso della carta arcobaleno: mettere ad esempio costante "all" che poi nel controllo autorizza a prendere una carda da qualunque torre
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
        Position[] harvesterZone = getPosition(Constants.HARVESTER);
        boolean canTakeCard;

        canTakeCard = checkFunctions.checkPosition(position,harvesterZone,familyM);
        if (canTakeCard)
            gameActions().harvester(position,familyM,servantsNumber,this);
        else
            throw new CantDoActionException();
    }


    /**
     * @param familyM
     * @param cardToProduct
     * @return
     */

    //todo rifare metodo senza posizione
    public void production(FamilyMember familyM, List<BuildingCard> cardToProduct) throws CantDoActionException {
        int maxValueOfProduction;
        Position[] productionZone = getPosition(Constants.PRODUCTION);
        boolean canPlaceCard;
        boolean canTakeCards;

        maxValueOfProduction = familyM.getMyValue() + getPersonalBoardReference().getBonusOnActions().getProductionBonus() + checkFunctions.getServants(this);
        canPlaceCard = checkFunctions.checkPosition(position,productionZone,familyM);

        if (position > 0)
            maxValueOfProduction = maxValueOfProduction - Constants.MALUS_PROD_HARV;
        canTakeCards =  checkAvaiabiltyToProduct(cardToProduct, maxValueOfProduction);

        if (canPlaceCard && canTakeCards)
            gameActions().production(position,familyM,cardToProduct,this);
        else
            throw new CantDoActionException();
    }


    public boolean checkAvaiabiltyToProduct(List<BuildingCard> cardToProduct, int maxValueOfProduction) {
        BuildingCost totalCardCosts = new BuildingCost();
        for (BuildingCard b: cardToProduct){
            if (b.getCost().getDiceCost() > maxValueOfProduction)
                return false;
        }
        //attenione ho fatto che anche la
        return true;
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

    private DevelopmentCard getCard(String towerColor, int floor) {
        return room.getBoard().getTrueArrayList(towerColor)[floor].getCardOnThisFloor();
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
