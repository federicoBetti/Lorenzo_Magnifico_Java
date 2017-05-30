package Project.Server.Network;

import Project.Server.Network.Socket.SocketPlayerHandler;
import Project.Server.Server;

import java.io.IOException;

//qua ci sono tutti i metodi che devono essere implementati
// sia dal server RMI sia dal server Socket. sono le chiamate che il client puo fare sul server
public abstract class AbstractServer {

    Server server;

    public AbstractServer( Server server ){
        this.server = server;
    }

    public abstract void loginRequest(String nickname, SocketPlayerHandler socketPlayerHandler) throws IOException;
}
