package project.server.network;

import project.server.network.socket.SocketPlayerHandler;
import project.server.Server;

import java.io.IOException;

//qua ci sono tutti i metodi che devono essere implementati
// sia dal server rmi sia dal server socket. sono le chiamate che il client puo fare sul server
public abstract class AbstractServer {

    Server server;

    public AbstractServer( Server server ){
        this.server = server;
    }

    public abstract void loginRequest(String nickname, SocketPlayerHandler socketPlayerHandler) throws IOException;
}
