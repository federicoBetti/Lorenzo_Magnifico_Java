package project.messages.updatesmessages;

import project.controller.Constants;
import project.model.Board;
import project.model.Harvester;

/**
 * Created by raffaelebongo on 14/06/17.
 */
public class HarvesterUpdate extends Updates {
    Harvester[] harvesterZone;

    public HarvesterUpdate(Harvester[] harvester ){
        harvesterZone = harvester;
    }

    @Override
    public String toString() {
        return Constants.BOARD_UPDATE;
    }

    @Override
    public void doUpdate(Board board) {
        board.setHarvesterZone(harvesterZone);
    }
}
