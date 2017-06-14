package project.server.network.exception;


import project.server.network.PlayerHandler;
import project.messages.OkOrNo;

public class CantDoActionException extends Exception {
    public CantDoActionException(PlayerHandler playerHandler, String s){
        super(s);
        playerHandler.cantDoAction(new OkOrNo());
    }
    public CantDoActionException(String s){
        super(s);
    }
}
