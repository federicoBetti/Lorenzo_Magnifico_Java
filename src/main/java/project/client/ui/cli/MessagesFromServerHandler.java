package project.client.ui.cli;

import project.controller.Constants;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by raffaelebongo on 05/06/17.
 */
public class MessagesFromServerHandler {

    Cli cli;
    Map<String, ContextCreator> map;
    ContextCreator contextCreator;

    public MessagesFromServerHandler( Cli cli ){
        map = new HashMap<>();
        this.cli = cli;
        map.put(Constants.YOUR_TURN, this::mainContext );
        map.put(Constants.LOGIN_SUCCEDED, this:: waitingForNewInteraction );
    }

    private void waitingForNewInteraction() throws IOException, ClassNotFoundException {
        cli.waitingForNewInteraction();
    }

    void handleMessage(String message) throws IOException, ClassNotFoundException {
        contextCreator = map.get(message);
        contextCreator.build();
    }
    private void mainContext() {
        cli.mainContext();
    }

    @FunctionalInterface
    private interface ContextCreator{
        void build() throws IOException, ClassNotFoundException;
    }
}
