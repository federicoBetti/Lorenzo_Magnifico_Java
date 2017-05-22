package Project.Server.Network.Socket;

import Project.Server.Network.AbstractServer;
import Project.Server.Server;

import java.io.IOException;
import java.net.ServerSocket;


public class SocketServer extends AbstractServer{

    private ServerSocket serverSocket;

    public SocketServer( Server server ){
        super(server);
    }

    public void startServerSocket(int socketPort) throws IOException {
        serverSocket = new ServerSocket( socketPort );
        //TODO completare se necessario
    }

    /**
     * TODO ciclo in ascolto di altri client che instaura la connessione
     * TODO e crea un classe dedicata per quel client "PLAYER HANDLER"
     */
    public void run(){
        while(true){

        }
    }
}
