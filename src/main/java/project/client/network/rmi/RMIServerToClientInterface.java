package project.client.network.rmi;

import com.sun.org.apache.regexp.internal.RE;
import project.controller.cardsfactory.LeaderCard;
import project.messages.*;
import project.messages.updatesmessages.Updates;
import project.model.Tile;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

/**
 * questi sono i metodi che il server puo chiamare sul client,
 * spesso di risposta a qualche chiamata del client precedente. devono essere implementati per forza dall'RMIClient
 */
public interface RMIServerToClientInterface extends Remote, Serializable{

    void takeAnotherCard(BonusInteraction towerAction) throws RemoteException;

    void notify (Notify notify) throws RemoteException;

    void endTurn() throws RemoteException;

    void takePrivilege(BonusInteraction takePrivilegesAction) throws RemoteException;

    int askForPraying() throws RemoteException;

    void ok(BonusInteraction bonusInteraction) throws RemoteException;

    void cantDoAction() throws RemoteException;

    int canUseBothPaymentMethod() throws RemoteException;

    void itMyTurn() throws RemoteException;

    void sendNotification(Notify notifications)throws RemoteException;

    void sendUpdates(Updates updates)throws RemoteException;

    void bonusTowerAction(TowerAction returnFromEffect)throws RemoteException;

    void sendBonusProdHarv(BonusProductionOrHarvesterAction returnFromEffect)throws RemoteException;

    void sendRequestForPrivileges(TakePrivilegesAction returnFromEffect)throws RemoteException;

    void actionOk() throws  RemoteException;

    void doProductionHarvester(BonusInteraction bonusInteraction) throws RemoteException;

    void loginSucceded() throws RemoteException;

    int getScelta() throws RemoteException;

    String leaderCardChosen(List<LeaderCard> leaders) throws RemoteException;

    void matchStarted(int roomPlayers, String familyColour) throws RemoteException;

    void timerTurnDelayed() throws RemoteException;

    int tileChoosen(ArrayList<Tile> tiles) throws RemoteException;

    void nicknameAlreadyUsed() throws RemoteException;

    void waitForYourTurn() throws RemoteException;

    void prayed() throws RemoteException;
}
