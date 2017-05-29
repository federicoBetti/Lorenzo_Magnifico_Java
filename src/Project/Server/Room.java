package Project.Server;

import Project.Controller.Effects.EffectsFactory.BuildExcommunicationEffects;
import Project.Controller.SupportFunctions.AllSupportFunctions;
import Project.MODEL.Board;
import Project.MODEL.Player;
import Project.Server.Network.GameActions;
import Project.Server.Network.PlayerHandler;

import java.security.KeyStore;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;


/**
 * TODO completare
 */
public class Room {

    private final Server server;
    /**
     * Stato della partita completo ad eccezione delle personalBoard che sono contenute nel player
     */


    private Board board;

    int maxPlayers; //todo da definire: bisogna farla scegliere allo user

    private HashMap<Player,AllSupportFunctions> playerAllSupportFunctionsMap;

    public HashMap< String, PlayerHandler> nicknamePlayersMap;

    private BuildExcommunicationEffects buildExcommunicationEffects;

    /**
     * Riferimento alla classe GameActions
     */

    GameActions gameActions;

    AllSupportFunctions allSupportFunctions;


    Room(Server server){
        playerAllSupportFunctionsMap = new HashMap<Player,AllSupportFunctions>();
        nicknamePlayersMap = new HashMap<String, PlayerHandler>();
        buildExcommunicationEffects = new BuildExcommunicationEffects();
        this.server = server;
    }

    public boolean isFull() {
        int count = 0;
        for (Map.Entry<String, PlayerHandler> entry: nicknamePlayersMap.entrySet()) {
            count++;
        }
        if ( count == maxPlayers )
            return true;
        else
            return false;
    }
    /**
     * TODO devo poter arrivare ai metodi di check da qui
     */





    /**
     * Getter and Setter
     * @return
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

    public BuildExcommunicationEffects getBuildExcommunicationEffects() {
        return buildExcommunicationEffects;
    }


    public int getRoomPlayers() {
        int count = 0;
        for (Map.Entry<String, PlayerHandler> entry : nicknamePlayersMap.entrySet()) {
            count++;
        }
        return count;
    }
}
