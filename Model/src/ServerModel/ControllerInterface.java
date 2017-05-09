package ServerModel;

import java.util.*;

/**
 * 
 */
public interface ControllerInterface {

    /**
     * @param position 
     * @param familyM
     */
    public BonusInteraction TakeDevelopementCard(int position, FamilyMember familyM);

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
     * @param CardToProduct 
     * @return
     */
    public void Production(int position, FamilyMember familyM, ArrayList<Card> CardToProduct);

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
     * @param LeaderCardIndex 
     * @return
     */
    public void PlayLeaderCard(int LeaderCardIndex);

    /**
     * @param LeaderCardIndex 
     * @return
     */
    public void DiscardLeaderCard(int LeaderCardIndex);

    /**
     * @return
     */
    public void RollDice();

    /**
     * @param PrivelgeNumber 
     * @return
     */
    public void GoToCouncilPalace(int PrivelgeNumber);

    /**
     * @return
     */
    public void GoToCouncilPalace();

}