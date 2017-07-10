package project.server;

import com.google.gson.Gson;
import project.PlayerFile;
import project.PrinterClass.UnixColoredPrinter;
import project.configurations.Configuration;
import project.configurations.TimerSettings;
import project.controller.Constants;
import project.controller.cardsfactory.ExcommunicationTile;
import project.controller.cardsfactory.LeaderCard;
import project.controller.effects.effectsfactory.BuildExcommunicationEffects;
import project.controller.supportfunctions.AllSupportFunctions;
import project.controller.supportfunctions.BasicSupportFunctions;
import project.messages.updatesmessages.*;
import project.model.*;
import project.server.network.PlayerHandler;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;


/**
 * This class contains all the methods for handling the match's state
 */
public class Room {

    private final Server server;

    private Board board;

    private int maxPlayers;

    private Map<Player, AllSupportFunctions> playerAllSupportFunctionsMap;

    Map<String, PlayerHandler> nicknamePlayersMap;

    private BuildExcommunicationEffects buildExcommunicationEffects;

    private GameActions gameActions;

    private boolean matchStarted;

    TimerSettings timerSettings;

    private Timer timer;

    private PlayerHandler lastPlayer;

    boolean draftTime = false;


    /**
     * Constructor
     *
     * @param server server's reference
     */
    public Room(Server server) {
        playerAllSupportFunctionsMap = new HashMap<>();
        nicknamePlayersMap = new HashMap<>();
        buildExcommunicationEffects = new BuildExcommunicationEffects();
        matchStarted = false;
        gameActions = new GameActions(this);
        this.server = server;
        timerSettings = server.getTimerSettings();
        maxPlayers = 4;
        timer = new Timer();
    }

    /**
     * This method check if the room is full, so if the number of player with the boolean "on" setted to true is equals
     * to the number of the players that started the match
     *
     * @return true if the room is full, else false
     */
    boolean isFull() {
        int count = 0;
        for (Map.Entry<String, PlayerHandler> entry : nicknamePlayersMap.entrySet())
            if (entry.getValue().isOn())
                count++;

        return count == maxPlayers;
    }


    /**
     * Get the board related with the room
     *
     * @return room board's reference
     */
    public Board getBoard() {
        return board;
    }

    /**
     * Get the support functions dedicated to a player
     *
     * @param player playerHandler's reference
     * @return the support function
     */
    public AllSupportFunctions getMySupportFunction(Player player) {
        return playerAllSupportFunctionsMap.get(player);
    }

    /**
     * Set the support functions dedicated to a player
     *
     * @param player playerHandler's reference
     */
    public void setMySupportFunction(AllSupportFunctions allSupportFunctions, PlayerHandler player) {
        playerAllSupportFunctionsMap.put(player, allSupportFunctions);
    }

    /**
     * This method counts the number of players on
     *
     * @return the number of players on
     */
    public int numberOfPlayerOn() {
        int count = 0;
        for (Map.Entry<String, PlayerHandler> entry : nicknamePlayersMap.entrySet())
            if (entry.getValue().isOn())
                count++;
        return count;
    }

    /**
     * This method checks if there is the minimum number of players on for starting the match
     *
     * @return true if there is the minimum number of players on, else false
     */
    boolean minimumNumberOfPlayers() {
        int count = 0;
        for (Map.Entry<String, PlayerHandler> entry : nicknamePlayersMap.entrySet())
            if (entry.getValue().isOn())
                count++;
        return count >= 2;
    }

    /**
     * Get the Game Action object
     *
     * @return Game Action object
     */
    public GameActions getGameActions() {
        return gameActions;
    }

    /**
     * Get the number of players in the room
     *
     * @return the number of players in the room
     */
    public int getRoomPlayers() {
        return nicknamePlayersMap.size();
    }

    /**
     * Generete and get the list of players in the room from the correpsonding HashMap
     *
     * @return the list of players in the room
     */
    public List<PlayerHandler> getListOfPlayers() {
        List<PlayerHandler> list = new ArrayList<>();
        for (Map.Entry<String, PlayerHandler> entry : nicknamePlayersMap.entrySet()) {
            list.add(entry.getValue());
        }
        return list;
    }

    /**
     * This method act all the operation for initializing and starting the match in the correct way
     */
    void startMatch() {
        draftTime = true;
        String[] colors = fillColors();
        int i = 0;
        List<PlayerHandler> playerInTheMatch = new ArrayList<>();
        for (Map.Entry<String, PlayerHandler> player : nicknamePlayersMap.entrySet()) {
            if (player.getValue().isOn()) {
                BasicSupportFunctions supportFunctions = new BasicSupportFunctions(player.getValue());
                playerAllSupportFunctionsMap.put(player.getValue(), supportFunctions);
                playerInTheMatch.add(player.getValue());
                resetPlayers(player.getValue());
                player.getValue().setFamilyColour(colors[i]);
                player.getValue().setFamilyColourInFamilyMembers();

                i++;
            }
        }

        maxPlayers = i;

        try {
            board = new Board(playerInTheMatch.size());

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        gameActions.setBoard(board);

        Collections.shuffle(playerInTheMatch);
        fillExcommunicationTile();

        board.getDeckCard().setDevelopmentDeck(shuffleDeck(board.getDeckCard().getDevelopmentDeck()));

        //draft leader
        leaderDraft(playerInTheMatch);

        //draft tile
        tileDraft(playerInTheMatch);

        //Start match
        for (PlayerHandler p : getListOfPlayers()) {

            if (p.isDisconnectedInDraft()) {
                PlayerHandler oldPlayer = null;
                p.setOn(true);
                for (PlayerHandler player : playerInTheMatch)
                    if (p.getName().equals(player.getName()))
                        oldPlayer = player;
                server.loadPlayerState(this, p, oldPlayer);
            }

            p.matchStarted(getRoomPlayers(), p.getFamilyColour());

        }

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            UnixColoredPrinter.Logger.print(Constants.SLEEP_INTERRUPTED);
            Thread.currentThread().interrupt();
        }
        placeCardInTowers();


        gameActions.rollDice();
        matchStarted = true;

        board.getTurn().setPlayerTurn(getListOfPlayers());

        configurePlayers();

        gameActions.firstPlayerTurn();

    }

    /**
     * This method is used for configuring the players before starting the match
     */
    private void configurePlayers() {

        int moreCoin = 0;

        for (PlayerHandler p : getListOfPlayers()) {
            setResources(p, moreCoin);
            if (p.isOn()) {

                p.sendUpdates(new PersonalBoardUpdate(p, p.getName()));
                p.sendUpdates(new TowersUpdate(board.getAllTowers(), p.getName()));
                p.sendUpdates(new MarketUpdate(board, p.getName()));
                p.sendUpdates(new ExcomunicationUpdate(board.getExcommunicationZone(), p.getName()));
                p.sendUpdates(new HarvesterUpdate(board.getHarvesterZone(), p.getName()));
                p.sendUpdates(new FamilyMemberUpdate(p, p.getName()));
                p.sendUpdates(new ScoreUpdate(p, p.getName()));
                p.sendUpdates(new DiceValueUpdate(board.getDiceValue(), board.getTurn()));
                moreCoin++;
            }
        }
    }

    /**
     * this method reset the player's score, personal board and his familiars
     *
     * @param player playerHandler's reference
     */
    private void resetPlayers(PlayerHandler player) {
        Configuration configuration = new Configuration();
            try {
                player.setPersonalBoardReference(new PersonalBoard());
                player.setScore(new Score());
                configuration.loadFamilyMembers(player);
            } catch (FileNotFoundException e) {
                UnixColoredPrinter.Logger.print(Constants.FILE_NOT_FOUND_EXCEPTION);
            }
        }

    /**
     * This method realize the tiles bonus' draft
     *
     * @param playerInTheMatch the list of players in the match
     */
    private void tileDraft(List<PlayerHandler> playerInTheMatch) {

        List<Tile> tiles = fillListTile();
        ListIterator<PlayerHandler> iterator = playerInTheMatch.listIterator();
        while (iterator.hasNext())
            iterator.next();

        while (iterator.hasPrevious()) {
            PlayerHandler p = iterator.previous();
            if (p.isOn()) {
                Timer timer = gameActions.myTimerActions(p);
                int tileId = p.chooseTile(tiles);
                timer.cancel();
                if (tileId == -1) {
                    System.out.println("Sono disconnesso è piglio la prima tile che capita");
                    p.getPersonalBoardReference().setMyTile(tiles.get(0));
                    tiles.remove(tiles.get(0));
                    continue;
                }

                System.out.println("ha scelto la tile numero " + tileId);
                Tile tile = getTrueTile(tileId, tiles);
                p.getPersonalBoardReference().setMyTile(tile);
                tiles.remove(tile);
            } else {
                p.getPersonalBoardReference().setMyTile(tiles.get(0));
                tiles.remove(tiles.get(0));
            } //todo vedere qua perchè non fa partire partita
        }
    }

    /**
     * This method realize the leader cards' draft
     *
     * @param playerInTheMatch the list of players in the match
     */
    private void leaderDraft(List<PlayerHandler> playerInTheMatch) {

        int i;
        ArrayList<ArrayList<LeaderCard>> listsForDraft = getListOfLeader();

        for (i = 0; i < Constants.LEADER_CARD_NUMBER_PER_PLAYER; i++) {
            ListIterator<ArrayList<LeaderCard>> leaderIterator = listsForDraft.listIterator();
            ListIterator<PlayerHandler> playerIterator = playerInTheMatch.listIterator();
            String leaderName;

            while (leaderIterator.hasNext() && playerIterator.hasNext()) {
                PlayerHandler player = playerIterator.next();
                ArrayList<LeaderCard> leaders = leaderIterator.next();
                if (player.isOn()) {
                    Timer timer = gameActions.myTimerActions(player);
                    leaderName = player.leaderCardChosen(leaders);
                    timer.cancel();

                    if (leaderName.equals("-1")) {
                        System.out.println("SONO QUI");
                        player.getPersonalBoardReference().getMyLeaderCard().add(leaders.get(0));
                        leaders.remove(leaders.get(0));
                        continue;
                    }

                    System.out.println("DRAFT NORMALE");
                    LeaderCard leaderToAdd = getLeader(leaderName, leaders);
                    player.getPersonalBoardReference().getMyLeaderCard().add(leaderToAdd);
                    leaders.remove(leaderToAdd);
                } else {
                    player.getPersonalBoardReference().getMyLeaderCard().add(leaders.get(0));
                    leaders.remove(leaders.get(0));
                }
            }
            listsForDraft = shiftLeaderList(listsForDraft);
        }

    }

    /**
     * This method place the cards in the towers when the match starts and every round
     */
    private void placeCardInTowers() {
        Tower[][] tower = board.getAllTowers();
        DevelopmentCard[][][] deck = board.getDeckCard().getDevelopmentDeck();
        int i, j;
        for (i = 0; i < Constants.NUMBER_OF_TOWERS; i++) {
            for (j = 0; j < Constants.CARD_FOR_EACH_TOWER; j++) {
                tower[i][j].setCardOnThisFloor(deck[i][0][j]);
            }
        }
        board.setTowers(tower);

    }

    /**
     * This method fill the excommunication zone with 3 random excommunication tiles
     */
    private void fillExcommunicationTile() {
        ExcommunicationTile[][] deck = board.getDeckCard().getExcommunicationCard();
        ExcommunicationZone[] zone = new ExcommunicationZone[Constants.PERIOD_NUMBER];
        Random r = new Random();
        int rand;
        for (int i = 0; i < Constants.PERIOD_NUMBER; i++) {
            rand = r.nextInt(Constants.EXCOMMUNICATION_CARD_NUMBER_PER_PERIOD);
            ExcommunicationTile ex = deck[i][rand];
            zone[i] = new ExcommunicationZone(ex);
        }
        board.setExcommunicationZone(zone);
    }

    /**
     * This method set the player's resource when the match starts
     *
     * @param player   playerHandler's reference
     * @param moreCoin int that represent tha quantity of coins more that the player has to have respect of the first
     *                 player in the match
     */
    private void setResources(PlayerHandler player, int moreCoin) {
        player.getPersonalBoardReference().setWood(2);
        player.getPersonalBoardReference().setStone(2);
        player.getPersonalBoardReference().setServants(3);
        player.getPersonalBoardReference().setCoins(5 + moreCoin);
    }

    /**
     * Get a specific tile between the bonus tiles's list
     *
     * @param tileId tile number choosen
     * @param tiles  list of bonus tiles
     * @return tile choosen reference
     */
    private Tile getTrueTile(int tileId, List<Tile> tiles) {
        for (Tile t : tiles)
            if (t.getTileNumber() == tileId)
                return t;
        return null;
    }

    /**
     * Add the Bonus tiles to a list and shuffle it
     *
     * @return the list of tiles shuffled
     */
    private ArrayList<Tile> fillListTile() {
        ArrayList<Tile> tiles = new ArrayList<>(4);
        tiles.addAll(Arrays.asList(board.getDeckCard().getProdHarvTiles()));
        Collections.shuffle(tiles);
        return tiles;
    }

    /**
     * Get the leader card corresponding to the name
     *
     * @param leaderName leader card's name as a String
     * @param leaders    list of leader cards
     * @return the leader card related to the name
     */
    private LeaderCard getLeader(String leaderName, ArrayList<LeaderCard> leaders) {
        for (LeaderCard l : leaders) {
            if (l.getName().equals(leaderName))
                return l;
        }
        return null;
    }

    /**
     * This method removes the card choosen in the draft and shift the list of cards to send it to the right player
     *
     * @param listsForDraft list of Leader cards' lists
     * @return the list for the draft shifted
     */
    private ArrayList<ArrayList<LeaderCard>> shiftLeaderList(ArrayList<ArrayList<LeaderCard>> listsForDraft) {
        ArrayList<LeaderCard> firstList = listsForDraft.get(0);
        listsForDraft.remove(0);
        listsForDraft.add(firstList);
        return listsForDraft;
    }

    //todo c'è una riga di shuffle commentata

    /**
     * This method create the list of lists for the leaders drafting
     *
     * @return the list for the draft
     */
    private ArrayList<ArrayList<LeaderCard>> getListOfLeader() {
        int numberOfCard = Constants.LEADER_CARD_NUMBER_PER_PLAYER;
        ArrayList<ArrayList<LeaderCard>> listsForDraft = new ArrayList<>();
        ArrayList<LeaderCard> leaders = board.getDeckCard().getLeaderCardDeck();
        //Collections.shuffle(leaders);
        for (int i = 0; i < getRoomPlayers(); i++) {
            ArrayList<LeaderCard> ll = new ArrayList<>(leaders.subList(numberOfCard * i, numberOfCard * i + numberOfCard));
            listsForDraft.add(ll);
        }
        return listsForDraft;
    }


    /**
     * This method fills an array with the colours of the players
     *
     * @return the array of colors
     */
    private String[] fillColors() {
        String[] colors = new String[4];
        colors[0] = Constants.GREEN;
        colors[1] = Constants.RED;
        colors[2] = Constants.YELLOW;
        colors[3] = Constants.BLUE;

        return colors;
    }

    /**
     * This method shuffles the development's card deck
     *
     * @param deck development's card deck reference
     * @return the deck shuffled
     */
    private DevelopmentCard[][][] shuffleDeck(DevelopmentCard[][][] deck) {
        Random rnd = ThreadLocalRandom.current();
        for (int j = 0; j < Constants.CARD_TYPE_NUMBER; j++) {
            for (int k = 0; k < Constants.PERIOD_NUMBER; k++) {
                for (int i = Constants.CARD_FOR_EACH_PERIOD - 1; i > 0; i--) {
                    int index = rnd.nextInt(i + 1);
                    DevelopmentCard card = deck[j][k][index];
                    deck[j][k][index] = deck[j][k][i];
                    deck[j][k][i] = card;
                }
            }
        }
        return deck;
    }

    /**
     * Get the server reference
     *
     * @return server reference
     */
    public Server getServer() {
        return server;
    }

    /**
     * Check the variable that says if the match is started
     *
     * @return the boolean that says if the match is started
     */
    public boolean isMatchStarted() {
        return matchStarted;
    }

    /**
     * This method takes the history of a specific player from the json file that contains it
     *
     * @param nickname player's nickname as a string
     * @return the object containing the statistics about the player
     */
    public String takeStatistics(String nickname) {
        String currentFile;
        Gson gson = new Gson();

        try {
            currentFile = readFile(Constants.FILENAME, StandardCharsets.UTF_8);
            PlayerFile[] arrayPlayers = gson.fromJson(currentFile, PlayerFile[].class);

            for (PlayerFile player : arrayPlayers)
                if (player.getPlayerName().equals(nickname)) {
                    return gson.toJson(player);
                }

        } catch (IOException e) {
            e.printStackTrace();
            e.printStackTrace();
        }
        return null;
    }

    /**
     * This method reads all the bytes of a file
     *
     * @param path     file path as a String
     * @param encoding Constant definitions for the standard
     * @return
     * @throws IOException
     */
    private String readFile(String path, Charset encoding)
            throws IOException {
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return new String(encoded, encoding);
    }

    /**
     * Get the last player that has played reference
     *
     * @return the last player that has played reference
     */
    public PlayerHandler getLastPlayer() {
        return lastPlayer;
    }

    /**
     * Set the last player that has played reference
     *
     * @return the last player that has played reference
     */
    public void setLastPlayer(PlayerHandler lastPlayer) {
        this.lastPlayer = lastPlayer;
    }

    /**
     * Set the variable board in the room
     *
     * @param board board's reference
     */
    public void setBoard(Board board) {
        this.board = board;
    }

    /**
     * This method get the maoo of player as key and support function as value
     *
     * @return the map's reference
     */
    Map<Player, AllSupportFunctions> getPlayerAllSupportFunctionsMap() {
        return playerAllSupportFunctionsMap;
    }

    /**
     * This method call on each player on the method after match
     */
    public void afterGame() {
        for (PlayerHandler player : getListOfPlayers()) {
            if (player.isOn())
                player.afterMatch();
        }
    }

    /**
     * This method call on the server the method "addWinnersToTheFile"
     *
     * @param name winner's name as a string
     */
    void addWinnersToTheFile(String name) {
        server.addWinnersToTheFile(name);
    }

    /**
     * This method call on the server the method "generateRanking"
     *
     * @return the ranking as a list of PlayerFile.json object
     */
    public List<PlayerFile> generateRanking() {
        return getServer().generateRanking();
    }

    /**
     * Get the timer reference
     *
     * @return timer reference
     */
    Timer getTimer() {
        return timer;
    }

    /**
     * Set the timer variable
     *
     * @param timer Timer variable
     */
    void setTimer(Timer timer) {
        this.timer = timer;
    }

    public Map<String, PlayerHandler> getNicknamePlayersMap() {
        return nicknamePlayersMap;
    }
}
