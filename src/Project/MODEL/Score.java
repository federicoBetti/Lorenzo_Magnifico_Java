package Project.MODEL;

/**
 * 
 */
public class Score {

    /**
     * Default constructor
     */
    public Score() {
    }

    /**
     * 
     */
    private int VictoryPoints;

    /**
     * 
     */
    private int MilitaryPoints;

    /**
     * 
     */
    private int FaithPoints;

    public int getVictoryPoints() {
        return VictoryPoints;
    }

    public void setVictoryPoints(int victoryPoints) {
        VictoryPoints = victoryPoints;
    }

    public int getMilitaryPoints() {
        return MilitaryPoints;
    }

    public void setMilitaryPoints(int militaryPoints) {
        MilitaryPoints = militaryPoints;
    }

    public int getFaithPoints() {
        return FaithPoints;
    }

    public void setFaithPoints(int faithPoints) {
        FaithPoints = faithPoints;
    }
}