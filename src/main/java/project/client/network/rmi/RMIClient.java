package project.client.network.rmi;


import project.PlayerFile;
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
import java.util.List;
import java.util.concurrent.BlockingQueue;

/**
 * This class communicates directly with the RMIserver that calls the methods on the dedicated RMIPlayerHandler
 */
public class RMIClient extends AbstractClient implements RMIServerToClientInterface {

    private String nickname;
    private RMIClientToServerInterface myServer;
    private String myUniqueId;
    private ClientSetter clientSetter;
    private HashMap<String,UpdateMethods> updateHashMap;

    private BlockingQueue<List<Integer>> integerListQueue;

    public RMIClient(ClientSetter clientSetter, String IP) throws ClientConnectionException {
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


    /**
     * This methods calls the method loginSucceded on the clientSetter
     *
     * @throws RemoteException A RemoteException is the common superclass for a number of communication-related
     *                         exceptions that may occur during the execution of a remote method call
     */
    @Override
    public void loginSucceded() throws RemoteException {
        clientSetter.loginSucceded();

    }

    /**
     * This method establish the connection between the client and the Server. The client search the server RMI on a
     * registry located on a specific port, make himself exportable and pass to the server his instance and the server
     * creates an RMIPlayer handler dedicated to him. The client receive an unique identifier that will use for
     * comunicates with his dedicated RMIPlayerHandler.
     *
     * @throws ClientConnectionException Exception due to errors in client's connection
     */
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

    /**
     * This method calls the method loginRequest on the RMIServer
     *
     * @param loginParameter player's nickname
     */
    @Override
    public void loginRequest(String loginParameter) {
        nickname = loginParameter;
        try {
            myServer.loginRequest(myUniqueId,loginParameter);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method calls the method takeDevCard on the RMIServer
     *
     * @param towerColour tower colour as a String
     * @param floor floor's number
     * @param familiarColour familiar colour as a String
     */
    @Override
    public void takeDevCard(String towerColour, int floor, String familiarColour) {
        try {
            myServer.takeDevCard(myUniqueId,towerColour,floor,familiarColour);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method calls the method goToMarketRequest on the RMIServer
     *
     * @param position number of the position in the market
     * @param familyColour familiar colour as a String
     */
    @Override
    public void marketAction(int position, String familyColour) {
        try {
            myServer.goToMarketRequest(myUniqueId,position,familyColour);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method calls the method goToCouncilPalaceRequest on the RMIServer
     *
     * @param floor floor's number
     * @param familyMemberColour familiar colour as a String
     */
    @Override
    public void councilAction(int floor, String familyMemberColour) {
        try {
            myServer.goToCouncilPalaceRequest( myUniqueId, floor, familyMemberColour );
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method calls the method playLeaderCardRequest on the RMIServer
     *
     * @param name leader's name
     */
    @Override
    public void playLeaderCard(String name) {
        try {
            myServer.playLeaderCardRequest(myUniqueId,name);
        } catch (RemoteException e) {
            System.out.println(e.getMessage());
            System.out.println(e.getCause());
        }
    }

    /**
     * This method calls the method discardLeaderCardRequest on the RMIServer
     *
     * @param name leader card's name to discard
     */
    @Override
    public void discardLeaderCard(String name) {
        try {
            myServer.discardLeaderCardRequest(myUniqueId,name);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method calls the method exitOnBonusAction on the RMIServer
     */
    @Override
    public void sendExitToBonusAction() {
        try {
            myServer.exitOnBonusAction(myUniqueId);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method calls the method sendBonusHarvester on the RMIServer
     *
     * @param servantsNumber number of servants
     */
    @Override
    public void bonusHarvesterAction(int servantsNumber) {
        try {
            myServer.sendBonusHarvester(myUniqueId, servantsNumber);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    /**
     * Thsi method calls the method sendBonusProduction on the RMIServer
     *
     * @param parameters list of building card's name on which perform the production
     */
    @Override
    public void bonusProductionAction(List<String> parameters) {
        try {
            myServer.sendBonusProduction(myUniqueId, parameters);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method calls the method takeBonusCardAction on the ServerRMI
     *
     * @param floor floor's number
     * @param towerColour tower colour as a String
     */
    @Override
    public void takeBonusCardAction(int floor, String towerColour) {
        try {
            myServer.sendBonusCardAction(myUniqueId, floor, towerColour);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method calls the method sendImmediatePrivileges on the ServerRMI
     *
     * @param privileges list of privileges' number that the players wants to take
     */
    @Override
    public void immediatePriviledgeAction(List<Integer> privileges) {
        try {
            myServer.sendImmediatePrivileges(myUniqueId,privileges);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method calls actionOn on the clientSetter
     */
    public void actionOk() {
        clientSetter.actionOk();
    }

    /**
     *
     *
     * @param bonusInteraction
     */
    @Override
    public void doProductionHarvester(BonusInteraction bonusInteraction) {

    }

    /**
     * This method calls the method harvesterRequest on the RMIServer
     *
     * @param familyMemberColour familiar color as a String
     * @param servantsNumber number of servants
     */
    @Override
    public void harvesterAction(String familyMemberColour, int servantsNumber) {
        try {
            myServer.harvesterRequest(myUniqueId,familyMemberColour,servantsNumber);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method calls the method productionRequest on the RMIServer
     *
     * @param familiarChosen familiar choosen color as a String
     * @param buildingCards list of building cards' name on which perform the production
     */
    @Override
    public void productionAction(String familiarChosen, List<String> buildingCards) {
        try {
            myServer.productionRequest(myUniqueId,familiarChosen,buildingCards);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method calls the method skipTurn on the RMIServer
     */
    @Override
    public void skipTurn() {
        try {
            myServer.skipTurn(myUniqueId);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    /**
     * Functional interface for call the right update method
     */
    @FunctionalInterface
    private interface UpdateMethods{
        void doUpdate(Updates updates);
    }

    /**
     * This method calls the boardUpdate on the clientSetter
     *
     * @param updates board update object
     */
    private void boardUpdate(Updates updates){
        clientSetter.boardUpdate(updates);
    }

    /**
     * This method calls the personalBoardUpdate on the clientSetter
     *
     * @param updates personal board update object
     */
    private void personalBoardUpdate(Updates updates){
        clientSetter.personalBoardUpdate(updates);
    }

    /**
     * This method calls the scoreUpdate on the clientSetter
     *
     * @param updates score update object
     */
    private void scoreUpdate(Updates updates){
        clientSetter.scoreUpdate(updates);
    }

    /**
     * This method calls the scoreUpdate on the clientSetter
     *
     * @param updates the score update object
     */
    private void familyMemberUpdate(Updates updates){
        clientSetter.familyMemberUpdate(updates);
    }

    /**
     *
     *
     * @param updates
     */
    private void excommunicationTaken(Updates updates) {
        clientSetter.excommunicationTake(updates);
    }

    //QUA CI SONO I METODI DI RITORNO DA SERVER A CLIENT

    public void takeAnotherCard(BonusInteraction towerAction){
        clientSetter.takeBonusCard((TowerAction)towerAction);
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
    public void showRanking() {
        try {
            myServer.askForRanking(myUniqueId);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void winnerComunication() {

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
    public void sendStatistics(PlayerFile playerFile) throws RemoteException {
        clientSetter.receiveStatistics(playerFile);
    }

    @Override
    public void sendRanking(List<PlayerFile> ranking) throws RemoteException {
        clientSetter.ranking(ranking);
    }

    @Override
    public void afterMatch() throws RemoteException {
        clientSetter.afterGame();
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
    public void showStatistic() {
        try {
            myServer.askForStatistics(myUniqueId);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void newGameRequest(String nickname) {
        try {
            myServer.newGameRequest(myUniqueId, nickname);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
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
    public String leaderCardChosen(List<LeaderCard> leaders) throws RemoteException {
        System.out.println("mi sono arrivate le carte leader");
        return clientSetter.getLeaderCardChosen(leaders);
    }

    @Override
    public void matchStarted(int roomPlayers, String familyColour) throws RemoteException {
        clientSetter.matchStarted(roomPlayers,familyColour);
    }
}
