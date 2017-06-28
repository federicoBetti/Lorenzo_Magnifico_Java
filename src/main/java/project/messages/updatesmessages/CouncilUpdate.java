package project.messages.updatesmessages;

import project.controller.Constants;
import project.model.Board;
import project.model.Council;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by raffaelebongo on 14/06/17.
 */
public class CouncilUpdate extends Updates implements Serializable {

    private ArrayList<Council> councilZone;

    public CouncilUpdate( ArrayList<Council> council, String nickname ){
        super(nickname);
        councilZone = council;
    }

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
