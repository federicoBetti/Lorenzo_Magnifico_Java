package project;

import java.io.Serializable;

/**
 * Created by raffaelebongo on 02/07/17.
 */
public class PlayerFile implements Serializable{

    private String playerName;
    private int numberOfVictories;
    private int numberOfDefeats;
    private int numberOfGames;

    public PlayerFile(){
        numberOfDefeats = 0;
        numberOfVictories = 0;
        numberOfGames = 1;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public int getNumberOfVictories() {
        return numberOfVictories;
    }

    public void setNumberOfVictories(int numberOfVictories) {
        this.numberOfVictories = numberOfVictories;
    }

    public int getNumberOfDefeats() {
        return numberOfDefeats;
    }

    public void setNumberOfDefeats(int numberOfDefeats) {
        this.numberOfDefeats = numberOfDefeats;
    }

    public int getNumberOfGames() {
        return numberOfGames;
    }

    public void setNumberOfGames(int numberOfGames) {
        this.numberOfGames = numberOfGames;
    }
}
