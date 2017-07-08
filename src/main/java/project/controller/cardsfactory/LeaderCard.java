package project.controller.cardsfactory;

import java.io.Serializable;

/**
 * This class represent the Leader card
 */
public class LeaderCard implements Serializable{

    private String name;

    private boolean isPlayed;

    private boolean onePerTurn;

    private boolean requirementsSatisfied;

    private String requirementsDescription;

    private String cardDescription;


    /**
     * Get onePerTurn
     *
     * @return onePerTurn
     */
    public boolean isOnePerTurn() {
        return onePerTurn;
    }

    /**
     * Get name
     *
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Get isPlayed
     *
     * @return isPlayed
     */
    public boolean isPlayed() {
        return isPlayed;
    }

    /**
     * Set played
     *
     * @param played played
     */
    public void setPlayed(boolean played) {
        isPlayed = played;
    }

    /**
     * Get cardDescription
     *
     * @return cardDescription
     */
    public String getCardDescription() {
        return cardDescription;
    }

    /**
     * Get requirementsDescription
     *
     * @return requirementsDescription
     */
    public String getRequirementsDescription() {
        return requirementsDescription;
    }

    /**
     * Get requirementsSatisfied
     *
     * @return requirementsSatisfied
     */
    public boolean isRequirementsSatisfied() {
        return requirementsSatisfied;
    }

    /**
     * Set requirementsSatisfied
     *
     * @param requirementsSatisfied requirementsSatisfied
     */
    public void setRequirementsSatisfied(boolean requirementsSatisfied) {
        this.requirementsSatisfied = requirementsSatisfied;
    }
}