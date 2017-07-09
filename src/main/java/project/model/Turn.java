package project.model;


import project.server.network.PlayerHandler;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * This class represent the turn
 */
public class Turn implements Serializable{


    transient private List<PlayerHandler> playerTurn;
    transient private List<PlayerHandler> skipTurnForExcommunication;
    private List<String> playersColor;
    private List<String> playerName;
    /*
    * variable used for checking the finishing of the round
    * */
    private int rotation;

    /**
     * Constructor
     */
    Turn(){
        playerTurn = new ArrayList<>();
        skipTurnForExcommunication = new ArrayList<>();
        rotation = 0;
    }

    /**
     * Get playerTurn
     *
     * @return playerTurn
     */
    public List<PlayerHandler> getPlayerTurn() {
        return playerTurn;
    }

    /**
     * Set playerTurn
     *
     * @param playerTurn playerTurn
     */
    public void setPlayerTurn(List<PlayerHandler> playerTurn) {
        this.playerTurn = playerTurn;
    }

    /**
     * Get playerotationrTurn
     *
     * @return rotation
     */
    public int getRotation() {
        return rotation;
    }

    /**
     * Set rotation
     *
     * @param rotation rotation
     */
    public void setRotation(int rotation) {
        this.rotation = rotation;
    }

    /**
     * This method fill the lists in the class
     */
    public void fillLists(){
        playerName = new ArrayList<>();
        playersColor = new ArrayList<>();
        for (PlayerHandler playerHandler: playerTurn){
            playersColor.add(playerHandler.getFamilyColour());
            playerName.add(playerHandler.getName());
        }
    }

    /**
     * Get playersColor
     *
     * @return playersColor
     */
    public List<String> getPlayersColor() {
        return playersColor;
    }

    /**
     * Get playerName
     *
     * @return playerName
     */
    public List<String> getPlayerName() {
        return playerName;
    }

    /**
     * Set playerName
     *
     * @param playerName playerName
     */
    void setPlayerName(List<String> playerName) {
        this.playerName = playerName;
    }

    /**
     * This method add a player in the skipTurnForExcommunication list
     *
     * @param player palyerHandle's reference
     */
    public void addSkipTurn(PlayerHandler player) {
        skipTurnForExcommunication.add(player);
    }

    /**
     * Get skipTurnForExcommunication
     *
     * @return skipTurnForExcommunication
     */
    public List<PlayerHandler> getSkipTurnForExcommunication() {
        return skipTurnForExcommunication;
    }
}
