package project.client.network.socket;

import project.PlayerFile;
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
 * This class communicate directly with the socket player handler class
 */
public class SocketClient extends AbstractClient {

    private String nickname;
    private ClientSetter clientSetter;
    private MessagesFromServerHandler messageHandler;
    private Socket socket;
    private ObjectOutputStream objectOutputStream;
    private ObjectInputStream objectInputStream;
    Object token;
    Object token1;

    public SocketClient(ClientSetter clientSetter, String IP) throws ClientConnectionException {
        this.clientSetter = clientSetter;
        this.messageHandler = new MessagesFromServerHandler(this);
        token = new Object();
        token1 = new Object();
        try {
            socket = new Socket(IP, Constants.SOCKET_PORT);
            objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectInputStream = new ObjectInputStream(socket.getInputStream());
            goToLogin();
        } catch (IOException e) {
            e.printStackTrace();
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
        while (true) {
            try {

                System.out.println("SONO NEL WHIOLE TRUE");
                message = (String) objectInputStream.readObject();
                System.out.println("IL MESSAGE é: " + message);
                messageHandler.handleMessage(message);

            } catch (IOException | ClassNotFoundException e) {
                System.exit(1);
            }
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
        try {

            synchronized (token) {
                token.wait();
            }
            System.out.println("il res è stato mandato");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
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
            e.printStackTrace();
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
        try {

            synchronized (token) {
                token.wait();
            }
            System.out.println("il res è stato mandato");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("il res viene mandato: " + answer);
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
            e.printStackTrace();
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
            e.printStackTrace();
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
            e.printStackTrace();
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

            System.out.println("il res è stato mandato");

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
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
     *
     */
    void bonusProduction() {
        BonusProductionOrHarvesterAction bonusProd = null;
        try {
            bonusProd = (BonusProductionOrHarvesterAction) objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        clientSetter.bonusProduction(bonusProd);
    }

    public void bonusProductionAction(List<String> parameters) {
        sendGenericObject(Constants.BONUS_PRODUCTION);
        sendAllStrings(parameters);
    }

    void takeBonusCard() {
        TowerAction towerAction = null;
        try {
            towerAction = (TowerAction) objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        clientSetter.takeBonusCard(towerAction);
    }


    public void choicePe() {
        new TimerReader().start();
        int peChoosen = clientSetter.choicePe();
        sendGenericObject(peChoosen);

        synchronized (token) {
            try {
                token.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    void bothPaymentsAvailable() {
        new TimerReader().start();
        int costChoice = clientSetter.bothPaymentsAvailable();


        System.out.println("mandato! e vado in wait");

        sendGenericObject(costChoice);

        synchronized (token) {
            try {
                token.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Svegliato e vado in wait");
    }

    public void leaderDraft() {
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

            synchronized (token) {
                token.wait();
            }

        } catch (IOException | ClassNotFoundException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void disconnessionMesaage() {
        try {
            String message = (String)objectInputStream.readObject();
            clientSetter.disconnessionMessage(message);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private class TimerReader extends Thread {

        @Override
        public void run() {
            try {

                String message = (String) objectInputStream.readObject();

                if (message.equals(Constants.TIMER_TURN_DELAYED)) {
                    timerTurnDelayed();

                    System.out.println("Dormo...");

                    System.out.println("mi hanno svegliato e aspetto di ricevere");

                    message = (String) objectInputStream.readObject();
                    while (!message.equals(Constants.ACTION_DONE_ON_TIME)) {
                        message = (String) objectInputStream.readObject();
                        System.out.println("ricevuto:" + message);
                    }

                    synchronized (token) {
                        token.notify();
                    }

                    System.out.println("NOTIFY");
                } else if (message.equals(Constants.ACTION_DONE_ON_TIME)) { //era solo if

                    synchronized (token) {
                        token.notify();
                    }
                }


            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

    }


    //updates

    public void scoreUpdate() {
        Updates update = null;
        try {
            update = (ScoreUpdate) objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        clientSetter.scoreUpdate(update);
    }

    public void personalBoardUpdate() {
        Updates update = null;
        try {
            update = (PersonalBoardUpdate) objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        clientSetter.personalBoardUpdate(update);
    }

    public void familyMemberUpdate() {
        Updates update = null;
        try {
            update = (FamilyMemberUpdate) objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        clientSetter.familyMemberUpdate(update);
    }


    public void skipTurn() {
        sendGenericObject(Constants.SKIP_TURN);
    }


    public void timerTurnDelayed() {
        clientSetter.timerTurnDelayed();
    }


    public void boardUpdate() {
        Updates update = null;
        try {
            update = (Updates) objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        clientSetter.boardUpdate(update);
    }


    public void matchStarted() {
        int roomPlayersNumber = 0;
        try {
            roomPlayersNumber = (int) objectInputStream.readObject();
            String playerFamilyColour = (String) objectInputStream.readObject();
            clientSetter.matchStarted(roomPlayersNumber, playerFamilyColour);

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    public void cantDoAction() {
        clientSetter.cantDoAction();
    }


    @Override
    public void newNickname(String nickname) {
        sendGenericObject(nickname);
    }


    public void terminate() {
    }


    void receiveStatistics() {
        try {
            PlayerFile statistics = (PlayerFile) objectInputStream.readObject();
            clientSetter.receiveStatistics(statistics);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void showRanking() {
        sendGenericObject(Constants.SHOW_RANKING);
    }

    @Override
    public void winnerComunication() {
        try {
            String winner = (String )objectInputStream.readObject();
            clientSetter.winnerComunication(winner);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }


    public void ranking() {
        try {
            List<PlayerFile> ranking = (List<PlayerFile>)objectInputStream.readObject();
            clientSetter.ranking(ranking);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void newGameRequest(String nickname) {
        sendGenericObject(Constants.NEW_GAME);
        sendGenericObject(nickname);
    }

    public void prayed() {
        clientSetter.prayed();
    }

    public void excommunicationTake() {
        try {
            Updates update = (Updates) objectInputStream.readObject();
            clientSetter.excommunicationTake(update);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }


    public void createWaitingForYourTurnContext() {
        clientSetter.waitingForYourTurn();
    }

    //sending methods
    private void send2Parameters(Object parameter1, Object parameter2) {
        try {
            objectOutputStream.writeObject(parameter1);
            objectOutputStream.flush();
            objectOutputStream.reset();

            objectOutputStream.writeObject(parameter2);
            objectOutputStream.flush();
            objectOutputStream.reset();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

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
            e.printStackTrace();
        }
    }

    private void sendAllStrings(List<String> parameters) {
        sendGenericObject(parameters.size());
        for (String elem : parameters) {

            try {
                objectOutputStream.writeObject(elem);
                objectOutputStream.flush();
                objectOutputStream.reset();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void sendAllIntegers(List<Integer> parameters) {

        for (Integer elem : parameters) {
            try {
                objectOutputStream.writeObject(elem);
                objectOutputStream.flush();
                objectOutputStream.reset();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void sendGenericObject(Object kindOfRequest) {

        try {
            objectOutputStream.writeObject(kindOfRequest);
            objectOutputStream.flush();
            objectOutputStream.reset();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void nicknameAlreadyUsed() {
        clientSetter.nicknameAlreadyUsed();
    }

    public void loginSucceded() {
        clientSetter.loginSucceded();
    }


    public void tileDraft() throws IOException, ClassNotFoundException {
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

            synchronized (token) {
                token.wait();
            }

        } catch (IOException | ClassNotFoundException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}


