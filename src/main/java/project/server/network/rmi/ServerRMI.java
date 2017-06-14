package project.server.network.rmi;

import project.client.network.rmi.RMIServerToClientInterface;
import project.server.network.AbstractServer;
import project.server.Server;
import project.server.network.exception.CanUseBothPaymentMethodException;
import project.server.network.exception.CantDoActionException;

import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.ServerException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.*;

//questa classe prende tutte le connessioni e i metodi chamti con rmi.
// poi in base a chi l'ha chiamato, invoca i meotid giusti sull'rmiGameActions corretto

//attenzione che è buona norma che le funzioni di implementazione del gioco (cioè non riguardanti la conessione) non ritornino niente.
// solo quelli di connessione possono ritornare qualcosa anche perchè sono gli unici metodi che non chiamano
// qualcosa su un ogetto RMIGameActions


public class ServerRMI extends AbstractServer implements RMIClientToServerInterface{


    /**
     * Internal cache that maps all logged used with an unique session token that identify the single player.
     * This is required in order to identify the rmi player when he is making a new request to the server.
     */
    private final Map<String, RMIPlayerHandler> mapUniqueIdPlayer;

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
        try {
            registry.rebind("RMIClientToServerInterface", this);
            UnicastRemoteObject.exportObject(this, port);
            //Debug.verbose("Server successfully initialized");
        } catch (RemoteException e) {
            throw new ServerException("Server interface not loaded", e);
        }
    }
/*
    /**
     * Create or load registry in a specified port number.
     * @param port number of the port to use.
     * @return the created or retrieved registry.
     * @throws ServerException if some error occurs.
     *
    private Registry createOrLoadRegistry(int port) throws ServerException {
        try {
            return LocateRegistry.createRegistry(port);
        } catch (RemoteException e) {
            Debug.debug("RMI Registry already exists", e);
        }
        try {
            return LocateRegistry.getRegistry(port);
        } catch (RemoteException e) {
            Debug.debug("RMI Registry not found", e);
        }
        throw new ServerException("Cannot initialize RMI registry");
    }


    */

    /**
     * questo metodo è il primo che viene eseguito per connettersi che restituisce la stringa per identificare il client
     * @return
     */
    public String connect(RMIServerToClientInterface player){
        String clientUniqueId = UUID.randomUUID().toString();
        mapUniqueIdPlayer.put(clientUniqueId, new RMIPlayerHandler(player));
        return clientUniqueId;
    }

    private RMIPlayerHandler getPlayerHandler(String clientUniqueId){
        return mapUniqueIdPlayer.get(clientUniqueId);
    }


    public void loginRequest(String clientUniqueId, String nickname) throws IOException {
        server.loginRequest(nickname,getPlayerHandler(clientUniqueId));
    }

    public void takeDevCard(String clientUniqueId, String towerColour, int floor, String familyMemberColour) throws RemoteException {
        getPlayerHandler(clientUniqueId).takeDevCard(towerColour, floor, familyMemberColour);
    }

    public void choosePaymentForVentureCard(String clientUniqueId, int position, int paymentChoosen, String familyMemberColour) {
        getPlayerHandler(clientUniqueId).choosePaymentForVenturesCard(position,paymentChoosen,familyMemberColour);

    }

    public void harvesterRequest(String clientUniqueId, int position, String familyMemberColour, int servantsNumber) {
        getPlayerHandler(clientUniqueId).harvesterRequest(position,familyMemberColour, servantsNumber);
    }

    public void productionRequest(String clientUniqueId, int position, String familyMemberColour, List<String> cards ){
        getPlayerHandler(clientUniqueId).productionRequest(position,familyMemberColour, cards);
    }

    public void goToMarketRequest(String clientUniqueId,int position, String familyMemberColour)  {
        getPlayerHandler(clientUniqueId).goToMarketRequest(position,familyMemberColour);
    }

    public void playLeaderCardRequest(String clientUniqueId, String leaderCardName)  {
        getPlayerHandler(clientUniqueId).playLeaderCardRequest(leaderCardName);
    }

    public void discardLeaderCardRequest(String clientUniqueId, String leaderCardName) {
        getPlayerHandler(clientUniqueId).discardLeaderCardRequest(leaderCardName);
    }

    public void goToCouncilPalaceRequest(String clientUniqueId, int privilegeNumber, String familyMemberColour)  {
        getPlayerHandler(clientUniqueId).goToCouncilPalaceRequest(privilegeNumber, familyMemberColour);
    }

    public void takePrivilegeRequest(String clientUniqueId, int privilegeNumber){
        getPlayerHandler(clientUniqueId).takePrivilegeRequest(privilegeNumber);
    }



}
