package project.model;

import java.io.Serializable;

/**
 * This class represents the score
 */
public class Score implements Serializable{

    private int victoryPoints;

    private int militaryPoints;

    private int faithPoints;

    /**
     * This method creates a string that describes the score
     *
     * @return the string
     */
    public String toScreen() {
        return "Victory points: " + String.valueOf(getVictoryPoints()) + "\n" +
                "Faith points: " + String.valueOf(getFaithPoints()) + "\n" +
                "Military Points: " + String.valueOf(getMilitaryPoints());

    }

    /**
     * Get victoryPoints
     *
     * @return victoryPoints
     */
    public int getVictoryPoints() {
        return victoryPoints;
    }

    /**
     * Set victoryPoints
     *
     * @param victoryPoints victoryPoints
     */
    public void setVictoryPoints(int victoryPoints) {
        this.victoryPoints = victoryPoints;
    }

    /**
     * Get victormilitaryPointsyPoints
     *
     * @return militaryPoints
     */
    public int getMilitaryPoints() {
        return militaryPoints;
    }

    /**
     * Set militaryPoints
     *
     * @param militaryPoints militaryPoints
     */
    public void setMilitaryPoints(int militaryPoints) {
        this.militaryPoints = militaryPoints;
    }

    /**
     * Get faithPoints
     *
     * @return faithPoints
     */
    public int getFaithPoints() {
        return faithPoints;
    }

    /**
     * Set v
     *
     * @param faithPoints faithPoints
     */
    public void setFaithPoints(int faithPoints) {
        this.faithPoints = faithPoints;
    }
}