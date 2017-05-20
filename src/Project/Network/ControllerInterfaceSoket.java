package Project.Network;

import Project.MODEL.Card;
import Project.MODEL.Card.Card;
import Project.MODEL.FamilyMember;

import java.util.*;

/**
 * 
 */
public class ControllerInterfaceSoket implements ControllerInterface {

    /**
     * Default constructor
     */
    public ControllerInterfaceSoket() {
    }

    /**
     * @param position 
     * @param familyM
     */
    public void TakeDevelopementCard(String TowerColor, int position, FamilyMember familyM) {
        // TODO implement here
    }

    /**
     * @param position 
     * @param familyM 
     * @return
     *
     * Commento: serve la posizione? non si mette nella prima posizione libera?
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

    @Override
    public void GoTOMarket(int position, FamilyMember familyM) {

    }

    /**
     * @param position 
     * @param familyM 
     * @return
     */
    public void GoToMarket(int position, FamilyMember familyM) {
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

    /**
     * @param leaderCardName
     * @return
     *
     * IO PASSEREI LA STRINGA CON IL NOME DELLA CARTA LEADER IN MODO DA FARE IL MAPPING FRA ENUM E NOME DELLA CARTA
     */
    public void PlayLeaderCard( String leaderCardName ) {
        // TODO implement here
        return null;
    }

    /**
     * @param leaderCardName
     * @return
     */
    public void DiscardLeaderCard ( String leaderCardName ) {
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