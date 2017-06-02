package project.client.network.socket;

import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

/**
 * Created by raffaelebongo on 29/05/17.
 */
public class SocketClientAnswerHandler {

        Scanner input = new Scanner(System.in );
        SocketClient client;
        HashMap<String, ReturnHandler> map;
        ReturnHandler returnHandler;

        public SocketClientAnswerHandler(SocketClient client) {
            this.client = client;
            map = new HashMap<>();
            loadMap();
        }

        private void loadMap() {
            //todo load map with the necessary fields
        }


        public void handleReturn(Object object) throws IOException, ClassNotFoundException {
            this.returnHandler = map.get(object.toString());
            returnHandler.handle();
        }

    @FunctionalInterface
        private interface ReturnHandler{
            void handle() throws IOException, ClassNotFoundException;
        }
}
