package Project.Server.Network;

import Project.toDelete.BonusInteraction;
import Project.MODEL.Card;
import Project.MODEL.Card.Card;
import Project.MODEL.FamilyMember;

import java.util.*;

/**
 * 
 */
public interface ControllerInterface {

    /**
     * @param position 
     * @param familyM
     */
    public BonusInteraction TakeDevelopementCard(String towerColor, int position, FamilyMember familyM);

    /**
     * @param position 
     * @param familyM 
     * @return
     */
    public void Harvester(int position, FamilyMember familyM);

    /**
     * @param position 
     * @param familyM 
     * @return
     */
    public void Production(int position, FamilyMember familyM);

    /**
     * @param position 
     * @param familyM 
     * @param cardToProduct
     * @return
     */
    public void Production(int position, FamilyMember familyM, ArrayList<Card> cardToProduct);

    /**
     * @param position 
     * @param familyM 
     * @return
     */
    public void GoTOMarket(int position, FamilyMember familyM);

    /**
     * @return
     */
    public void JumpTurn();

    /**
     * @param leaderName
     * @return
     */
    public void PlayLeaderCard(String leaderName );

    /**
     * @param leaderName
     * @return
     */
    public void DiscardLeaderCard(String leaderName);

    /**
     * @return
     */
    public void RollDice();

    /**
     * @param privelgeNumber
     * @return
     */
    public void GoToCouncilPalace(int privelgeNumber);

    /**
     * @return
     */
    public void GoToCouncilPalace();

}