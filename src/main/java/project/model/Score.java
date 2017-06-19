package project.model;

import java.io.Serializable;

/**
 * 
 */
public class Score implements Serializable{

    private int victoryPoints;

    private int militaryPoints;

    private int faithPoints;




    public int getVictoryPoints() {
        return victoryPoints;
    }

    public void setVictoryPoints(int victoryPoints) {
        this.victoryPoints = victoryPoints;
    }

    public int getMilitaryPoints() {
        return militaryPoints;
    }

    public void setMilitaryPoints(int militaryPoints) {
        this.militaryPoints = militaryPoints;
    }

    public int getFaithPoints() {
        return faithPoints;
    }

    public void setFaithPoints(int faithPoints) {
        this.faithPoints = faithPoints;
    }
}