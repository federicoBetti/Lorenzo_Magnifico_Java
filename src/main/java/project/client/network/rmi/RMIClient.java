package project.client.network.rmi;


import project.client.clientexceptions.ClientConnectionException;
import project.client.network.AbstractClient;
import project.client.ui.ClientSetter;
import project.controller.Constants;
import project.messages.BonusProductionOrHarvesterAction;
import project.messages.Notify;
import project.messages.TakePrivilegesAction;
import project.messages.TowerAction;
import project.server.network.rmi.RMIClientToServerInterface;

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

    RMIClientToServerInterface myServer;
    String myUniqueId;
    ClientSetter clientSetter;

    public RMIClient(ClientSetter clientSetter) throws ClientConnectionException {
        super();
        this.clientSetter = clientSetter;
        connect();
    }

    //QUA CI SONO I METODI DA CLIENT A SERVER

    public void connect() throws ClientConnectionException {
        try {
            Registry registry = LocateRegistry.getRegistry(Constants.LOCAL_ADDRESS, Constants.RMI_PORT);
            myServer = (RMIClientToServerInterface) registry.lookup("RMIClientToServerInterface");
            UnicastRemoteObject.exportObject(this, 0);
            myUniqueId = myServer.connect(this);
        } catch (RemoteException | NotBoundException e) {
            throw new ClientConnectionException(e);
        }
    }


    //QUA CI SONO I METODI DI RITORNO DA SERVER A CLIENT

    public void takeAnotherCard(TowerAction towerAction){
        clientSetter.takeBonusCard(towerAction);
    }

    public void doProductionHarvester (BonusProductionOrHarvesterAction bonusProductionOrHarvesterAction){
        clientSetter.doProductionHarvester(bonusProductionOrHarvesterAction);
    }

    @Override
    public void notify(Notify notify) {
        clientSetter.notifyClient(notify);
    }

    @Override
    public void endTurn() {
        clientSetter.endTurn();
    }

    @Override
    public void takePrivilege(TakePrivilegesAction takePrivilegesAction) {
        clientSetter.takePrivilege(takePrivilegesAction);
    }

}
