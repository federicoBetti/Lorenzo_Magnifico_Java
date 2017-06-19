package project.messages.updatesmessages;

import project.controller.Constants;
import project.model.Board;
import project.model.Council;

import java.io.Serializable;
import java.util.List;

/**
 * Created by raffaelebongo on 14/06/17.
 */
public class CouncilUpdate extends Updates implements Serializable {

    List<Council> councilZone;

    public CouncilUpdate( List<Council> council ){
        councilZone = council;
    }
    @Override
    public String toString() {
        return Constants.BOARD_UPDATE;
    }

    @Override
    public void doUpdate(Board board) {
        board.setCouncilZone(councilZone);
    }
}
