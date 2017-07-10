package project.server;

import com.google.gson.Gson;
import project.PlayerFile;
import project.PrinterClass.UnixColoredPrinter;
import project.configurations.Configuration;
import project.configurations.TimerSettings;
import project.controller.Constants;
import project.controller.supportfunctions.AllSupportFunctions;
import project.messages.updatesmessages.*;
import project.model.Turn;
import project.server.network.PlayerHandler;
import project.server.network.rmi.ServerRMI;
import project.server.network.socket.SocketServer;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

/**
 * This is the main Server class. The main of this class, once started, launch both an RMI server and a Socket Server
 * for receiving all kinds of clients connection's requests. This class also manage the login requests and operations
 * with persistance purposes.
 */
public class Server {

    private ArrayList<Room> rooms;

    private SocketServer serverSocket;

    private ServerRMI rmiServer;

    private TimerSettings timerSettings;

    public Server() {
        rooms = new ArrayList<>();
        serverSocket = new SocketServer(this);
        rmiServer = new ServerRMI(this);
        Configuration configuration = new Configuration();
        UnixColoredPrinter.Logger logger = new UnixColoredPrinter.Logger();
        logger.toString();

        try {
            this.timerSettings = configuration.loadTimer();
        } catch (FileNotFoundException e) {
            UnixColoredPrinter.Logger.print("error loading files");
        }
    }

    public static void main(String[] args) throws IOException {
        Server server = new Server();
        server.startServer(Constants.SOCKET_PORT, Constants.RMI_PORT);

    }

    /**
     * start servers
     * @param socketPort socket port
     * @param rmiPort rmi port
     * @throws IOException Signals that an I/O exception of some sort has occurred
     */
    private void startServer(int socketPort, int rmiPort) throws IOException {
        serverSocket.startServer(socketPort);
        rmiServer.startServer(rmiPort);
    }

    /**
     * This method manage the login and the reconnection of the players placing them in the correct room
     *
     * @param nickname player's nickname as a String
     * @param player   playerHandler's reference
     */
    public void loginRequest(String nickname, PlayerHandler player) {

        createPlayerFile(nickname);

        if (nicknameAlreadyUsed(nickname)) {
            player.nicknameAlredyUsed();
            return;
        }

        if (rooms.isEmpty() || roomsAreAllFull()) {
            UnixColoredPrinter.Logger.print("new room created!");
            createNewRoom(nickname, player);
            return;
        }

        for (Room room : rooms) {
            if (!room.isMatchStarted()) {
                if (room.nicknamePlayersMap.containsKey(nickname) && !room.nicknamePlayersMap.get(nickname).isOn()) { // riconnessione giocatre andato down durante il collegament alla partita

                    PlayerHandler oldPlayer = room.nicknamePlayersMap.get(nickname);
                    player.setName(nickname);
                    player.setRoom(room);
                    room.nicknamePlayersMap.replace(nickname, player);
                    AllSupportFunctions element = room.getPlayerAllSupportFunctionsMap().get(oldPlayer);
                    room.getPlayerAllSupportFunctionsMap().remove(oldPlayer);
                    room.getPlayerAllSupportFunctionsMap().put(player, element);

                    if (!room.draftTime) {
                        player.setOn(true);

                        room.setTimer(checkAndStartTheTimer(room));
                    } else {
                        player.setDisconnectedInDraft(true);
                    }


                    return;

                } else if (!room.isFull() && !room.isMatchStarted()) { // if room is not full, add player to the room
                    player.setName(nickname);
                    player.setOn(true);
                    player.setRoom(room);
                    room.nicknamePlayersMap.put(nickname, player);
                    player.loginSucceded();
                    if (room.numberOfPlayerOn() == 2) room.setTimer(checkAndStartTheTimer(room));

                    if (room.isFull()) {
                        player.tokenNotify();
                        room.getTimer().cancel();
                        startMatch(room);
                    }
                    return;
                }

            } else if (room.nicknamePlayersMap.containsKey(nickname) && !room.nicknamePlayersMap.get(nickname).isOn()) { //durante la partita
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
        if (allMatchStarted()) createNewRoom(nickname, player);
    }

    /**
     * This method ad thw winner to the global file of players
     *
     * @param winnerName winner's name a string
     */
    void addWinnersToTheFile(String winnerName) {

        Gson gson = new Gson();
        String currentFile;
        try {

            currentFile = readFile(Constants.FILENAME, StandardCharsets.UTF_8);
            PlayerFile[] arrayPlayers = gson.fromJson(currentFile, PlayerFile[].class);

            for (PlayerFile player : arrayPlayers)
                if (player.getPlayerName().equals(winnerName))
                    player.setNumberOfVictories(player.getNumberOfVictories() + 1);
                else player.setNumberOfDefeats(player.getNumberOfDefeats() + 1);

            String fileUpgraded = gson.toJson(arrayPlayers);


            try (FileWriter f2 = new FileWriter(Constants.FILENAME, false);){
                f2.write(fileUpgraded);
            }

        } catch (IOException e) {
            UnixColoredPrinter.Logger.print(Constants.IO_EXCEPTION);
        }
    }

    /**
     * This class is the Victories comparator for comapring the players for generarting the ranking
     */
    class VictoriesComparator implements Comparator<PlayerFile> {

        @Override
        public int compare(PlayerFile o1, PlayerFile o2) {
            if (o1.getNumberOfVictories() > o2.getNumberOfVictories()) return 1;
            else if (o1.getNumberOfVictories() == o2.getNumberOfVictories())
                if (o1.getNumberOfDefeats() < o2.getNumberOfDefeats()) {
                    return 1;
                } else return 0;

            else return -1;
        }
    }


    /**
     * This method generate the global ranking of players
     *
     * @return list of players ordered for victories
     */
    List<PlayerFile> generateRanking() {
        Gson gson = new Gson();
        String currentFile;
        List<PlayerFile> playerFileList = new ArrayList<>();
        try {

            currentFile = readFile(Constants.FILENAME, StandardCharsets.UTF_8);

            PlayerFile[] arrayPlayers = gson.fromJson(currentFile, PlayerFile[].class);
            VictoriesComparator comparator = new VictoriesComparator();
            Collections.addAll(playerFileList, arrayPlayers);
            Collections.sort(playerFileList, comparator);
            Collections.reverse(playerFileList);

            return playerFileList;
        } catch (IOException e) {
            UnixColoredPrinter.Logger.print(Constants.IO_EXCEPTION);
        }
        return playerFileList;
    }


    /**
     * This method create the file with the players if it doesn't exit, add a json object to the file if is the first
     * match for the current player or increase the number of matches played if he had played at least a match before
     * the current one
     *
     * @param nickname player's nickname as a string
     */
    private void createPlayerFile(String nickname) {
        PlayerFile playerFile = new PlayerFile();
        playerFile.setPlayerName(nickname);
        String fileUpgraded = null;

        Gson gson = new Gson();

        File file = new File(Constants.FILENAME);
        try (
                FileWriter fw = new FileWriter(file.getAbsoluteFile(), true);
                BufferedWriter bw = new BufferedWriter(fw);){


            if (!file.exists()) {
                file.createNewFile();
                PlayerFile[] array = new PlayerFile[1];
                array[0] = playerFile;
                String data = gson.toJson(array);
                bw.write(data);
                return;
            }

            String currentFile = readFile(Constants.FILENAME, StandardCharsets.UTF_8);

            PlayerFile[] arrayPlayers = gson.fromJson(currentFile, PlayerFile[].class); //lo trasformo in oggetto

            for (PlayerFile player : arrayPlayers)
                if (player.getPlayerName().equals(nickname)) {
                    player.setNumberOfGames(player.getNumberOfGames() + 1);
                    fileUpgraded = gson.toJson(arrayPlayers);
                    break;

                }

            try (RandomAccessFile randomAccessFile = new RandomAccessFile(Constants.FILENAME, "rw")) {

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
                    try (FileWriter fw1 = new FileWriter(file.getAbsoluteFile(), false); BufferedWriter bw1 = new BufferedWriter(fw1)) {
                        bw1.write(fileUpgraded);
                    }
                }
            }

        } catch (IOException e) {
            UnixColoredPrinter.Logger.print(Constants.IO_EXCEPTION);
        }
    }

    /**
     * This method reads all the bytes of a file
     *
     * @param path     file path as a String
     * @param encoding Constant definitions for the standard
     * @return
     * @throws IOException
     */
    private String readFile(String path, Charset encoding) throws IOException {
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return new String(encoded, encoding);
    }


    private boolean allMatchStarted() {
        for (Room room : getRooms())
            if (!room.isMatchStarted()) return false;
        return true;
    }

    /**
     * This method sends all the updates to a specific player
     *
     * @param player   playerHandler's reference
     * @param room     players room's reference
     * @param nickname player's nickname as a String
     */
    public void sendAllUpdates(PlayerHandler player, Room room, String nickname) {
        player.sendUpdates(new PersonalBoardUpdate(player, nickname));
        player.sendUpdates(new TowersUpdate(room.getBoard().getAllTowers(), nickname));
        player.sendUpdates(new ScoreUpdate(player, nickname));
        player.sendUpdates(new CouncilUpdate(room.getBoard().getCouncilZone(), nickname));
        player.sendUpdates(new ExcomunicationUpdate(room.getBoard().getExcommunicationZone(), nickname));
        player.sendUpdates(new HarvesterUpdate(room.getBoard().getHarvesterZone(), nickname));
        player.sendUpdates(new DiceValueUpdate(room.getBoard().getDiceValue(), room.getBoard().getTurn()));
        player.sendUpdates(new FamilyMemberUpdate(player, nickname));
        player.sendUpdates(new MarketUpdate(room.getBoard(), nickname));
        player.sendUpdates(new ProductionUpdate(room.getBoard().getProductionZone(), nickname));
    }

    /**
     * This method replace the old player reference with the new one after a reconnection of the same player
     *
     * @param turn      turn's reference
     * @param oldPlayer playerHandler's reference to the old player
     * @param newPlayer playerHandler's reference to the new player
     */
    private void replaceInTurn(Turn turn, PlayerHandler oldPlayer, PlayerHandler newPlayer) {
        for (int i = 0; i < turn.getPlayerTurn().size(); i++) {
            if (turn.getPlayerTurn().get(i) == oldPlayer) {
                turn.getPlayerTurn().set(i, newPlayer);
                return;
            }
        }
    }

    /**
     * This method load all the information about the state of a player in a new playerHandler's reference after a
     * reconnection during the match
     *
     * @param room      player's room reference
     * @param oldPlayer playerHandler's reference to the old player
     * @param newPlayer playerHandler's reference to the new player
     */
    void loadPlayerState(Room room, PlayerHandler newPlayer, PlayerHandler oldPlayer) {

        newPlayer.setName(oldPlayer.getName());
        newPlayer.setPersonalBoardReference(oldPlayer.getPersonalBoardReference());
        newPlayer.setScore(oldPlayer.getScore());
        newPlayer.setAllFamilyMembers(oldPlayer.getAllFamilyMembers());
        newPlayer.setFamilyColour(oldPlayer.getFamilyColour());

        replaceInTurn(room.getBoard().getTurn(), oldPlayer, newPlayer);
    }

    /**
     * Check if a nickname with which the current player is trying to do a login is already used by another player or
     * not
     *
     * @param nickname player's nickname as String
     * @returnt true if the nickname is already used, else false
     */
    private boolean nicknameAlreadyUsed(String nickname) {
        for (Room room : rooms) {
            for (Map.Entry<String, PlayerHandler> entry : room.nicknamePlayersMap.entrySet())
                if (entry.getKey().equals(nickname) && entry.getValue().isOn()) return true;
        }
        return false;
    }

    /**
     * This method check is the number of player on is equals to 2 and, if this is true, trigger the start match timer
     *
     * @param room player's room reference
     * @return reference to the timer
     */
    private Timer checkAndStartTheTimer(Room room) {
        if (room.numberOfPlayerOn() == 2) return myTimerStartMatch(room, this.timerSettings);

        return new Timer();
    }

    /**
     * This method calls "startMatch" method on the room
     *
     * @param room room's reference
     */
    private void startMatch(Room room) {
        room.startMatch();
    }

    /**
     * This method create a new room and add the palyer that is currently doing the login in the room's hashMap with
     * playerHandler's reference and nickname
     *
     * @param nickname player's nickename as String
     * @param player   playerhandler's reference
     */
    private void createNewRoom(String nickname, PlayerHandler player) {
        Room room = new Room(this);
        rooms.add(room);
        player.setOn(true);
        player.setRoom(room);
        player.setName(nickname);
        room.nicknamePlayersMap.put(nickname, player);

        player.loginSucceded();
    }

    /**
     * Check if all rooms are full
     *
     * @return true if all rooms are full, else false
     */
    private boolean roomsAreAllFull() {
        for (Room room : rooms) {
            if (!room.isFull()) return false;
        }
        return true;
    }

    /**
     * This method starts the timer for starting the match
     *
     * @param room          room's reference
     * @param timerSettings object created from a Json file that contains all the timer's specific
     * @return timer's reference
     */
    private Timer myTimerStartMatch(Room room, TimerSettings timerSettings) {

        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                if (room.minimumNumberOfPlayers()) {
                    startMatch(room);
                }
            }
        };

        Timer timer = new Timer(timerSettings.getStartMatchTimerName());
        timer.schedule(timerTask, timerSettings.getDelayTimerStartMatch());
        return timer;
    }

    /**
     * Get the timerSettings's reference
     *
     * @return timerSettings's reference
     */
    TimerSettings getTimerSettings() {
        return timerSettings;
    }

    /**
     * Get the room's reference
     *
     * @return room's reference
     */
    ArrayList<Room> getRooms() {
        return rooms;
    }

    void setRooms(ArrayList<Room> rooms) {
        this.rooms = rooms;
    }


}
