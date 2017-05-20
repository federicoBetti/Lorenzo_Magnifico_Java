package Project.MODEL;


import Project.Controller.CardsFactory.ExcommunicationZone;

import java.util.ArrayList;

/**
 * 
 */
public final class Board {

    int caso;
    /**
     * Default constructor
     */
    public Board() {
    }


    /**
     * 
     */
    private Tower towers;

    /**
     * 
     */
    private Set<Harvester> harvester_zone;

    /**
     * 
     */
    private Set<Production> Production_zone;

    /**
     * 
     */
    private Set<Market> Market_zone;

    /**
     * 
     */
    private Set<Council> Council_zone;

    /**
     * 
     */
    private ExcommunicationZone[] Excommunication_zone;

    /**
     * 
     */
    private ArrayList<Player> TurnOrder;

    /**
     * 
     */
    private int period;

    /**
     * 
     */
    private int isPlaying;

    /**
     * 
     */
    private int round;

    /**
     * 
     */
    private Deck deckCard;

    /**
     * 
     */
    private int[] diceValue;

    /**
     * @return
     */
    public void EndTunr() {
        // TODO implement here
        return null;
    }

    /**
     * @return
     */
    public void EndGame() {
        // TODO implement here
        return null;
    }

    /**
     * @return
     */
    public void FillTowers() {
        // TODO implement here
        return null;
    }

    /**
     * @return
     */
    public void ResetFamilyMembers() {
        // TODO implement here
        return null;
    }

    /**
     * @return
     */
    public void ThrowDice() {
        // TODO implement here
        return null;
    }

    /**
     * @return
     */
    public void CheckFaithTruck() {
        // TODO implement here
        return null;
    }

    /**
     * @return
     */
    public void CheckFinalPointsMilitary() {
        // TODO implement here
        return null;
    }

    /**
     * @return
     */
    public void CheckWinner() {
        // TODO implement here
        return null;
    }

}