package Project.Server;

import Project.Controller.Effects.EffectsFactory.BuildExcommunicationEffects;
import Project.Controller.SupportFunctions.AllSupportFunctions;
import Project.MODEL.Board;
import Project.MODEL.Player;
import Project.Server.Network.GameActions;
import Project.Server.Network.PlayerHandler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;


/**
 * TODO completare
 */
public class Room {

    /**
     * Stato della partita completo ad eccezione delle personalBoard che sono contenute nel player
     */


    private Board board;

    private HashMap<Player,AllSupportFunctions> playerAllSupportFunctionsMap;

    private ArrayList<PlayerHandler> roomPlayers;

    private BuildExcommunicationEffects buildExcommunicationEffects;

    /**
     * Riferimento alla classe GameActions
     */

    GameActions gameActions;

    //TODO chiedere a betti se l'idea è questa
    AllSupportFunctions allSupportFunctions;


    Room (){
        roomPlayers = new ArrayList<>();
    }

    /**
     * TODO devo poter arrivare ai metodi di check da qui
     */

    public Board getBoard() {
        return board;
    }

    public AllSupportFunctions getMySupportFunction (Player player){
        return playerAllSupportFunctionsMap.get(player);
    }

    public void setMySupportFunction(AllSupportFunctions allSupportFunctions, PlayerHandler player){
        playerAllSupportFunctionsMap.put(player,allSupportFunctions);
    }

    public GameActions getGameActions() {
        return gameActions;
    }

    public ArrayList<PlayerHandler> getRoomPlayers() {
        return roomPlayers;
    }

    public BuildExcommunicationEffects getBuildExcommunicationEffects() {
        return buildExcommunicationEffects;
    }
}
