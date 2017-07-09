package project.messages.updatesmessages;

import project.controller.Constants;
import project.model.Board;
import project.model.ExcommunicationZone;

import java.io.Serializable;

/**
 * Object sends to the client for notifying that excommunication tiles have been changed in the model
 */
public class ExcomunicationUpdate extends Updates implements Serializable{

    private ExcommunicationZone[] excommunicationZone;

    /**
     * COnstructor
     *
     * @param exc excommunication's zonr
     * @param nickname player's nickname
     */
    public ExcomunicationUpdate( ExcommunicationZone[] exc, String nickname ){
        super(nickname);
        excommunicationZone = exc;
    }

    /**
     * This method act the board's update in the client
     *
     * @param board board's reference
     */
    public void doUpdate(Board board ){
        board.setExcommunicationZone(excommunicationZone);
    }

    /**
     * This method build a string that describes the update
     *
     * @return the description
     */
    @Override
    public String toScreen() {
        return null;
    }

    /**
     * String the descibe the class
     *
     * @return the constants
     */
    @Override
    public String toString() {
        return Constants.BOARD_UPDATE;
    }
}
