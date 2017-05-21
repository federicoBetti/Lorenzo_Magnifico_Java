package Project.Network.Socket;

import Project.Server;

import java.io.IOException;
import java.net.ServerSocket;

/**
 * Created by raffaelebongo on 20/05/17.
 */
public class SocketServer {

    Server server;
    private ServerSocket serverSocket;

    public SocketServer( Server server ){
        this.server = server;
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
