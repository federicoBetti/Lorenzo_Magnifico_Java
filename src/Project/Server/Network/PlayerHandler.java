package Project.Server.Network;

import Project.MODEL.Card;
import Project.MODEL.FamilyMember;
import Project.MODEL.Player;
import Project.Server.Room;
import Project.toDelete.BonusInteraction;

import java.util.ArrayList;

/**
 * Created by raffaelebongo on 22/05/17.
 */
public class PlayerHandler extends Player {

    Room room;



    /**
     * @param position
     * @param familyM
     */
    public BonusInteraction TakeDevelopementCard(String towerColor, int position, FamilyMember familyM){

    };

    /**
     * @param position
     * @param familyM
     * @return
     */
    public void Harvester(int position, FamilyMember familyM){

    };

    /**
     * @param position
     * @param familyM
     * @return
     */
    public void Production(int position, FamilyMember familyM){};

    /**
     * @param position
     * @param familyM
     * @param cardToProduct
     * @return
     */
    public void Production(int position, FamilyMember familyM, ArrayList<Card> cardToProduct){

    };

    /**
     * @param position
     * @param familyM
     * @return
     */
    public void GoTOMarket(int position, FamilyMember familyM){

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
