package project.server.network.socket;

import project.PlayerFile;
import project.PrinterClass.UnixColoredPrinter;
import project.controller.cardsfactory.BuildingCard;
import project.controller.Constants;
import project.controller.cardsfactory.LeaderCard;
import project.messages.*;
import project.messages.updatesmessages.Updates;
import project.model.FamilyMember;
import project.model.Tile;
import project.server.network.PlayerHandler;
import project.server.network.exception.CanUseBothPaymentMethodException;
import project.server.network.exception.CantDoActionException;

import java.io.Closeable;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * This class is created when a connection with a socket client is performed. It receive the messages from the client and pass them
 * to "ServerDataHandler" class that elaborate the message and call a specific method here for acting the correct operations.
 */

public class SocketPlayerHandler extends PlayerHandler implements Runnable {

    private transient SocketServer socketServer;
    private transient Socket socket;
    private transient ObjectInputStream objectInputStream;
    private transient ObjectOutputStream objectOutputStream;
    private transient ServerDataHandler serverDataHandler;
    private transient final Object token;
    private transient final Object token1;
    private boolean firstConnection;

    public SocketPlayerHandler(SocketServer socketServer, Socket socket) throws IOException {
        super();
        firstConnection = true;
        token = new Object();
        token1 = new Object();
        this.setOn(true);
        this.socketServer = socketServer;
        this.socket = socket;
        this.objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
        this.objectOutputStream.flush();
        this.objectInputStream = new ObjectInputStream(socket.getInputStream());
        serverDataHandler = new ServerDataHandler(this, objectInputStream, objectOutputStream);

    }

    /**
     * for testing
     */
    public SocketPlayerHandler() {
        super();
        token = new Object();
        token1 = new Object();
    }

    /**
     * Thread lauched by "SocketServer" class that is always active for reading by and object input stream the client's messages
     */
    @Override
    public void run() {
        try {

            Object object = objectInputStream.readObject();
            serverDataHandler.handleRequest(object);

            if (firstConnection) {
                synchronized (token) {
                    try {
                        token.wait();
                    } catch (InterruptedException e) {
                        UnixColoredPrinter.Logger.print("Interrupt!");
                        Thread.currentThread().interrupt();
                    }
                }
            }

            while (true) {

                object = objectInputStream.readObject();
                serverDataHandler.handleRequest(object);
            }

        } catch (CantDoActionException e) {
            cantDoAction();
        } catch (CanUseBothPaymentMethodException e) {      //todo questa deve esserci?
            canUseBothPaymentMethod();
        } catch (IOException e) {   //todo queste due eccezioni qui
            broadcastDisconnessioneMessage(this);
            this.setOn(false);
        } catch (ClassNotFoundException e) {
            UnixColoredPrinter.Logger.print(Constants.CONNECTION_EXCEPTION);
        }
        closeSocket(objectInputStream);
        closeSocket(objectOutputStream);
        closeSocket(socket);
    }


    /**
     * This method pass the client's nickname to SocketServer class.
     * @param nickname nickname choosen by the client
     *
     * @throws IOException Signals that an I/O exception of some sort has occurred. This class is the general class of
     * exceptions produced by failed or interrupted I/O operations.
     *
     * @throws ClassNotFoundException Thrown when an application tries to load in a class through its string name using:
     *The forName method in class Class
     *The findSystemClass method in class ClassLoader .
     *The loadClass method in class ClassLoader.
     *but no definition for the class with the specified name could be found.
     */
    void loginRequestAnswer(String nickname) throws IOException {
        socketServer.loginRequest(nickname, this);
    }

    /**
     * Method called when the client wants to skip the turn.
     */
    void socketSkipTurn() {
        skipTurn();
    }

    /**
     * Method called when the client wants to take a development card the towers
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public void takeDevCard() throws IOException, ClassNotFoundException {

        String towerColour = (String) objectInputStream.readObject();
        int floor = (int) objectInputStream.readObject();
        String familyMemberColour = (String) objectInputStream.readObject();
        FamilyMember familyMember = findFamilyMember(familyMemberColour);

        try {
            clientTakeDevelopmentCard(towerColour, floor, familyMember);
        } catch (CantDoActionException e) {
            cantDoAction();
        }
    }


    /**
     * Method called when is taken a Venture card from the towers and this card have two  discretionary payments.
     * Another objectInputStream is opened because the
     * @throws IOException
     * @throws ClassNotFoundException

    void choosePaymentForVentureCard() throws IOException, ClassNotFoundException {
        int position = (int) objectInputStream.readObject();
        String familyMemberColour = (String) objectInputStream.readObject();
        int paymentChosen = (int) objectInputStream.readObject();
        FamilyMember familyMember = findFamilyMember(familyMemberColour);

        clientChosenPaymentForVenturesCard(position, familyMember, paymentChosen);
    }*/

    /**
     * Method called when the client wants to perform an harvetser action. It receives form the client the familiar colour
     * as a String and the number of servants that wants to use.
     *
     * @throws IOException
     * @throws ClassNotFoundException
     */
    void harvesterRequest() throws IOException, ClassNotFoundException {
        String familyMemberColour = (String) objectInputStream.readObject();
        int servantsNumber = (int) objectInputStream.readObject();
        FamilyMember familyMember = findFamilyMember(familyMemberColour);

        try {
            harvester(familyMember, servantsNumber);
        } catch (CantDoActionException e) {
            cantDoAction();
        }
    }

    //ok

    /**
     * Method called when the client wants to perform a production action. It receives from the client the familiar colour
     * as a String and the List of building cards on which the client wants to perform the production.
     *
     * @throws IOException
     * @throws ClassNotFoundException
     */
    void productionRequest() throws IOException, ClassNotFoundException {
        String familyMemberColour = (String) objectInputStream.readObject();
        FamilyMember familyMember = findFamilyMember(familyMemberColour);
        ArrayList<BuildingCard> cards = receiveListOfBuildingCard();

        try {
            production(familyMember, cards);
        } catch (CantDoActionException e) {
            cantDoAction();
        }

    }

    /**
     * Method called when a client wants to place a familiar in the market. It receives from the client the position
     * in the market on which he wants to place the familiar as an int and the familiar colour
     * as a String.
     *
     * @throws IOException
     * @throws ClassNotFoundException
     */
    void goToMarketRequest() throws IOException, ClassNotFoundException {
        int position = (int) objectInputStream.readObject();
        String familyMemberColour = (String) objectInputStream.readObject();
        FamilyMember familyMember = findFamilyMember(familyMemberColour);

        try {
            goToMarket(position, familyMember);
        } catch (CantDoActionException e) {
            cantDoAction();
        }
    }

    /**
     * Method called when the client wants to play a Leader card. it receives from the client the leader card's name as
     * a String.
     *
     * @throws IOException
     * @throws ClassNotFoundException
     */
    void playLeaderCardRequest() throws IOException, ClassNotFoundException {
        String leaderCardName = (String) objectInputStream.readObject();

        try {
            playLeaderCard(leaderCardName);
        } catch (CantDoActionException e) {
            cantDoAction();
        }
    }

    /**
     * Method called when the client wants to discard a Leader Card. It receives from the client the leader card's name
     * as a String.
     *
     * @throws IOException
     * @throws ClassNotFoundException
     */
    void discardLeaderCardRequest() throws IOException, ClassNotFoundException {
        String leaderCardName = (String) objectInputStream.readObject();

        try {
            discardLeaderCard(leaderCardName);
        } catch (CantDoActionException e) {
            cantDoAction();
        }
    }

    /**
     * Method called when the client wants to place a familiar in the Council palace zone. It receives from the client
     * the privilege number that wants to enjoy and the familiar colour as a String.
     *
     * @throws IOException
     * @throws ClassNotFoundException
     */
    void goToCouncilPalaceRequest() throws IOException, ClassNotFoundException {
        int privilegeNumber = (int) objectInputStream.readObject();
        String familyMemberColour = (String) objectInputStream.readObject();
        FamilyMember familyMember = findFamilyMember(familyMemberColour);
        try {
            goToCouncilPalace(privilegeNumber, familyMember);
        } catch (CantDoActionException e) {
            cantDoAction();
        }
    }

    /**
     *
     *
     * @throws IOException
     * @throws ClassNotFoundException

    void takePrivilegeRequest() throws IOException, ClassNotFoundException {
        int privilegeNumber = (int) objectInputStream.readObject();

        takePrivilege(privilegeNumber);
    } */


    /**
     * @return list of Building cards directly from the player's personal board.
     */
    private List<BuildingCard> takeMyProductionCards() {
        return getPersonalBoardReference().getBuildings();
    }

    /**
     * Method called for sending a general object identifying it sending before a specific string obtained by toString method
     *
     * @param returnFromEffect Object returned by a "doEffect" method of any effect performed.
     */

    public void sendAnswer(Object returnFromEffect) {
        if (isOn()) {
            try {
                objectOutputStream.writeObject(returnFromEffect.toString());

                objectOutputStream.writeObject(returnFromEffect);
                objectOutputStream.flush();
                objectOutputStream.reset();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Method used for sending to a client the String that comunicates that an action have been performed correctly.
     */
    @Override
    public void sendActionOk() {
        if (isOn()) {
            try {
                objectOutputStream.writeObject(Constants.OK_OR_NO);
                objectOutputStream.flush();
                objectOutputStream.reset();

            } catch (IOException e) {
                UnixColoredPrinter.Logger.print(Constants.CONNECTION_EXCEPTION);
            }
        }
    }

    /**
     * Method called when the time for the action is up. When this happens, all the players active receive a notification
     * that inform them that the player is off.
     */
    @Override
    public void timerTurnDelayed() {
        sendString(Constants.TIMER_TURN_DELAYED);
        broadcastDisconnessioneMessage(this);
    }

    /**
     * Send the notification to all players on that the previous player is disconnected.
     *
     * @param currentPlayer player that has been disconnected
     */
    //todo metterlo su
    private void broadcastDisconnessioneMessage(PlayerHandler currentPlayer ) {

        for (PlayerHandler player : getRoom().getBoard().getTurn().getPlayerTurn()) {
            if (player != currentPlayer && player.isOn()) {
                //da mettere nel player handòer
                //player.sendString(Constants.DISCONNECTION_MESSAGE);
                //player.sendString(currentPlayer.getName() + " is disconnected");
            }
        }
    }

    /**
     * Method called from the "Server" in the login method for sending to the client a string which informs the player
     * that the nickname choosen is already used by another client.
     */
    @Override
    public void nicknameAlredyUsed() {
        sendString(Constants.NICKNAME_USED);
        try {
            String newNickname = (String) objectInputStream.readObject();
            loginRequestAnswer(newNickname);
        } catch (IOException | ClassNotFoundException e) {
            UnixColoredPrinter.Logger.print(Constants.CONNECTION_EXCEPTION);
        }
    }

    /**
     * Method called from the "Server" in the login method for sending to the client a string which informs the player
     * that his login is succeded.
     */
    @Override
    public void loginSucceded() {
        sendString(Constants.LOGIN_SUCCEDED);
    }

    /**
     * Method called from the "Server" in the login method for sending to the client a string which informs the player
     * that he has to wait for his next turn.
     */
    @Override
    protected void waitForYourTurn() {
        sendString(Constants.WAITING_FOR_YOUR_TURN);
    }


    /**
     * Method called for informing the client about the "Leader Draft Time" in the match. This method send to the client
     * some leader cards in a row and later wait for a String that represent the Leader card's name choosen by the player.
     *
     * @param leaders list of leader cards in which the player can choose
     * @return the leader card's name choosen by the player or "-1" in case of client's disconnection
     */
    @Override
    public String leaderCardChosen(List<LeaderCard> leaders) {

        sendString(Constants.LEADER_DRAFT);

        try {
            objectOutputStream.writeObject(leaders.size());

            for (LeaderCard leaderCard : leaders) {

                objectOutputStream.writeObject(leaderCard);
                objectOutputStream.flush();
                objectOutputStream.reset();
            }

            String res = (String) objectInputStream.readObject();
            sendString(Constants.ACTION_DONE_ON_TIME);
            return res;
        } catch (IOException e) {
            setOn(false);
            return "-1";
        } catch (ClassNotFoundException e) {
            UnixColoredPrinter.Logger.print("Class not found");

        }
        return "-1";
    }


    /**
     * Method called for informing the client about the "Tile Draft Time" in the match. This method send to the client
     * some Tiles in a row and later wait for a String that represent the tile's number choosen by the player.
     *
     * @param tiles list of Bonus tiles
     * @return the tile's number choosen by the player or "-1" in case of disconnection
     */
    @Override
    public int chooseTile(List<Tile> tiles) {

        sendString(Constants.TILE_DRAFT);
        try {
            objectOutputStream.writeObject(tiles.size());

            for (Tile tile : tiles) {

                objectOutputStream.writeObject(tile);
                objectOutputStream.flush();
                objectOutputStream.reset();

            }

            int choice = (int) objectInputStream.readObject();
            sendString(Constants.ACTION_DONE_ON_TIME);
            return choice;
        } catch (IOException | ClassNotFoundException e) {
            setOn(false);
            return -1;
        }
    }

    /**
     * Set the "firstConnection" boolean to false. It's necessary because in the first connection, the client has to pass
     * in the draft period and it needs to lock the "usual" objectInputStream. Finished the drafts, the variable is set
     * to false and this make the socket connection works also for further reconnections.
     */
    @Override
    public void tokenNotify() {
        firstConnection = false;
    }

    /**
     * Method called for comunicating to the client that he prayed with success.
     */
    @Override
    public void prayed() {
        sendString(Constants.PRAYED);
    }

    /**
     * Method called for comunicating to the client that he has to go in the after game context.
     */
    @Override
    public void afterMatch() {
        sendString(Constants.AFTER_GAME);
    }


    /**
     * Method called when a match is finished and the client decides to play another match with his previous nickname.
     * It keeps the token in wait for stopping the objectInputStream in the Thread's "while true" for performing
     * correctly the Drafts actions.
     *
     * @param nickname client's nickname
     */
    void newGame(String nickname) {
        try {
            loginRequestAnswer(nickname);
            synchronized (token) {
                token.wait();
            }
        } catch (IOException | InterruptedException e) {
            UnixColoredPrinter.Logger.print(Constants.CONNECTION_EXCEPTION);
        }
    }
ClassNotFoundException
    /**
     * Method called for send to the client the ranking of all players that have played with the game
     * @param ranking list of players ordered for victories, defeats and number of matches played
     */
    @Override
    public void sendRanking(List<PlayerFile> ranking) {

        try {
            objectOutputStream.writeObject(Constants.SHOW_RANKING);
            objectOutputStream.writeObject((ArrayList)ranking);
            objectOutputStream.flush();
            objectOutputStream.reset();
        } catch (IOException e) {
            UnixColoredPrinter.Logger.print(Constants.CONNECTION_EXCEPTION);
        }
    }

    /**
     * Method called for sending to the client his statistics about all matches played with the game
     * @param playerFile
     */
    @Override
    protected void sendStatistic(PlayerFile playerFile) {
        sendString(Constants.RECEIVE_STATISTICS);
        try {
            objectOutputStream.writeObject(playerFile);
            objectOutputStream.flush();
            objectOutputStream.reset();
        } catch (IOException e) {
            UnixColoredPrinter.Logger.print(Constants.CONNECTION_EXCEPTION);

        }
    }

    /**
     * Method called for comunicating to the client that he has to go in the after game context in the case in which
     * all player are temporalrily off.
     */
    @Override
    public void afterGameIftemporarilyOff() {
            try {
                objectOutputStream.writeObject(Constants.AFTER_GAME);
                objectOutputStream.flush();
                objectOutputStream.reset();
            } catch (IOException e) {
                return;
            }
    }

    /**
     * Method called for comunicating to the winner that he has won the match
     * @param winnerString
     */
    @Override
    public void winnerComunication(String winnerString ) {
        sendString(Constants.WINNER_COMUNICATION);
        try {
            objectOutputStream.writeObject(winnerString);
            objectOutputStream.flush();
            objectOutputStream.reset();
        } catch (IOException e ) {
            return;
        }
    }

    /**
     * Method called for starting the match
     *
     * @param roomPlayers  number of player in the room
     * @param familyColour colour of the player
     */
    @Override
    public void matchStarted(int roomPlayers, String familyColour) {
        try {
            synchronized (token) {
                token.notifyAll();
            }
            if (isOn()) {
                setMatchStartedVar(true);

                objectOutputStream.writeObject(Constants.MATCH_STARTED);
                objectOutputStream.writeObject(roomPlayers);
                objectOutputStream.writeObject(familyColour);
                objectOutputStream.flush();
                objectOutputStream.reset();
            }
        } catch (IOException e) {
            setOn(false);
        }
    }


    /**
     * Method called for informing the client that he can't perfom an operation requested
     */
    @Override
    public void cantDoAction() {
        sendString(Constants.CANT_DO_ACTION);
    }

    /**
     * Method called when the client wants to take a Venture card and this card has two possible payments and the client
     * has enough resources pay the card in both ways.
     *
     * @return the number of the choice or "-1" if the client is disconnected
     */
    @Override
    public int canUseBothPaymentMethod() {
        sendString(Constants.BOTH_PAYMENT_METHODS_AVAILABLE);
        try {
            int choice = (int) objectInputStream.readObject();
            sendString(Constants.ACTION_DONE_ON_TIME);

            return choice;
        } catch (IOException | ClassNotFoundException e) {
            UnixColoredPrinter.Logger.print(Constants.CONNECTION_EXCEPTION);
        }
        return -1;
    }


    /**
     * Method called for informing the client that it's his turn.
     */
    @Override
    public void itsMyTurn() {
        sendString(Constants.YOUR_TURN);
    }

    /**
     * Method called informing the client that he has the possibility of praying. If the client is the last player
     * that played before the praying time, he has the Thread's "while true" stopped because he called the method in the
     * server for asking to all of praying, so he can open the objectInputStream, waiting for the answer and return;
     * else if the client isn't the last in the turn, the method send the string to the client and call a wait on a token,
     * the client receive the string "ASK_FOR_PRAYING" and send a string that, after being read by the Thread's while true,
     * notify this method's token and keep in wait the another token in the while true for making the methods receive the
     * answer in his objectInputStream.
     *
     * @return 0 if the client wants to pray, 1 if the client doesn't want it, -1 if the client has been disconnected
     */
    @Override
    public int sendAskForPraying() {

        while (true) {
            try {
                if (getRoom().getLastPlayer() == this) {
                    sendString(Constants.ASK_FOR_PRAYING_LAST_PLAYER);
                    int answer = (int) objectInputStream.readObject();
                    sendString(Constants.ACTION_DONE_ON_TIME);
                    return answer;
                }

                sendString(Constants.ASK_FOR_PRAYING);
                synchronized (token) {
                    token.wait();
                }

                int answer = (int) objectInputStream.readObject();
                sendString(Constants.ACTION_DONE_ON_TIME);

                synchronized (token1) {
                    token1.notifyAll();
                }

                return answer;

            } catch (IOException | ClassNotFoundException | InterruptedException e) {
                UnixColoredPrinter.Logger.print(Constants.CONNECTION_EXCEPTION);

            }
        }
    }

    /**
     * Method called for notifying the wait in "sendAskForPraying" method.
     */
    void waitForPraying() {

        setCallPray(true);
        synchronized (token) {
            token.notifyAll();
        }

        synchronized (token1) {
            try {
                token1.wait();
            } catch (InterruptedException e) {
                UnixColoredPrinter.Logger.print("Interrupted!");
                Thread.currentThread().interrupt();
            }
        }
    }

/*    @Override
    public int sendAskForPraying() {
        if (isOn()) {
            sendAnswer(Constants.ASK_FOR_PRAYING);
            int answer = -1;
            try {
                System.out.println("Ho mandato la richiesta di preghiere");
                answer = (int) objectInputStream.readObject();
                System.out.println(answer);
                return answer;
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

        }
        return -1;
    }   */


    /**
     * Method called for sending a generic string
     * @param message string to send
     */
    public void sendString(String message) {

        try {
            objectOutputStream.writeObject(message);
            objectOutputStream.flush();
            objectOutputStream.reset();
        } catch (IOException e) {
            UnixColoredPrinter.Logger.print("Connection error");
            setOn(false);
        }

    }


    /**
     * Method called for sending a generic update
     * @param updates
     */
    @Override
    public void sendUpdates(Updates updates) {
        if (isOn()) {
            sendAnswer(updates);
        }
    }


    /**
     * Method called when a building card has two alternative permanent effects
     * @return 0 for the first effect, 1 for the second, -1 if the client has been disconnected
     */
    @Override
    public int sendChoicePE() {
        String kindOfChoice = Constants.CHOICE_PE;

        try {
            objectOutputStream.writeObject(kindOfChoice);
            objectOutputStream.flush();
            objectOutputStream.reset();
            int choice = (int) objectInputStream.readObject();
            sendString(Constants.ACTION_DONE_ON_TIME);
            return choice;
        } catch (IOException | ClassNotFoundException e) {
            UnixColoredPrinter.Logger.print(Constants.CONNECTION_EXCEPTION);

        }
        return -1;
    }

    /**
     * Method called when a card taken from a Tower has a Bonus Tower Action as immediate effect
     * @param returnFromEffect the object with the Bonus Tower Action's characteristics
     */
    @Override
    public void sendBonusTowerAction(TowerAction returnFromEffect) {

        while (true) {
            sendAnswer(returnFromEffect);
            String answer = null;
            try {
                answer = (String) objectInputStream.readObject();
            } catch (IOException | ClassNotFoundException e) {
                UnixColoredPrinter.Logger.print(Constants.CONNECTION_EXCEPTION);

            }

            if (answer.equals(Constants.EXIT))
                return;

            try {
                takeBonusDevCard(answer, returnFromEffect);
                return;
            } catch (CantDoActionException c) {
                try {
                    objectOutputStream.writeObject(Constants.NOT_ENOUGH_RESOURCES);
                    objectOutputStream.flush();
                    objectOutputStream.reset();
                } catch (IOException e1) {
                    UnixColoredPrinter.Logger.print(Constants.CONNECTION_EXCEPTION);

                }
            }
        }

    }

    /**
     * Method called when a card taken from a Tower has a Bonus Harvester or Production as immediate effect.
     * @param returnFromEffect the object with the Bonus Production or Harvester's characteristics
     */
    @Override
    public void sendBonusProdOrHarv(BonusProductionOrHarvesterAction returnFromEffect) {
        while (true) {
            try {

                sendAnswer(returnFromEffect);
                String answer = (String) objectInputStream.readObject();
                int intServantsNumber;

                switch (answer) {
                    case Constants.EXIT:
                        return;

                    case Constants.BONUS_PRODUCTION:
                        ArrayList<BuildingCard> cards = receiveListOfBuildingCard();
                        doBonusProduct(returnFromEffect, cards);
                        return;

                    case Constants.BONUS_HARVESTER:
                        intServantsNumber = (int)objectInputStream.readObject();
                        doBonusHarv(returnFromEffect, intServantsNumber);
                        return;
                    default:
                        return;

                }

            } catch (IOException | ClassNotFoundException e) {
                UnixColoredPrinter.Logger.print(Constants.CONNECTION_EXCEPTION);
            } catch (CantDoActionException e) {
                try {
                    objectOutputStream.writeObject(Constants.NOT_ENOUGH_RESOURCES);
                    objectOutputStream.flush();
                    objectOutputStream.reset();
                } catch (IOException e1) {
                    UnixColoredPrinter.Logger.print(Constants.CONNECTION_EXCEPTION);

                }
            }
        }
    }

    /**
     * Method called when a card taken from a Tower has a bonus privilege as immediate effects
     * @param returnFromEffect the object with the Bonus privilege's characteristics
     */
    @Override
    public void sendRequestForPriviledges(TakePrivilegesAction returnFromEffect) {
        sendAnswer(returnFromEffect);
        takePriviledgesInArow(returnFromEffect);
    }


    /**
     * Method called for receiving privileges taking in a row due to an card's immediate effect and performing the
     * right effect.
     * @param returnFromEffect the object with the Bonus priviledge characteristics
     */
    private void takePriviledgesInArow(TakePrivilegesAction returnFromEffect) {

        try {
            String message = (String) objectInputStream.readObject();
            if (message.equals(Constants.ACTION_DONE_ON_TIME)) {

                for (int count = 0; count < returnFromEffect.getQuantityOfDifferentPrivileges(); count++) {
                    int privilegeNumber = 0;

                    privilegeNumber = (int) objectInputStream.readObject();
                    takePrivilege(privilegeNumber);
                }
            } else if (message.equals(Constants.EXIT))
                return;

        } catch (IOException | ClassNotFoundException e) {
            UnixColoredPrinter.Logger.print(Constants.CONNECTION_EXCEPTION);

        }
    }


    /**
     * Method called by "sendBonusTowerAction" for receiving the floor and acting all the resulting operations in the
     * server.
     *
     * @param towerColour card's tower colour that the client wants to take
     * @param returnFromEffect the object with the Bonus Tower Action characteristics
     * @throws CantDoActionException thrown when some parameters are uncorrected
     */
    private void takeBonusDevCard(String towerColour, TowerAction returnFromEffect) throws CantDoActionException {

        int floor = 0;
        try {
            floor = (int) objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            UnixColoredPrinter.Logger.print(Constants.CONNECTION_EXCEPTION);

        }
        clientTakeBonusDevelopementCard(towerColour, floor, returnFromEffect);

    }

    private ArrayList<BuildingCard> receiveListOfBuildingCard() {

        List<BuildingCard> myBuildings = takeMyProductionCards();

        ArrayList<BuildingCard> cardsForProduction = new ArrayList<>();
        try {
            int size = (int) objectInputStream.readObject();

            for (int i = 0; i < size; i++) {
                String cardName = (String) objectInputStream.readObject();
                for (BuildingCard card : myBuildings) {
                    if (card.getName().equals(cardName))
                        cardsForProduction.add(card);
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            UnixColoredPrinter.Logger.print(Constants.CONNECTION_EXCEPTION);

        }

        return cardsForProduction;
    }

    /**
     * For closing safely the socket
     * @param closeable  is a source or destination of data that can be closed. The close method is invoked to release
     *                  resources that the object is holding
     */
    private void closeSocket(Closeable closeable) {
        try {
            closeable.close();
        } catch (IOException e) {
            UnixColoredPrinter.Logger.print("Connection exception");
        }
    }


}
