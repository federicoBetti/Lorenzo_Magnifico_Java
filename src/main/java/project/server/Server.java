package project.server;

import com.google.gson.Gson;
import project.PlayerFile;
import project.configurations.Configuration;
import project.configurations.TimerSettings;
import project.controller.Constants;
import project.controller.supportfunctions.AllSupportFunctions;
import project.messages.updatesmessages.*;
import project.model.Player;
import project.model.Turn;
import project.server.network.PlayerHandler;
import project.server.network.rmi.ServerRMI;
import project.server.network.socket.SocketServer;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
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

    public Server() throws IOException {
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


        if (rooms.isEmpty() || roomsAreAllFull()) {
            System.out.println("CREO NUOVA ROOM");
            createNewRoom(nickname, player);
            return;
        }

        for (Room room : rooms) {
            if (!room.isMatchStarted()) {
                //riconnessione
                if (room.nicknamePlayersMap.containsKey(nickname) && !room.nicknamePlayersMap.get(nickname).isOn()) { // riconnessione giocatre andato down durante il collegament alla partita
                    System.out.println("RICONNESSIONE MATCH NOT STARTED");

                    PlayerHandler oldPlayer = room.nicknamePlayersMap.get(nickname);
                    player.setName(nickname);
                    player.setRoom(room);
                    room.nicknamePlayersMap.replace(nickname, player);
                    AllSupportFunctions element = room.getPlayerAllSupportFunctionsMap().get(oldPlayer);
                    room.getPlayerAllSupportFunctionsMap().remove(oldPlayer);
                    room.getPlayerAllSupportFunctionsMap().put(player, element);

                    if (!room.draftTime) {
                        player.setOn(true);
                        checkAndStartTheTimer(room, player);
                    }
                    else {
                        player.disconnectedInDraft = true;
                    }


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
                        player.tokenNotify();
                        startMatch(room);
                    }
                    return;
                }

            } else if (room.nicknamePlayersMap.containsKey(nickname) && !room.nicknamePlayersMap.get(nickname).isOn()) { //durante la partita
                System.out.println("RICONNESSIONE IN PARTITA");
                player.setOn(true);
                player.setRoom(room);
                PlayerHandler oldPlayer = room.nicknamePlayersMap.get(nickname);
                loadPlayerState(room, player, oldPlayer);
                room.nicknamePlayersMap.replace(nickname, player);
                AllSupportFunctions element = room.getPlayerAllSupportFunctionsMap().get(oldPlayer);
                room.getPlayerAllSupportFunctionsMap().remove(oldPlayer);
                room.getPlayerAllSupportFunctionsMap().put(player, element);
                player.loginSucceded();
                player.tokenNotify();
                player.matchStarted(room.getRoomPlayers(), player.getFamilyColour());
                sendAllUpdates(player, room, nickname);
                return;

            }
        }
        if (allMatchStarted())
            createNewRoom(nickname, player);
    }

    private void createPlayerFile(String nickname) {
        PlayerFile playerFile = new PlayerFile();
        playerFile.setPlayerName(nickname);
        String fileUpgraded = null;

        Gson gson = new Gson();
        BufferedWriter bw = null;
        FileWriter fw = null;

        try {
            File file = new File(Constants.FILENAME);

            if (!file.exists()) {
                System.out.println("CREO IL FILE");
                file.createNewFile();
                fw = new FileWriter(file.getAbsoluteFile(), true);
                bw = new BufferedWriter(fw);
                PlayerFile[] array = new PlayerFile[1];
                array[0] = playerFile;
                String data = gson.toJson(array);
                bw.write(data);
                return;
            }

            String currentFile = readFile(Constants.FILENAME, StandardCharsets.UTF_8);
            System.out.println("Il file in questo momento è: " + currentFile);

            PlayerFile[] arrayPlayers = gson.fromJson(currentFile, PlayerFile[].class); //lo trasformo in oggetto

            for (PlayerFile player : arrayPlayers)
                if (player.getPlayerName().equals(nickname)) {
                    player.setNumberOfGames(player.getNumberOfGames() + 1);
                    fileUpgraded = gson.toJson(arrayPlayers);
                    break;

                }

            fw = new FileWriter(file.getAbsoluteFile(), true);
            bw = new BufferedWriter(fw);
            RandomAccessFile randomAccessFile = new RandomAccessFile(Constants.FILENAME, "rw");

            long pos = randomAccessFile.length();
            while (randomAccessFile.length() > 0) {
                pos--;
                randomAccessFile.seek(pos);
                if (randomAccessFile.readByte() == ']') {
                    randomAccessFile.seek(pos);
                    break;
                }
            }

            // se non è stato trovato il player nel file
            if (fileUpgraded == null) { // aggiungo l'elemento
                String jsonElement = gson.toJson(playerFile);
                randomAccessFile.writeBytes("," + jsonElement + "]");

            } else {
                System.out.println("STO AGGIUNGENDO IL SECONDO PEZZO AL FILE");
                System.out.println(fileUpgraded);
                fw = new FileWriter(file.getAbsoluteFile(), false);
                bw = new BufferedWriter(fw);
                bw.write(fileUpgraded);

            }
            randomAccessFile.close();

        } catch (IOException e) {

            e.printStackTrace();

        } finally {

            try {

                if (bw != null)
                    bw.close();

                if (fw != null)
                    fw.close();


            } catch (IOException ex) {

                ex.printStackTrace();

            }
        }
    }

    private String readFile(String path, Charset encoding)
            throws IOException {
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return new String(encoded, encoding);
    }

    private String playerIsInTheFile(String nickname, String filename) {
        Gson gson = new Gson();
        try {

            FileInputStream fis = new FileInputStream(filename);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader bufferedReader = new BufferedReader(isr);
            //StringBuilder sb = new StringBuilder();
            String finalString = "";
            String line;
            while ((line = bufferedReader.readLine()) != null) { //stringhifico il file
                finalString += line;
                //sb.append(line);
            }

            //String json = sb.toString();

            System.out.println(finalString);
            PlayerFile[] arrayPlayers = gson.fromJson(finalString, PlayerFile[].class); //lo trasformo in oggetto

            for (PlayerFile player : arrayPlayers)
                if (player.getPlayerName().equals(nickname)) {
                    player.setNumberOfGames(player.getNumberOfGames() + 1);
                    {
                        return gson.toJson(arrayPlayers);
                    }
                }

            /* br = new BufferedReader(new FileReader(filename));


            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while (line != null) {
                sb.append(line);
                line = br.readLine();
            }*/

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }


    private boolean allMatchStarted() {
        for (Room room : getRooms())
            if (!room.isMatchStarted())
                return false;
        return true;
    }

    private void sendAllUpdates(PlayerHandler player, Room room, String nickname) {
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
            if (turn.getPlayerTurn().get(i) == oldPlayer) {
                turn.getPlayerTurn().set(i, newPlayer);
                return;
            }
        }
    }

    void loadPlayerState(Room room, PlayerHandler newPlayer, PlayerHandler oldPlayer ) {

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

    void myTimerStartMatch(Room room, TimerSettings timerSettings, PlayerHandler player) {

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
