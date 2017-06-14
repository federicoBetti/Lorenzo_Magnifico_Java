package project.messages.updatesmessages;

import project.model.Board;
import project.model.Council;

import java.util.List;

/**
 * Created by raffaelebongo on 14/06/17.
 */
public class CouncilUpdate extends Updates {

    List<Council> councilZone;

    public CouncilUpdate( List<Council> council ){
        councilZone = council;
    }
    @Override
    public String toString() {
        return null;
    }

    @Override
    public void doUpdate(Board board) {
        board.setCouncilZone(councilZone);
    }
}
