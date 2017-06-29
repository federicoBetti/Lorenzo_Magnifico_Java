package project.server;

import project.configurations.Configuration;
import project.configurations.TimerSettings;
import project.controller.Constants;
import project.model.Board;
import project.model.Deck;
import project.model.Turn;
import project.server.network.PlayerHandler;
import project.server.network.rmi.ServerRMI;
import project.server.network.socket.SocketServer;

import java.io.IOException;
import java.rmi.AlreadyBoundException;
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
        //todo mettere configuration
        configuration = new Configuration();
        this.timerSettings = configuration.loadTimer();
    }

    public static void main(String[] args) throws IOException {
        Server server = new Server();
        server.startServer(Constants.SOCKET_PORT, Constants.RMI_PORT );

    }

    private void startServer( int socketPort, int rmiPort ) throws IOException {
        serverSocket.startServer(socketPort);
        try {
            rmiServer.startServer(rmiPort);
        } catch (AlreadyBoundException e) {
            e.printStackTrace();
        }
    }


    /**
     * TODO implemetare tutti i metodi che si occupano della gestione delle room e del fileXML.controller dei giocatori
     */

    public void loginRequest(String nickname, PlayerHandler player) throws IOException {
        if ( nicknameAlreadyUsed(nickname))
            player.nicknameAlredyUsed();

        if (rooms.isEmpty() || roomsAreAllFull()) {
            System.out.println(roomsAreAllFull());
            createNewRoom(nickname, player);
            return;
        }


        for (Room room : rooms) {
            if (!room.isMatchStarted()) { //riconnessione
                if (room.nicknamePlayersMap.containsKey(nickname) && !room.nicknamePlayersMap.get(nickname).isOn()) { // riconnessione giocatre andato down durante il collegament alla partita
                    System.out.println("riconnessione");
                    player.setName(nickname);
                    player.setOn(true);
                    player.setRoom(room);
                    room.nicknamePlayersMap.replace(nickname, player);
                    checkAndStartTheTimer(room, player);

                } else if (!room.isFull()) { //se la room non è piena aggiungo il giocatore
                    player.setName(nickname);
                    player.setOn(true);
                    player.setRoom(room);
                    room.nicknamePlayersMap.put(nickname, player);
                    player.loginSucceded();
                    checkAndStartTheTimer(room, player);

                    if (room.isFull()) {
                        startMatch(room);
                    }
                    break;
                }
            } else {    //partita iniziata
                if( room.nicknamePlayersMap.containsKey(nickname) && !room.nicknamePlayersMap.get(nickname).isOn() ){ //durante la partita
                    player.setOn(true);
                    player.setRoom(room);
                    loadPlayerState(room, nickname, player );
                    room.nicknamePlayersMap.replace(nickname, player);
                    player.loginSucceded();
                }
            }
        }
    }

    private void replaceInTurn(Turn turn, PlayerHandler oldPlayer, PlayerHandler newPlayer) {
        for (int i = 0; i < turn.getPlayerTurn().size(); i++  ){
            if ( turn.getPlayerTurn().get(i) == oldPlayer )
                turn.getPlayerTurn().set( i, newPlayer );
        }
    }

    private void loadPlayerState(Room room, String nickname, PlayerHandler newPlayer) {
        PlayerHandler oldPlayer = room.nicknamePlayersMap.get(nickname);

        newPlayer.setName(oldPlayer.getName());
        newPlayer.setPersonalBoardReference(oldPlayer.getPersonalBoardReference());
        newPlayer.setScore(oldPlayer.getScore());
        newPlayer.setAllFamilyMembers(oldPlayer.getAllFamilyMembers());
        newPlayer.setTurnOrder(oldPlayer.getTurnOrder());
        newPlayer.setFamilyColour(oldPlayer.getFamilyColour());
        newPlayer.setLeaderEffectsUsefull(oldPlayer.getLeaderEffectsUsefull());
        newPlayer.setExcommunicationEffectsUseful(oldPlayer.getExcommunicationEffectsUseful());

        replaceInTurn(room.getBoard().getTurn(), oldPlayer, newPlayer);
    }

    private boolean nicknameAlreadyUsed( String nickname ){
        for ( Room room: rooms ) {
            for (Map.Entry<String, PlayerHandler> entry : room.nicknamePlayersMap.entrySet())
                if (entry.getKey().equals(nickname) && entry.getValue().isOn())
                    return true;
        }
        return false;
    }


    //todo togliere player
    private void checkAndStartTheTimer( Room room, PlayerHandler player ){
        if ( room.numberOfPlayerOn() == 2 ){
            myTimerStartMatch( room, this.timerSettings, player );
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
        player.setRoom(room);
        player.setName(nickname);
        room.nicknamePlayersMap.put(nickname, player);

        System.out.println(room.nicknamePlayersMap.entrySet());
        player.loginSucceded();
    }

    private boolean roomsAreAllFull() {
        for ( Room room: rooms ){
            if ( !room.isFull() )
                return false;
        }
        return true;
    }

    private void myTimerStartMatch( Room room, TimerSettings timerSettings, PlayerHandler player ) {

        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                if (room.minimumNumberOfPlayers()) {
                   // player.itsMyTurn();
                    startMatch(room);
                }
            }
        };

        //todo far partire timer
        Timer timer = new Timer(timerSettings.getStartMatchTimerName());
        timer.schedule(timerTask, timerSettings.getDelayTimerStartMatch());
    }

    public TimerSettings getTimerSettings() {
        return timerSettings;
    }
}
