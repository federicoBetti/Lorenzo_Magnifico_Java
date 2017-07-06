package project.server.network.socket;

/**
 * Created by raffaelebongo on 21/05/17.
 */

import project.controller.Constants;
import project.server.network.exception.CanUseBothPaymentMethodException;
import project.server.network.exception.CantDoActionException;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * Ha un riferimento al PlayerHandler. Dopo che il PlayerHandler ha ricevuto il primo paccjetto TCP con
 * il tipo di mossa da effettuare, invia l'oggetto contenente tale informazione al ServerDataHandler che,
 * in base al tipo di mossa ( attraverso un'interfaccia funzionale ), si prepara a ricevere determinati
 * dati dal client a cui è connesso. Successivamente in callback ritorna al PlayerHandler che chiama la sua
 * room e da li il preciso metodo dalla classe delle azioni che modifica lo stato della partita.
 */

class ServerDataHandler {

    private SocketPlayerHandler socketPlayerHandler;
    private ObjectInputStream objectInputStream;
    private ObjectOutputStream objectOutputStream;
    private Map<String, MethodsHadler> map;
    private MethodsHadler methodsHadler;
    int counterSkipTurn;

    public ServerDataHandler(SocketPlayerHandler socketPlayerHandler, ObjectInputStream input, ObjectOutputStream output ){
        map = new HashMap<>();
        this.socketPlayerHandler = socketPlayerHandler;
        objectInputStream = input;
        objectOutputStream = output;
        counterSkipTurn = 0;
        loadMap();
    }

    private void loadMap(){
        map.put(Constants.LOGIN_REQUEST, this::loginRequest );
        map.put(Constants.TAKE_DEV_CARD, this::takeDevCard );
        map.put(Constants.CHOOSE_PAYMENT_FOR_VENTURE_CARD, this::choosePaymentForVentureCard );
        map.put(Constants.HARVESTER, this:: harvesterRequest );
        map.put(Constants.PRODUCTION, this:: productionRequest);
        map.put(Constants.GO_TO_MARKET, this:: goToMarketRequest );
        map.put(Constants.JUMP_TURN, this:: jumpTurn );
        map.put(Constants.PLAY_LEADER_CARD, this:: playLeaderCardRequest );
        map.put(Constants.DISCARD_LEADER_CARD, this:: discardLeaderCard );
        map.put(Constants.ROLL_DICES, this::rollDices );
        map.put(Constants.GO_TO_COUNCIL_PALACE, this::goToCouncilPalaceRequest );
        map.put(Constants.TAKE_PRIVILEDGE, this::takePriviledgeRequest );
        map.put(Constants.PRAY, this::prayRequest );
        map.put(Constants.DONT_PRAY, this::dontPrayRequest );
        map.put(Constants.SKIP_TURN, this::skipTurn );
        map.put(Constants.PRAYING_REQUEST_RECEIVED, this:: waitForPraying );
        map.put(Constants.RECONNECT, this:: reconnectClient);
        map.put(Constants.SHOW_STATISTICS, this:: showStatistics );
        map.put(Constants.NEW_GAME, this:: newGame );
        map.put(Constants.SHOW_RANKING, this:: takeRanking );
        //todo completare con tutte le stringhe giuste e i metodi
    }

    private void takeRanking() {
        socketPlayerHandler.takeRanking();
    }

    private void newGame() {
        try {
            String nickname = (String)objectInputStream.readObject();
            socketPlayerHandler.newGame(nickname);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    private void showStatistics() {
        socketPlayerHandler.showStatistics();
    }

    private void reconnectClient() {
        socketPlayerHandler.reconnectClient();
    }

    private void waitForPraying() {
        socketPlayerHandler.waitForPraying();
    }

    private void skipTurn() {//todo cercare di sicnronizzare qui
        socketPlayerHandler.socketSkipTurn();
    }

    private void dontPrayRequest() {
        socketPlayerHandler.dontPray();
    }

    private void prayRequest() {
        socketPlayerHandler.pray();
    }

    private void takePriviledgeRequest() throws IOException, ClassNotFoundException {
        socketPlayerHandler.takePrivilegeRequest();
    }

    private void goToCouncilPalaceRequest() throws IOException, ClassNotFoundException {
        System.out.println("sono nella handle del mercato");
        socketPlayerHandler.goToCouncilPalaceRequest();
    }

    private void rollDices() {
        socketPlayerHandler.rollDices();
    }

    private void discardLeaderCard() throws CantDoActionException, IOException, ClassNotFoundException {
        socketPlayerHandler.discardLeaderCardRequest();
    }

    private void playLeaderCardRequest() throws CantDoActionException, IOException, ClassNotFoundException {
        socketPlayerHandler.playLeaderCardRequest();
    }

    private void jumpTurn() {
        socketPlayerHandler.jumpTurn();
    }

    private void goToMarketRequest() throws CantDoActionException, IOException, ClassNotFoundException {
        System.out.println("sono nella handle del mercato");
        socketPlayerHandler.goToMarketRequest();
    }

    private void productionRequest() throws CantDoActionException, IOException, ClassNotFoundException {
        socketPlayerHandler.productionRequest();
    }

    private void harvesterRequest() throws IOException, ClassNotFoundException, CantDoActionException {
        socketPlayerHandler.harvesterRequest();
    }

    private void choosePaymentForVentureCard() throws IOException, ClassNotFoundException {
        socketPlayerHandler.choosePaymentForVentureCard();
    }

    private void takeDevCard() throws ClassNotFoundException, CantDoActionException, CanUseBothPaymentMethodException, IOException {
        socketPlayerHandler.takeDevCard();
    }


    private void loginRequest() throws IOException, ClassNotFoundException {
        Object nickname = objectInputStream.readObject();
        socketPlayerHandler.loginRequestAnswer((String) nickname);
    }

    public void handleRequest( Object object ) throws IOException, ClassNotFoundException, CantDoActionException, CanUseBothPaymentMethodException {

        this.methodsHadler = map.get(object.toString());
        this.methodsHadler.handle();
    }

    @FunctionalInterface
    private interface MethodsHadler{
        void handle() throws IOException, ClassNotFoundException, CantDoActionException, CanUseBothPaymentMethodException;
    }
}
