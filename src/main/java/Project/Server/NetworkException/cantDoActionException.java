package Project.Server.NetworkException;


import Project.Server.Network.PlayerHandler;
import Project.Messages.OkOrNo;

public class cantDoActionException extends Exception {
    public cantDoActionException  (PlayerHandler playerHandler, String s){
        super(s);
        playerHandler.cantDoAction(new OkOrNo(false));
    }
    public cantDoActionException (String s){
        super(s);
    }
}
