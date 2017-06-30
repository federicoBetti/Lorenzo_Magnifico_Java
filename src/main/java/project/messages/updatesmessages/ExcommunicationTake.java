package project.messages.updatesmessages;

import project.controller.Constants;
import project.controller.cardsfactory.ExcommunicationTile;
import project.server.network.PlayerHandler;

/**
 * Created by raffaelebongo on 30/06/17.
 */
public class ExcommunicationTake extends Updates {
    ExcommunicationTile exTile;

    public ExcommunicationTake(PlayerHandler player, ExcommunicationTile exTile) {
        super(player.getName());
        this.exTile = exTile;
    }

    @Override
    public String toString() {
        return Constants.EXCOMMUNICATION_TAKEN;
    }

    @Override
    public String toScreen() {
        return getNicknameCurrentPlayer() + "has take this excommunication:\n" + exTile.getExcommunicationEffect().toScreen();
    }
}
