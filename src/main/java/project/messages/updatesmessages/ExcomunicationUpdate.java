package project.messages.updatesmessages;

import project.controller.Constants;
import project.model.Board;
import project.model.ExcommunicationZone;

import java.io.Serializable;

/**
 * Created by raffaelebongo on 14/06/17.
 */
public class ExcomunicationUpdate extends Updates implements Serializable{

    ExcommunicationZone[] excommunicationZone;

    public ExcomunicationUpdate( ExcommunicationZone[] exc ){
        excommunicationZone = exc;
    }

    public void doUpdate( Board board ){
        board.setExcommunicationZone(excommunicationZone);
    }

    @Override
    public String toString() {
        return Constants.BOARD_UPDATE;
    }
}
