package project.client.network.socket;

import project.PlayerFile;
import project.PrinterClass.UnixColoredPrinter;
import project.client.clientexceptions.ClientConnectionException;
import project.client.network.AbstractClient;
import project.client.ui.ClientSetter;
import project.controller.Constants;
import project.controller.cardsfactory.LeaderCard;
import project.messages.BonusProductionOrHarvesterAction;
import project.messages.TakePrivilegesAction;
import project.messages.TowerAction;
import project.messages.updatesmessages.*;
import project.model.Tile;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * This class communicates directly with the socket player handler class
 */
public class SocketClient extends AbstractClient {

    private String nickname;
    private transient  ClientSetter clientSetter;
    private transient  MessagesFromServerHandler messageHandler;
    private transient  Socket socket;
    private transient  ObjectOutputStream objectOutputStream;
    private transient  ObjectInputStream objectInputStream;
    private final transient Object token;
    private String interruptdeExceptionString = "program terminates during a queue .take() in the socket client";

    public SocketClient(ClientSetter clientSetter, String addressIP) throws ClientConnectionException {
        this.clientSetter = clientSetter;
        this.messageHandler = new MessagesFromServerHandler(this);
        token = new Object();
        try {
            socket = new Socket(addressIP, Constants.SOCKET_PORT);
            objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectInputStream = new ObjectInputStream(socket.getInputStream());
            goToLogin();
        } catch (IOException e) {
            UnixColoredPrinter.Logger.print(Constants.CONNECTION_EXCEPTION);
            throw new ClientConnectionException(e);
        }
    }

    /**
     * This method calls goToLogin on clientSetter
     */
    private void goToLogin() {
        clientSetter.goToLogin();
    }

    /**
     * This method is always reading the input stream for receiving string from the socket player handler and pass
     * them to the MessageFromServerHandler class
     */
    private void waitingForTheNewInteraction() {

        String message;

        try {
            while (true) {
                    message = (String) objectInputStream.readObject();
                    messageHandler.handleMessage(message);
            }
        } catch (IOException | ClassNotFoundException e) {
            UnixColoredPrinter.Logger.print(Constants.CONNECTION_ERROR );
            System.exit(0);
        }
    }

    /**
     * this method send the login request to the server
     * @param loginParameter player's nickname as a string
     */
    @Override
    public void loginRequest(String loginParameter) {
        sendGenericObject(Constants.LOGIN_REQUEST);
        sendGenericObject(loginParameter);
        setNickname(loginParameter);
        waitingForTheNewInteraction();
    }

    /**
     * This method sends to the server the parameters for the takes development card action
     *
     * @param towerColour tower colour as a string
     * @param floor number of the floor
     * @param familiarColour colour of the familiar that the player wants to play as a String
     */
    @Override
    public void takeDevCard(String towerColour, int floor, String familiarColour) {
        sendGenericObject(Constants.TAKE_DEV_CARD);
        send3Parameters(towerColour, floor, familiarColour);
    }


    /**
     * this method calls actionOk on the clientSetter
     */
    public void actionOk() {
        clientSetter.actionOk();
    }

    /**
     * This method sends the parameters for performing the market action
     *
     * @param position int that represents the position in the market
     * @param familyColour colour of the familiar that the player wants to place
     */
    @Override
    public void marketAction(int position, String familyColour) {
        sendGenericObject(Constants.GO_TO_MARKET);
        send2Parameters(position, familyColour);
    }

    /**
     * This method sends the parameters for performing the council action
     *
     * @param priviledgeNumber number of the privilege choosen by the player
     * @param familyColour colour of the familiar that the player wants to place
     */
    @Override
    public void councilAction(int priviledgeNumber, String familyColour) {
        sendGenericObject(Constants.GO_TO_COUNCIL_PALACE);
        send2Parameters(priviledgeNumber, familyColour);
    }

    /**
     * This method sends the parameters for performing the production action
     *
     * @param familiarColor colour of the familiar that the player wants to place
     * @param buildingCards list of buildings cards on which the player wants to perform the production
     */
    @Override
    public void productionAction(String familiarColor, List<String> buildingCards) {
        sendGenericObject(Constants.PRODUCTION);
        sendGenericObject(familiarColor);
        sendAllStrings(buildingCards);
    }

    /**
     * This method sends the parameters for performing the immediatePriviledgeAction
     *
     * @param privileges list of privileges that the player wants to play
     */
    @Override
    public void immediatePriviledgeAction(List<Integer> privileges) {
        sendGenericObject(Constants.ACTION_DONE_ON_TIME);
        sendAllIntegers(privileges);
        itsMyTurn();
    }

    /**
     * This method sends the parameters for taking the leader card
     *
     * @param name leader card's name that the player wants to play
     */
    @Override
    public void playLeaderCard(String name) {

        sendGenericObject(Constants.PLAY_LEADER_CARD);
        sendGenericObject(name);
    }

    /**
     * This method sends the parameters for discarding the leader card
     *
     * @param name leader card's name that the player wants to discard
     */
    @Override
    public void discardLeaderCard(String name) {
        sendGenericObject(Constants.DISCARD_LEADER_CARD);
        sendGenericObject(name);
    }

    /**
     * This method starts a Thread that reads if the time for the turn is up and call on the client setter the
     * askForPraying method. This method return an int that represents the choice of praying or not and sends the
     * answer to the server. This method is used only for the last player's prayer
     */
    void askForPrayingLastPlayer() {
        new TimerReader().start();
        int answer = clientSetter.askForPraying();
        sendGenericObject(answer);

        tokenWait();
    }

    /**
     * This method sends to the server the constants RECONNECT for reconnecting the client to the match
     */
    @Override
    public void reconnect() {
        try {
            objectOutputStream.writeObject(Constants.RECONNECT);
            objectOutputStream.flush();
            objectOutputStream.reset();
        } catch (IOException e) {
            UnixColoredPrinter.Logger.print(Constants.CONNECTION_ERROR );
        }
        createWaitingForYourTurnContext();
    }

    /**
     * This method calls afterGame on the clientSetter
     */
    public void afterGame() {
        clientSetter.afterGame();
    }

    /**
     * This method send the statistic request to the server
     */
    @Override
    public void showStatistic() {
        sendGenericObject(Constants.SHOW_STATISTICS);
    }

    /**
     * This method starts a Thread that reads if the time for the turn is up and call on the client setter the
     * askForPraying method. This method return an int that represents the choice of praying or not and sends the
     * answer to the server. This method is used for all the prayer's requests except the last player's prayer
     */
    public void askForPraying() {
        new TimerReader().start();
        sendGenericObject(Constants.PRAYING_REQUEST_RECEIVED);
        //thread che ascolta il timer

        int answer = clientSetter.askForPraying();
        sendGenericObject(answer);

        tokenWait();

    }

    /**
     * This method calls itsMyTurn on the clientSetter
     */
    void itsMyTurn() {
        clientSetter.itsMyTurn();
    }

    /**
     * This method send to the server the choice of does not act the bonus action
     */
    @Override
    public void sendExitToBonusAction() {
        try {
            objectOutputStream.writeObject(Constants.EXIT);
            objectOutputStream.flush();
            objectOutputStream.reset();
        } catch (IOException e) {
            UnixColoredPrinter.Logger.print(Constants.CONNECTION_EXCEPTION);
            System.exit(1);
        }
    }

    /**
     * This method sends to the server the parameters for taking the bonus card
     *
     * @param floor the floor's number of the tower
     * @param towerColour tower colour as a String
     */
    public void takeBonusCardAction(int floor, String towerColour) {
        try {
            objectOutputStream.writeObject(towerColour);
            objectOutputStream.writeObject(floor);
            objectOutputStream.flush();
            objectOutputStream.reset();
        } catch (IOException e) {
            UnixColoredPrinter.Logger.print(Constants.CONNECTION_EXCEPTION);
            System.exit(1);
        }
    }

    /**
     * This method sends to the server the parameters for performing an harvester action
     *
     * @param familyColour familiar colour choosen as a String
     * @param servantsNumber number of servants used for the harvester
     */
    @Override
    public void harvesterAction(String familyColour, int servantsNumber) {
        sendGenericObject(Constants.HARVESTER);
        send2Parameters(familyColour, servantsNumber);
    }

    /**
     * This method receive the object that contains the characteristics about the bonus harvester action that
     * the player has to perform and calls bonusHarvester on the clientSetter
     */
    void bonusHarvester() {
        BonusProductionOrHarvesterAction bonusHarv = null;
        try {
            bonusHarv = (BonusProductionOrHarvesterAction) objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            UnixColoredPrinter.Logger.print(Constants.CONNECTION_EXCEPTION);
            System.exit(1);
        }
        clientSetter.bonusHarvester(bonusHarv);
    }

    /**
     * This method receive the object that contains the characteristics about the bonus privilege/es that
     * the player has to take and calls takeImmediatePrivilege on the clientSetter
     */

    void takeImmediatePrivilege() {
        try {
            TakePrivilegesAction privilegesAction = (TakePrivilegesAction) objectInputStream.readObject();
            clientSetter.takeImmediatePrivilege(privilegesAction);


        } catch (IOException | ClassNotFoundException e) {
            UnixColoredPrinter.Logger.print(Constants.CONNECTION_EXCEPTION);
            System.exit(1);
        }

    }

    /**
     * This method sends all the parameters necesary for acting a bonus harvester
     *
     * @param servantsNumber number of servants
     */
    public void bonusHarvesterAction(int servantsNumber) {
        sendGenericObject(Constants.BONUS_HARVESTER);
        sendGenericObject(servantsNumber);
    }

    /**
     * This method receive the object that contains the charateristics of the bonus production to do and calls the
     * method bonusProduction on the clientSetter
     */
    void bonusProduction() {
        BonusProductionOrHarvesterAction bonusProd = null;
        try {
            bonusProd = (BonusProductionOrHarvesterAction) objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            UnixColoredPrinter.Logger.print(Constants.CONNECTION_EXCEPTION);
            System.exit(1);
        }
        clientSetter.bonusProduction(bonusProd);
    }

    /**
     * This method sends to the server all the parameters for performing the bonus production action
     *
     * @param parameters
     */
    public void bonusProductionAction(List<String> parameters) {
        sendGenericObject(Constants.BONUS_PRODUCTION);
        sendAllStrings(parameters);
    }

    /**
     * This method receive the object that contains the charateristics of the bonus tower action to do and calls the
     * method takeBonusCard on the clientSetter
     */
    void takeBonusCard() {
        TowerAction towerAction = null;
        try {
            towerAction = (TowerAction) objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            UnixColoredPrinter.Logger.print(Constants.CONNECTION_EXCEPTION);
            System.exit(1);
        }
        clientSetter.takeBonusCard(towerAction);
    }


    /**
     * This method starts a Thread that reads if the time for the turn is up and calls on the client setter the
     * choicePe method. This method return an int that represents the choice of permanent effect and sends the
     * answer to the server.
     */
    public void choicePe() {
        new TimerReader().start();
        int peChoosen = clientSetter.choicePe();
        sendGenericObject(peChoosen);

        tokenWait();

    }

    /**
     * method used to wait on a token
     */
    private void tokenWait() {
        while (true) {
            synchronized (token) {
                try {
                    token.wait();
                } catch (InterruptedException e) {
                    UnixColoredPrinter.Logger.print(interruptdeExceptionString);
                    Thread.currentThread().interrupt();
                }
            }
            break;
        }

    }

    /**
     * This method starts a Thread that reads if the time for the turn is up and calls on the client setter the
     * bothPaymentsAvailable method. This method return an int that represents the choice of payment and sends the
     * answer to the server.
     */
    void bothPaymentsAvailable() {
        new TimerReader().start();
        int costChoice = clientSetter.bothPaymentsAvailable();

        sendGenericObject(costChoice);

        tokenWait();

    }

    /**
     * This method receive the list of leader cards for the draft. After it starts a Thread that reads if the time for
     * the turn is up and calls on the client setter the getLeaderCardChosen method. This method return an int that
     * represents the leader card choosen and sends the answer to the server.
     */
    void leaderDraft() {
        List<LeaderCard> leaders = new ArrayList<>();

        try {
            int size = (int) objectInputStream.readObject();

            for (int i = 0; i < size; i++) {
                LeaderCard leaderCard = (LeaderCard) objectInputStream.readObject();
                leaders.add(leaderCard);
            }

            new TimerReader().start();
            String leaderCardChoosen = clientSetter.getLeaderCardChosen(leaders);
            objectOutputStream.writeObject(leaderCardChoosen);

            tokenWait();

        } catch (IOException | ClassNotFoundException e) {
            UnixColoredPrinter.Logger.print(Constants.CONNECTION_EXCEPTION);
            System.exit(1);
        }
    }

    /**
     * This method receive a message about the disconnession of another player and calls on the clientSetter the
     * method disconnectionMessage
     */
    void disconnessionMesaage() {
        try {
            String message = (String)objectInputStream.readObject();
            clientSetter.disconnessionMessage(message);
        } catch (IOException | ClassNotFoundException e) {
            UnixColoredPrinter.Logger.print(Constants.CONNECTION_EXCEPTION);
            System.exit(1);
        }
    }

    /**
     * This private class is a Thread that is called for waiting for a timer message if it expires while the player
     * is choosing his action. If it happens, this thread calls the method timerTurnDelayed
     */
    private class TimerReader extends Thread {

        @Override
        public void run() {
            try {

                String message = (String) objectInputStream.readObject();

                if (message.equals(Constants.TIMER_TURN_DELAYED)) {
                    timerTurnDelayed();

                    message = (String) objectInputStream.readObject();
                    while (!message.equals(Constants.ACTION_DONE_ON_TIME)) {
                        message = (String) objectInputStream.readObject();
                    }

                    synchronized (token) {
                        token.notifyAll();
                    }

                } else if (message.equals(Constants.ACTION_DONE_ON_TIME)) {

                    synchronized (token) {
                        token.notifyAll();
                    }
                }


            } catch (IOException | ClassNotFoundException e) {
                UnixColoredPrinter.Logger.print(Constants.CONNECTION_EXCEPTION);
            System.exit(1);
            }
        }

    }


    /**
     * This method receive a scoreUpdate object and calls the method scoreUpdate on the clientSetter
     */
    void scoreUpdate() {
        Updates update = null;
        try {
            update = (ScoreUpdate) objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            UnixColoredPrinter.Logger.print(Constants.CONNECTION_EXCEPTION);
            System.exit(1);
        }
        clientSetter.scoreUpdate(update);
    }

    /**
     * This method receive a personaBoardUpdate object and calls the method personalBoardUpdate on the clientSetter
     */
    void personalBoardUpdate() {
        Updates update = null;
        try {
            update = (PersonalBoardUpdate) objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            UnixColoredPrinter.Logger.print(Constants.CONNECTION_EXCEPTION);
            System.exit(1);
        }
        clientSetter.personalBoardUpdate(update);
    }

    /**
     * This method receive a FamilyMemberUpdate object and calls the method familyMemberUpdate on the clientSetter
     */
    void familyMemberUpdate() {
        Updates update = null;
        try {
            update = (FamilyMemberUpdate) objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            UnixColoredPrinter.Logger.print(Constants.CONNECTION_EXCEPTION);
            System.exit(1);
        }
        clientSetter.familyMemberUpdate(update);
    }

    /**
     * This method comunicates to the server that the player wants to skip the turn
     */
    public void skipTurn() {
        sendGenericObject(Constants.SKIP_TURN);
    }

    /**
     * This method calls the method timerTurnDelayed on the clientSetter
     */
    void timerTurnDelayed() {
        clientSetter.timerTurnDelayed();
    }

    /**
     * This method receive a boardUpdate object and calls the method boardUpdate on the clientSetter
     */
    void boardUpdate() {
        Updates update = null;
        try {
            update = (Updates) objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            UnixColoredPrinter.Logger.print(Constants.CONNECTION_EXCEPTION);
            System.exit(1);
        }
        clientSetter.boardUpdate(update);
    }

    /**
     * This method receive the number of players in the match and the player's family colour as a string and calls
     * the method matchStarted on the clientSetter
     */
    public void matchStarted() {
        int roomPlayersNumber;
        try {
            roomPlayersNumber = (int) objectInputStream.readObject();
            String playerFamilyColour = (String) objectInputStream.readObject();
            clientSetter.matchStarted(roomPlayersNumber, playerFamilyColour);

        } catch (IOException | ClassNotFoundException e) {
            UnixColoredPrinter.Logger.print(Constants.CONNECTION_EXCEPTION);
            System.exit(1);
        }
    }

    /**
     * This method calls the method cantDoAction on the clietSetter
     */
    public void cantDoAction() {
        clientSetter.cantDoAction();
    }

    /**
     * This method send the player's nickname to the server
     *
     * @param nickname nickname as a String
     */
    @Override
    public void newNickname(String nickname) {
        sendGenericObject(nickname);
    }

    /**
     * This method receive an PlayerFile.json object containing the player's statistics and calls receiveStatistics on
     * the clientSetter
     */
    void receiveStatistics() {
        try {
            PlayerFile statistics = (PlayerFile) objectInputStream.readObject();
            clientSetter.receiveStatistics(statistics);
        } catch (IOException | ClassNotFoundException e) {
            UnixColoredPrinter.Logger.print(Constants.CONNECTION_EXCEPTION);
            System.exit(1);
        }
    }

    /**
     * This methods sends the ranking's request to the server
     */
    @Override
    public void showRanking() {
        sendGenericObject(Constants.SHOW_RANKING);
    }

    /**
     * This method receive the winner's name and calls winnerComunication on the clientSetter
     */
    public void winnerComunication() {
        try {
            String winner = (String )objectInputStream.readObject();
            clientSetter.winnerComunication(winner);
        } catch (IOException | ClassNotFoundException e) {
            UnixColoredPrinter.Logger.print(Constants.CONNECTION_EXCEPTION);
            System.exit(1);
        }

    }

    /**
     * This method receives the list of players ordered as a ranking and calls the method ranking on the clientSetter
     */
    public void ranking() {
        try {
            List<PlayerFile> ranking = (List<PlayerFile>)objectInputStream.readObject();
            clientSetter.ranking(ranking);
        } catch (IOException | ClassNotFoundException e) {
            UnixColoredPrinter.Logger.print(Constants.CONNECTION_EXCEPTION);
            System.exit(1);
        }
    }

    /**
     * This method sends to the server the player's nickname with the request of playing anothe match
     *
     * @param nickname player's nickname
     */
    @Override
    public void newGameRequest(String nickname) {
        sendGenericObject(Constants.NEW_GAME);
        sendGenericObject(nickname);
    }

    /**
     * This method calls the method prayed on the clientSetter
     */
    public void prayed() {
        clientSetter.prayed();
    }

    /**
     * This method receive the object that contains the excommunicatio characteristics and calls excommunicationTake
     * on the clientSetter
     */
    void excommunicationTake() {
        try {
            Updates update = (Updates) objectInputStream.readObject();
            clientSetter.excommunicationTake(update);
        } catch (IOException | ClassNotFoundException e) {
            UnixColoredPrinter.Logger.print(Constants.CONNECTION_EXCEPTION);
            System.exit(1);
        }

    }

    /**
     * This method calls waitingForYourTurn on the clientSetter
     */
    void createWaitingForYourTurnContext() {
        clientSetter.waitingForYourTurn();
    }

    /**
     * This method sends 2 generica parameters
     *
     * @param parameter1 first object
     * @param parameter2 second object
     */
    private void send2Parameters(Object parameter1, Object parameter2) {
        try {
            objectOutputStream.writeObject(parameter1);
            objectOutputStream.flush();
            objectOutputStream.reset();

            objectOutputStream.writeObject(parameter2);
            objectOutputStream.flush();
            objectOutputStream.reset();
        } catch (IOException e) {
            UnixColoredPrinter.Logger.print(Constants.CONNECTION_EXCEPTION);
            System.exit(1);
        }
    }

    /**
     * This method sends 3 generica parameters
     *
     * @param parameter1 first object
     * @param parameter2 second object
     * @param parameter3 third object
     */
    private void send3Parameters(Object parameter1, Object parameter2, Object parameter3) {

        try {
            objectOutputStream.writeObject(parameter1);
            objectOutputStream.flush();
            objectOutputStream.reset();

            objectOutputStream.writeObject(parameter2);
            objectOutputStream.flush();
            objectOutputStream.reset();

            objectOutputStream.writeObject(parameter3);
            objectOutputStream.flush();
            objectOutputStream.reset();

        } catch (IOException e) {
            UnixColoredPrinter.Logger.print(Constants.CONNECTION_EXCEPTION);
            System.exit(1);
        }
    }

    /**
     * This method sends a list of strings
     *
     * @param parameters list of strings
     */
    private void sendAllStrings(List<String> parameters) {
        sendGenericObject(parameters.size());
        for (String elem : parameters) {

            try {
                objectOutputStream.writeObject(elem);
                objectOutputStream.flush();
                objectOutputStream.reset();
            } catch (IOException e) {
                UnixColoredPrinter.Logger.print(Constants.CONNECTION_EXCEPTION);
            System.exit(1);
            }
        }
    }

    /**
     * This method sends a list of integer
     *
     * @param parameters list of integer
     */
    private void sendAllIntegers(List<Integer> parameters) {

        for (Integer elem : parameters) {
            try {
                objectOutputStream.writeObject(elem);
                objectOutputStream.flush();
                objectOutputStream.reset();
            } catch (IOException e) {
                UnixColoredPrinter.Logger.print(Constants.CONNECTION_EXCEPTION);
            System.exit(1);
            }
        }
    }

    /**
     * This method send a generic object
     *
     * @param kindOfRequest generic object
     */
    private void sendGenericObject(Object kindOfRequest) {

        try {
            objectOutputStream.writeObject(kindOfRequest);
            objectOutputStream.flush();
            objectOutputStream.reset();
        } catch (IOException e) {
            UnixColoredPrinter.Logger.print(Constants.CONNECTION_EXCEPTION);
            System.exit(1);
        }
    }


    /**
     * This method calls nicknameAlreadyUsed on the clientSetter
     */
    public void nicknameAlreadyUsed() {
        clientSetter.nicknameAlreadyUsed();
    }

    /**
     * This method calls loginSucceded on the clientSetter
     */
    public void loginSucceded() {
        clientSetter.loginSucceded();
    }

    /*
     * This method receive the list of bonus tiles for the draft. After it starts a Thread that reads if the time for
     * the turn is up and calls on the client setter the tileDraft method. This method return an int that
     * represents the bonus tile choosen and sends the answer to the server.
     *
     * @throws IOException
     * @throws ClassNotFoundException
     */
    void tileDraft() throws IOException, ClassNotFoundException {
        List<Tile> tiles = new ArrayList<>();

        int size = (int) objectInputStream.readObject();

        try {
            for (int i = 0; i < size; i++) {

                Tile tile = (Tile) objectInputStream.readObject();
                tiles.add(tile);

            }
            new TimerReader().start();
            int choice = clientSetter.tileDraft(tiles);
            objectOutputStream.writeObject(choice);
            objectOutputStream.flush();
            objectOutputStream.reset();

            tokenWait();

        } catch (IOException | ClassNotFoundException e) {
            UnixColoredPrinter.Logger.print(Constants.CONNECTION_EXCEPTION);
            System.exit(1);
        }
    }

    /**
     * This method gets the nickname
     *
     * @return nickname as a String
     */
    public String getNickname() {
        return nickname;
    }

    /**
     * This method set the nickname
     *
     * @param nickname as a String
     */
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}


