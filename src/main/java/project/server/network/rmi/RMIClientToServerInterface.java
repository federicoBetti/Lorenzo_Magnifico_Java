package project.server.network.rmi;


import project.client.network.rmi.RMIServerToClientInterface;

import java.io.IOException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

/**
 * interface in which there are all the methods called from client to server uscing RMI connection
 */
 public interface RMIClientToServerInterface extends  Remote{

    /**
     *
     * @param player instance of the payer that wants to build a connection
     * @return unique string to identify each singe player
     * @throws RemoteException A RemoteException is the common superclass for a number of communication-related exceptions
     * that may occur during the execution of a remote method call. Each method of a remote interface, an interface that extends java.rmi.
     * Remote, must list RemoteException in its throws clause.
     */
     String connect(RMIServerToClientInterface player) throws RemoteException;

    /**
     * method called by the client that wants to make the login
     * @param clientUniqueId player's unique identifier
     * @param nickname player's nickname
     * @throws IOException A RemoteException is the common superclass for a number of communication-related exceptions
     * that may occur during the execution of a remote method call. Each method of a remote interface, an interface that extends java.rmi.
     * Remote, must list RemoteException in its throws clause.
     */
     void loginRequest(String clientUniqueId, String nickname) throws IOException;

    /**
     *
     * @param clientUniqueId player's unique identifier
     * @param towerColour colour of tower selected by the client
     * @param floor floor of the tower selcted by the client
     * @param familyMemberColour color of the familiar chosen by the client to perform the action
     * @throws RemoteException A RemoteException is the common superclass for a number of communication-related exceptions
     * that may occur during the execution of a remote method call. Each method of a remote interface, an interface that extends java.rmi.
     * Remote, must list RemoteException in its throws clause.
     */
     void takeDevCard(String clientUniqueId, String towerColour, int floor, String familyMemberColour) throws RemoteException;

    /**
     *
     * @param clientUniqueId player's unique identifier
     * @param familyMemberColour color of the familiar chosen by the client to perform the action
     * @param servantsNumber number of servants used to perform the action
     * @throws RemoteException A RemoteException is the common superclass for a number of communication-related exceptions
     * that may occur during the execution of a remote method call. Each method of a remote interface, an interface that extends java.rmi.
     * Remote, must list RemoteException in its throws clause.
     */
     void harvesterRequest(String clientUniqueId, String familyMemberColour, int servantsNumber)throws RemoteException;

    /**
     *
     * @param clientUniqueId player's unique identifier
     * @param familyMemberColour color of the familiar chosen by the client to perform the action
     * @param cards cards selected for production action
     * @throws RemoteException A RemoteException is the common superclass for a number of communication-related exceptions
     * that may occur during the execution of a remote method call. Each method of a remote interface, an interface that extends java.rmi.
     * Remote, must list RemoteException in its throws clause.
     */
     void productionRequest(String clientUniqueId, String familyMemberColour, List<String> cards)throws RemoteException;

    /**
     *
     * @param clientUniqueId player's unique identifier
     * @param position position in the market where the client want to place the familiar
     * @param familyMemberColour color of the familiar chosen by the client to perform the action
     * @throws RemoteException A RemoteException is the common superclass for a number of communication-related exceptions
     * that may occur during the execution of a remote method call. Each method of a remote interface, an interface that extends java.rmi.
     * Remote, must list RemoteException in its throws clause.
     */
     void goToMarketRequest(String clientUniqueId,int position, String familyMemberColour) throws RemoteException ;

    /**
     *
     * @param clientUniqueId player's unique identifier
     * @param leaderCardName name of the leader card selected
     * @throws RemoteException A RemoteException is the common superclass for a number of communication-related exceptions
     * that may occur during the execution of a remote method call. Each method of a remote interface, an interface that extends java.rmi.
     * Remote, must list RemoteException in its throws clause.
     */
     void playLeaderCardRequest(String clientUniqueId, String leaderCardName) throws RemoteException ;

    /**
     *
     * @param clientUniqueId player's unique identifier
     * @param leaderCardName name of the leader card selected
     * @throws RemoteException A RemoteException is the common superclass for a number of communication-related exceptions
     * that may occur during the execution of a remote method call. Each method of a remote interface, an interface that extends java.rmi.
     * Remote, must list RemoteException in its throws clause.
     */
     void discardLeaderCardRequest(String clientUniqueId, String leaderCardName)throws RemoteException;

    /**
     *
     * @param clientUniqueId player's unique identifier
     * @param privilegeNumber number of the privilege selcted
     * @param familyMemberColour color of the familiar chosen by the client to perform the action
     * @throws RemoteException A RemoteException is the common superclass for a number of communication-related exceptions
     * that may occur during the execution of a remote method call. Each method of a remote interface, an interface that extends java.rmi.
     * Remote, must list RemoteException in its throws clause.
     */
     void goToCouncilPalaceRequest(String clientUniqueId, int privilegeNumber, String familyMemberColour) throws RemoteException;

    /**
     *
     * @param clientUniqueId player's unique identifier
     * @throws RemoteException A RemoteException is the common superclass for a number of communication-related exceptions
     * that may occur during the execution of a remote method call. Each method of a remote interface, an interface that extends java.rmi.
     * Remote, must list RemoteException in its throws clause.
     */
     void exitOnBonusAction(String clientUniqueId) throws RemoteException;

    /**
     *
     * @param clientUniqueId player's unique identifier
     * @param servantsNumber number of servants used to perform the action
     * @throws RemoteException A RemoteException is the common superclass for a number of communication-related exceptions
     * that may occur during the execution of a remote method call. Each method of a remote interface, an interface that extends java.rmi.
     * Remote, must list RemoteException in its throws clause.
     */
     void sendBonusHarvester(String clientUniqueId, int servantsNumber) throws RemoteException;

    /**
     *
     * @param clientUniqueId player's unique identifier
     * @param parameters list of cards selected for the production
     * @throws RemoteException A RemoteException is the common superclass for a number of communication-related exceptions
     * that may occur during the execution of a remote method call. Each method of a remote interface, an interface that extends java.rmi.
     * Remote, must list RemoteException in its throws clause.
     */
     void sendBonusProduction(String clientUniqueId, List<String> parameters) throws RemoteException;

    /**
     *
     * @param clientUniqueId player's unique identifier
     * @param floor floor of the tower selcted by the client
     * @param towerColour colour of tower selected by the client
     * @throws RemoteException A RemoteException is the common superclass for a number of communication-related exceptions
     * that may occur during the execution of a remote method call. Each method of a remote interface, an interface that extends java.rmi.
     * Remote, must list RemoteException in its throws clause.
     */
     void sendBonusCardAction(String clientUniqueId, int floor, String towerColour) throws RemoteException;

    /**
     *
     * @param clientUniqueId player's unique identifier
     * @param privileges list of privilege selected
     * @throws RemoteException A RemoteException is the common superclass for a number of communication-related exceptions
     * that may occur during the execution of a remote method call. Each method of a remote interface, an interface that extends java.rmi.
     * Remote, must list RemoteException in its throws clause.
     */
     void sendImmediatePrivileges(String clientUniqueId, List<Integer> privileges) throws RemoteException;

    /**
     *
     * @throws RemoteException A RemoteException is the common superclass for a number of communication-related exceptions
     * that may occur during the execution of a remote method call. Each method of a remote interface, an interface that extends java.rmi.
     * Remote, must list RemoteException in its throws clause.
     */
     void ping() throws RemoteException;

    /**
     *
     * @param clientUniqueId player's unique identifier
     * @throws RemoteException A RemoteException is the common superclass for a number of communication-related exceptions
     * that may occur during the execution of a remote method call. Each method of a remote interface, an interface that extends java.rmi.
     * Remote, must list RemoteException in its throws clause.
     */
     void skipTurn(String clientUniqueId) throws RemoteException;

    /**
     *
     * @param clientUniqueId player's unique identifier
     * @throws RemoteException A RemoteException is the common superclass for a number of communication-related exceptions
     * that may occur during the execution of a remote method call. Each method of a remote interface, an interface that extends java.rmi.
     * Remote, must list RemoteException in its throws clause.
     */
     void reconnect(String clientUniqueId) throws RemoteException;

    /**
     *
     * @param clientUniqueId player's unique identifier
     * @throws RemoteException A RemoteException is the common superclass for a number of communication-related exceptions
     * that may occur during the execution of a remote method call. Each method of a remote interface, an interface that extends java.rmi.
     * Remote, must list RemoteException in its throws clause.
     */
     void askForStatistics(String clientUniqueId) throws RemoteException;

    /**
     *
     * @param clientUniqueId player's unique identifier
     * @param nickname nickname used for the new game request
     * @throws RemoteException A RemoteException is the common superclass for a number of communication-related exceptions
     * that may occur during the execution of a remote method call. Each method of a remote interface, an interface that extends java.rmi.
     * Remote, must list RemoteException in its throws clause.
     */
     void newGameRequest(String clientUniqueId, String nickname) throws RemoteException;

    /**
     *
     * @param clientUniqueId player's unique identifier
     * @throws RemoteException A RemoteException is the common superclass for a number of communication-related exceptions
     * that may occur during the execution of a remote method call. Each method of a remote interface, an interface that extends java.rmi.
     * Remote, must list RemoteException in its throws clause.
     */
     void askForRanking(String clientUniqueId) throws RemoteException;
 }
