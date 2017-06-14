package project.server;
import project.configurations.*;
import project.controller.Constants;
import project.server.network.PlayerHandler;
import project.server.network.rmi.ServerRMI;
import project.server.network.socket.SocketServer;
import project.messages.Notify;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by raffaelebongo on 18/05/17.
 */
public class Server {


    private static final int RMI_PORT = 2;

    private ArrayList<Room> rooms;

    private SocketServer serverSocket;

    private ServerRMI rmiServer;

    Configuration configuration;

    public Server() throws IOException {
        rooms = new ArrayList<>();
        serverSocket = new SocketServer(this);
        rmiServer = new ServerRMI(this);
        configuration = new Configuration();
        configuration.loadConfiguration();//TODO vanno implementate tutte i metodi di configurazione e chiamati da qui

    }

    public static void main(String[] args) throws IOException {
        Server server = new Server();
        server.startServer(Constants.SOCKET_PORT, Constants.RMI_PORT );

    }

    private void startServer( int socketPort, int rmiPort ) throws IOException {
        serverSocket.startServer(socketPort);
        rmiServer.startServer(rmiPort);
    }


    /**
     * TODO implemetare tutti i metodi che si occupano della gestione delle room e del fileXML.login dei giocatori
     */

    public void loginRequest(String nickname, PlayerHandler player) throws IOException {
        if ( rooms.isEmpty() || roomsAreAllFull()  ) {
            createNewRoom(nickname, player);
            return;
        }
            for (Room room : rooms) {
                if (!room.isFull()) {
                    room.nicknamePlayersMap.put(nickname, player);
                    Notify notify = new Notify(Constants.LOGIN_SUCCEDED);
                    player.sendAnswer(notify);
                    if ( room.isFull())
                        startMatch(room);
                    break;
                }
            }

        }

    private void startMatch(Room room) {
        Notify notify = new Notify(Constants.YOUR_TURN);
        room.getBoard().getTurn().getPlayerTurn().get(0).sendAnswer(notify);
    }

    private void createNewRoom(String nickname, PlayerHandler player) {
            Room room = new Room(this);
            rooms.add(room);
            room.nicknamePlayersMap.put(nickname, player);
             //todo bisogna definire il metodo che fa i turni
            Notify notify = new Notify(Constants.LOGIN_SUCCEDED);
            player.sendAnswer(notify);
    }

    private boolean roomsAreAllFull() {
        for ( Room room: rooms ){
            if ( !room.isFull() )
                return false;
        }
        return true;
    }
}
