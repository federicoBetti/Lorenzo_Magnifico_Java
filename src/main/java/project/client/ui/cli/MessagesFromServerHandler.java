package project.client.ui.cli;

import project.client.network.socket.SocketClient;
import project.controller.Constants;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by raffaelebongo on 05/06/17.
 */
public class MessagesFromServerHandler {

    Map<String, ContextCreator> map;
    ContextCreator contextCreator;
    SocketClient client;


    public MessagesFromServerHandler(SocketClient client ){
        map = new HashMap<>();
        this.client = client;
        map.put(Constants.YOUR_TURN, this::mainContext );
        map.put(Constants.LOGIN_SUCCEDED, this:: waitingForNewInteraction );
        map.put(CliConstants.TAKE_BONUS_CARD, this::takeBonusCard );
    }

    private void takeBonusCard() {
        try {
            client.takeBonusCard();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void waitingForNewInteraction() throws IOException, ClassNotFoundException {
        client.waitingForTheNewInteraction();
    }

    public void handleMessage(String message) throws IOException, ClassNotFoundException {
        contextCreator = map.get(message);
        contextCreator.build();
    }
    private void mainContext() {
        client.mainContext();
    }

    @FunctionalInterface
    private interface ContextCreator{
        void build() throws IOException, ClassNotFoundException;
    }
}
