package project.messages.updatesmessages;

import project.controller.Constants;
import project.server.network.PlayerHandler;

/**
 * Object sends to the client for notifying that the player has taken an excommunication
 */
public class ExcommunicationTaken extends Updates {
    String exTile;

    /**
     * Constructor
     *
     * @param player playerHandler's reference
     * @param exTile excommunication's description
     */
    public ExcommunicationTaken(PlayerHandler player, String exTile) {
        super(player.getName());
        this.exTile = exTile;
    }

    /**
     * String the descibe the class
     *
     * @return the constants
     */
    @Override
    public String toString() {
        return Constants.EXCOMMUNICATION_TAKEN_UPDATE;
    }

    /**
     * This method build a string that describes the update
     *
     * @return the description
     */
    @Override
    public String toScreen() {
        return getNicknameCurrentPlayer() + "has take this excommunication:\n";
    }

    /**
     * get the excommunication's description
     *
     * @return exTile
     */
    public String getExTile() {
        return exTile;
    }
}
