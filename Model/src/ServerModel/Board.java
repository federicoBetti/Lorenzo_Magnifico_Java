package ServerModel;



import java.util.*;

/**
 * 
 */
public final class Board {

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
    private List<Player> TurnOrder;

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