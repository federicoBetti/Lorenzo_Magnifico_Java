package project.server.network;

import project.model.DevelopmentCard;
import project.messages.BonusInteraction;
import project.model.FamilyMember;

import java.util.*;

/**
 * 
 */
public interface ControllerInterface {

    /**
     * @param position 
     * @param familyM
     */
    public BonusInteraction takeDevelopementCard(String towerColor, int position, FamilyMember familyM);

    /**
     * @param position 
     * @param familyM 
     * @return
     */
    public void harvester(int position, FamilyMember familyM);

    /**
     * @param position 
     * @param familyM 
     * @return
     */
    public void production(int position, FamilyMember familyM);

    /**
     * @param position 
     * @param familyM 
     * @param cardToProduct
     * @return
     */
    public void production(int position, FamilyMember familyM, List<DevelopmentCard> cardToProduct);

    /**
     * @param position 
     * @param familyM 
     * @return
     */
    public void goTOMarket(int position, FamilyMember familyM);

    /**
     * @return
     */
    public void jumpTurn();

    /**
     * @param leaderName
     * @return
     */
    public void playLeaderCard(String leaderName );

    /**
     * @param leaderName
     * @return
     */
    public void discardLeaderCard(String leaderName);

    /**
     * @return
     */
    public void rollDice();

    /**
     * @param privelgeNumber
     * @return
     */
    public void goToCouncilPalace(int privelgeNumber);

    /**
     * @return
     */
    public void goToCouncilPalace();

}