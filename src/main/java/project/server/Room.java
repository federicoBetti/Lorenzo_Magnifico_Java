package project.server;

import com.google.gson.Gson;
import project.PlayerFile;
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
 * TODO completare
 */
public class Room {

    private final Server server;
    /**
     * Stato della partita completo ad eccezione delle personalBoard che sono contenute nel player
     */

    private Board board;

    private int maxPlayers;

    private Map<Player, AllSupportFunctions> playerAllSupportFunctionsMap;

    public Map<String, PlayerHandler> nicknamePlayersMap;

    private BuildExcommunicationEffects buildExcommunicationEffects;

    private GameActions gameActions;

    private boolean matchStarted;

    AllSupportFunctions allSupportFunctions;

    TimerSettings timerSettings;

    PlayerHandler lastPlayer;
    public boolean draftTime = false;


    public Room(Server server) {
        playerAllSupportFunctionsMap = new HashMap<>();
        nicknamePlayersMap = new HashMap<>();
        buildExcommunicationEffects = new BuildExcommunicationEffects();
        matchStarted = false;
        gameActions = new GameActions(this);
        this.server = server;
        timerSettings = server.getTimerSettings();
        maxPlayers = 4;
    }

    boolean isFull() {
        int count = 0;
        for (Map.Entry<String, PlayerHandler> entry : nicknamePlayersMap.entrySet())
            if (entry.getValue().isOn())
                count++;

        if (count == maxPlayers )
            return true;
        return false;
    }


    public Board getBoard() {
        return board;
    }

    public AllSupportFunctions getMySupportFunction(Player player) {
        return playerAllSupportFunctionsMap.get(player);
    }

    public void setMySupportFunction(AllSupportFunctions allSupportFunctions, PlayerHandler player) {
        playerAllSupportFunctionsMap.put(player, allSupportFunctions);
    }

    public int numberOfPlayerOn() {
        int count = 0;
        for (Map.Entry<String, PlayerHandler> entry : nicknamePlayersMap.entrySet())
            if (entry.getValue().isOn())
                count++;
        return count;
    }

    public boolean minimumNumberOfPlayers() {
        int count = 0;
        for (Map.Entry<String, PlayerHandler> entry : nicknamePlayersMap.entrySet())
            if (entry.getValue().isOn())
                count++;
        if (count >= 2)
            return true;
        return false;
    }

    public GameActions getGameActions() {
        return gameActions;
    }

    public BuildExcommunicationEffects getBuildExcommunicationEffects() {
        return buildExcommunicationEffects;
    }


    public int getRoomPlayers() {
        return nicknamePlayersMap.size();
    }

    public List<PlayerHandler> getListOfPlayers() {
        List<PlayerHandler> list = new ArrayList<>();
        for (Map.Entry<String, PlayerHandler> entry : nicknamePlayersMap.entrySet()) {
            list.add(entry.getValue());
        }
        return list;
    }

    public void startMatch() {
        draftTime = true;
        String[] colors = fillColors();
        int i = 0;
        List<PlayerHandler> playerInTheMatch = new ArrayList<>();
        for (Map.Entry<String, PlayerHandler> player : nicknamePlayersMap.entrySet()) {
            if (player.getValue().isOn()) {
                BasicSupportFunctions supportFunctions = new BasicSupportFunctions(player.getValue());
                playerAllSupportFunctionsMap.put(player.getValue(), supportFunctions);
                playerInTheMatch.add(player.getValue());
                player.getValue().setFamilyColour(colors[i]);
                player.getValue().setFamilyColourInFamilyMembers();
               // player.getValue().setDisconnectedInDraft(true);
                i++;
            }
        }

        maxPlayers = i;

        try {
            board = new Board(playerInTheMatch.size());

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            //todo gestire
        }
        gameActions.setBoard(board);

        Collections.shuffle(playerInTheMatch);
        fillExcommunicationTile();

        //todo mischia il mazzo, funge
        //board.getDeckCard().setDevelopmentDeck(shuffleDeck(board.getDeckCard().getDevelopmentDeck()));


        //todo aggiungere questa parte per il draft

        //draft leader

/*
        ArrayList<ArrayList<LeaderCard>> listsForDraft = getListOfLeader();

        for (i = 0; i < Constants.LEADER_CARD_NUMBER_PER_PLAYER; i++) {
            ListIterator<ArrayList<LeaderCard>> leaderIterator = listsForDraft.listIterator();
            ListIterator<PlayerHandler> playerIterator = playerInTheMatch.listIterator();
            String leaderName;

            while (leaderIterator.hasNext() && playerIterator.hasNext()) {
                PlayerHandler player = playerIterator.next();
                ArrayList<LeaderCard> leaders = leaderIterator.next();
                if ( player.isOn() ) {
                    Timer timer = gameActions.myTimerActions(player);
                    leaderName = player.leaderCardChosen(leaders);
                    timer.cancel();

                    if ( leaderName.equals("-1")){
                        System.out.println("SONO QUI");
                        player.getPersonalBoardReference().getMyLeaderCard().add(leaders.get(0));
                        leaders.remove(leaders.get(0));
                        continue;
                    }

                    System.out.println("DRAFT NORMALE");
                    LeaderCard leaderToAdd = getLeader(leaderName, leaders);
                    player.getPersonalBoardReference().getMyLeaderCard().add(leaderToAdd);
                    leaders.remove(leaderToAdd);
                }

                else {
                    player.getPersonalBoardReference().getMyLeaderCard().add(leaders.get(0));
                    leaders.remove(leaders.get(0));
                }
            }
            listsForDraft = shiftLeaderList(listsForDraft);
        }

        //draft tile

        ArrayList<Tile> tiles = fillListTile();
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
        } */
        //inizia la partita
        for (PlayerHandler p : getListOfPlayers()) {

                if ( p.disconnectedInDraft ) {
                    PlayerHandler oldPlayer = null;
                    p.setOn(true);
                    for ( PlayerHandler player : playerInTheMatch )
                        if ( p.getName().equals(player.getName()))
                            oldPlayer = player;
                    System.out.println("Old player : "  + oldPlayer);
                    server.loadPlayerState(this, p, oldPlayer);
                }

                p.matchStarted(getRoomPlayers(), p.getFamilyColour());
                System.out.println("mando MATCH STARTED");

        }

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        placeCardInTowers();

        int moreCoin = 0;
        for (PlayerHandler p : getListOfPlayers() ) {
            //setResources(p, moreCoin);
            if ( p.isOn() ) {
                int fauthPoint = 3;
                p.getScore().setMilitaryPoints(p.getScore().getMilitaryPoints() + 10);
                p.getScore().setFaithPoints(fauthPoint);
                p.sendUpdates(new PersonalBoardUpdate(p, p.getName()));
                p.sendUpdates(new TowersUpdate(board.getAllTowers(), p.getName()));
                p.sendUpdates(new MarketUpdate(board, p.getName()));
                p.sendUpdates(new ExcomunicationUpdate(board.getExcommunicationZone(),p.getName()));
                p.sendUpdates(new HarvesterUpdate(board.getHarvesterZone(), p.getName()));
                p.sendUpdates(new FamilyMemberUpdate(p, p.getName()));
                p.sendUpdates(new ScoreUpdate(p, p.getName()));
                //todo cancellare: aggiunto solo per provare il both payment
                System.out.println("mando UPDATES");
                moreCoin++;
            }
        }

        gameActions.rollDice();
        matchStarted = true;

        board.getTurn().setPlayerTurn(getListOfPlayers());
        gameActions.firstPlayerTurn();

    }

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

    private void fillExcommunicationTile() {
        ExcommunicationTile[][] deck = board.getDeckCard().getExcomunicationCard();
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

    private void setResources(PlayerHandler player, int moreCoin) {
        player.getPersonalBoardReference().setWood(2);
        player.getPersonalBoardReference().setStone(2);
        player.getPersonalBoardReference().setServants(3);
        player.getPersonalBoardReference().setCoins(5 + moreCoin);
    }

    private Tile getTrueTile(int tileId, ArrayList<Tile> tiles) {
        for (Tile t : tiles)
            if (t.getTileNumber() == tileId)
                return t;
        return null;
    }

    private ArrayList<Tile> fillListTile() {
        ArrayList<Tile> tiles = new ArrayList<>(4);
        for (Tile t : board.getDeckCard().getProdHaarvTiles())
            tiles.add(t);
        Collections.shuffle(tiles);
        return tiles;
    }

    private LeaderCard getLeader(String leaderName, ArrayList<LeaderCard> leaders) {
        for (LeaderCard l : leaders) {
            if (l.getName().equals(leaderName))
                return l;
        }
        return null;
    }

    private ArrayList<ArrayList<LeaderCard>> shiftLeaderList(ArrayList<ArrayList<LeaderCard>> listsForDraft) {
        ArrayList<LeaderCard> firstList = listsForDraft.get(0);
        listsForDraft.remove(0);
        listsForDraft.add(firstList);
        return listsForDraft;
    }

    private ArrayList<ArrayList<LeaderCard>> getListOfLeader() {
        int numberOfCard = Constants.LEADER_CARD_NUMBER_PER_PLAYER;
        ArrayList<ArrayList<LeaderCard>> listsForDraft = new ArrayList<>();
        ArrayList<LeaderCard> leaders = board.getDeckCard().getLeaderCardeck();
        Collections.shuffle(leaders);
        for (int i = 0; i < getRoomPlayers(); i++) {
            ArrayList<LeaderCard> ll = new ArrayList<>(leaders.subList(numberOfCard * i, numberOfCard * i + numberOfCard));
            listsForDraft.add(ll);
        }
        return listsForDraft;
    }


    private String[] fillColors() {
        String[] colors = new String[4];
        colors[0] = Constants.GREEN;
        colors[1] = Constants.RED;
        colors[2] = Constants.YELLOW;
        colors[3] = Constants.BLUE;

        return colors;
    }

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

    public Server getServer() {
        return server;
    }

    public boolean isMatchStarted() {
        return matchStarted;
    }

    public void broadcastMessage(String afterGame) {
        for (PlayerHandler player : getListOfPlayers()) {
            if (player.isOn())
                player.sendString(afterGame);
        }
    }

    public String takeStatistics(String nickname) {
        String currentFile;
        Gson gson = new Gson();

        try {
            currentFile = readFile(Constants.FILENAME, StandardCharsets.UTF_8);

            System.out.println("Il file in questo momento è: " + currentFile);
            System.out.println();

            PlayerFile[] arrayPlayers = gson.fromJson(currentFile, PlayerFile[].class); //lo trasformo in oggetto

            for (PlayerFile player : arrayPlayers)
                if (player.getPlayerName().equals(nickname)) {
                    String playerStat = gson.toJson(player);
                    return playerStat;
                }

        } catch (IOException e) {
            e.printStackTrace();
            e.printStackTrace();
        }
        return null;
    }

    private String readFile(String path, Charset encoding)
            throws IOException {
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return new String(encoded, encoding);
    }

    public PlayerHandler getLastPlayer() {
        return lastPlayer;
    }

    public void setLastPlayer(PlayerHandler lastPlayer) {
        this.lastPlayer = lastPlayer;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public Map<Player, AllSupportFunctions> getPlayerAllSupportFunctionsMap() {
        return playerAllSupportFunctionsMap;
    }

    public void setPlayerAllSupportFunctionsMap(Map<Player, AllSupportFunctions> playerAllSupportFunctionsMap) {
        this.playerAllSupportFunctionsMap = playerAllSupportFunctionsMap;
    }

    public void afterGame() {
        for ( PlayerHandler player : getListOfPlayers() ){
            if ( player.isOn() )
                player.afterMatch();
        }
    }

    public void addWinnersToTheFile(String name) {
        server.addWinnersToTheFile(name);
    }

    public List<PlayerFile> generateRanking() {
        return getServer().generateRanking();
    }
}
