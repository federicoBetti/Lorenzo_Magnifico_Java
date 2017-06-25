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

    public ExcomunicationUpdate( ExcommunicationZone[] exc, String nickname ){
        super(nickname);
        excommunicationZone = exc;
    }

    @Override
    public ExcommunicationZone[] doUpdateExcommunicatio(){
        return excommunicationZone;
    }

    @Override
    public String toScreen() {
        return null;
    }

    @Override
    public String toString() {
        return Constants.BOARD_UPDATE;
    }
}
