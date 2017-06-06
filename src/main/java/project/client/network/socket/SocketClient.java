package project.client.network.socket;

import project.client.ui.AbstractUI;
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
    public SocketClient(ClientSetter clientSetter) {
        this.clientSetter = clientSetter;
        this.messageHandler = new MessagesFromServerHandler(this);
        try {
            socket = new Socket(Constants.LOCAL_ADDRESS, Constants.SOCKET_PORT);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            objectInputStream = new ObjectInputStream(socket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void waitingForTheNewInteraction() throws IOException, ClassNotFoundException {
        String message = (String)objectInputStream.readObject();
        messageHandler.handleMessage(message);
    }

    @Override
    public void loginRequest(String loginParameter) throws IOException, ClassNotFoundException {
        sendKindOfRequest(Constants.LOGIN_REQUEST);

        objectOutputStream.writeObject(loginParameter);
        objectOutputStream.flush();
        objectOutputStream.reset();
    }

    public void takeDevCard(String[] parameters) throws IOException {
        sendKindOfRequest(Constants.TAKE_DEV_CARD);
        sendParameters(parameters);
    }

    void sendParameters(Object[] parameters ) throws IOException {
        for (Object ob : parameters) {
            objectOutputStream.writeObject(ob);
            objectOutputStream.flush();
            objectOutputStream.reset();
        }
    }

    void sendKindOfRequest(String kindOfRequest ) throws IOException {
        objectOutputStream.writeObject(kindOfRequest);
        objectOutputStream.flush();
        objectOutputStream.reset();
    }

    public void mainContext() {
        clientSetter.mainContext();
    }

    public void takeBonusCard() throws IOException, ClassNotFoundException {
        TowerAction towerAction = (TowerAction) objectInputStream.readObject();
        clientSetter.takeBonusCard(towerAction);
    }
}

