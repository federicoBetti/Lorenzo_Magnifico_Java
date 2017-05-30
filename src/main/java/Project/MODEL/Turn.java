package Project.MODEL;

import Project.Server.Network.PlayerHandler;

import java.util.ArrayList;

/**
 * Created by raffaelebongo on 29/05/17.
 */
public class Turn {

    ArrayList<PlayerHandler> playerTurn;

    public Turn(){
        playerTurn = new ArrayList<PlayerHandler>();
    }

    public void setPlayerTurn(ArrayList<Player> playerTurn) {
        this.playerTurn = playerTurn;
    }

    public ArrayList<PlayerHandler> getPlayerTurn() {
        return playerTurn;
    }
}
