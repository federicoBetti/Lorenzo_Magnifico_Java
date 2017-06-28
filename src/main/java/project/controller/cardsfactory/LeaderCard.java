package project.controller.cardsfactory;

import java.io.Serializable;

/**
 * 
 */
public class LeaderCard implements Serializable{

    private String name;

    private boolean isPlayed;

    private boolean onePerTurn;

    private String requirementsDescription;

    private String cardDescription;


    public boolean isOnePerTurn() {
        return onePerTurn;
    }

    public String getName() {
        return name;
    }

    public boolean isPlayed() {
        return isPlayed;
    }

    public void setPlayed(boolean played) {
        isPlayed = played;
    }

    public String getCardDescription() {
        return cardDescription;
    }

    public String getRequirementsDescription() {
        return requirementsDescription;
    }
}