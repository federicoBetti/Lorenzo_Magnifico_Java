package Project.Server.Network;

import Project.Controller.CardsFactory.BuildingCard;
import Project.Controller.CardsFactory.LeaderCard;
import Project.Controller.CardsFactory.VenturesCard;
import Project.Controller.CheckFunctions.AllCheckFunctions;
import Project.Controller.CheckFunctions.BasicCheckFunctions;
import Project.Controller.SupportFunctions.LeaderCardRequirements;
import Project.MODEL.Card;
import Project.MODEL.FamilyMember;
import Project.MODEL.Player;
import Project.Server.Room;
import Project.toDelete.BonusInteraction;
import Project.toDelete.BothCostCanBeSatisfied;
import Project.toDelete.Notify;
import Project.toDelete.OkOrNo;

import java.util.ArrayList;


public abstract class PlayerHandler extends Player {

    Room room;
    AllCheckFunctions checkFunctions;


    PlayerHandler(){
        checkFunctions = new BasicCheckFunctions();
    }
    /**
     * @param position
     * @param familyM
     */

    /**
     * controllare se si puo fare una check functions che si chiamauguale CheckIfCanTakeCard che prendere come parametr una volta buildingCard una volta TerritoryCard e cosi via
     * @param towerColor
     * @param position
     * @param familyM
     */
    private void clientTakeDevelopementCard(String towerColor, int position, FamilyMember familyM){
        boolean canTakeCard = checkFunctions.Check_Position(position, room.getBoard().getTrueArrayList(towerColor), familyM);
        int canTakeVenturesCard;
        if (towerColor == "purple") {
            canTakeVenturesCard = checkFunctions.CheckCardCostVentures((VenturesCard) room.getBoard().getTrueArrayList(towerColor)[position].getCardOnThisFloor(), this);
            if (canTakeVenturesCard == 0) canTakeCard = false;
            else if (canTakeVenturesCard == 3)
                possoUsareEntrambiIPagamentException(new BothCostCanBeSatisfied());
        }
        else
            canTakeCard = canTakeCard && checkFunctions.CheckCardCost(room.getBoard().getTrueArrayList(towerColor)[position].getCardOnThisFloor(),this);
        if (canTakeCard){
            room.getGameActions().takeDevelopementCard(room.getBoard().getTrueArrayList(towerColor)[position], familyM, this);
            room.getGameActions().broadcastNotifications(new Notify(getName() + " has taken " + room.getBoard().getTrueArrayList(towerColor)[position].getCardOnThisFloor().getName() ));
        }
        else
             cantDoActionException(new OkOrNo());
    }



    ;

    /**
     *
     * @param position
     * @param familyM
     * @param servantsNumber
     */

    private void Harvester(int position, FamilyMember familyM, int servantsNumber){
        boolean canTakeCard = checkFunctions.Check_Position(position,room.getBoard().getTrueArrayList("harvester"),familyM);
        if (canTakeCard)
            room.getGameActions().harvester(position,familyM,servantsNumber,this);
        else
             cantDoActionException(new OkOrNo());
    };


    /**
     * @param position
     * @param familyM
     * @param cardToProduct
     * @return
     */

    public void Production(int position, FamilyMember familyM, ArrayList<BuildingCard> cardToProduct){
        int maxValueOfProduction;
        maxValueOfProduction = familyM.getMyValue() + getPersonalBoardReference().getBonusOnActions().getProductionBonus();
        if (position > 0)
            maxValueOfProduction = maxValueOfProduction -3;
        boolean canTakeCard = checkFunctions.Check_Position(position,room.getBoard().getTrueArrayList("production"),familyM);
        canTakeCard = canTakeCard && checkFunctions.CheckAvaiabiltyToProduct(cardToProduct, maxValueOfProduction);
        if (canTakeCard)
            room.getGameActions().production(position,familyM,cardToProduct,this);
        else
             cantDoActionException(new OkOrNo());
    };

    /**
     * @param position
     * @param familyM
     * @return
     */
    public void GoTOMarket(int position, FamilyMember familyM){
        boolean canGoToMarket = checkFunctions.Check_Position(position,room.getBoard().getTrueArrayList("market"),familyM);
        if (canGoToMarket)
            room.getGameActions().goToMarket(position,familyM,this);
        else
             cantDoActionException(new OkOrNo());
    };

    /**
     * @return
     */
    public void JumpTurn(){

    };

    /**
     * @param leaderName
     * @return
     */
    public void PlayLeaderCard(String leaderName){
        LeaderCardRequirements leaderCardRequirements = new LeaderCardRequirements();
        for (LeaderCard l: getPersonalBoardReference().getMyLeaderCard()){
            if (l.getName().equals(leaderName)){
                if (leaderCardRequirements.CheckRequirements(leaderName,this))
                    room.getGameActions().playLeaderCard(leaderName,this);
                else
                     cantDoActionException(new OkOrNo());
            }
        }

         cantDoActionException(new OkOrNo());
    };

    /**
     * @param leaderName
     * @return
     */
    public void DiscardLeaderCard(String leaderName){
        for (LeaderCard l: getPersonalBoardReference().getMyLeaderCard()){
            if (l.getName().equals(leaderName)){
                room.getGameActions().discardLeaderCard(leaderName,this);
            }
        }
        //arriva qua in caso non ha trovato la leader card nel proprio mazzo, forse è un controllo inutile se presuppongo che mi arrivano
        //carte solo che ho
         cantDoActionException(new OkOrNo());
    };

    /**
     * @return
     */
    public void clientRollDice(){
        //controllare se è corretto con un test, il secondo controllo indica se io sono il primo di turno e quindi ho la facoltà di tirare i dadi
        if (room.getBoard().getEndRound() && room.getBoard().getTurnOrder().get(0).equals(this))
            room.getGameActions().rollDice();
    };

    /**
     * @param privelgeNumber
     */
    public void clientGoToCouncilPalace(int privelgeNumber, FamilyMember familyMember){
        // ho supposto che posso andare nel palazzo del consiglio anche se c'è gia un altro del mio colore
        room.getGameActions().goToCouncilPalace(privelgeNumber,familyMember,this);
    };

    /**
     *
     * @param privilegeNumber
     */
    public void clientTakePrivilege(int privilegeNumber){
        room.getGameActions().takeCouncilPrivilege(privilegeNumber, this);
    }

    /**
     * da qua iniziano a comparire i metodi di ritorno al client. che poi potrebbero essere anche lo stesso dove cambia solo il coso che mandi
     * in rmi però è più comodo avere metodi diversi
     * penso anche in socket cosi sai gia che se devo mandare dal metodo chiamato possoUsareEntrambi... so che il parametro da passare è quella
     * stringa BOTH_COST_CAN_BE_SATISFIED
     */

    public abstract void sendAnswer(BonusInteraction returnFromEffect);

    protected abstract void cantDoActionException(OkOrNo okOrNo);

    protected abstract void possoUsareEntrambiIPagamentException(BothCostCanBeSatisfied bothCostCanBeSatisfied);

    public abstract void itsMyTurn(); //non saprei che parametri passare

    /**
     * manda al client la richiesta se vuole pregare o meno. il client o manderà la richiest di pregare o si rimetterà in ascolto
     */
    public abstract void sendAskForPraying(); //
}
