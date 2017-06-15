package project.server.network.rmi;

import project.client.network.rmi.RMIServerToClientInterface;
import project.controller.Constants;
import project.controller.cardsfactory.BuildingCard;
import project.messages.*;
import project.messages.updatesmessages.Updates;
import project.model.FamilyMember;
import project.server.network.PlayerHandler;
import project.server.network.exception.CanUseBothPaymentMethodException;
import project.server.network.exception.CantDoActionException;

import java.io.IOException;
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
public class RMIPlayerHandler extends PlayerHandler{

    private RMIServerToClientInterface myClient;
    private HashMap<String,Talker> bonusType;

    RMIPlayerHandler(RMIServerToClientInterface rmiServerToClientInterface){
        this.myClient = rmiServerToClientInterface;
        fillHashMapBonusType();
    }

    private void fillHashMapBonusType() {
        bonusType.put(Constants.TOWER_ACTION, myClient::takeAnotherCard);
        bonusType.put(Constants.BONUS_PRODUCTION_HARVESTER_ACTION, myClient::doProductionHarvester);
        bonusType.put(Constants.OK_OR_NO, myClient::ok);
        bonusType.put(Constants.TAKE_PRIVILEGE_ACTION, myClient::takePrivilege);
    }

    private interface Talker{

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
    public void cantDoAction() {
        try {
            myClient.cantDoAction();
        } catch (RemoteException e) {
            //todo gestire eccezzione di rete
        }
    }

    @Override
    public int canUseBothPaymentMethod()  {
        try {
            myClient.canUseBothPaymentMethod();
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
    public void sendAskForPraying()  {
        try {
            myClient.askForPraying();
        } catch (RemoteException e) {
            //todo gestire eccezzione di rete
        }
    }

    @Override
    public void sendString(String message) {
        //todo uaglio
    }

    @Override
    public void sendNotification(Notify notifications) {

    }

    @Override
    public void sendUpdates(Updates updates) {

    }

    @Override
    public int sendPossibleChoice(String kindOfChoice){
        return 0;
    }

    @Override
    public void sendBonusTowerAction(BonusInteraction returnFromEffect) throws IOException {

    }

    // qua inizia la parte delle chiamate del client sul server

    void takeDevCard(String towerColour, int floor, String familyMemberColour) throws RemoteException {
        FamilyMember familyMember = findFamilyMember(familyMemberColour);
        try {
            clientTakeDevelopmentCard(towerColour,floor,familyMember);
        } catch (CantDoActionException e) {
            cantDoAction();
        }
    }




    void choosePaymentForVenturesCard(int position, int paymentChosen, String familyMemberColour) {
        FamilyMember familyMember = findFamilyMember(familyMemberColour);
        clientChosenPaymentForVenturesCard(position, familyMember, paymentChosen );
    }

    void harvesterRequest(int position, String familyMemberColour, int servantsNumber)  {
        FamilyMember familyMember = findFamilyMember(familyMemberColour);
        try {
            harvester(position, familyMember, servantsNumber);
        } catch (CantDoActionException e) {
            cantDoAction();
        }
    }

    void productionRequest(int position, String familyMemberColour, List<String> cards)  {
        FamilyMember familyMember = findFamilyMember(familyMemberColour);
        ArrayList<BuildingCard> buildingCards = new ArrayList<>();
        for (BuildingCard buildingCard: getPersonalBoardReference().getBuildings()){
            if (cards.contains(buildingCard.getName())){
                buildingCards.add(buildingCard);
            }
        }
        try {
            production(position, familyMember, buildingCards );
        } catch (CantDoActionException e) {
            cantDoAction();
        }
    }

    void goToMarketRequest(int position, String familyMemberColour)  {
        FamilyMember familyMember = findFamilyMember(familyMemberColour);
        try {
            goToMarket(position, familyMember);
        } catch (CantDoActionException e) {
            cantDoAction();
        }
    }

    void playLeaderCardRequest(String leaderCardName)  {
        try {
            playLeaderCard(leaderCardName);
        } catch (CantDoActionException e) {
            cantDoAction();
        }
    }

    void discardLeaderCardRequest(String leaderCardName)  {
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
}
