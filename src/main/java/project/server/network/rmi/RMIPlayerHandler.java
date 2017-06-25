package project.server.network.rmi;

import project.client.network.rmi.RMIServerToClientInterface;
import project.controller.Constants;
import project.controller.cardsfactory.BuildingCard;
import project.controller.cardsfactory.LeaderCard;
import project.messages.*;
import project.messages.updatesmessages.Updates;
import project.model.FamilyMember;
import project.server.network.PlayerHandler;
import project.server.network.exception.CantDoActionException;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * questo esetende playerHandler, quindi deve fare un override dei metodi di ritorno. da qua passeranno i metodi di
 * ritorno. la game actions chiamerà i metodi di ritorno che sono sul player handler e verranno usati quelli in socket
 * o rmi in base alla conessione del giocatore
 * per quanto riguarda client -> server: da qui chiamo i metodi sulla classe PlayerHandler, che faranno i contrlli e
 * chiamerà il metodo effettivo sulla gameactions.
 */
public class RMIPlayerHandler extends PlayerHandler {

    private transient RMIServerToClientInterface myClient;
    private HashMap<String, Talker> bonusType;


    private BonusProductionOrHarvesterAction lastHarvProd;
    private TowerAction lastTowerAction;

    RMIPlayerHandler(RMIServerToClientInterface rmiServerToClientInterface) {
        this.myClient = rmiServerToClientInterface;
        bonusType = new HashMap<>(4);
        fillHashMapBonusType();
    }

    private void fillHashMapBonusType() {
        bonusType.put(Constants.TOWER_ACTION, myClient::takeAnotherCard);
        bonusType.put(Constants.BONUS_PRODUCTION_HARVESTER_ACTION, myClient::doProductionHarvester);
        bonusType.put(Constants.OK_OR_NO, myClient::ok);
        bonusType.put(Constants.TAKE_PRIVILEGE_ACTION, myClient::takePrivilege);
    }

    public void doBonusHarvester(int servantsNumber) {
        try {
            doBonusHarv(lastHarvProd,servantsNumber);
        } catch (CantDoActionException e) {
            e.printStackTrace();
        }
    }

    public void doBonusProduction(List<String> parameters) {
        ArrayList<BuildingCard> buildingCards = new ArrayList<>();
        for (BuildingCard buildingCard : getPersonalBoardReference().getBuildings()) {
            if (parameters.contains(buildingCard.getName())) {
                buildingCards.add(buildingCard);
            }
        }
        try {
            doBonusProduct(lastHarvProd, buildingCards);
        } catch (CantDoActionException e) {
            e.printStackTrace();
        }
    }

    public void takeBonusCardAction(int floor, String towerColour) {
        try {
            clientTakeBonusDevelopementCard(towerColour,floor,lastTowerAction);
        } catch (CantDoActionException e) {
            e.printStackTrace();
        }
    }

    public void takeImmediatePrivileges(List<Integer> privileges) {
        for (Integer i: privileges)
            takePrivilege(i.intValue());
    }


    private interface Talker {

        void sendEffectAnswer(BonusInteraction bonusInteraction) throws RemoteException;
    }

    @Override
    public void sendAnswer(Object returnFromEffect) {
        // chiama il metodo giusto sul client
        try {
            bonusType.get(returnFromEffect.toString()).sendEffectAnswer((BonusInteraction) returnFromEffect);
        } catch (RemoteException e) {
            //todo gestire eccezzione dii rete
        }
    }

    @Override
    public void sendNotification(Notify notifications) {
        try {
            myClient.sendNotification(notifications);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void sendUpdates(Updates updates) {
        try {
            System.out.println(updates.toScreen());
            myClient.sendUpdates(updates);
        } catch (RemoteException e) {
            System.out.println(e.getCause());
            System.out.println(e.getMessage());
        }
    }

    @Override
    public int sendPossibleChoice(String kindOfChoice) {
        return 1;
    }

    @Override
    public void sendBonusTowerAction(TowerAction returnFromEffect){
        lastTowerAction = returnFromEffect;
        try {
            myClient.bonusTowerAction(returnFromEffect);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void sendBonusProdOrHarv(BonusProductionOrHarvesterAction returnFromEffect) {
        try {
            this.lastHarvProd = returnFromEffect;
            myClient.sendBonusProdHarv(returnFromEffect);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void sendRequestForPriviledges(TakePrivilegesAction returnFromEffect) {
        try {
            myClient.sendRequestForPrivileges(returnFromEffect);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void cantDoAction() {
        try {
            myClient.cantDoAction();
        } catch (RemoteException e) {
            //todo gestire eccezzione di rete
        }
    }

    @Override
    public int canUseBothPaymentMethod() {
        try {
             return myClient.canUseBothPaymentMethod();
        } catch (RemoteException e) {
            //todo gestire eccezzione di rete
        }
        return 0;
    }

    @Override
    public void itsMyTurn() {
        try {
            myClient.itMyTurn();
        } catch (RemoteException e) {
            //todo gestire eccezzione di rete
        }
    }

    @Override
    public void sendAskForPraying() {
        try {
            myClient.askForPraying();
        } catch (RemoteException e) {
            //todo gestire eccezzione di rete
        }
    }

    @Override
    public void sendActionOk() {
        try {
            myClient.actionOk();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void timerTurnDelayed() {
        try {
            myClient.timerTurnDelayed();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void nicknameAlredyUsed() {

    }

    @Override
    public void loginSucceded() {
        System.out.println("login RMI succeded");
        try {
            myClient.loginSucceded();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String leaderCardChosen(List<LeaderCard> leaders) {
        try {
            String leaderName = myClient.leaderCardChosen(leaders);
            System.out.println(leaderName);
            return leaderName;
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void matchStarted(int roomPlayers, String familyColour) {
        try {
            myClient.matchStarted(roomPlayers,familyColour);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void sendString(String message) {
        //todo uaglio
    }



    // qua inizia la parte delle chiamate del client sul server

    void takeDevCard(String towerColour, int floor, String familyMemberColour) throws RemoteException {
        FamilyMember familyMember = findFamilyMember(familyMemberColour);
        try {
            clientTakeDevelopmentCard(towerColour, floor, familyMember);
        } catch (CantDoActionException e) {
            cantDoAction();
        }
    }


    void choosePaymentForVenturesCard(int position, int paymentChosen, String familyMemberColour) {
        FamilyMember familyMember = findFamilyMember(familyMemberColour);
        clientChosenPaymentForVenturesCard(position, familyMember, paymentChosen);
    }

    void harvesterRequest(String familyMemberColour, int servantsNumber) {
        FamilyMember familyMember = findFamilyMember(familyMemberColour);
        try {
            harvester(familyMember, servantsNumber);
        } catch (CantDoActionException e) {
            cantDoAction();
        }
    }

    void productionRequest(String familyMemberColour, List<String> cards) {
        FamilyMember familyMember = findFamilyMember(familyMemberColour);
        ArrayList<BuildingCard> buildingCards = new ArrayList<>();
        for (BuildingCard buildingCard : getPersonalBoardReference().getBuildings()) {
            if (cards.contains(buildingCard.getName())) {
                buildingCards.add(buildingCard);
            }
        }
        try {
            production(familyMember, buildingCards);
        } catch (CantDoActionException e) {
            cantDoAction();
        }
    }

    void goToMarketRequest(int position, String familyMemberColour) {
        FamilyMember familyMember = findFamilyMember(familyMemberColour);
        try {
            goToMarket(position, familyMember);
        } catch (CantDoActionException e) {
            cantDoAction();
        }
    }

    void playLeaderCardRequest(String leaderCardName) {
        try {
            playLeaderCard(leaderCardName);
        } catch (CantDoActionException e) {
            cantDoAction();
        }
    }

    void discardLeaderCardRequest(String leaderCardName) {
        try {
            discardLeaderCard(leaderCardName);
        } catch (CantDoActionException e) {
            cantDoAction();
        }
    }

    void goToCouncilPalaceRequest(int privilegeNumber, String familyMemberColour) {
        FamilyMember familyMember = findFamilyMember(familyMemberColour);
        goToCouncilPalace(privilegeNumber, familyMember);
    }

    void takePrivilegeRequest(int privilegeNumber) {
        takePrivilege(privilegeNumber);
    }




    public void scelta() {
        int ciao = 0;
        try {
            ciao = myClient.getScelta();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        System.out.println(ciao);
        skipTurn();
    }
}
