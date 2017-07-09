package project.client.network.socket;

import project.controller.Constants;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * This class receive strings from the socket client and call a related method with the functional interface
 */
class MessagesFromServerHandler {

    private Map<String, ContextCreator> map;
    private ContextCreator contextCreator;
    private SocketClient client;


    MessagesFromServerHandler(SocketClient client){
        map = new HashMap<>();
        this.client = client;

        map.put(Constants.YOUR_TURN, this::mainContext );
        map.put(Constants.LOGIN_SUCCEDED, this:: loginSucceded );
        map.put(Constants.TOWER_ACTION, this::takeBonusCard );
        map.put(Constants.MATCH_STARTED, this:: matchStarted );
        //updates
        map.put(Constants.PERSONAL_BOARD_UPDATE, this::personalBoardUpdate);
        map.put(Constants.SCORE_UPDATE, this:: scoreUpdate );
        map.put(Constants.UPDATE, this:: personalBoardUpdate );
        map.put(Constants.FAMILY_MEMBER_UPDATE, this:: familyMemberUpdate );
        map.put(Constants.BOARD_UPDATE, this:: boardUpdate );
        map.put(Constants.CANT_DO_ACTION, this::cantDoAction );
        map.put(Constants.BOTH_PAYMENT_METHODS_AVAILABLE, this::bothPaymentsAvailable );
        map.put(Constants.CHOICE_PE, this:: choicePe );
        map.put(Constants.BONUS_PRODUCTION, this:: bonusProduction );
        map.put(Constants.BONUS_HARVESTER, this:: bonusHarvester );
        map.put(Constants.TAKE_PRIVILEGE_ACTION, this:: takeImmediatePriviledge );
        map.put(Constants.ASK_FOR_PRAYING, this::askForPraying);
        map.put(Constants.OK_OR_NO, this::actionOk );
        map.put(Constants.NICKNAME_USED, this:: nicknameAlreadyUsed );
        map.put(Constants.TIMER_TURN_DELAYED, this:: timerTurnDelayed );
        map.put(Constants.TILE_DRAFT, this::tileDraft );
        map.put(Constants.LEADER_DRAFT, this:: leaderDraft );
        map.put(Constants.PRAYED, this:: prayed );
        map.put(Constants.EXCOMMUNICATION_TAKEN_UPDATE, this:: excommunicationTake );
        map.put(Constants.ASK_FOR_PRAYING_LAST_PLAYER, this:: askForPrayingLastPlayer);
        map.put(Constants.ACTION_DONE_ON_TIME, this:: actionDone );
        map.put(Constants.WAITING_FOR_YOUR_TURN, this::waitingForYourTurn);
        map.put(Constants.AFTER_GAME, this:: afterGame );
        map.put(Constants.RECEIVE_STATISTICS, this::receiveStatistics );
        map.put(Constants.SHOW_RANKING, this:: ranking );
        map.put(Constants.DISCONNECTION_MESSAGE, this:: disconnessionMessage );
        map.put(Constants.WINNER_COMUNICATION, this::winnerComunication );
        map.put(Constants.NOT_ENOUGH_RESOURCES, this:: cantDoAction );
    }

    /**
     * This method calls winnerComunication method on the client
     */
    private void winnerComunication() {
        client.winnerComunication();
    }

    /**
     * This method calls disconnessionMessage method on the client
     */
    private void disconnessionMessage() {
        client.disconnessionMesaage();
    }

    /**
     * This method calls ranking method on the client
     */
    private void ranking() {
        client.ranking();
    }

    /**
     * This method calls receiveStatistics method on the client
     */
    private void receiveStatistics() {
        client.receiveStatistics();
    }

    /**
     * This method calls afterGame method on the client
     */
    private void afterGame() {
        client.afterGame();
    }

    /**
     * This method calls waitingForYourTurn method on the client
     */
    private void waitingForYourTurn() {
        client.createWaitingForYourTurnContext();
    }

    /**
     * This method is used for consuming the Action Done On Time constants when the timer reader is down
     */
    private void actionDone() {
    }

    /**
     * This method calls askForPrayingLastPlayer method on the client
     */
    private void askForPrayingLastPlayer() {
        client.askForPrayingLastPlayer();
    }

    /**
     * This method calls excommunicationTake method on the client
     */
    private void excommunicationTake() {
        client.excommunicationTake();
    }

    /**
     * This method calls prayed method on the client
     */
    private void prayed() {
        client.prayed();
    }

    /**
     * This method calls leaderDraft method on the client
     */
    private void leaderDraft() {
        client.leaderDraft();
    }

    /**
     * This method calls tileDraft method on the client
     */
    private void tileDraft() {
        try {
            client.tileDraft();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method calls matchStarted method on the client
     */
    private void matchStarted() {
        client.matchStarted();
    }

    /**
     * This method calls timerTurnDelayed method on the client
     */
    private void timerTurnDelayed() {
        client.timerTurnDelayed();
    }

    /**
     * This method calls loginSucceded method on the client
     */
    private void loginSucceded() {
        client.loginSucceded();
    }

    /**
     * This method calls nicknameAlreadyUsed method on the client
     */
    private void nicknameAlreadyUsed() {
        client.nicknameAlreadyUsed();
    }

    /**
     * This method calls actionOk method on the client
     */
    private void actionOk() {
        client.actionOk();
    }

    /**
     * This method calls askForPraying method on the client
     */
    private void askForPraying() {
        System.out.println("ASK FOR PRAYING");
        client.askForPraying();
    }

    /**
     * This method calls takeImmediatePrivilege method on the client
     */
    private void takeImmediatePriviledge()  {
       client.takeImmediatePrivilege();
    }

    /**
     * This method calls bonusHarvester method on the client
     */
    private void bonusHarvester()  {
        client.bonusHarvester();
    }

    /**
     * This method calls bonusProduction method on the client
     */
    private void bonusProduction() {
        client.bonusProduction();
    }

    /**
     * This method calls choicePe method on the client
     */
    private void choicePe() {
        client.choicePe();
    }

    /**
     * This method calls bothPaymentsAvailable method on the client
     */
    private void bothPaymentsAvailable() {
        client.bothPaymentsAvailable();
    }

    /**
     * This method calls cantDoAction method on the client
     */
    private void cantDoAction()  {
        client.cantDoAction();
    }

    /**
     * This method calls boardUpdate method on the client
     */
    private void boardUpdate()  {
        client.boardUpdate();
    }

    /**
     * This method calls familyMemberUpdate method on the client
     */
    private void familyMemberUpdate()  {
        client.familyMemberUpdate();
    }

    /**
     * This method calls personalBoardUpdate method on the client
     */
    private void personalBoardUpdate()  {
        client.personalBoardUpdate();
    }

    /**
     * This method calls scoreUpdate method on the client
     */
    private void scoreUpdate()  {
        client.scoreUpdate();
    }

    /**
     * This method calls takeBonusCard method on the client
     */
    private void takeBonusCard() {
        client.takeBonusCard();
    }

    /**
     * This method calls itsMyTurn method on the client
     */
    private void mainContext() {
        client.itsMyTurn();
    }

    /**
     * It overwrite the functional interface with the reference of a specific method in the map according to the string
     * received from Socket client
     *
     * @param message string received from the socket client
     * @throws IOException  Signals that an I/O exception of some sort has occurred. This
     * @throws ClassNotFoundException hrown when an application tries to load in a class through its
     * string name using:
     * The forName method in class Class.
     * The findSystemClass method in class ClassLoader.
     * The loadClass method in class ClassLoader
     * but no definition for the class with the specified name could be found.
     */
    void handleMessage(String message) throws IOException, ClassNotFoundException {
        contextCreator = map.get(message);
        contextCreator.build();
    }

    /**
     * functional interface
     */
    @FunctionalInterface
    private interface ContextCreator{
        void build() throws IOException, ClassNotFoundException;
    }
}
