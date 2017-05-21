package Project;
import Project.Configurations.*;
import Project.Network.RMI.ServerRMI;
import Project.Network.Room;
import Project.Network.Socket.ServerPlayer;
import Project.Network.Socket.ServerSocket;
import com.google.gson.Gson;

import javax.management.remote.rmi.RMIServer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.SocketHandler;

/**
 * Created by raffaelebongo on 18/05/17.
 */
public class Server {

    private static final int SOCKET_PORT = 1;

    private static final int RMI_PORT = 2;

    private HashMap< String, ServerPlayer> playerMap;

    private ArrayList<Room> rooms;

    private ServerSocket serverSocket;

    private ServerRMI rmiServer;

    Configuration configuration;

    public Server(){
        playerMap = new HashMap<String, ServerPlayer>();
        rooms = new ArrayList<Room>();
        serverSocket = new ServerSocket(this);
        rmiServer = new ServerRMI(this);
        configuration = new Configuration();
        configuration.loadConfiguration();//TODO vanno implementate tutte i metodi di configurazione e chiamati da qui

    }

    public static void main(String[] args){
        Server server = new Server();
        server.startServer(SOCKET_PORT, RMI_PORT );

    }

    private void startServer( int socketPort, int rmiPort ){
        serverSocket.startServerSocket(socketPort);
        rmiServer.startServerRmi(rmiPort);
    }

    /**
     * TODO implemetare tutti i metodi che si occupano della gestione delle room e del login dei giocatori
     */

}
