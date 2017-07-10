package project.client.network.rmi;

import project.server.PlayerFile;
import project.controller.cardsfactory.LeaderCard;
import project.messages.*;
import project.messages.updatesmessages.Updates;
import project.model.Tile;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

/**
 * This class contains all the methods that the RMIServer can call on the RMIClient
 */
public interface RMIServerToClientInterface extends Remote, Serializable{

    /**
     * Abstract takeAnotherCard
     */
    void takeAnotherCard(BonusInteraction towerAction) throws RemoteException;

    /**
     * Abstract takePrivilege
     */
    void takePrivilege(BonusInteraction takePrivilegesAction) throws RemoteException;

    /**
     * Abstract askForPraying
     */
    int askForPraying() throws RemoteException;

    /**
     * Abstract ok
     */
    void ok(BonusInteraction bonusInteraction) throws RemoteException;

    /**
     * Abstract cantDoAction
     */
    void cantDoAction() throws RemoteException;

    /**
     * Abstract canUseBothPaymentMethod
     */
    int canUseBothPaymentMethod() throws RemoteException;

    /**
     * Abstract itMyTurn
     */
    void itMyTurn() throws RemoteException;

    /**
     * Abstract sendUpdates
     */
    void sendUpdates(Updates updates)throws RemoteException;

    /**
     * Abstract bonusTowerAction
     */
    void bonusTowerAction(TowerAction returnFromEffect)throws RemoteException;

    /**
     * Abstract sendBonusProdHarv
     */
    void sendBonusProdHarv(BonusProductionOrHarvesterAction returnFromEffect)throws RemoteException;

    /**
     * Abstract sendRequestForPrivileges
     */
    void sendRequestForPrivileges(TakePrivilegesAction returnFromEffect)throws RemoteException;

    /**
     * Abstract actionOk
     */
    void actionOk() throws  RemoteException;

    /**
     * Abstract loginSucceded
     */
    void loginSucceded() throws RemoteException;

    /**
     * Abstract leaderCardChosen
     */
    String leaderCardChosen(List<LeaderCard> leaders) throws RemoteException;

    /**
     * Abstract matchStarted
     */
    void matchStarted(int roomPlayers, String familyColour) throws RemoteException;

    /**
     * Abstract timerTurnDelayed
     */
    void timerTurnDelayed() throws RemoteException;

    /**
     * Abstract tileChoosen
     */
    int tileChoosen(List<Tile> tiles) throws RemoteException;

    /**
     * Abstract nicknameAlreadyUsed
     */
    void nicknameAlreadyUsed() throws RemoteException;

    /**
     * Abstract waitForYourTurn
     */
    void waitForYourTurn() throws RemoteException;

    /**
     * Abstract prayed
     */
    void prayed() throws RemoteException;

    /**
     * Abstract sendChoicePE
     */
    int sendChoicePE() throws RemoteException;

    /**
     * Abstract sendStatistics
     */
    void sendStatistics(PlayerFile playerFile) throws RemoteException;

    /**
     * Abstract sendRanking
     */
    void sendRanking(List<PlayerFile> ranking) throws RemoteException;

    /**
     * Abstract afterMatch
     */
    void afterMatch() throws RemoteException;

    /**
     * abstract winner comunication
     */
    void winnerComunication(String winnerString) throws RemoteException;

    /**
     * abstarct afterGameIfTemporaryOff
     */
    void afterGameIfTemporaryOff() throws RemoteException;

    /**
     * abstract disconectionNotification
     *
     * @param name name
     * @throws RemoteException remote exception
     */
    void disconectionNotification(String name) throws RemoteException;
}

