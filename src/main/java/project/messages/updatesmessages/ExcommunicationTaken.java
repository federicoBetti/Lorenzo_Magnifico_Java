package project.messages.updatesmessages;

import project.controller.Constants;
import project.server.network.PlayerHandler;

/**
 * Created by raffaelebongo on 30/06/17.
 */
public class ExcommunicationTaken extends Updates {
    String exTile;

    public ExcommunicationTaken(PlayerHandler player, String exTile) {
        super(player.getName());
        this.exTile = exTile;
    }

    @Override
    public String toString() {
        return Constants.EXCOMMUNICATION_TAKEN_UPDATE;
    }

    @Override
    public String toScreen() {
        return getNicknameCurrentPlayer() + "has take this excommunication:\n";
    }

    public String getExTile() {
        return exTile;
    }
}
