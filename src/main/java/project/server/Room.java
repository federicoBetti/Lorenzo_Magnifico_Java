package project.server;

import project.configurations.TimerSettings;
import project.controller.Constants;
import project.controller.cardsfactory.LeaderCard;
import project.controller.effects.effectsfactory.BuildExcommunicationEffects;
import project.controller.supportfunctions.AllSupportFunctions;
import project.model.Board;
import project.model.Player;
import project.server.network.PlayerHandler;

import java.io.FileNotFoundException;
import java.util.*;


/**
 * TODO completare
 */
public class Room {

    private final Server server;
    /**
     * Stato della partita completo ad eccezione delle personalBoard che sono contenute nel player
     */

    private Board board;

    int maxPlayers;

    private Map<Player,AllSupportFunctions> playerAllSupportFunctionsMap;

    public Map< String, PlayerHandler> nicknamePlayersMap;

    private BuildExcommunicationEffects buildExcommunicationEffects;

    private GameActions gameActions;

    private boolean matchStarted;

    AllSupportFunctions allSupportFunctions;

    TimerSettings timerSettings;


     Room(Server server){
        playerAllSupportFunctionsMap = new HashMap<>();
        nicknamePlayersMap = new HashMap<>();
        buildExcommunicationEffects = new BuildExcommunicationEffects();
        matchStarted = false;
        gameActions = new GameActions(this);
        this.server = server;
        timerSettings = server.getTimerSettings();
    }

     boolean isFull() {
        int count = 0;
        for (Map.Entry<String, PlayerHandler> entry : nicknamePlayersMap.entrySet())
            if ( entry.getValue().isOn() )
                count++;

        if ( count == maxPlayers )
            return true;
        return false;
    }


    public Board getBoard() {
        return board;
    }

    public AllSupportFunctions getMySupportFunction (Player player){
        return playerAllSupportFunctionsMap.get(player);
    }

    public void setMySupportFunction(AllSupportFunctions allSupportFunctions, PlayerHandler player){
        playerAllSupportFunctionsMap.put(player,allSupportFunctions);
    }

    public int numberOfPlayerOn(){
        int count = 0;
        for (Map.Entry<String, PlayerHandler> entry : nicknamePlayersMap.entrySet())
            if ( entry.getValue().isOn() )
                count++;
        return count;
    }

    public boolean minimumNumberOfPlayers(){
        int count = 0;
        for (Map.Entry<String, PlayerHandler> entry : nicknamePlayersMap.entrySet())
            if ( entry.getValue().isOn() )
                count++;
        if( count >= 2 )
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

    public List<PlayerHandler> getListOfPlayers(){
        List<PlayerHandler> list = new ArrayList<>();
        for (Map.Entry<String, PlayerHandler> entry : nicknamePlayersMap.entrySet()) {
            list.add(entry.getValue());
        }
        return list;
    }

    public void startMatch() {
        String[] colors = fillColors();
        int i = 0;
        List<PlayerHandler> playerInTheMatch = new ArrayList<>();
        for (Map.Entry<String, PlayerHandler> player : nicknamePlayersMap.entrySet()) {
            if ( player.getValue().isOn() ){
                playerInTheMatch.add(player.getValue());
                player.getValue().setFamilyColour(colors[i]);
                player.getValue().setFamilyColourInFamilyMembers();
                i++;
            }
        }

        try {
            board = new Board(playerInTheMatch.size());

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            //todo gestire
        }

        Collections.shuffle(playerInTheMatch);
        board.getTurn().setPlayerTurn(playerInTheMatch);

        //todo aggiungere questa parte per il draft
        /*
        //draft
        List<List<LeaderCard>> listsForDraft = getListOfLeader();

        for (i = 0; i< Constants.LEADER_CARD_NUMBER_PER_PLAYER; i++){

            ListIterator<List<LeaderCard>> leaderIterator = listsForDraft.listIterator();
            ListIterator<PlayerHandler> playerIterator = playerInTheMatch.listIterator();

            while (leaderIterator.hasNext() && playerIterator.hasNext()) {
                PlayerHandler player = playerIterator.next();
                List<LeaderCard> leaders = leaderIterator.next();

                String leaderName = player.leaderCardChosen(leaders);
                LeaderCard leaderToAdd = getLeader(leaderName, leaders);

                player.getPersonalBoardReference().getMyLeaderCard().add(leaderToAdd);
            }
            listsForDraft = shiftLeaderList(listsForDraft);
        }
        */

        //inizia la partita
        for (PlayerHandler p: playerInTheMatch){
            p.matchStarted(getRoomPlayers(), p.getFamilyColour());
        }

        getBoard().getTurn().getPlayerTurn().get(0).itsMyTurn();
        matchStarted = true;
        myTimerSkipTurn(getBoard().getTurn().getPlayerTurn().get(0));
    }

    private LeaderCard getLeader(String leaderName, List<LeaderCard> leaders) {
        for (LeaderCard l: leaders){
            if (l.getName().equals(leaderName))
                return l;
        }
        return null;
    }

    private List<List<LeaderCard>> shiftLeaderList(List<List<LeaderCard>> listsForDraft) {
        List<LeaderCard> firstList = listsForDraft.get(0);
        listsForDraft.remove(0);
        listsForDraft.add(firstList);
        return listsForDraft;
    }

    private List<List<LeaderCard>> getListOfLeader() {
        List<List<LeaderCard>> listsForDraft = new ArrayList<>();
        List<LeaderCard> leaders = board.getDeckCard().getLeaderCardeck();
        Collections.shuffle(leaders);
        for (int i = 0; i<getRoomPlayers(); i++){
            List<LeaderCard> l = leaders.subList(5 * i,5*i + 5);
            listsForDraft.add(l);
        }
        return listsForDraft;
    }


    private String[] fillColors() {
        String [] colors = new String[4];
        colors[0] = Constants.GREEN;
        colors[1] = Constants.RED;
        colors[2] = Constants.YELLOW;
        colors[3] = Constants.BLUE;

        return colors;
    }

    public void myTimerSkipTurn( PlayerHandler player ) {

        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                player.timerTurnDelayed();
                gameActions.nextTurn( player );
            }
        };

        Timer timer = new Timer(timerSettings.getSkipTurnTimerName());
        timer.schedule(timerTask, timerSettings.getDelayTimerSkipTurn());
    }

    public boolean isMatchStarted() {
        return matchStarted;
    }
}
