package project.server.network.rmi;

//qua ci devono essere scritti tutti i metodi che il client puo chiamare sul server
// è diversa da quella controler interface perchè ci sono anche i metodi di connessione che
// sono quelli in più che verranno chiamati dal client sul server

import project.client.network.rmi.RMIServerToClientInterface;

import java.io.IOException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

 public interface RMIClientToServerInterface extends  Remote{

     String connect(RMIServerToClientInterface player) throws RemoteException;
     
     void loginRequest(String clientUniqueId, String nickname) throws IOException;

     void takeDevCard(String clientUniqueId, String towerColour, int floor, String familyMemberColour) throws RemoteException;

     void choosePaymentForVentureCard(String clientUniqueId, int position, int paymentChoosen, String familyMemberColour)throws RemoteException;

     void harvesterRequest(String clientUniqueId, String familyMemberColour, int servantsNumber)throws RemoteException;

     void productionRequest(String clientUniqueId, String familyMemberColour, List<String> cards)throws RemoteException;

     void goToMarketRequest(String clientUniqueId,int position, String familyMemberColour) throws RemoteException ;

     void playLeaderCardRequest(String clientUniqueId, String leaderCardName) throws RemoteException ;

     void discardLeaderCardRequest(String clientUniqueId, String leaderCardName)throws RemoteException;

     void goToCouncilPalaceRequest(String clientUniqueId, int privilegeNumber, String familyMemberColour) throws RemoteException;

     void takePrivilegeRequest(String clientUniqueId, int privilegeNumber)throws RemoteException;

     void prayOrNot(String myUniqueId,boolean action) throws RemoteException;

     void exitOnBonusAction(String myUniqueId) throws RemoteException;

     void setChoicePe(String myUniqueId,int input) throws RemoteException;

     void sendBonusHarvester(String myUniqueId, int servantsNumber) throws RemoteException;

     void sendBonusProduction(String myUniqueId, List<String> parameters) throws RemoteException;

     void sendBonusCardAction(String myUniqueId, int floor, String towerColour) throws RemoteException;

     void sendImmediatePrivileges(String myUniqueId, List<Integer> privileges) throws RemoteException;

     void sendChoicePaymentVc(String myUniqueId, int payment) throws RemoteException;

     void ping() throws RemoteException;

     void skipTurn(String myUniqueId) throws RemoteException;

     void scelta(String myUniqueId) throws RemoteException;

     void reconnect(String myUniqueId) throws RemoteException;
 }
