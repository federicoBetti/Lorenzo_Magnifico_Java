package Project.Server;

import Project.Controller.SupportFunctions.AllSupportFunctions;
import Project.MODEL.Board;
import Project.MODEL.Player;
import Project.Server.Network.ControllerInterface;

import java.util.ArrayList;

/**
 * Created by raffaelebongo on 20/05/17.
 */

/**
 * TODO completare
 */
public class Room {

    /**
     * Stato della partita completo ad eccezione delle personalBoard che sono contenute nel player
     */


    private Board board;

    ArrayList<Player,ControllerInterface> players;

    /**
     * Riferimento alla classe GameActions
     */

    ControllerInterface gameActions;

    //TODO chiedere a betti se l'idea è questa
    AllSupportFunctions allSupportFunctions;



    /**
     * TODO devo poter arrivare ai metodi di check da qui
     */

    public Board getBoard() {
        return board;
    }
}
