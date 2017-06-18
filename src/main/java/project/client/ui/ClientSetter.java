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

/**
 * this class is the "bridge" between user interface and client.
 */
public class ClientSetter {
    AbstractClient client;
    AbstractUI ui;

    Board uiBoard;
    PersonalBoard uiPersonalBoard;
    Score uiScore;
    FamilyMember[] uiFamilyMembers;

    //todo creare board
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

    //these methods call other methods on the client

    public void loginRequest(String loginParameter)   {
        client.loginRequest(loginParameter);
    }

    public void takeDevCard(String towerColour, String floor, String familiarColour )  {
        client.takeDevCard(towerColour, floor, familiarColour);
    }

    public void harvesterAction(String position, String familyMemberColour, String servantsNumber)  {
        //è brutto che prenda stringhe, perche non: int,String,int
        client.harvesterAction(position, familyMemberColour, servantsNumber);
    }

    public void marketAction(String position, String familyColour)  {
        client.marketAction(position, familyColour);
    }

    public void councilAction(String priviledgeNumber, String familiarColour )  {
        client.councilAction( priviledgeNumber, familiarColour);
    }

    public void productionAction(String[] parameters)   {
        //è orrend che renda un array di stirnghe quando sono int,string,List<String>
        client.productionAction(parameters);
    }

    public void takeBonusCardAction(String floor, String towerColour )  {
        client.takeBonusCardAction(floor, towerColour);
    }

    public void playLeaderCard(String action)   {
        client.playLeaderCard(action);
    }

    public void discardLeaderCard(String name)   {
        client.discardLeaderCard(name);
    }

    public void prayOrNot(String action)   {
        client.prayOrNot(action);
    }

    public void sendExitToBonusAction()   {
        client.sendExitToBonusAction();
    }

    public void sendChoicePe(String input)   {
        client.sendChoicePe(input);
    }

    public void bonusHarvesterAction(String servantsNumber)   {
        client.bonusHarvesterAction( servantsNumber );
    }

    public void immediatePriviledgeAction(String[] privileges)  {
        client.immediatePriviledgeAction( privileges );
    }

    public void bonusProductionAction(String[] parameters)   {
        client.bonusProductionAction(parameters);
    }

    public void sendChoicePaymentVc(String payment)  {
        client.sendChoicePaymentVc(payment);
    }

    public void skipTurn() {
        //todo
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

    public void askForPraying(){
        ui.askForPraying();
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
    public void bothPaymentsAvailable() {
        ui.bothPaymentsAvailable();
    }


    //updates methods

    public void boardUpdate(Updates update) {
        update.doUpdate(uiBoard);
        ui.boardUpdate();
    }

    public void scoreUpdate(Updates update) {
        update.doUpdate(uiScore);
        ui.scoreUpdate();
    }

    public void personalBoardUpdate(Updates update) {
        update.doUpdate(uiPersonalBoard);
        ui.personalBoardUpdate();
    }

    public void familyMemberUpdate(Updates update) {
        update.doUpdate(uiFamilyMembers);
        ui.familyMemberUpdate();
    }

    public void connect(String username, String password) {
        System.out.println(username + " " + password);
        ui.startGame(3);
        //startGame(2);
    }


    public void doProductionHarvester(BonusProductionOrHarvesterAction bonusProductionOrHarvesterAction) {
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
}
