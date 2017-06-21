package project.messages.updatesmessages;

import project.controller.Constants;
import project.model.Board;
import project.model.Harvester;

import java.io.Serializable;
import java.util.List;

/**
 * Created by raffaelebongo on 14/06/17.
 */
public class HarvesterUpdate extends Updates implements Serializable {

    private List<Harvester> harvesterZone;

    public HarvesterUpdate(List<Harvester> harvester ){
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
