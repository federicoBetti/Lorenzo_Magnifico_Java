package project.server.network.rmi;

//qua ci devono essere scritti tutti i metodi che il client puo chiamare sul server
// è diversa da quella controler interface perchè ci sono anche i metodi di connessione che
// sono quelli in più che verranno chiamati dal client sul server

import project.client.network.rmi.RMIServerToClientInterface;
import project.server.network.ControllerInterface;

import java.io.IOException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

 public interface RMIClientToServerInterface extends ControllerInterface, Remote{

     String connect(RMIServerToClientInterface player) throws RemoteException;
     
     void loginRequest(String clientUniqueId, String nickname) ;

     void takeDevCard(String clientUniqueId, String towerColour, int floor, String familyMemberColour) throws RemoteException;

     void choosePaymentForVentureCard(String clientUniqueId, int position, int paymentChoosen, String familyMemberColour)throws RemoteException;

     void harvesterRequest(String clientUniqueId, int position, String familyMemberColour, int servantsNumber)throws RemoteException;

     void productionRequest(String clientUniqueId, int position, String familyMemberColour, List<String> cards )throws RemoteException;

     void goToMarketRequest(String clientUniqueId,int position, String familyMemberColour) throws RemoteException ;

     void playLeaderCardRequest(String clientUniqueId, String leaderCardName) throws RemoteException ;

     void discardLeaderCardRequest(String clientUniqueId, String leaderCardName)throws RemoteException;

     void goToCouncilPalaceRequest(String clientUniqueId, int privilegeNumber, String familyMemberColour) throws RemoteException;

     void takePrivilegeRequest(String clientUniqueId, int privilegeNumber)throws RemoteException;
}
