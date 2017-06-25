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

    public TowersUpdate(Tower[][] towers, String nickname ) {
        super(nickname);
        towersZone = towers;
    }

    @Override
    public String toString() {
        return Constants.BOARD_UPDATE;
    }

    @Override
    public Tower[][] doUpdateTowers(){
        return towersZone;
    }

    @Override
    public String toScreen() {
       return "A card has been taken from a tower!    " + towersZone[0][0].getFamiliarOnThisPosition();
    }
}
