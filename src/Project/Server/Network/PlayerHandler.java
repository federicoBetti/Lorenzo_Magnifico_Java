package Project.Server.Network;

import Project.Controller.CardsFactory.BuildingCard;
import Project.Controller.CheckFunctions.AllCheckFunctions;
import Project.Controller.CheckFunctions.BasicCheckFunctions;
import Project.MODEL.Card;
import Project.MODEL.FamilyMember;
import Project.MODEL.Player;
import Project.Server.Room;
import Project.toDelete.BonusInteraction;

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
    private BonusInteraction clientTakeDevelopementCard(String towerColor, int position, FamilyMember familyM){
        boolean canTakeCard = checkFunctions.Check_Position(position, room.getBoard().getTrueArrayList(towerColor), familyM);
        canTakeCard = canTakeCard && checkFunctions.CheckCardCost(room.getBoard().getTrueArrayList(towerColor)[position].getCardOnThisFloor(),this);
        if (towerColor == "green"){
            canTakeCard = canTakeCard && checkFunctions.CheckCapabilityToTakeTerritory(this);
        }
        if (canTakeCard){
            BonusInteraction bonusInteraction = room.getGameActions().takeDevelopementCard(room.getBoard().getTrueArrayList(towerColor)[position], familyM, this);
        }
        //TODO vedere come fare il ritorno
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
    };


    /**
     * @param position
     * @param familyM
     * @param cardToProduct
     * @return
     */
    //qua secondo me non serve il numero di servi perche tanto dici quali carte vuoi fare quindi lo cappisci dal numero max
    public void Production(int position, FamilyMember familyM, ArrayList<BuildingCard> cardToProduct){
        boolean canTakeCard = checkFunctions.Check_Position(position,room.getBoard().getTrueArrayList("production"),familyM);
        canTakeCard = canTakeCard && checkFunctions.CheckAvaiabiltyToProduct(cardToProduct,this);
        if (canTakeCard)
            room.getGameActions().production(position,familyM,cardToProduct,this);
    };

    /**
     * @param position
     * @param familyM
     * @return
     */
    public void GoTOMarket(int position, FamilyMember familyM){
        checkFunctions.Check_Position(position,room.getBoard().getTrueArrayList("market"),familyM);
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
    public void PlayLeaderCard(String leaderName ){};

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

}
