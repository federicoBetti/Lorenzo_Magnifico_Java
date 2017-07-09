package project.messages.updatesmessages;

import project.controller.Constants;
import project.model.Board;
import project.model.Council;

import java.io.Serializable;
import java.util.List;

/**
 * Object sends to the client for notifying that the council zone has been modified
 */
public class CouncilUpdate extends Updates implements Serializable {

    private List<Council> councilZone;

    /**
     * Constructor
     *
     * @param council list of council position
     * @param nickname palyer's nickname
     */
    public CouncilUpdate( List<Council> council, String nickname ){
        super(nickname);
        councilZone = council;
    }

    /**
     *
     *
     * @param board
     */
    @Override
    public void doUpdate(Board board) {
        board.setCouncilZone(councilZone);
    }

    @Override
    public String toScreen() {
        return  getNicknameCurrentPlayer() +" has placed a familiar in the Council palace!";
    }

    @Override
    public String toString() {
        return Constants.BOARD_UPDATE;
    }
}
