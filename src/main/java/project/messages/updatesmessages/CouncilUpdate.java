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

    private List<Council> councilZone;

    public CouncilUpdate( List<Council> council ){
        super();
        councilZone = council;
    }

    @Override
    public void doUpdate(Board board) {
        board.setCouncilZone(councilZone);
    }

    @Override
    public String toScreen() {

    }

    @Override
    public String toString() {
        return Constants.BOARD_UPDATE;
    }
}
