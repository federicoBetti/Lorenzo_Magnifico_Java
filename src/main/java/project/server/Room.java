package project.server;

import project.controller.effects.effectsfactory.BuildExcommunicationEffects;
import project.controller.supportfunctions.AllSupportFunctions;
import project.model.Board;
import project.model.Player;
import project.server.network.GameActions;
import project.server.network.PlayerHandler;

import java.util.HashMap;
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

    private Map<Player,AllSupportFunctions> playerAllSupportFunctionsMap;

    public Map< String, PlayerHandler> nicknamePlayersMap;

    private BuildExcommunicationEffects buildExcommunicationEffects;

    GameActions gameActions;

    AllSupportFunctions allSupportFunctions;


    Room(Server server){
        playerAllSupportFunctionsMap = new HashMap<>();
        nicknamePlayersMap = new HashMap<>();
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

        return false;
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
