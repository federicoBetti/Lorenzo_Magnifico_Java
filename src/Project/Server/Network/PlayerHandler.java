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

import java.util.ArrayList;
import java.util.Objects;


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
    private void clientTakeDevelopementCard(String towerColor, int position, FamilyMember familyM){
        boolean canTakeCard = checkFunctions.Check_Position(position, room.getBoard().getTrueArrayList(towerColor), familyM);
        int canTakeVenturesCard;
        if (towerColor == "purple") {
            canTakeVenturesCard = checkFunctions.CheckCardCostVentures((VenturesCard) room.getBoard().getTrueArrayList(towerColor)[position].getCardOnThisFloor(), this);
            if (canTakeVenturesCard == 0)
                canTakeCard = false;
            else  if (canTakeVenturesCard == 3)
                throw possoUsareEntrambiIPagamentException();
        }
        if ("green" == towerColor){
            canTakeCard = canTakeCard && checkFunctions.CheckCapabilityToTakeTerritory(this);
        }
        if (canTakeCard){
            room.getGameActions().takeDevelopementCard(room.getBoard().getTrueArrayList(towerColor)[position], familyM, this);
        }
        else
            throw cantDoActionException();
        //TODO vedere come fare il ritorno negativ
    };

    /**
     * @param position
     * @param familyM
     * @return
     */

    //TODO mi serve sapere o quanti servi voglio usare o in quali carte voglio l'harvester (direi la prima)
    private void Harvester(int position, FamilyMember familyM, int servantsNumber){
        boolean canTakeCard = checkFunctions.Check_Position(position,room.getBoard().getTrueArrayList("harvester"),familyM);
        if (canTakeCard)
            room.getGameActions().harvester(position,familyM,servantsNumber,this);
        else
            throw cantDoActionException();
    };


    /**
     * @param position
     * @param familyM
     * @param cardToProduct
     * @return
     */
    //qua secondo me non serve il numero di servi perche tanto dici quali carte vuoi fare quindi lo cappisci dal numero max
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
            throw cantDoActionException();
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
            throw cantDoActionException();
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
            if (Objects.equals(l.getName(), leaderName)){
                if (leaderCardRequirements.CheckRequirements(leaderName,this))
                    room.getGameActions().playLeaderCard(leaderName,this);
                else
                    throw cantDoActionException();
            }
        }

        throw cantDoActionException();
    };

    /**
     * @param leaderName
     * @return
     */
    public void DiscardLeaderCard(String leaderName){

    };

    /**
     * @return
     */
    public void RollDice(){
    };

    /**
     * @param privelgeNumber
     * @return
     */
    public void GoToCouncilPalace(int privelgeNumber){

    };

    /**
     * @return
     */
    public void GoToCouncilPalace(){

    };

    public abstract void sendReturn(BonusInteraction returnFromEffect);

}
