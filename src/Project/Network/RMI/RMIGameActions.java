package Project.Network.RMI;

import Project.Client.Network.RMI.RMIServerToClientInterface;
import Project.MODEL.Card;
import Project.MODEL.Card.Card;
import Project.MODEL.FamilyMember;
import Project.Network.ControllerInterface;
import Project.toDelete.BonusInteraction;

import java.util.*;

/**
 *
 */
public class RMIGameActions implements ControllerInterface {

    /**
     * invocando metoi sul myClient chiamo cose sul client. è il modo per comunicare
     */
    private RMIServerToClientInterface myClient;
    /**
     * dal costruttore prendo un riferimento al client cosi posso chiamare i metodi su di lui
     */
    public RMIGameActions(RMIServerToClientInterface rmiServerToClientInterface) {
        this.myClient = rmiServerToClientInterface;
    }

    /**
     * @param position
     * @param familyM
     */
    public void TakeDevelopementCard(int position, FamilyMember familyM) {
        // TODO implement here
    }

    @Override
    public BonusInteraction TakeDevelopementCard(String towerColor, int position, FamilyMember familyM) {
        return null;
    }

    /**
     * @param position
     * @param familyM
     * @return
     */
    public void Harvester(int position, FamilyMember familyM) {
        // TODO implement here
        return null;
    }

    /**
     * @param position
     * @param familyM
     * @return
     */
    public void Production(int position, FamilyMember familyM) {
        // TODO implement here
        return null;
    }

    /**
     * @param position
     * @param familyM
     * @param CardToProduct
     * @return
     */
    public void Production(int position, FamilyMember familyM, ArrayList<Card> CardToProduct) {
        // TODO implement here
        return null;
    }

    /**
     * @param position
     * @param familyM
     * @return
     */
    public void GoTOMarket(int position, FamilyMember familyM) {
        // TODO implement here
        return null;
    }

    /**
     * @return
     */
    public void JumpTurn() {
        // TODO implement here
        return null;
    }

    @Override
    public void PlayLeaderCard(String leaderName) {

    }

    @Override
    public void DiscardLeaderCard(String leaderName) {

    }

    /**
     * @param LeaderCardIndex
     * @return
     */
    public void PlayLeaderCard(int LeaderCardIndex) {
        // TODO implement here
        return null;
    }

    /**
     * @param LeaderCardIndex
     * @return
     */
    public void DiscardLeaderCard(int LeaderCardIndex) {
        // TODO implement here
        return null;
    }

    /**
     * @return
     */
    public void RollDice() {
        // TODO implement here
        return null;
    }

    /**
     * @param PrivelgeNumber
     * @return
     */
    public void GoToCouncilPalace(int PrivelgeNumber) {
        // TODO implement here
        return null;
    }

    /**
     * @return
     */
    public void GoToCouncilPalace() {
        // TODO implement here
        return null;
    }

}