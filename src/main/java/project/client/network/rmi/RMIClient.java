package project.client.network.rmi;


import project.client.clientexceptions.ClientConnectionException;
import project.client.network.AbstractClient;
import project.client.ui.ClientSetter;
import project.controller.Constants;
import project.controller.cardsfactory.LeaderCard;
import project.messages.*;
import project.messages.updatesmessages.Updates;
import project.model.Tile;
import project.server.network.rmi.RMIClientToServerInterface;

import java.io.IOException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.BlockingQueue;

/**
 * qua devono essere implementati sia i metodi di AbstracClient (cioè tutti quelli che il client deve chiamare sul server, sia quelli della
 * interfaccia (quelli che il server chiama sul client)
 * devo avere un riferimento al main game, cioè al client vero e proprio. all'RMIClient arrivano le risposte e le passa al main game
 *  che poi a sua volta chiama il relatiivo aggiornamento di GUI.
 *  questi metodi che ritornano sono anche quelli dell'update della UI in tempo reale
 */
public class RMIClient extends AbstractClient implements RMIServerToClientInterface {

    @Override
    public void loginSucceded() throws RemoteException {
        clientSetter.loginSucceded();

    }


    private RMIClientToServerInterface myServer;
    private String myUniqueId;
    private ClientSetter clientSetter;
    private HashMap<String,UpdateMethods> updateHashMap;

    private BlockingQueue<List<Integer>> integerListQueue;

    public RMIClient(ClientSetter clientSetter) throws ClientConnectionException {
        super();
        fillUpdateHashMap();
        this.clientSetter = clientSetter;
        System.out.println("provo a connettermi RMI");
        connect();
    }

    private void fillUpdateHashMap() {
        updateHashMap = new HashMap<>(4);
        updateHashMap.put(Constants.BOARD_UPDATE,this::boardUpdate);
        updateHashMap.put(Constants.PERSONAL_BOARD_UPDATE,this::personalBoardUpdate);
        updateHashMap.put(Constants.FAMILY_MEMBER_UPDATE,this::familyMemberUpdate);
        updateHashMap.put(Constants.SCORE_UPDATE,this::scoreUpdate);
        updateHashMap.put(Constants.EXCOMMUNICATION_TAKEN_UPDATE,this::excommunicationTaken);
    }

    //QUA CI SONO I METODI DA CLIENT A SERVER

    private void connect() throws ClientConnectionException {
        try {
            Registry reg = LocateRegistry.getRegistry(8001);
            myServer = (RMIClientToServerInterface) reg.lookup("ServerRMI");
            myServer.ping();
            UnicastRemoteObject.exportObject(this, 0);
            myUniqueId = myServer.connect(this);
        } catch (RemoteException | NotBoundException e) {
            throw new ClientConnectionException(e);
        }
        System.out.println("sto andando al login");
        clientSetter.goToLogin();
    }

    @Override
    public void loginRequest(String loginParameter) {
        try {
            myServer.loginRequest(myUniqueId,loginParameter);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void waitingForTheNewInteraction() {

    }

    @Override
    public void takeDevCard(String towerColour, int floor, String familiarColour) {
        try {
            myServer.takeDevCard(myUniqueId,towerColour,floor,familiarColour);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void marketAction(int position, String familyColour) {
        try {
            myServer.goToMarketRequest(myUniqueId,position,familyColour);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void councilAction(int parameter1, String parameter2) {
        try {
            myServer.goToCouncilPalaceRequest(myUniqueId,parameter1,parameter2);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void playLeaderCard(String action) {
        try {
            myServer.playLeaderCardRequest(myUniqueId,action);
        } catch (RemoteException e) {
            System.out.println(e.getMessage());
            System.out.println(e.getCause());
        }
    }

    @Override
    public void discardLeaderCard(String name) {
        try {
            myServer.discardLeaderCardRequest(myUniqueId,name);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

  //todo non dobbiamo usare solo askForPraying?
    public void prayOrNot(boolean action) {
        try {
            myServer.prayOrNot(myUniqueId,action);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void sendExitToBonusAction() {
        try {
            myServer.exitOnBonusAction(myUniqueId);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }


    public void bonusHarvester(BonusProductionOrHarvesterAction action) {
        clientSetter.bonusHarvester(action);
    }

    @Override
    public void bonusHarvesterAction(int servantsNumber) {
        try {
            myServer.sendBonusHarvester(myUniqueId, servantsNumber);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void bonusProductionAction(List<String> parameters) {
        try {
            myServer.sendBonusProduction(myUniqueId, parameters);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void takeBonusCardAction(int floor, String towerColour) {
        try {
            myServer.sendBonusCardAction(myUniqueId, floor, towerColour);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void immediatePriviledgeAction(List<Integer> privileges) {
        try {
            myServer.sendImmediatePrivileges(myUniqueId,privileges);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void takeImmediatePrivilege(TakePrivilegesAction action) {
        clientSetter.takeImmediatePrivilege(action);
    }

    @Override
    public void sendChoicePaymentVc(int payment) {
        try {
            myServer.sendChoicePaymentVc(myUniqueId, payment);
        } catch (RemoteException e) {

        }
    }

    public void actionOk() {
        clientSetter.actionOk();
    }

    @Override
    public void doProductionHarvester(BonusInteraction bonusInteraction) {

    }

    @Override
    public void harvesterAction(String familyMemberColour, int servantsNumber) {
        try {
            myServer.harvesterRequest(myUniqueId,familyMemberColour,servantsNumber);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void productionAction(String familiarChosen, List<String> buildingCards) {
        try {
            myServer.productionRequest(myUniqueId,familiarChosen,buildingCards);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void skipTurn() {
        try {
            myServer.skipTurn(myUniqueId);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    private interface UpdateMethods{
        void doUpdate(Updates updates);
    }

    private void boardUpdate(Updates updates){
        clientSetter.boardUpdate(updates);
    }
    private void personalBoardUpdate(Updates updates){
        clientSetter.personalBoardUpdate(updates);
    }
    private void scoreUpdate(Updates updates){
        clientSetter.scoreUpdate(updates);
    }
    private void familyMemberUpdate(Updates updates){
        clientSetter.familyMemberUpdate(updates);
    }


    private void excommunicationTaken(Updates updates) {
        clientSetter.excommunicationTake(updates);
    }

    //QUA CI SONO I METODI DI RITORNO DA SERVER A CLIENT

    public void takeAnotherCard(BonusInteraction towerAction){
        clientSetter.takeBonusCard((TowerAction)towerAction);
    }


    @Override
    public void notify(Notify notify) {
        clientSetter.notifyClient(notify);
    }

    @Override
    public void endTurn() {
       // clientSetter.skipTurn();
    }

    @Override
    public void takePrivilege(BonusInteraction takePrivilegesAction) {
        clientSetter.takeImmediatePrivilege((TakePrivilegesAction)takePrivilegesAction);
    }

    @Override
    public int askForPraying() {
        return clientSetter.askForPraying();
    }

    @Override
    public void ok(BonusInteraction bonusInteraction) {
        clientSetter.actionOk();
    }

    @Override
    public void cantDoAction() {
        clientSetter.cantDoAction();
    }

    @Override
    public void newNickname(String nickname) {
        try {
            myServer.loginRequest(myUniqueId,nickname);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void terminate() {

    }

    @Override
    public void receiveStatistics() {

    }

    @Override
    public void showRanking() {

    }

    @Override
    public void ranking() {

    }

    @Override
    public void prayed() {
        clientSetter.prayed();
    }

    @Override
    public int sendChoicePE() throws RemoteException {
        return clientSetter.choicePe();
    }


    @Override
    public void notifyPlayer() {

    }

    @Override
    public void reconnect() {
        try {
            myServer.reconnect(myUniqueId);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void afterGame() {

    }

    @Override
    public void showStatistic() {

    }

    @Override
    public void newGameRequest() {
        //to implement
    }

    @Override
    public int canUseBothPaymentMethod() {
        return clientSetter.bothPaymentsAvailable();
    }

    @Override
    public void itMyTurn() {
        clientSetter.itsMyTurn();
    }

    @Override
    public void sendNotification(Notify notifications) {
        clientSetter.notifyClient(notifications);
    }

    @Override
    public void sendUpdates(Updates updates) {
        updateHashMap.get(updates.toString()).doUpdate(updates);
    }

    @Override
    public void bonusTowerAction(TowerAction returnFromEffect) {
        clientSetter.takeBonusCard(returnFromEffect);
    }

    @Override
    public void sendBonusProdHarv(BonusProductionOrHarvesterAction returnFromEffect) {
        if (returnFromEffect.toString().equals(Constants.BONUS_HARVESTER))
            clientSetter.bonusHarvester(returnFromEffect);
        else
            clientSetter.bonusProduction(returnFromEffect);
    }

    @Override
    public void sendRequestForPrivileges(TakePrivilegesAction returnFromEffect) {
        clientSetter.takeImmediatePrivilege(returnFromEffect);
    }


    @Override
    public void timerTurnDelayed() {
        clientSetter.timerTurnDelayed();
    }

    @Override
    public int tileChoosen(ArrayList<Tile> tiles) throws RemoteException {

        return clientSetter.tileDraft(tiles);
    }

    @Override
    public void nicknameAlreadyUsed() throws RemoteException {
        clientSetter.nicknameAlreadyUsed();
    }

    @Override
    public void waitForYourTurn() throws RemoteException {
        clientSetter.waitingForYourTurn();
    }

    @Override
    public void boardUpdate() {
    }

    @Override
    public void matchStarted() {

    }

    @Override
    public String leaderCardChosen(List<LeaderCard> leaders) throws RemoteException {
        System.out.println("mi sono arrivate le carte leader");
        return clientSetter.getLeaderCardChosen(leaders);
    }

    @Override
    public void matchStarted(int roomPlayers, String familyColour) throws RemoteException {
        clientSetter.matchStarted(roomPlayers,familyColour);
    }
}
