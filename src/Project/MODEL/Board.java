package Project.MODEL;


import Project.Controller.CardsFactory.ExcommunicationZone;

import java.util.ArrayList;

/**
 * 
 */
public final class Board {


    private Tower towers;

    /**
     * 
     */
    private ArrayList<Harvester> harvester_zone;

    /**
     * 
     */
    private ArrayList<Production> Production_zone;

    /**
     * 
     */
    private ArrayList<Market> Market_zone;

    /**
     * 
     */
    private ArrayList<Council> Council_zone;

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