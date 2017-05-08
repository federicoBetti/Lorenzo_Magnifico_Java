package ServerModel;

import java.util.*;

/**
 * 
 */
public class LeaderCard {

    /**
     * Default constructor
     */
    public LeaderCard() {
    }

    /**
     * 
     */
    private String name;

    /**
     * 
     */
    private boolean onePerTurn;


    private boolean isPlayed = false;
    /**
     * @return
     */
    public void Discard() {
        // TODO implement here
    }

    /**
     * @return
     */
    public void PlayCard() {
        // TODO implement here
    }

    /**
     * @return
     */
    private void CheckRequirements() {
        // TODO implement here
    }

    public boolean isPlayed() {
        return isPlayed;
    }

    public void setPlayed(boolean played) {
        isPlayed = played;
    }
}