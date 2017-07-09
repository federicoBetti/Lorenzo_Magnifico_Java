package project.server.network.rmi;

import project.PrinterClass.UnixColoredPrinter;
import project.client.network.rmi.RMIServerToClientInterface;
import project.server.Server;
import project.server.network.AbstractServer;

import java.rmi.RemoteException;
import java.rmi.ServerException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * this is the class of RMI Server. All the RMI connections call methods on this class and it will split them through the right RMI player handler
 */

public class ServerRMI extends AbstractServer implements RMIClientToServerInterface{


    /**
     * Internal cache that maps all logged used with an unique session token that identify the single player.
     * This is required in order to identify the rmi player when he is making a new request to the server.
     */
    private final Map<String, RMIPlayerHandler> mapUniqueIdPlayer;

    /**
     * constructor
     * @param server main server that collect login requests
     */
    public ServerRMI( Server server ){
        super(server);
        mapUniqueIdPlayer = new HashMap<>();
    }

    /**
     * Start the RMIServer connection.
     * @param port number of the port to use.
     * @throws ServerException if some error occurs.
     */

    public void startServer(int port) throws RemoteException {
        Registry registry = LocateRegistry.createRegistry(port);
        UnicastRemoteObject.exportObject(this,0);
        try {
                registry.rebind("ServerRMI", this);

        } catch (RemoteException e) {
            UnixColoredPrinter.Logger.print("error on the binding of the registry");
        }
    }

    /**
     * method that accept connection fro clients. it creates and RMI player handler and return an unique string to identify each single client
     * @param player instance of the payer that wants to build a connection
     * @return client unique identifier
     */
    public String connect(RMIServerToClientInterface player){
        String clientUniqueId = UUID.randomUUID().toString();
        mapUniqueIdPlayer.put(clientUniqueId, new RMIPlayerHandler(player));
        return clientUniqueId;
    }

    /**
     * getter
     * @param clientUniqueId client unique identifier
     * @return the RMI playerhandler linked to that clientUniqueId
     */
    private RMIPlayerHandler getPlayerHandler(String clientUniqueId){
        return mapUniqueIdPlayer.get(clientUniqueId);
    }

    /**
     * method called form the client to login himself in the server
     * @param clientUniqueId player's unique identifier
     * @param nickname player's nickname
     */
    public void loginRequest(String clientUniqueId, String nickname)  {
        server.loginRequest(nickname,getPlayerHandler(clientUniqueId));
    }

    /**
     * method called to perform a take development card action
     * @param clientUniqueId player's unique identifier
     * @param towerColour colour of tower selected by the client
     * @param floor floor of the tower selected by the client
     * @param familyMemberColour color of the familiar chosen by the client to perform the action
     */
    public void takeDevCard(String clientUniqueId, String towerColour, int floor, String familyMemberColour){
        getPlayerHandler(clientUniqueId).takeDevCard(towerColour, floor, familyMemberColour);
    }

    /**
     * method called t perform a harvester action
     * @param clientUniqueId player's unique identifier
     * @param familyMemberColour color of the familiar chosen by the client to perform the action
     * @param servantsNumber number of servants used to perform the action
     */
    public void harvesterRequest(String clientUniqueId, String familyMemberColour, int servantsNumber) {
        getPlayerHandler(clientUniqueId).harvesterRequest(familyMemberColour, servantsNumber);
    }

    /**
     * method called to perform a production action
     * @param clientUniqueId player's unique identifier
     * @param familyMemberColour color of the familiar chosen by the client to perform the action
     * @param cards cards selected for production action
     */
    public void productionRequest(String clientUniqueId, String familyMemberColour, List<String> cards){
        getPlayerHandler(clientUniqueId).productionRequest(familyMemberColour, cards);
    }

    /**
     * method called to perform a market action
     * @param clientUniqueId player's unique identifier
     * @param position position in the market where the client want to place the familiar
     * @param familyMemberColour color of the familiar chosen by the client to perform the action
     */
    public void goToMarketRequest(String clientUniqueId,int position, String familyMemberColour)  {
        getPlayerHandler(clientUniqueId).goToMarketRequest(position,familyMemberColour);
    }

    /**
     * method called to play a leader card
     * @param clientUniqueId player's unique identifier
     * @param leaderCardName name of the leader card selected
     */
    public void playLeaderCardRequest(String clientUniqueId, String leaderCardName)  {
        getPlayerHandler(clientUniqueId).playLeaderCardRequest(leaderCardName);
    }

    /**
     * method caled to discard a leader card
     * @param clientUniqueId player's unique identifier
     * @param leaderCardName name of the leader card selected
     */
    public void discardLeaderCardRequest(String clientUniqueId, String leaderCardName) {
        getPlayerHandler(clientUniqueId).discardLeaderCardRequest(leaderCardName);
    }

    /**
     * method called to perform an action in the council place
     * @param clientUniqueId player's unique identifier
     * @param privilegeNumber number of the privilege selected
     * @param familyMemberColour color of the familiar chosen by the client to perform the action
     */
    public void goToCouncilPalaceRequest(String clientUniqueId, int privilegeNumber, String familyMemberColour)  {
        getPlayerHandler(clientUniqueId).goToCouncilPalaceRequest(privilegeNumber, familyMemberColour);
    }

    /**
     * method called when the timer is expired and the player is performing a bonus action
     * @param clientUniqueId player's unique identifier
     */
    public void exitOnBonusAction(String clientUniqueId) {
        getPlayerHandler(clientUniqueId).exitOnBonusAction();
    }

    /**
     * method called to perform a bonus harvester action
     * @param clientUniqueId player's unique identifier
     * @param servantsNumber number of servants used to perform the action
     */
    public void sendBonusHarvester(String clientUniqueId, int servantsNumber) {
        getPlayerHandler(clientUniqueId).doBonusHarvester(servantsNumber);
    }

    /**
     * method called to perform a bonus production action
     * @param clientUniqueId player's unique identifier
     * @param parameters list of cards selected for the production
     */
    public void sendBonusProduction(String clientUniqueId, List<String> parameters) {
        getPlayerHandler(clientUniqueId).doBonusProduction(parameters);
    }

    /**
     * method called to perform a bonus action of take a development card
     * @param clientUniqueId player's unique identifier
     * @param floor floor of the tower selected by the client
     * @param towerColour colour of tower selected by the client
     */
    public void sendBonusCardAction(String clientUniqueId, int floor, String towerColour)  {
        getPlayerHandler(clientUniqueId).takeBonusCardAction(floor,towerColour);
    }

    /**
     * method called to perform an action bonus of taking privileges from the council
     * @param clientUniqueId player's unique identifier
     * @param privileges list of privilege selected
     */
    public void sendImmediatePrivileges(String clientUniqueId, List<Integer> privileges) {
        getPlayerHandler(clientUniqueId).takeImmediatePrivilegesNotify(privileges);
    }

    /**
     * method used to ping the server dung the connection
     */
    public void ping()  {
        //used to ping the server
    }

    /**
     * method called to skip turn
     * @param clientUniqueId player's unique identifier
     */
    public void skipTurn(String clientUniqueId)  {
        getPlayerHandler(clientUniqueId).skipTurn();
    }


    /**
     * method called to reconnect the player after a disconnection
     * @param clientUniqueId player's unique identifier
     */
    public void reconnect(String clientUniqueId)  {
        getPlayerHandler(clientUniqueId).reconnectClient();
    }

    /**
     * method called from the client to ask for the statistics
     * @param clientUniqueId player's unique identifier
     */
    public void askForStatistics(String clientUniqueId) {
        getPlayerHandler(clientUniqueId).showStatistics();
    }

    /**
     * method called from the client that wants to play another match
     * @param clientUniqueId player's unique identifier
     * @param nickname nickname used for the new game request
     */
    public void newGameRequest(String clientUniqueId, String nickname)  {
        server.loginRequest(nickname, getPlayerHandler(clientUniqueId));
    }

    /**
     * method called by the wants the server rankings
     * @param clientUniqueId player's unique identifier
     */
    public void askForRanking(String clientUniqueId) {
        getPlayerHandler(clientUniqueId).takeRanking();
    }

}
