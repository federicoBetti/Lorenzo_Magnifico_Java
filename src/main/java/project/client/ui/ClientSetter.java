package project.client.ui;

import project.client.clientexceptions.NotAllowedSelection;
import project.client.network.AbstractClient;
import project.client.clientexceptions.ClientConnectionException;
import project.client.network.rmi.RMIClient;
import project.client.network.socket.SocketClient;
import project.client.ui.cli.Cli;
import project.controller.cardsfactory.LeaderCard;
import project.messages.BonusProductionOrHarvesterAction;
import project.messages.Notify;
import project.messages.TakePrivilegesAction;
import project.messages.TowerAction;
import project.messages.updatesmessages.ExcommunicationTaken;
import project.messages.updatesmessages.Updates;
import project.model.*;

import java.util.List;

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

    //todo fare metodi uguali per rmi e socket in quelli che ritornano risposte e oserei dire di farli tutti con return int
    public ClientSetter(String kindOfUI ) {
        uiBoard = new Board();
        uiPersonalBoard = new PersonalBoard();
        uiScore = new Score();
        uiFamilyMembers = new FamilyMember[4];

        if ( kindOfUI.equals("CLI"))
            ui = new Cli(this);
        else
            ui = new Gui(this);
        ui.startUI();
    }

    public void setConnectionType(String connectionType) {
        switch (connectionType) {
            case "socket":
                try {
                    this.client = new SocketClient(this);
                } catch (ClientConnectionException e) {
                    System.out.println("errore di connessione");
                }
                break;
            case "RMI":
                try {
                    this.client = new RMIClient(this);
                } catch (ClientConnectionException e) {
                    System.out.println("errore di connessione RMI");
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

    //these methods call other methods on the client

    public void loginRequest(String loginParameter){
        client.loginRequest(loginParameter);
    }

    public void takeDevCard(String towerColour, int floor, String familiarColour )  {
        client.takeDevCard(towerColour, floor, familiarColour);
    }

    public void harvesterAction( String familyMemberColour, int servantsNumber)  {
        client.harvesterAction(familyMemberColour, servantsNumber);
    }

    public void marketAction(int position, String familyColour)  {
        client.marketAction(position, familyColour);
    }

    public void councilAction(int priviledgeNumber, String familiarColour )  {
        client.councilAction( priviledgeNumber, familiarColour);
    }

    public void productionAction(String familiarColor, List<String> buidingCards)   {
        client.productionAction(familiarColor,buidingCards);
    }

    public void takeBonusCardAction(int floor, String towerColour )  {
        client.takeBonusCardAction(floor, towerColour);
    }

    public void playLeaderCard(String action)   {
        client.playLeaderCard(action);
    }

    public void discardLeaderCard(String name)   {
        client.discardLeaderCard(name);
    }

    public void discardLeaderCard(int cardSelected) {
        String leaderName = uiPersonalBoard.getMyLeaderCard().get(cardSelected).getName();
        discardLeaderCard(leaderName);
    }

    public void playLeaderCard(int cardSelected) {
        String leaderName = uiPersonalBoard.getMyLeaderCard().get(cardSelected).getName();
        playLeaderCard(leaderName);
    }

    public void sendExitToBonusAction()   {
        client.sendExitToBonusAction();
    }

    public void sendChoicePe(int selection)   {
        //da me arrivano 1 o 2
        client.sendChoicePe(selection);
    }

    public void bonusHarvesterAction(int servantsNumber)   {
        client.bonusHarvesterAction( servantsNumber );
    }

    public void immediatePriviledgeAction(List<Integer> privileges)  {
        client.immediatePriviledgeAction( privileges );
    }

    public void bonusProductionAction(List<String> buildingCards)   {
        client.bonusProductionAction(buildingCards);
    }

    public void sendChoicePaymentVc(int payment)  {
        //da me arrivano 0 o 1
        client.sendChoicePaymentVc(payment);
    }

    public void skipTurn() {
        client.skipTurn();

    }


    //these methods call other methods on the ui

    public void itsMyTurn() {
        ui.mainContext();
    }

    public void takeBonusCard(TowerAction towerAction ){
        ui.takeBonusCard(towerAction);
    }

    public void choicePe() {
        ui.choicePe();
    }

    public void bonusHarvester(BonusProductionOrHarvesterAction bonusHarv) {
        ui.bonusHarvester(bonusHarv);
    }

    public void bonusProduction(BonusProductionOrHarvesterAction bonusProd) {
        ui.bonusProduction(bonusProd);
    }

    public int askForPraying(){
        int res =  ui.askForPraying();
        return res;
    }

    public void actionOk() {
        ui.actionOk();
    }

    public void cantDoAction() {
        ui.cantDoAction();
    }

    public void takeImmediatePrivilege(TakePrivilegesAction privilegesAction)  {
        ui.takeImmediatePrivilege(privilegesAction);
    }


    //updates methods


    public synchronized void scoreUpdate(Updates update) {
        uiScore = update.doUpdate(uiScore);
        ui.scoreUpdate(update);
    }

    public synchronized void personalBoardUpdate(Updates update) {
        uiPersonalBoard = update.doUpdate(uiPersonalBoard);
        ui.personalBoardUpdate(update);
    }

    public synchronized void familyMemberUpdate(Updates update) {
        uiFamilyMembers = update.doUpdateFamilyMembers();
        ui.familyMemberUpdate(update);
    }

    public synchronized void boardUpdate(Updates updates) {
        updates.doUpdate(uiBoard);
        //updates.stamp();
        ui.boardUpdate(updates);
    }


    public void connect(String username, String password) {
        System.out.println(username + " " + password);
        ui.startGame(3);
        //startGame(2);
    }



    //todo check utility
    public void notifyClient(Notify notify) {
    }

    public Board getUiBoard() {
        return uiBoard;
    }

    public PersonalBoard getUiPersonalBoard() {
        return uiPersonalBoard;
    }

    public Score getUiScore() {
        return uiScore;
    }

    public FamilyMember[] getUiFamilyMembers() {
        return uiFamilyMembers;
    }

    public void nicknameAlreadyUsed() {
        ui.nicknameAlreadyUsed();
    }

    public void waitingForYourTurn() {
        ui.waitingForYourTurn();
    }

    public void goToLogin() {
        ui.goToLogin();
    }

    public AbstractClient getClient() {
        return client;
    }

    public void setClient(AbstractClient client) {
        this.client = client;
    }

    public void loginSucceded() {
        ui.loginSucceded();
    }

    public int bothPaymentsAvailable() {
        return  ui.bothPaymentsAvailable();
    }

    public void timerTurnDelayed() {
        ui.waitingForYourTurn();
    }

    public int getScelta() {
        return ui.getScelta();
    }

    public String getLeaderCardChosen(List<LeaderCard> leaders) {
        return ui.getLeaderCardChosen(leaders);
    }

    public int tileDraft(List<Tile> tiles) {
        return ui.tileDraft(tiles);
    }

    public void matchStarted(int roomPlayers, String familyColour) {
        ui.matchStarted(roomPlayers,familyColour);
    }

    public void newNickname(String nickname) {
        client.newNickname(nickname);
    }

    public void prayed() {
        ui.prayed();
    }

    public void excommunicationTake(Updates update) {
        ui.excommunicationTaken((ExcommunicationTaken)update);
    }
}
