package project.server.network.rmi;

import project.client.network.rmi.RMIServerToClientInterface;
import project.controller.Constants;
import project.controller.cardsfactory.BuildingCard;
import project.controller.cardsfactory.LeaderCard;
import project.messages.*;
import project.messages.updatesmessages.Updates;
import project.model.FamilyMember;
import project.model.Tile;
import project.server.network.PlayerHandler;
import project.server.network.exception.CantDoActionException;

import java.rmi.RemoteException;
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
    private final Object tokenn;
    transient private String towerColourChosen;
    private int floorChosen;
    private int servantsNumber;
    private ArrayList<BuildingCard> productionCards;
    List<Integer> privileges;
    private int prayingChoice;
    private int paymentMethodChosen;

    RMIPlayerHandler(RMIServerToClientInterface rmiServerToClientInterface) {
        this.myClient = rmiServerToClientInterface;
        bonusType = new HashMap<>(4);
        fillHashMapBonusType();
        tokenn = new Object();
    }

    private void fillHashMapBonusType() {
        bonusType.put(Constants.TOWER_ACTION, myClient::takeAnotherCard);
        bonusType.put(Constants.BONUS_PRODUCTION_HARVESTER_ACTION, myClient::doProductionHarvester);
        bonusType.put(Constants.OK_OR_NO, myClient::ok);
        bonusType.put(Constants.TAKE_PRIVILEGE_ACTION, myClient::takePrivilege);
    }

    public void doBonusHarvester(int servantsNumber) {
        this.servantsNumber = servantsNumber;

        synchronized (tokenn){
            tokenn.notify();
        }
    }

    public void doBonusProduction(List<String> parameters) {
        ArrayList<BuildingCard> buildingCards = new ArrayList<>();
        for (BuildingCard buildingCard : getPersonalBoardReference().getBuildings()) {
            if (parameters.contains(buildingCard.getName())) {
                buildingCards.add(buildingCard);
            }
        }
        this.productionCards = buildingCards;

        synchronized (tokenn){
            tokenn.notify();
        }
    }

    public void takeBonusCardAction(int floor, String towerColour) {
        this.floorChosen = floor;
        this.towerColourChosen = towerColour;

        synchronized (tokenn){
            tokenn.notify();
        }

    }

    public void takeImmediatePrivilegesNotify(List<Integer> privileges) {
        this.privileges = privileges;
        System.out.println("ora faccio notify dei privilegi");
        synchronized (tokenn) {
            tokenn.notify();
        }
        System.out.println("ho notificato i privilegi");
    }

    public void exitOnBonusAction() {
        towerColourChosen = null;
        productionCards= null;
        servantsNumber = -1;
        privileges = null;
        prayingChoice = 1;

        synchronized (tokenn){
            System.out.println("sto per notificare tutti che mi è scaduto anche il timer");
            tokenn.notify();
        }
    }

    public void anserAskForPraying(int choice) {
        prayingChoice = choice;
        synchronized (tokenn){
            tokenn.notify();
        }
    }

    public void answerBothPayment(int choice) {
        paymentMethodChosen = choice;

        synchronized (tokenn){
            tokenn.notify();
        }
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
        while (true) {
            try {
                myClient.bonusTowerAction(returnFromEffect);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
            synchronized (tokenn){
                try {
                    tokenn.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            if (towerColourChosen == null)
                return;

            try {
                clientTakeBonusDevelopementCard(towerColourChosen, floorChosen, returnFromEffect);
                break;
            } catch (CantDoActionException e) {
                //it's correct that continue
            }

        }
    }

    @Override
    public void sendBonusProdOrHarv(BonusProductionOrHarvesterAction returnFromEffect) {
        while (true) {
            try {
                myClient.sendBonusProdHarv(returnFromEffect);
            } catch (RemoteException e) {
                e.printStackTrace();
            }

            synchronized (tokenn){
                try {
                    tokenn.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            if (productionCards == null )
                return;

            try {

                if (returnFromEffect.toString().equals(Constants.BONUS_HARVESTER)) {
                    if (servantsNumber == -1) return;
                    doBonusHarv(returnFromEffect, servantsNumber);
                }
                else{
                    if (productionCards == null) return;
                    doBonusProduct(returnFromEffect, productionCards);
                }

                break;
            }
            catch (CantDoActionException e){

            }
        }
    }

    @Override
    public void sendRequestForPriviledges(TakePrivilegesAction returnFromEffect) {
        privileges = new ArrayList<>();
        try {
             myClient.sendRequestForPrivileges(returnFromEffect);
        } catch (RemoteException e) {
            e.printStackTrace();
        }

        synchronized (tokenn){
            try {
                System.out.println("sto andando nel wait dei privilegi");
                tokenn.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        if (privileges == null)
            return;

        for (Integer i: privileges)
            takePrivilege(i);
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
    public int sendAskForPraying(List<PlayerHandler> playerTurn) {
        try {
            return myClient.askForPraying();
        } catch (RemoteException e) {
            //todo gestire eccezzione di rete
        }
        return 0;
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
            System.out.println("il giocatore si è disconnesso");
            setOn(false);
        }
    }

    @Override
    public void nicknameAlredyUsed() {
        try {
            myClient.nicknameAlreadyUsed();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
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
    protected void waitForYourTurn() {
        try {
            myClient.waitForYourTurn();
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
    public int chooseTile(ArrayList<Tile> tiles) {
        try {
            return myClient.tileChoosen(tiles);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return 0;
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
