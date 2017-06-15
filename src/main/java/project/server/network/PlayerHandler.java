package project.server.network;

import project.controller.cardsfactory.BuildingCard;
import project.controller.cardsfactory.LeaderCard;
import project.controller.cardsfactory.VenturesCard;
import project.controller.checkfunctions.AllCheckFunctions;
import project.controller.checkfunctions.BasicCheckFunctions;
import project.controller.Constants;
import project.controller.supportfunctions.LeaderCardRequirements;
import project.messages.*;
import project.messages.updatesmessages.Updates;
import project.model.FamilyMember;
import project.model.Player;
import project.server.Room;
import project.server.network.exception.*;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

public abstract class PlayerHandler extends Player {

    Room room;
    AllCheckFunctions checkFunctions;
    final static String NO_ACTION_CAN_BE_DONE = "no action can be done";

    public PlayerHandler(){
        checkFunctions = new BasicCheckFunctions();
    }
    /**
     * @param position
     * @param familyM
     */

    /**
     * controllare se si puo fare una check functions che si chiamauguale CheckIfCanTakeCard che prendere come parametr una volta buildingCard una volta TerritoryCard e cosi via
     * @param towerColor
     * @param floor
     * @param familyM
     */
    protected void clientTakeDevelopementCard(String towerColor, int floor, FamilyMember familyM) throws CantDoActionException, CanUseBothPaymentMethodException{
        boolean canTakeCard = checkFunctions.checkPosition(floor, room.getBoard().getTrueArrayList(towerColor), familyM);
        int canTakeVenturesCard;
        boolean towerOccupied = checkFunctions.checkTowerOccupied(room.getBoard().getTrueArrayList(towerColor));
            if (towerColor == "purple") {
                canTakeVenturesCard = checkFunctions.checkCardCostVentures((VenturesCard) room.getBoard().getTrueArrayList(towerColor)[floor].getCardOnThisFloor(), this, towerOccupied, room.getBoard().getTrueArrayList(towerColor)[floor].getDiceValueOfThisFloor(), familyM.getMyValue());
                if (canTakeVenturesCard == 0 || !canTakeCard)
                    throw new CantDoActionException();
                else if (canTakeVenturesCard == 3){
                    int answer = canUseBothPaymentMethod();
                    //anser è la risposta su che pagamento vuoi usare
                }
                else room.getGameActions().takeVenturesCard(room.getBoard().getTrueArrayList(towerColor)[floor], familyM, this, towerOccupied, canTakeVenturesCard);
            } else {
                canTakeCard = canTakeCard && checkFunctions.checkCardCost(room.getBoard().getTrueArrayList(towerColor)[floor].getCardOnThisFloor(), this, towerOccupied, room.getBoard().getTrueArrayList(towerColor)[floor].getDiceValueOfThisFloor(), familyM.getMyValue());
                if (canTakeCard) {
                    room.getGameActions().takeNoVenturesCard(room.getBoard().getTrueArrayList(towerColor)[floor], familyM, this, towerOccupied);
                    room.getGameActions().broadcastNotifications(new Notify(getName() + " has taken " + room.getBoard().getTrueArrayList(towerColor)[floor].getCardOnThisFloor().getName()));
                } else throw new CantDoActionException();
            }

    }

    protected void clientTakeBonusDevelopementCard(String towerColour, int floor, FamilyMember familyMember) throws CantDoActionException{
        //todo oppure modificare quello di sopra
    }

    /**
     * this method is used when the system ask to the client which of VenturesCard payment he wants to use
     * @param position
     * @param familyMember
     * @param paymentChoosen
     */
    protected void clientChosenPaymentForVenturesCard(int position, FamilyMember familyMember, int paymentChoosen){
        boolean towerOccupied = checkFunctions.checkTowerOccupied(room.getBoard().getTrueArrayList(Constants.COLOUR_OF_TOWER_WITH_VENTURES_CARD));
        room.getGameActions().takeVenturesCard(room.getBoard().getTrueArrayList(Constants.COLOUR_OF_TOWER_WITH_VENTURES_CARD)[position], familyMember, this, towerOccupied, paymentChoosen);
    }

    /**
     *
     * @param position
     * @param familyM
     * @param servantsNumber
     */

    protected void harvester(int position, FamilyMember familyM, int servantsNumber) throws CantDoActionException {
        boolean canTakeCard = checkFunctions.checkPosition(position,room.getBoard().getTrueArrayList("harvester"),familyM);
        if (canTakeCard)
            room.getGameActions().harvester(position,familyM,servantsNumber,this);
        else
            throw new CantDoActionException();
    };


    /**
     * @param position
     * @param familyM
     * @param cardToProduct
     * @return
     */

    public void production(int position, FamilyMember familyM, List<BuildingCard> cardToProduct) throws CantDoActionException {
        int maxValueOfProduction;
        maxValueOfProduction = familyM.getMyValue() + getPersonalBoardReference().getBonusOnActions().getProductionBonus();
        if (position > 0)
            maxValueOfProduction = maxValueOfProduction -3;
        boolean canTakeCard = checkFunctions.checkPosition(position,room.getBoard().getTrueArrayList("production"),familyM);
        canTakeCard = canTakeCard && checkFunctions.checkAvaiabiltyToProduct((ArrayList<BuildingCard>) cardToProduct, maxValueOfProduction);
        if (canTakeCard)
            room.getGameActions().production(position,familyM, (ArrayList<BuildingCard>) cardToProduct,this);
        else
            throw new CantDoActionException();
    };

    /**
     * @param position
     * @param familyM
     * @return
     */
    public void goToMarket(int position, FamilyMember familyM) throws CantDoActionException {
        boolean canGoToMarket = checkFunctions.checkPosition(position,room.getBoard().getTrueArrayList("market"),familyM);
        if (canGoToMarket)
            room.getGameActions().goToMarket(position,familyM,this);
        else
            throw new CantDoActionException();
    };

    /**
     * @return
     */
    public void jumpTurn(){
        room.getGameActions().nextTurn(this);
    }

    /**
     * @param leaderName
     * @return
     */
    public void playLeaderCard(String leaderName) throws CantDoActionException {
        if (LeaderCardRequirements.checkRequirements(leaderName,this))
            room.getGameActions().playLeaderCard(leaderName,this);
        else
            throw new CantDoActionException();
    };

    /**
     * @param leaderName
     * @return
     */
    public void discardLeaderCard(String leaderName) throws CantDoActionException {
        for (LeaderCard l: getPersonalBoardReference().getMyLeaderCard()){
            if (l.getName().equals(leaderName)){
                room.getGameActions().discardLeaderCard(leaderName,this);
            }
        }
        //arriva qua in caso non ha trovato la leader card nel proprio mazzo, forse è un controllo inutile se presuppongo che mi arrivano
        //carte solo che ho
        throw new CantDoActionException();
    };

    /**
     * @return
     */
    public void rollDices(){
        //controllare se è corretto con un test, il secondo controllo indica se io sono il primo di turno e quindi ho la facoltà di tirare i dadi
        if (room.getBoard().getEndRound() && room.getBoard().getTurn().getPlayerTurn().get(0).equals(this))
            room.getGameActions().rollDice();
    };

    /**
     * @param privelgeNumber
     */
    public void goToCouncilPalace(int privelgeNumber, FamilyMember familyMember){
        // ho supposto che posso andare nel palazzo del consiglio anche se c'è gia un altro del mio colore
        room.getGameActions().goToCouncilPalace(privelgeNumber,familyMember,this);
    };

    /**
     *
     * @param privilegeNumber
     */
    public void takePrivilege(int privilegeNumber){
        room.getGameActions().takeCouncilPrivilege(privilegeNumber, this);
    }

    public void pray(){
        room.getGameActions().pray(this);
    }

    public void dontPray(){
        room.getGameActions().takeExcommunication(this);
    }

    protected FamilyMember findFamilyMember(String colour) {
        for (FamilyMember familyMember : getAllFamilyMembers())
            if (familyMember.getMyColour().equals(colour))
                return familyMember;

        return null;
    }
    /**
     * da qua iniziano a comparire i metodi di ritorno al client. che poi potrebbero essere anche lo stesso dove cambia solo il coso che mandi
     * in rmi però è più comodo avere metodi diversi
     * penso anche in socket cosi sai gia che se devo mandare dal metodo chiamato possoUsareEntrambi... so che il parametro da passare è quella
     * stringa BOTH_COST_CAN_BE_SATISFIED
     */


    public  void doBonusHarv(BonusInteraction returnFromEffect, int position) throws CantDoActionException{}

    public abstract void cantDoAction();

    public abstract int canUseBothPaymentMethod() ;

    public abstract void itsMyTurn(); //non saprei che parametri passare

    /**
     * manda al client la richiesta se vuole pregare o meno. il client o manderà la richiest di pregare o si rimetterà in ascolto
     */
    public abstract void sendAskForPraying(); //




    /*
    questi getter e setter che ci sono qua sotto servono per modificare la check function di un giocatoe con il decorato
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



    public abstract void sendString( String message );

    public abstract void sendAnswer(Object returnFromEffect);

    public abstract void sendNotification(Notify notifications);

    public abstract void sendUpdates(Updates updates);

    public abstract int sendPossibleChoice(String kindOfChoice);

    public abstract void sendBonusTowerAction(BonusInteraction returnFromEffect) throws IOException, ClassNotFoundException;

    public abstract void sendBonusProdOrHarv(BonusProductionOrHarvesterAction returnFromEffect) throws IOException, ClassNotFoundException;

    public void doBonusProduct(BonusProductionOrHarvesterAction returnFromEffect, int intServantsNumber, ArrayList<BuildingCard> cards) {
    }
}
