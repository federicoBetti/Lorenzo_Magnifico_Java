package project.messages.updatesmessages;

import project.controller.Constants;
import project.model.Board;
import project.model.Tower;

import java.io.Serializable;

/**
 * Created by raffaelebongo on 14/06/17.
 */
public class TowersUpdate extends Updates implements Serializable {

    Tower[][] towersZone;

    public TowersUpdate(Tower[][] towers ) {
        towersZone = towers;
    }

    @Override
    public String toString() {
        return Constants.BOARD_UPDATE;
    }

    @Override
    public void doUpdate(Board board) {
        board.setTowers(towersZone);
    }
}
