package project;

import java.io.Serializable;

/**
 * This class represent the player's statistic
 */
public class PlayerFile implements Serializable{

    private String playerName;
    private int numberOfVictories;
    private int numberOfDefeats;
    private int numberOfGames;

    /**
     * Constructor
     */
    public PlayerFile(){
        numberOfDefeats = 0;
        numberOfVictories = 0;
        numberOfGames = 1;
    }

    /**
     * Get playerName
     *
     * @return playerName
     */
    public String getPlayerName() {
        return playerName;
    }

    /**
     * Set playerName
     *
     * @param playerName playerName
     */
    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    /**
     * Get numberOfVictories
     *
     * @return numberOfVictories
     */
    public int getNumberOfVictories() {
        return numberOfVictories;
    }

    /**
     * Set numberOfVictories
     *
     * @param numberOfVictories numberOfVictories
     */
    public void setNumberOfVictories(int numberOfVictories) {
        this.numberOfVictories = numberOfVictories;
    }

    /**
     * Get numberOfDefeats
     *
     * @return numberOfDefeats
     */
    public int getNumberOfDefeats() {
        return numberOfDefeats;
    }

    /**
     * Set numberOfDefeats
     *
     * @param numberOfDefeats numberOfDefeats
     */
    public void setNumberOfDefeats(int numberOfDefeats) {
        this.numberOfDefeats = numberOfDefeats;
    }

    /**
     * Get numberOfGames
     *
     * @return numberOfGames
     */
    public int getNumberOfGames() {
        return numberOfGames;
    }

    /**
     * Set numberOfGames
     *
     * @param numberOfGames numberOfGames
     */
    public void setNumberOfGames(int numberOfGames) {
        this.numberOfGames = numberOfGames;
    }
}
