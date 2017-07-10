package project.server.network.socket;

/**
 * Created by raffaelebongo on 21/05/17.
 */

import project.PrinterClass.UnixColoredPrinter;
import project.controller.Constants;
import project.server.network.exception.CanUseBothPaymentMethodException;
import project.server.network.exception.CantDoActionException;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * This class receive the message from "SocketPlayerHandler" and call a method back on that class according to the
 * String received
 */

class ServerDataHandler {

    private SocketPlayerHandler socketPlayerHandler;
    private ObjectInputStream objectInputStream;
    private Map<String, MethodsHadler> map;
    private MethodsHadler methodsHadler;

    ServerDataHandler(SocketPlayerHandler socketPlayerHandler, ObjectInputStream input, ObjectOutputStream output){
        map = new HashMap<>();
        this.socketPlayerHandler = socketPlayerHandler;
        objectInputStream = input;
        loadMap();
    }

    /**
     * Load the map for the functional interface
     */
    private void loadMap(){
        map.put(Constants.LOGIN_REQUEST, this::loginRequest );
        map.put(Constants.TAKE_DEV_CARD, this::takeDevCard );
        map.put(Constants.HARVESTER, this:: harvesterRequest );
        map.put(Constants.PRODUCTION, this:: productionRequest);
        map.put(Constants.GO_TO_MARKET, this:: goToMarketRequest );
        map.put(Constants.JUMP_TURN, this:: jumpTurn );
        map.put(Constants.PLAY_LEADER_CARD, this:: playLeaderCardRequest );
        map.put(Constants.DISCARD_LEADER_CARD, this:: discardLeaderCard );
        map.put(Constants.ROLL_DICES, this::rollDices );
        map.put(Constants.GO_TO_COUNCIL_PALACE, this::goToCouncilPalaceRequest );
        map.put(Constants.PRAY, this::prayRequest );
        map.put(Constants.DONT_PRAY, this::dontPrayRequest );
        map.put(Constants.SKIP_TURN, this::skipTurn );
        map.put(Constants.PRAYING_REQUEST_RECEIVED, this:: waitForPraying );
        map.put(Constants.RECONNECT, this:: reconnectClient);
        map.put(Constants.SHOW_STATISTICS, this:: showStatistics );
        map.put(Constants.NEW_GAME, this:: newGame );
        map.put(Constants.SHOW_RANKING, this:: takeRanking );
    }

    /**
     * Call the corrisponding method on SocketPlayerHandler class
     */
    private void takeRanking() {
        socketPlayerHandler.takeRanking();
    }

    /**
     * Call the corrisponding method on SocketPlayerHandler class
     */
    private void newGame() {
        try {
            String nickname = (String)objectInputStream.readObject();
            socketPlayerHandler.newGame(nickname);
        } catch (IOException | ClassNotFoundException e) {
            UnixColoredPrinter.Logger.print(Constants.CONNECTION_EXCEPTION);
        }

    }

    /**
     * Call the corrisponding method on SocketPlayerHandler class
     */
    private void showStatistics() {
        socketPlayerHandler.showStatistics();
    }

    /**
     * Call the corrisponding method on SocketPlayerHandler class
     */
    private void reconnectClient() {
        socketPlayerHandler.reconnectClient();
    }

    /**
     * Call the corrisponding method on SocketPlayerHandler class
     */
    private void waitForPraying() {
        socketPlayerHandler.waitForPraying();
    }

    /**
     * Call the corrisponding method on SocketPlayerHandler class
     */
    private void skipTurn() {
        socketPlayerHandler.socketSkipTurn();
    }
    /**
     * Call the corrisponding method on SocketPlayerHandler class
     */
    private void dontPrayRequest() {
        socketPlayerHandler.dontPray();
    }
    /**
     * Call the corrisponding method on SocketPlayerHandler class
     */
    private void prayRequest() {
        socketPlayerHandler.pray();
    }

    /**
     * Call the corrisponding method on SocketPlayerHandler class
     */
    private void goToCouncilPalaceRequest() throws IOException, ClassNotFoundException {
        socketPlayerHandler.goToCouncilPalaceRequest();
    }

    /**
     * Call the corrisponding method on SocketPlayerHandler class
     */
    private void rollDices() {
        socketPlayerHandler.rollDices();
    }

    /**
     * Call the corrisponding method on SocketPlayerHandler class
     */
    private void discardLeaderCard() throws  IOException, ClassNotFoundException {
        socketPlayerHandler.discardLeaderCardRequest();
    }

    /**
     * Call the corrisponding method on SocketPlayerHandler class
     */
    private void playLeaderCardRequest() throws  IOException, ClassNotFoundException {
        socketPlayerHandler.playLeaderCardRequest();
    }

    /**
     * Call the corrisponding method on SocketPlayerHandler class
     */
    private void jumpTurn() {
        socketPlayerHandler.jumpTurn();
    }

    /**
     * Call the corrisponding method on SocketPlayerHandler class
     */
    private void goToMarketRequest() throws  IOException, ClassNotFoundException {
        socketPlayerHandler.goToMarketRequest();
    }

    /**
     * Call the corrisponding method on SocketPlayerHandler class
     */
    private void productionRequest() throws IOException, ClassNotFoundException {
        socketPlayerHandler.productionRequest();
    }

    /**
     * Call the corrisponding method on SocketPlayerHandler class
     */
    private void harvesterRequest() throws IOException, ClassNotFoundException{
        socketPlayerHandler.harvesterRequest();
    }


    /**
     * Call the corrisponding method on SocketPlayerHandler class
     */
    private void takeDevCard() throws ClassNotFoundException, IOException {
        socketPlayerHandler.takeDevCard();
    }

    /**
     * Call the corrisponding method on SocketPlayerHandler class
     */
    private void loginRequest() throws IOException, ClassNotFoundException {
        Object nickname = objectInputStream.readObject();
        socketPlayerHandler.loginRequestAnswer((String) nickname);
    }

    /**
     * It overwrite the functional interface with the reference of a specific method in the map according to the string
     * received from SocketPlayerHandler
     *
     * @param object
     * @throws IOException
     * @throws ClassNotFoundException
     * @throws CantDoActionException
     * @throws CanUseBothPaymentMethodException
     */
    void handleRequest(Object object) throws IOException, ClassNotFoundException, CantDoActionException, CanUseBothPaymentMethodException {

        this.methodsHadler = map.get(object.toString());
        this.methodsHadler.handle();
    }

    @FunctionalInterface
    private interface MethodsHadler{
        void handle() throws IOException, ClassNotFoundException, CantDoActionException, CanUseBothPaymentMethodException;
    }
}
