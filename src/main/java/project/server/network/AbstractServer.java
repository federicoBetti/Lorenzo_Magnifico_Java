package project.server.network;

import project.server.Server;
import java.io.IOException;

/**
 * Abstract class extended by the Socket Server and the RMI Server
 */
public abstract class AbstractServer {

    protected Server server;

    protected AbstractServer(Server server){
        this.server = server;
    }

    /**
     * This method calls the login method on the Server to which the client responds
     *
     * @param nickname nickname choosen by the player as a String
     * @param playerHandler playerhandler's reference
     * @throws IOException Input/Output exception
     */
    public void loginRequest(String nickname, PlayerHandler playerHandler) throws IOException{
        server.loginRequest(nickname,playerHandler);
    }
}
