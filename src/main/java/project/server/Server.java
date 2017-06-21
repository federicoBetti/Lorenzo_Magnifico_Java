package project.server;

import project.configurations.Configuration;
import project.configurations.TimerSettings;
import project.controller.Constants;
import project.server.network.PlayerHandler;
import project.server.network.rmi.ServerRMI;
import project.server.network.socket.SocketServer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by raffaelebongo on 18/05/17.
 */
public class Server {


    private static final int RMI_PORT = 2;

    private ArrayList<Room> rooms;

    private SocketServer serverSocket;

    private ServerRMI rmiServer;

    private TimerSettings timerSettings;

    private Configuration configuration;

    private Server() throws IOException {
        rooms = new ArrayList<>();
        serverSocket = new SocketServer(this);
        rmiServer = new ServerRMI(this);
        configuration = new Configuration();
        this.timerSettings = configuration.loadTimer();
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
     * TODO implemetare tutti i metodi che si occupano della gestione delle room e del fileXML.controller dei giocatori
     */

    public void loginRequest(String nickname, PlayerHandler player)  {
        if ( nicknameAlreadyUsed(nickname))
            player.sendAnswer(Constants.NICKNAME_USED);

        if ( rooms.isEmpty() || roomsAreAllFull()  ) {
            createNewRoom(nickname, player);
            return;
        }

        for (Room room : rooms) {
            if ( room.nicknamePlayersMap.containsKey(nickname) && !room.nicknamePlayersMap.get(nickname).isOn()) {
                player.setOn(true);
                room.nicknamePlayersMap.replace(nickname, player);
                checkAndStartTheTimer(room, player);
                System.out.println(room.nicknamePlayersMap.entrySet());
            }

            else if (!room.isFull()) {
                player.setOn(true);
                room.nicknamePlayersMap.put(nickname, player);
                System.out.println(room.nicknamePlayersMap.entrySet());
                player.sendAnswer(Constants.LOGIN_SUCCEDED);
                checkAndStartTheTimer(room, player);

                if ( room.isFull() ) {
                    startMatch(room);
                }

                break;
            }
        }
    }

    private boolean nicknameAlreadyUsed( String nickname ){
        for ( Room room: rooms ) {
            for (Map.Entry<String, PlayerHandler> entry : room.nicknamePlayersMap.entrySet())
                if (entry.getKey().equals(nickname) && entry.getValue().isOn())
                    return true;
        }
        return false;
    }

    private void checkAndStartTheTimer( Room room, PlayerHandler player ){
        if ( room.numberOfPlayerOn() == 2 ){
            myTimerStartMatch(player, room);
        }
    }



    private void startMatch(Room room) {
        room.startMatch();
    }

    private void createNewRoom(String nickname, PlayerHandler player) {
        Room room = new Room(this);
        rooms.add(room);
        System.out.println("new room created!");
        player.setOn(true);
        room.nicknamePlayersMap.put(nickname, player);

        System.out.println(room.nicknamePlayersMap.entrySet());
        player.sendString(Constants.LOGIN_SUCCEDED);
    }

    private boolean roomsAreAllFull() {
        for ( Room room: rooms ){
            if ( !room.isFull() )
                return false;
        }
        return true;
    }

    private void myTimerStartMatch( PlayerHandler player, Room room ) {

        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                if( room.minimumNumberOfPlayers() ) {
                    player.itsMyTurn();
                }
                else
                    System.out.println("è caduta connessione, non ci sono abbastanza player nella room");
            }
        };

        Timer timer = new Timer();
        System.out.println("timer iniziato da capo");
        timer.schedule(timerTask, 10000);
    }

    public void setTimerSettings(TimerSettings timerSettings) {
        this.timerSettings = timerSettings;
    }
}
