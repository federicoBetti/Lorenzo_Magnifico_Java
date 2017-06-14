package project.messages.updatesmessages;

import project.controller.Constants;
import project.model.Board;
import project.model.ExcommunicationZone;
import project.server.network.PlayerHandler;

/**
 * Created by raffaelebongo on 14/06/17.
 */
public class ExcomunicationUpdate extends Updates {

    ExcommunicationZone[] excommunicationZone;

    public ExcomunicationUpdate( ExcommunicationZone[] exc ){
        excommunicationZone = exc;
    }

    public void doUpdate( Board board ){
        board.setExcommunicationZone(excommunicationZone);
    }

    @Override
    public String toString() {
        return Constants.EXCOMUNICATION_UPDATE;
    }
}
