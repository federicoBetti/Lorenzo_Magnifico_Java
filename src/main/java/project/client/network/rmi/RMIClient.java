package project.client.network.rmi;


import project.client.clientexceptions.ClientConnectionException;
import project.client.network.AbstractClient;
import project.client.ui.ClientSetter;
import project.controller.Constants;
import project.messages.*;
import project.server.network.rmi.RMIClientToServerInterface;

import java.io.IOException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

/**
 * qua devono essere implementati sia i metodi di AbstracClient (cioè tutti quelli che il client deve chiamare sul server, sia quelli della
 * interfaccia (quelli che il server chiama sul client)
 * devo avere un riferimento al main game, cioè al client vero e proprio. all'RMIClient arrivano le risposte e le passa al main game
 *  che poi a sua volta chiama il relatiivo aggiornamento di GUI.
 *  questi metodi che ritornano sono anche quelli dell'update della UI in tempo reale
 */
public class RMIClient extends AbstractClient implements RMIServerToClientInterface {

    private RMIClientToServerInterface myServer;
    private String myUniqueId;
    private ClientSetter clientSetter;

    public RMIClient(ClientSetter clientSetter) throws ClientConnectionException {
        super();
        this.clientSetter = clientSetter;
        connect();
    }

    //QUA CI SONO I METODI DA CLIENT A SERVER

    public void connect() throws ClientConnectionException {
        try {
            Registry registry = LocateRegistry.getRegistry(Constants.RMI_PORT);
            myServer = (RMIClientToServerInterface) registry.lookup("ServerRMI");
            UnicastRemoteObject.exportObject(this, 0);
            myUniqueId = myServer.connect(this);
        } catch (RemoteException | NotBoundException e) {
            throw new ClientConnectionException(e);
        }
    }


    public void takeDevCard(String[] parameters) throws IOException {
        //myServer.takeDevCard(myUniqueId);
    }


    //QUA CI SONO I METODI DI RITORNO DA SERVER A CLIENT

    public void takeAnotherCard(BonusInteraction towerAction){
        clientSetter.takeBonusCard((TowerAction)towerAction);
    }

    public void doProductionHarvester (BonusInteraction bonusProductionOrHarvesterAction){
        clientSetter.doProductionHarvester((BonusProductionOrHarvesterAction)bonusProductionOrHarvesterAction);
    }

    @Override
    public void notify(Notify notify) {
        clientSetter.notifyClient(notify);
    }

    @Override
    public void endTurn() {
        clientSetter.skipTurn();
    }

    @Override
    public void takePrivilege(BonusInteraction takePrivilegesAction) {
        clientSetter.takeImmediatePrivilege((TakePrivilegesAction)takePrivilegesAction);
    }

    @Override
    public void askForPraying() {
        clientSetter.askForPraying();
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
    public void canUseBothPaymentMethod() {
        clientSetter.bothPaymentsAvailable();
    }

    @Override
    public void itMyTurn() {
        clientSetter.itsMyTurn();
    }


}
