package Project.Client.Network.Socket;

import Project.Client.AbstractUI;
import Project.Client.Network.AbstractClient;
import Project.Controller.Constants;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created by raffaelebongo on 29/05/17.
 */
public class SocketClient extends AbstractClient {

    AbstractUI abstractUI;
    SocketClientAnswerHandler answerHandler;
    String nickname;
    Socket socket;
    ObjectOutputStream objectOutputStream;
    ObjectInputStream objectInputStream;

    // cosi si collega con la user interface scelta e creata appositamente
    public SocketClient(AbstractUI abstractUI) {
        this.abstractUI = abstractUI;
        answerHandler = new SocketClientAnswerHandler(this);
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

        private void waitingForInput(){
            //todo aspetta input dalla UI e demanda letture successive o chiamate a metodi vari all'AnswerHandler

            answerHandler.handleReturn( );
    }
}
