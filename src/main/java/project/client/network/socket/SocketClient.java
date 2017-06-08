package project.client.network.socket;

import project.client.clientexceptions.ClientConnectionException;
import project.client.network.AbstractClient;
import project.client.ui.ClientSetter;
import project.client.ui.cli.MessagesFromServerHandler;
import project.controller.Constants;
import project.messages.TowerAction;

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
    String nickname;
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
    }

    public void takeDevCard(String towerColour, String floor, String familiarColour ) throws IOException, ClassNotFoundException {
        sendKindOfRequest(Constants.TAKE_DEV_CARD);
        sendParameters(towerColour, floor, familiarColour);
    }

    void sendParameters( String parameter1, String parameter2, String parameter3 ) throws IOException, ClassNotFoundException {

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

    void sendKindOfRequest(String kindOfRequest ) throws IOException {

        objectOutputStream.writeObject(kindOfRequest);
        objectOutputStream.flush();
        objectOutputStream.reset();
    }

    public void mainContext() {
        clientSetter.mainContext();
    }


    //receive answers
    public void takeBonusCard() throws IOException, ClassNotFoundException {
        TowerAction towerAction = (TowerAction) objectInputStream.readObject();
        clientSetter.takeBonusCard(towerAction);
    }
}

