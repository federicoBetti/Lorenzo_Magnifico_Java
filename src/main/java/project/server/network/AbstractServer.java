package project.server.network;

import project.server.Server;

import java.io.IOException;

//qua ci sono tutti i metodi che devono essere implementati
// sia dal server rmi sia dal server socket. sono le chiamate che il client puo fare sul server
public abstract class AbstractServer {

    protected Server server;

    protected AbstractServer(Server server){
        this.server = server;
    }

    //ci potrebbe essere anche lo startServer da essere scritto qua

    public void loginRequest(String nickname, PlayerHandler playerHandler) throws IOException{
        server.loginRequest(nickname,playerHandler);
    }
}
