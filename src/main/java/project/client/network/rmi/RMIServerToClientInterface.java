package project.client.network.rmi;

import project.messages.*;

import java.io.IOException;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * questi sono i metodi che il server puo chiamare sul client,
 * spesso di risposta a qualche chiamata del client precedente. devono essere implementati per forza dall'RMIClient
 */
public interface RMIServerToClientInterface extends Remote{

    void takeAnotherCard(BonusInteraction towerAction) throws RemoteException;

    void doProductionHarvester (BonusInteraction bonusProductionOrHarvesterAction) throws RemoteException;

    void notify (Notify notify);

    void endTurn();

    void takePrivilege(BonusInteraction takePrivilegesAction);

    void askForPraying() throws RemoteException;

    void ok(BonusInteraction bonusInteraction) throws RemoteException;

    void cantDoAction() throws RemoteException;

    void canUseBothPaymentMethod() throws RemoteException;

    void itMyTurn() throws RemoteException;
}
