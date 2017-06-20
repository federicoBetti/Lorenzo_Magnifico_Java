package project.model;


import project.server.network.PlayerHandler;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by raffaelebongo on 29/05/17.
 */
public class Turn {


    List<PlayerHandler> playerTurn;

    public Turn(){
        playerTurn = new ArrayList<>();
    }



    public List<PlayerHandler> getPlayerTurn() {
        return playerTurn;
    }

    public void setPlayerTurn(List<PlayerHandler> playerTurn) {
        this.playerTurn = playerTurn;
    }
}
