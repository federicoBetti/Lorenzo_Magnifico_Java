package Project.Server.Network.Socket;

/**
 * Created by raffaelebongo on 21/05/17.
 */

import Project.Controller.Constants;
import Project.Server.NetworkException.canUseBothPaymentMethodException;
import Project.Server.NetworkException.cantDoActionException;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;

/**
 * Ha un riferimento al PlayerHandler. Dopo che il PlayerHandler ha ricevuto il primo paccjetto TCP con
 * il tipo di mossa da effettuare, invia l'oggetto contenente tale informazione al ServerDataHandler che,
 * in base al tipo di mossa ( attraverso un'interfaccia funzionale ), si prepara a ricevere determinati
 * dati dal client a cui è connesso. Successivamente in callback ritorna al PlayerHandler che chiama la sua
 * room e da li il preciso metodo dalla classe delle azioni che modifica lo stato della partita.
 */

public class ServerDataHandler {

    SocketPlayerHandler socketPlayerHandler;
    ObjectInputStream objectInputStream;
    ObjectOutputStream objectOutputStream;
    HashMap<String, MethodsHadler> map;
    MethodsHadler methodsHadler;

    public ServerDataHandler(SocketPlayerHandler socketPlayerHandler, ObjectInputStream input, ObjectOutputStream output ){
        map = new HashMap<String, MethodsHadler>();
        this.socketPlayerHandler = socketPlayerHandler;
        objectInputStream = input;
        objectOutputStream = output;
        loadMap();
    }

    void loadMap(){
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
        //todo completare con tutte le stringhe giuste e i metodi
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
        socketPlayerHandler.goToCouncilPalaceRequest();
    }

    private void rollDices() {
        socketPlayerHandler.rollDices();
    }

    private void discardLeaderCard() throws cantDoActionException, IOException, ClassNotFoundException {
        socketPlayerHandler.discardLeaderCardRequest();
    }

    private void playLeaderCardRequest() throws cantDoActionException, IOException, ClassNotFoundException {
        socketPlayerHandler.playLeaderCardRequest();
    }

    private void jumpTurn() {
        socketPlayerHandler.jumpTurn();
    }

    private void goToMarketRequest() throws cantDoActionException, IOException, ClassNotFoundException {
        socketPlayerHandler.goToMarketRequest();
    }

    private void productionRequest() throws cantDoActionException, IOException, ClassNotFoundException {
        socketPlayerHandler.productionRequest();
    }

    private void harvesterRequest() throws IOException, ClassNotFoundException, cantDoActionException {
        socketPlayerHandler.harvesterRequest();
    }

    private void choosePaymentForVentureCard() throws IOException, ClassNotFoundException {
        socketPlayerHandler.choosePaymentForVentureCard();
    }

    private void takeDevCard() throws ClassNotFoundException, cantDoActionException, canUseBothPaymentMethodException, IOException {
        socketPlayerHandler.takeDevCard();
    }


    private void loginRequest() throws IOException, ClassNotFoundException {
        Object nickname = objectInputStream.readObject();
        socketPlayerHandler.loginRequestAnswer((String) nickname);
    }

    public void handleRequest( Object object ) throws IOException, ClassNotFoundException, cantDoActionException, canUseBothPaymentMethodException {

        this.methodsHadler = map.get(object.toString());
        this.methodsHadler.handle();
    }

    private interface MethodsHadler{
        void handle() throws IOException, ClassNotFoundException, cantDoActionException, canUseBothPaymentMethodException;
    }
}
