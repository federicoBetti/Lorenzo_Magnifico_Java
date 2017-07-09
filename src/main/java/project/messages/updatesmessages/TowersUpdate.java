package project.messages.updatesmessages;

import project.controller.Constants;
import project.model.Board;
import project.model.Tower;

import java.io.Serializable;

/**
 * Object sends to the client for notifying that the towers' zone has been changed
 */
public class TowersUpdate extends Updates implements Serializable {

    private Tower[][] towersZone;

    /**
     * Constructor
     *
     * @param towers towers' zone
     * @param nickname player's nickname
     */
    public TowersUpdate(Tower[][] towers, String nickname ) {
        super(nickname);
        towersZone = towers;
    }

    /**
     * String the descibe the class
     *
     * @return the constants
     */
    @Override
    public String toString() {
        return Constants.BOARD_UPDATE;
    }

    /**
     * This method act the board's update in the client
     *
     * @param board board's reference
     */
    @Override
    public void doUpdate(Board board) {
        board.setTowers(towersZone);
    }

    /**
     * This method build a string that describes the update
     *
     * @return the description
     */
    @Override
    public String toScreen() {
       return getNicknameCurrentPlayer() + " has taken from a tower!    ";
    }
}
