package Project.Server;
import Project.Configurations.*;
import Project.Server.Network.PlayerHandler;
import Project.Server.Network.RMI.ServerRMI;
import Project.Server.Network.Socket.SocketServer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by raffaelebongo on 18/05/17.
 */
public class Server {

    private static final int SOCKET_PORT = 1;

    private static final int RMI_PORT = 2;

    private HashMap< String, PlayerHandler> playerMap;

    private ArrayList<Room> rooms;

    private SocketServer serverSocket;

    private ServerRMI rmiServer;

    Configuration configuration;

    public Server(){
        playerMap = new HashMap<String, PlayerHandler>();
        rooms = new ArrayList<Room>();
        serverSocket = new SocketServer(this);
        rmiServer = new ServerRMI(this);
        configuration = new Configuration();
        configuration.loadConfiguration();//TODO vanno implementate tutte i metodi di configurazione e chiamati da qui

    }

    public static void main(String[] args){
        Server server = new Server();
        server.startServer(SOCKET_PORT, RMI_PORT );

    }

    private void startServer( int socketPort, int rmiPort ){
        try {
            serverSocket.startServerSocket(socketPort);
        } catch (IOException e) {
            e.printStackTrace();
        }
        rmiServer.startServerRmi(rmiPort);
    }

    /**
     * TODO implemetare tutti i metodi che si occupano della gestione delle room e del login dei giocatori
     */

}
