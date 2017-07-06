package project.model;


import project.server.network.PlayerHandler;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by raffaelebongo on 29/05/17.
 */
public class Turn implements Serializable{


    transient private List<PlayerHandler> playerTurn;
    private List<String> playersColor;
    private List<String> playerName;
    /*
    * variable used for checking the finishing of the round
    * */
    private int rotation;


    public Turn(){
        playerTurn = new ArrayList<>();
        rotation = 0;
    }

    public List<PlayerHandler> getPlayerTurn() {
        return playerTurn;
    }

    public void setPlayerTurn(List<PlayerHandler> playerTurn) {
        this.playerTurn = playerTurn;
    }

    public int getRotation() {
        return rotation;
    }

    public void setRotation(int rotation) {
        this.rotation = rotation;
    }

    public void fillLists(){
        playerName = new ArrayList<>();
        playersColor = new ArrayList<>();
        for (PlayerHandler playerHandler: playerTurn){
            playersColor.add(playerHandler.getFamilyColour());
            playerName.add(playerHandler.getName());
        }
    }

    public List<String> getPlayersColor() {
        return playersColor;
    }
}
