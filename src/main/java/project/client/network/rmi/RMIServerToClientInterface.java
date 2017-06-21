package project.client.network.rmi;

import project.messages.*;
import project.messages.updatesmessages.Updates;

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

    void sendNotification(Notify notifications)throws RemoteException;

    void sendUpdates(Updates updates)throws RemoteException;

    void bonusTowerAction(TowerAction returnFromEffect)throws RemoteException;

    void sendBonusProdHarv(BonusProductionOrHarvesterAction returnFromEffect)throws RemoteException;

    void sendRequestForPrivileges(TakePrivilegesAction returnFromEffect)throws RemoteException;

    void actionOk() throws  RemoteException;
}
