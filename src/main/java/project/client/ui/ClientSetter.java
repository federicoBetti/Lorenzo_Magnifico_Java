package project.client.ui;

import project.client.clientexceptions.NotAllowedSelection;
import project.client.network.AbstractClient;
import project.client.clientexceptions.ClientConnectionException;
import project.client.network.rmi.RMIClient;
import project.client.network.socket.SocketClient;
import project.client.ui.cli.Cli;
import project.messages.BonusProductionOrHarvesterAction;
import project.messages.Notify;
import project.messages.TakePrivilegesAction;
import project.messages.TowerAction;
import project.messages.updatesmessages.Updates;
import project.model.Board;
import project.model.FamilyMember;
import project.model.PersonalBoard;
import project.model.Score;

import java.io.IOException;

/**
 * this class is the "bridge" between user interface and client.
 */
public class ClientSetter {

    private AbstractClient client;
    private AbstractUI ui;

    private Board uiBoard;
    private PersonalBoard uiPersonalBoard;
    private Score uiScore;
    private FamilyMember[] uiFamilyMembers;

    //todo creare board
    public ClientSetter(String kindOfUI ) {
        uiBoard = new Board();
        uiPersonalBoard = new PersonalBoard();
        uiScore = new Score();
        uiFamilyMembers = new FamilyMember[4];

        if ( kindOfUI.equals("CLI"))
            this.ui = new Cli(this);
        else
            this.ui = new Gui(this);
        ui.startUI();
    }

    public void setConnectionType(String connectionType) {

        switch (connectionType) {
            case "socket":
                try {
                    client = new SocketClient(this);
                } catch (ClientConnectionException e) {
                    // errore nella conessione socket
                }
                break;
            case "rmi":
                try {
                    client = new RMIClient(this);
                } catch (ClientConnectionException e) {
                   // errore nella conessione RMI
                }
                break;
            default:
                try {
                    throw new NotAllowedSelection();
                } catch (NotAllowedSelection notAllowedSelection) {
                    //scelta non voluta
                }
                break;
        }
    }

    public void loginRequest(String loginParameter) throws IOException, ClassNotFoundException {
        client.loginRequest(loginParameter);
    }

    public void takeDevCard(String towerColour, String floor, String familiarColour ) throws IOException, ClassNotFoundException {
        client.takeDevCard(towerColour, floor, familiarColour);
    }

    public void mainContext() {
        ui.mainContext();
    }

    public void harvesterAction(String parameter1, String parameter2, String parameter3) throws IOException, ClassNotFoundException {
        client.harvesterAction(parameter1, parameter2, parameter3);
    }

    public void marketAction(String parameter1, String parameter2) throws IOException, ClassNotFoundException {
        client.marketAction(parameter1, parameter2);
    }

    public void councilAction(String parameter1, String parameter2 ) throws IOException, ClassNotFoundException {
        client.councilAction( parameter1, parameter2);
    }

    public void productionAction(String[] parameters) throws IOException, ClassNotFoundException {
        client.productionAction(parameters);
    }

    //metodi di ritorno
    /*
    public void startGame(int numberOfPlayer){
        ui.startGame(numberOfPlayer);
    }
    */
    public void takeBonusCard(TowerAction towerAction ){
        ui.takeBonusCard(towerAction);
    }


    public void doProductionHarvester(BonusProductionOrHarvesterAction bonusProductionOrHarvesterAction) {
        //to implement
    }


    public void endTurn() {
        //to implement
    }

    public void takePrivilege(TakePrivilegesAction takePrivilegesAction) {
        //to implement
    }


    public void playLeaderCard(String action) throws IOException, ClassNotFoundException {
        client.playLeaderCard(action);
    }

    public void discardLeaderCard(String name) throws IOException, ClassNotFoundException {
        client.discardLeaderCard(name);
    }

    public void prayOrNot(String action) throws IOException, ClassNotFoundException {
        client.prayOrNot(action);
    }

    public void sendExitToBonusAction() throws IOException, ClassNotFoundException {
        client.sendExitToBonusAction();
    }

    public void choicePe() {
        ui.choicePe();
    }

    public void sendChoicePe(String input) throws IOException, ClassNotFoundException {
        client.sendChoicePe(input);
    }

    public void askForPraying() {
    }

    public void actionOk() {
    }

    public void cantDoAction() {
    }

    public void canUseBothPaymentMethod() {
    }

    public void itsMyTurn() {
    }

    //updates

    public void boardUpdate(Updates update) {
        update.doUpdate(uiBoard);
    }

    public void scoreUpdate(Updates update) {
        update.doUpdate(uiScore);
    }

    public void personalBoardUpdate(Updates update) {
        update.doUpdate(uiPersonalBoard);
    }

    public void familyMemberUpdate(Updates update) {
        update.doUpdate(uiFamilyMembers);
    }






    //DA QUA SOTTO FACCIO LE PROVE IO PER VEDERE SE VA LA GUI
    public void connect(String username, String password) {
        System.out.println(username + " " + password);
        ui.startGame(3);
        //startGame(2);
    }

    public void bothPaymentsAvailable() {
        ui.bothPaymentsAvailable();
    }

    public void notifyClient(Notify notify) {
    }

    public void setUiBoard(Board uiBoard) {
        this.uiBoard = uiBoard;
    }

    public PersonalBoard getUiPersonalBoard() {
        return uiPersonalBoard;
    }

    public void setUiPersonalBoard(PersonalBoard uiPersonalBoard) {
        this.uiPersonalBoard = uiPersonalBoard;
    }

    public Score getUiScore() {
        return uiScore;
    }

    public void setUiScore(Score uiScore) {
        this.uiScore = uiScore;
    }

    public FamilyMember[] getUiFamilyMembers() {
        return uiFamilyMembers;
    }

    public void setUiFamilyMembers(FamilyMember[] uiFamilyMembers) {
        this.uiFamilyMembers = uiFamilyMembers;
    }



}
