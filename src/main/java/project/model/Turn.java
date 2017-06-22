package project.model;


import project.server.network.PlayerHandler;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by raffaelebongo on 29/05/17.
 */
public class Turn {


    private List<PlayerHandler> playerTurn;
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
}
