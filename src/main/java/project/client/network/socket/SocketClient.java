package project.client.network.socket;

import project.client.clientexceptions.ClientConnectionException;
import project.client.network.AbstractClient;
import project.client.ui.ClientSetter;
import project.controller.Constants;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * Created by raffaelebongo on 29/05/17.
 */
public class SocketClient extends AbstractClient {

    ClientSetter clientSetter;
    String nickname;
    Socket socket;
    ObjectOutputStream objectOutputStream;
    ObjectInputStream objectInputStream;

    // cosi si collega con la user interface scelta e creata appositamente
    public SocketClient(ClientSetter clientSetter) throws ClientConnectionException {
        this.clientSetter = clientSetter;
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
        clientSetter.handleMessage(message);
    }

    @Override
    public void loginRequest(String loginParameter) throws IOException, ClassNotFoundException {

        objectOutputStream.writeObject(Constants.LOGIN_REQUEST);
        objectOutputStream.flush();
        objectOutputStream.reset();

        objectOutputStream.writeObject(loginParameter);
        objectOutputStream.flush();
        objectOutputStream.reset();
    }
}

