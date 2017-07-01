package project.client.network.socket;

import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;
import project.client.clientexceptions.ClientConnectionException;
import project.client.network.AbstractClient;
import project.client.ui.ClientSetter;
import project.controller.Constants;
import project.controller.cardsfactory.LeaderCard;
import project.messages.BonusProductionOrHarvesterAction;
import project.messages.TakePrivilegesAction;
import project.messages.TowerAction;
import project.messages.updatesmessages.*;
import project.model.Board;
import project.model.Tile;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by raffaelebongo on 29/05/17.
 */
public class SocketClient extends AbstractClient {

    private String nickname;
    private ClientSetter clientSetter;
    private MessagesFromServerHandler messageHandler;
    private Socket socket;
    private ObjectOutputStream objectOutputStream;
    private ObjectInputStream objectInputStream;


    // cosi si collega con la user interface scelta e creata appositamente
    public SocketClient(ClientSetter clientSetter) throws ClientConnectionException {
        this.clientSetter = clientSetter;
        this.messageHandler = new MessagesFromServerHandler(this);
        try {
            socket = new Socket(Constants.LOCAL_ADDRESS, Constants.SOCKET_PORT);
            objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectInputStream = new ObjectInputStream(socket.getInputStream());
            goToLogin();
        } catch (IOException e) {
            e.printStackTrace();
            throw new ClientConnectionException(e);
        }
    }

    private void goToLogin() {
        clientSetter.goToLogin();
    }

    @Override
    public void waitingForTheNewInteraction() {

        String message;
        while (true) {
            try {

                message = (String) objectInputStream.readObject();
                messageHandler.handleMessage(message);

            } catch (IOException e) {

            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    //send requests
    @Override
    public void loginRequest(String loginParameter) {
        sendGenericObject(Constants.LOGIN_REQUEST);
        sendGenericObject(loginParameter);
        nickname = loginParameter;
        waitingForTheNewInteraction();
    }

    @Override
    public void takeDevCard(String towerColour, int floor, String familiarColour) {
        sendGenericObject(Constants.TAKE_DEV_CARD);
        send3Parameters(towerColour, floor, familiarColour);
    }

    @Override
    public void actionOk() {
        clientSetter.actionOk();
    }

    @Override
    public void marketAction(int position, String familyColour) {
        sendGenericObject(Constants.GO_TO_MARKET);
        send2Parameters(position, familyColour);
    }

    @Override
    public void councilAction(int priviledgeNumber, String familyColour) {
        sendGenericObject(Constants.GO_TO_COUNCIL_PALACE);
        send2Parameters(priviledgeNumber, familyColour);
    }

    @Override
    public void productionAction(String familiarColor, List<String> buildingCards) {
        sendGenericObject(Constants.PRODUCTION);
        sendGenericObject(familiarColor);
        sendAllStrings(buildingCards);
    }

    @Override
    public void immediatePriviledgeAction(List<Integer> privileges) {
        sendAllIntegers(privileges);
    }

    @Override
    public void playLeaderCard(String name) {

        sendGenericObject(Constants.PLAY_LEADER_CARD);
        sendGenericObject(name);
    }

    @Override
    public void discardLeaderCard(String name) {
        sendGenericObject(Constants.DISCARD_LEADER_CARD);
        sendGenericObject(name);
    }

    @Override
    public void askForPrayingLastPlayer() {
        //new TimerReader().start();
        int answer = clientSetter.askForPraying();

        try {
            objectOutputStream.writeObject(answer);
            objectOutputStream.flush();
            objectOutputStream.reset();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void askForPraying() {
        sendGenericObject(Constants.PRAYING_REQUEST_RECEIVED);
        System.out.println("mandata costante per far bloccare server read");
        int answer = clientSetter.askForPraying();
        System.out.println("il res viene mandato: " + clientSetter.askForPraying());

        try {
            objectOutputStream.writeObject(answer);
            objectOutputStream.flush();
            objectOutputStream.reset();
        } catch (IOException e) {
        e.printStackTrace();
    }
    }

    public void itsMyTurn() {
        clientSetter.itsMyTurn();
    }

    //receive answers: bonus interaction
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

    public void takeBonusCardAction(int floor, String towerColour) {
        send2Parameters(towerColour, floor);
    }

    @Override
    public void harvesterAction(String familyColour, int servantsNumber) {
        sendGenericObject(Constants.HARVESTER);
        send2Parameters(familyColour, servantsNumber);
    }


    public void bonusHarvester() {
        BonusProductionOrHarvesterAction bonusHarv = null;
        try {
            bonusHarv = (BonusProductionOrHarvesterAction) objectInputStream.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        clientSetter.bonusHarvester(bonusHarv);
    }

    public void takeImmediatePrivilege() {
        TakePrivilegesAction privilegesAction = null;
        try {
            privilegesAction = (TakePrivilegesAction) objectInputStream.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        clientSetter.takeImmediatePrivilege(privilegesAction);
    }

    public void bonusHarvesterAction(int servantsNumber) {
        sendGenericObject(Constants.BONUS_HARVESTER);
        sendGenericObject(servantsNumber);
    }

    public void bonusProduction() {
        BonusProductionOrHarvesterAction bonusProd = null;
        try {
            bonusProd = (BonusProductionOrHarvesterAction) objectInputStream.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        clientSetter.bonusProduction(bonusProd);
    }

    public void bonusProductionAction(List<String> parameters) {
        sendGenericObject(Constants.BONUS_PRODUCTION);
        sendAllStrings(parameters);
    }

    public void takeBonusCard() {
        TowerAction towerAction = null;
        try {
            towerAction = (TowerAction) objectInputStream.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        clientSetter.takeBonusCard(towerAction);
    }


    public void choicePe() {
        clientSetter.choicePe();
    }

    @Override
    public void sendChoicePe(int input) {
        sendGenericObject(input);
    }

    public void bothPaymentsAvailable() {
        new TimerReader().start();
        int costChoice = clientSetter.bothPaymentsAvailable();
        System.out.println("mandata la scelta del prezzo!");
        sendGenericObject(costChoice);
    }

    @Override
    public void sendChoicePaymentVc(int payment) {
        sendGenericObject(payment);
    }

    //updates

    public void scoreUpdate() {
        Updates update = null;
        try {
            update = (ScoreUpdate) objectInputStream.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        clientSetter.scoreUpdate(update);
    }

    public void personalBoardUpdate() {
        Updates update = null;
        try {
            update = (PersonalBoardUpdate) objectInputStream.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        clientSetter.personalBoardUpdate(update);
    }

    public void familyMemberUpdate() {
        Updates update = null;
        try {
            update = (FamilyMemberUpdate) objectInputStream.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        clientSetter.familyMemberUpdate(update);
    }


    public void skipTurn() {
        sendGenericObject(Constants.SKIP_TURN);
        createWaitingForYourTurnContext();
    }

    @Override
    public void timerTurnDelayed() {
        clientSetter.timerTurnDelayed();
    }

    @Override
    public void boardUpdate() {
        Updates update = null;
        try {
            update = (Updates) objectInputStream.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        clientSetter.boardUpdate(update);
    }

    @Override
    public void matchStarted() {
        int roomPlayersNumber = 0;
        try {
            roomPlayersNumber = (int) objectInputStream.readObject();
            String playerFamilyColour = (String) objectInputStream.readObject();
            clientSetter.matchStarted(roomPlayersNumber, playerFamilyColour);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void cantDoAction() {
        clientSetter.cantDoAction();
    }

    @Override
    public void newNickname(String nickname) {
        sendGenericObject(nickname);
    }

    @Override
    public void prayed() {
        clientSetter.prayed();
    }

    @Override
    public void excommunicationTake() {
        try {
            Updates update =(Updates)objectInputStream.readObject();
            clientSetter.excommunicationTake(update);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }



    private void createWaitingForYourTurnContext() {
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

    public void leaderDraft() {
        List<LeaderCard> leaders = new ArrayList<>();

        try {
            int size = (int)objectInputStream.readObject();

            for ( int i = 0; i < size; i++ ) {
                LeaderCard leaderCard = (LeaderCard) objectInputStream.readObject();
                leaders.add(leaderCard);
        }

        String leaderCardChoosen = clientSetter.getLeaderCardChosen(leaders);
        objectOutputStream.writeObject(leaderCardChoosen);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void tileDraft() throws IOException, ClassNotFoundException {
        List<Tile> tiles = new ArrayList<>();

            int size = (int) objectInputStream.readObject();

            for (int i = 0; i < size; i++) {

                try {
                    Tile tile = (Tile) objectInputStream.readObject();
                    tiles.add(tile);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }

            int choice = clientSetter.tileDraft(tiles);
            objectOutputStream.writeObject(choice);
            objectOutputStream.flush();
            objectOutputStream.reset();
    }

    private class TimerReader extends Thread {

        @Override
        public void run() {
            try {
                while ( true ) {
                    String message = (String) objectInputStream.readObject();

                    if (message.equals(Constants.TIMER_TURN_DELAYED)) {
                        System.out.println("RICEVUTO");
                        timerTurnDelayed();
                    }

                    if ( message.equals(Constants.ACTION_DONE_ON_TIME)) {
                        System.out.println("VADO A DORMIRE...CIAO!");
                        return;
                    }

                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

        }
    }

}

