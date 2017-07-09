package project.messages.updatesmessages;

import project.controller.Constants;
import project.model.Board;
import project.model.Harvester;

import java.io.Serializable;
import java.util.List;

/**
 * Object sends to the client for notifying that the harvester's zone has been changed
 */
public class HarvesterUpdate extends Updates implements Serializable {

    private List<Harvester> harvesterZone;

    /**
     * Constructor
     *
     * @param harvester list of harvester positions
     * @param nickname player's nickname
     */
    public HarvesterUpdate(List<Harvester> harvester, String nickname ){
        super(nickname);
        harvesterZone = harvester;
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
        board.setHarvesterZone(harvesterZone);
    }

    /**
     * This method build a string that describes the update
     *
     * @return the description
     */
    @Override
    public String toScreen() {
        return getNicknameCurrentPlayer() + " has performed an harvester action!";
    }
}
