package project.client.network.socket;

import project.client.ui.cli.CliConstants;
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


    public MessagesFromServerHandler( SocketClient client ){
        map = new HashMap<>();
        this.client = client;

        map.put(Constants.YOUR_TURN, this::mainContext );
        map.put(Constants.LOGIN_SUCCEDED, this:: waitingForNewInteraction );
        map.put(CliConstants.TAKE_BONUS_CARD, this::takeBonusCard );
        //updates
        map.put(Constants.SCORE_UPDATE, this:: scoreUpdate );
        map.put(Constants.PERSONAL_BOARD_UPDATE, this:: personalBoardUpdate );
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
    }

    private void actionOk() {
        client.actionOk();
    }

    private void askForPraying() {
        client.askForPraying();
    }

    private void takeImmediatePriviledge() throws IOException, ClassNotFoundException {
        client.takeImmediatePrivilege();
    }

    private void bonusHarvester() throws IOException, ClassNotFoundException {
        client.bonusHarvester();
    }

    private void bonusProduction() throws IOException, ClassNotFoundException {
        client.bonusProduction();
    }


    private void choicePe() {
        client.choicePe();
    }

    private void bothPaymentsAvailable() {
        client.bothPaymentsAvailable();
    }

    private void cantDoAction() throws IOException, ClassNotFoundException {
        //todo farla passare per il client setter e fargli fare questo nella ui: stamapre messaggio generale non posso fare azione
        waitingForNewInteraction();
    }

    private void boardUpdate() throws IOException, ClassNotFoundException {
        client.boardUpdate();
    }

    private void familyMemberUpdate() throws IOException, ClassNotFoundException {
        client.familyMemberUpdate();
    }

    private void personalBoardUpdate() throws IOException, ClassNotFoundException {
        client.personalBoardUpdate();
    }

    private void scoreUpdate() throws IOException, ClassNotFoundException {
        client.scoreUpdate();
    }

    private void takeBonusCard() {
        client.takeBonusCard();
    }

    private void waitingForNewInteraction() throws IOException, ClassNotFoundException {
        client.waitingForTheNewInteraction();
    }

    public void handleMessage(String message) throws IOException, ClassNotFoundException {
        contextCreator = map.get(message);
        contextCreator.build();
    }
    private void mainContext() {
        client.itsMyTurn();
    }

    @FunctionalInterface
    private interface ContextCreator{
        void build() throws IOException, ClassNotFoundException;
    }
}
