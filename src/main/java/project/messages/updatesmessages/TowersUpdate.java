package project.messages.updatesmessages;

import project.model.Board;
import project.model.Tower;

/**
 * Created by raffaelebongo on 14/06/17.
 */
public class TowersUpdate extends Updates {

    Tower[][] towersZone;

    public TowersUpdate(Tower[][] towers ) {
        towersZone = towers;
    }

    @Override
    public String toString() {
        return null;
    }

    @Override
    public void doUpdate(Board board) {
        board.setTowers(towersZone);
    }
}
