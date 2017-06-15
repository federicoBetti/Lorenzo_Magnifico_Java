package project.client.network.socket;

import project.client.ui.cli.CliConstants;
import project.client.ui.cli.InputException;
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
        map.put(Constants.NOT_ENOUGH_RESOURCES, this:: notEnoughResources );
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
    }

    private void takeImmediatePriviledge() {
        client.takeImmediatePrivilege();
    }

    private void bonusHarvester() throws IOException, ClassNotFoundException {
        client.bonusHarvester();
    }

    private void bonusProduction() throws IOException, ClassNotFoundException {
        client.bonusProduction();
    }

    private void notEnoughResources() throws IOException, ClassNotFoundException {
        System.out.println("Not enough resources available for doing the action");
        client.waitingForTheNewInteraction();
    }

    private void choicePe() {
        client.choicePe();
    }

    private void bothPaymentsAvailable() {
        client.bothPaymentsAvailable();
    }

    private void cantDoAction() throws IOException, ClassNotFoundException {
        System.out.println("you have not the minimun requirements for acting the action.\n " +
                "please try to act another action");
        client.waitingForTheNewInteraction();
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
