package project.server;

import com.google.gson.Gson;
import project.PlayerFile;
import project.configurations.Configuration;
import project.configurations.TimerSettings;
import project.controller.Constants;
import project.messages.updatesmessages.*;
import project.model.Turn;
import project.server.network.PlayerHandler;
import project.server.network.rmi.ServerRMI;
import project.server.network.socket.SocketServer;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.rmi.AlreadyBoundException;
import java.util.*;

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
        server.startServer(Constants.SOCKET_PORT, Constants.RMI_PORT);

    }

    private void startServer(int socketPort, int rmiPort) throws IOException {
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
        System.out.println("SONO NEL METODO DI LOGIN");
        System.out.println(nickname);
        System.out.println(player);
        System.out.println(roomsAreAllFull());

        createPlayerFile(nickname);

        if (nicknameAlreadyUsed(nickname)) {
            System.out.println("NICKNAME GIA USATO");
            player.nicknameAlredyUsed();
            return;
        }


        if ( rooms.isEmpty() || roomsAreAllFull()) {
            System.out.println("CREO NUOVA ROOM");
            createNewRoom(nickname, player);
            return;
        }

            for (Room room : rooms) {
                if (!room.isMatchStarted()) {
                    //riconnessione
                    if (room.nicknamePlayersMap.containsKey(nickname) && !room.nicknamePlayersMap.get(nickname).isOn()) { // riconnessione giocatre andato down durante il collegament alla partita
                        System.out.println("RICONNESSIONE MATCH NOT STARTED");
                        player.setName(nickname);
                        player.setOn(true);
                        player.setRoom(room);
                        room.nicknamePlayersMap.replace(nickname, player);
                        checkAndStartTheTimer(room, player);
                        return;

                    } else if (!room.isFull() && !room.isMatchStarted()) { //se la room non è piena aggiungo il giocatore
                        System.out.println("ROOM NON PIENA AGGIUNGO IL GIOCATORE");
                        player.setName(nickname);
                        player.setOn(true);
                        player.setRoom(room);
                        room.nicknamePlayersMap.put(nickname, player);
                        player.loginSucceded();
                        checkAndStartTheTimer(room, player);

                        if (room.isFull()) {
                            startMatch(room);
                        }
                        return;
                    }

                } else if (room.nicknamePlayersMap.containsKey(nickname) && !room.nicknamePlayersMap.get(nickname).isOn()) { //durante la partita
                        System.out.println("RICONNESSIONE IN PARTITA");
                        player.setOn(true);
                        player.setRoom(room);
                        loadPlayerState(room, nickname, player);
                        sendAllUpdates(player, room, nickname);
                        room.nicknamePlayersMap.replace(nickname, player);
                        if (numberOfPlayersOn(room.getBoard().getTurn().getPlayerTurn()) == 1) {
                            player.itsMyTurn();
                            room.getGameActions().myTimerSkipTurn(player, room.getListOfPlayers() );
                        }

                        player.loginSucceded();
                        return;

                    }
                }
        if ( allMatchStarted() )
            createNewRoom(nickname, player);
    }

    private void createPlayerFile(String nickname) {
        PlayerFile playerFile = new PlayerFile();
        playerFile.setPlayerName(nickname);
        Gson gson = new Gson();
        String playerFileJson = gson.toJson(playerFile);

        File file = new File("/persistance/persistance.json");
        try {
            FileWriter f = new FileWriter(file, true );
            f.write(playerFileJson);
            f.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean allMatchStarted() {
        for ( Room room : getRooms() )
            if ( !room.isMatchStarted() )
                return false;
        return true;
    }

    private void sendAllUpdates( PlayerHandler player, Room room, String nickname ) {
        player.sendUpdates(new PersonalBoardUpdate(player, nickname));
        player.sendUpdates(new TowersUpdate(room.getBoard().getAllTowers(), nickname));
        player.sendUpdates(new ScoreUpdate(player, nickname));
        player.sendUpdates(new CouncilUpdate(room.getBoard().getCouncilZone(), nickname));
       // player.sendUpdates(new ExcomunicationUpdate(room.getBoard().getExcommunicationZone(), nickname));
        player.sendUpdates(new HarvesterUpdate(room.getBoard().getHarvesterZone(), nickname));
        player.sendUpdates(new DiceValueUpdate(room.getBoard().getDiceValue()));
        player.sendUpdates(new FamilyMemberUpdate(player, nickname));
        player.sendUpdates(new MarketUpdate(room.getBoard(), nickname));
        player.sendUpdates(new ProductionUpdate(room.getBoard().getProductionZone(), nickname));
    }


    private int numberOfPlayersOn(List<PlayerHandler> players) {
        int count = 0;
        for (PlayerHandler player : players) {
            if (player.isOn())
                count++;
        }
        return count;
    }

    private void replaceInTurn(Turn turn, PlayerHandler oldPlayer, PlayerHandler newPlayer) {
        for (int i = 0; i < turn.getPlayerTurn().size(); i++) {
            if (turn.getPlayerTurn().get(i) == oldPlayer)
                turn.getPlayerTurn().set(i, newPlayer);
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
        newPlayer.setExcommunicationEffectsUseful(oldPlayer.getExcommunicationEffectsUseful());

        replaceInTurn(room.getBoard().getTurn(), oldPlayer, newPlayer);
    }

    private boolean nicknameAlreadyUsed(String nickname) {
        for (Room room : rooms) {
            for (Map.Entry<String, PlayerHandler> entry : room.nicknamePlayersMap.entrySet())
                if (entry.getKey().equals(nickname) && entry.getValue().isOn())
                    return true;
        }
        return false;
    }


    //todo togliere player
    private void checkAndStartTheTimer(Room room, PlayerHandler player) {
        if (room.numberOfPlayerOn() == 2) {
            myTimerStartMatch(room, this.timerSettings, player);
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
        for (Room room : rooms) {
            if (!room.isFull())
                return false;
        }
        return true;
    }

    private void myTimerStartMatch(Room room, TimerSettings timerSettings, PlayerHandler player) {

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

    public ArrayList<Room> getRooms() {
        return rooms;
    }

    public void setRooms(ArrayList<Room> rooms) {
        this.rooms = rooms;
    }
}
