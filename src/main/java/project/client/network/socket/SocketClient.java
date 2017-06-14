package project.client.network.socket;

import project.client.clientexceptions.ClientConnectionException;
import project.client.network.AbstractClient;
import project.client.ui.ClientSetter;
import project.controller.Constants;
import project.messages.TowerAction;
import project.messages.updatesmessages.*;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * Created by raffaelebongo on 29/05/17.
 */
public class SocketClient extends AbstractClient {

    ClientSetter clientSetter;
    MessagesFromServerHandler messageHandler;
    Socket socket;
    ObjectOutputStream objectOutputStream;
    ObjectInputStream objectInputStream;

    // cosi si collega con la user interface scelta e creata appositamente
    public SocketClient(ClientSetter clientSetter) throws ClientConnectionException {
        this.clientSetter = clientSetter;
        this.messageHandler = new MessagesFromServerHandler(this);
        try {
            socket = new Socket(Constants.LOCAL_ADDRESS, Constants.SOCKET_PORT);
            objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectInputStream = new ObjectInputStream(socket.getInputStream());
        } catch (IOException e) {
            throw new ClientConnectionException(e);
        }
    }

    @Override
    public void waitingForTheNewInteraction() throws IOException, ClassNotFoundException {
        String message = (String)objectInputStream.readObject();
        messageHandler.handleMessage(message);
    }

    //send requests
    @Override
    public void loginRequest(String loginParameter) throws IOException, ClassNotFoundException {
        sendKindOfRequest(Constants.LOGIN_REQUEST);

        objectOutputStream.writeObject(loginParameter);
        objectOutputStream.flush();
        objectOutputStream.reset();

        waitingForTheNewInteraction();
    }

    public void takeDevCard(String towerColour, String floor, String familiarColour ) throws IOException, ClassNotFoundException {
        sendKindOfRequest(Constants.TAKE_DEV_CARD);
        send3Parameters(towerColour, floor, familiarColour);
    }

    public void harvesterAction(String parameter1, String parameter2, String parameter3) throws IOException, ClassNotFoundException {
        sendKindOfRequest(Constants.HARVESTER);
        send3Parameters(parameter1, parameter2, parameter3);
    }

    public void marketAction( String parameter1, String parameter2 ) throws IOException, ClassNotFoundException {
        sendKindOfRequest(Constants.GO_TO_MARKET);
        send2Parameters(parameter1, parameter2);
    }

    public void councilAction(String parameter1, String parameter2) throws IOException, ClassNotFoundException {
        sendKindOfRequest(Constants.GO_TO_COUNCIL_PALACE);
        send2Parameters(parameter1, parameter2);
    }

    public void productionAction(String[] parameters) throws IOException, ClassNotFoundException {
        sendKindOfRequest(Constants.PRODUCTION);
        sendAllParameters(parameters);
    }

    public void playLeaderCard(String name ) throws IOException, ClassNotFoundException {

        sendKindOfRequest(Constants.PLAY_LEADER_CARD);

        objectOutputStream.writeObject(name);
        objectOutputStream.flush();
        objectOutputStream.reset();

        waitingForTheNewInteraction();
    }

    public void discardLeaderCard(String name) throws IOException, ClassNotFoundException {
        sendKindOfRequest(Constants.DISCARD_LEADER_CARD);

        objectOutputStream.writeObject(name);
        objectOutputStream.flush();
        objectOutputStream.reset();

        waitingForTheNewInteraction();
    }

    public void prayOrNot(String action) throws IOException, ClassNotFoundException {

        if ( action.equals("yes"))
            sendKindOfRequest(Constants.PRAY);
        else
            sendKindOfRequest(Constants.DONT_PRAY);

        waitingForTheNewInteraction();

    }



    public void mainContext() {
        clientSetter.mainContext();
    }

    //receive answers
    public void takeBonusCard() throws IOException, ClassNotFoundException {
        TowerAction towerAction = (TowerAction) objectInputStream.readObject();
        clientSetter.takeBonusCard(towerAction);
    }

    //updates

    public void scoreUpdate() throws IOException, ClassNotFoundException {
        Updates update = (ScoreUpdate)objectInputStream.readObject();
        clientSetter.scoreUpdate(update);
        try {
            waitingForTheNewInteraction();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void personalBoardUpdate() throws IOException, ClassNotFoundException {
        Updates update = (PersonalBoardUpdate)objectInputStream.readObject();
        clientSetter.personalBoardUpdate(update);
        try {
            waitingForTheNewInteraction();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void familyMemberUpdate() throws IOException, ClassNotFoundException {
        Updates update = (FamilyMemberUpdate)objectInputStream.readObject();
        clientSetter.familyMemberUpdate(update);
        try {
            waitingForTheNewInteraction();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    public void boardUpdate() throws IOException, ClassNotFoundException {
        Updates update = (DiceValueUpdate)objectInputStream.readObject();
        clientSetter.boardUpdate(update);
        try {
            waitingForTheNewInteraction();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    //sending methods
    void send2Parameters(String parameter1, String parameter2 ) throws IOException, ClassNotFoundException{
        objectOutputStream.writeObject(parameter1);
        objectOutputStream.flush();
        objectOutputStream.reset();

        objectOutputStream.writeObject(parameter2);
        objectOutputStream.flush();
        objectOutputStream.reset();

        waitingForTheNewInteraction();
    }

    void send3Parameters(String parameter1, String parameter2, String parameter3 ) throws IOException, ClassNotFoundException {

        objectOutputStream.writeObject(parameter1);
        objectOutputStream.flush();
        objectOutputStream.reset();

        objectOutputStream.writeObject(parameter2);
        objectOutputStream.flush();
        objectOutputStream.reset();

        objectOutputStream.writeObject(parameter3);
        objectOutputStream.flush();
        objectOutputStream.reset();

        waitingForTheNewInteraction();
    }

    void sendAllParameters(String[] parameters ) throws IOException, ClassNotFoundException {

        for( String elem: parameters ){
            objectOutputStream.writeObject(elem);
            objectOutputStream.flush();
            objectOutputStream.reset();
        }

        waitingForTheNewInteraction();
    }

    void sendKindOfRequest(String kindOfRequest ) throws IOException {

        objectOutputStream.writeObject(kindOfRequest);
        objectOutputStream.flush();
        objectOutputStream.reset();
    }

    public void bothPaymentsAvailable() {
        clientSetter.bothPaymentsAvailable();
    }
}

