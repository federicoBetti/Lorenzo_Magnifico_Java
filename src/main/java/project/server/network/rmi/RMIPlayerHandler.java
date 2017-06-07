package project.server.network.rmi;

import project.client.network.rmi.RMIServerToClientInterface;
import project.controller.Constants;
import project.controller.cardsfactory.BuildingCard;
import project.messages.*;
import project.model.FamilyMember;
import project.server.network.PlayerHandler;
import project.server.network.exception.CanUseBothPaymentMethodException;
import project.server.network.exception.CantDoActionException;

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

    RMIServerToClientInterface myClient;
    HashMap<String,talker> bonusType;

    RMIPlayerHandler(RMIServerToClientInterface rmiServerToClientInterface){
        this.myClient = rmiServerToClientInterface;
        fillHashMapBonusType();
    }

    private void fillHashMapBonusType() {
        bonusType.put(Constants.TOWER_ACTION, myClient::takeAnotherCard);
        bonusType.put(Constants.BONUS_PRODUCTION_HARVESTER_ACTION, this::takeAnotherCard);
        bonusType.put(Constants.OK_OR_NO, this::takeAnotherCard);
        bonusType.put(Constants.TAKE_PRIVILEGE_ACTION, this::takeAnotherCard);
        //todo finire di mettere le cose nella mappa e fare l'interfaccia e fare le chiamate di metodo diverse in base alla stringa
    }

    private interface talker{
        void sendEffectAnswer(BonusInteraction bonusInteraction);
    }

    @Override
    public void sendAnswer(BonusInteraction returnFromEffect) {
        myClient
    }

    @Override
    public void cantDoAction(OkOrNo okOrNo) {
        // to implement
    }

    @Override
    public void canUseBothPaymentMethod(BothCostCanBeSatisfied bothCosts) {
        // to implement
    }

    @Override
    public void itsMyTurn() {
        // to implement
    }

    @Override
    public void sendAskForPraying() {
        // to implement
    }

    // qua inizia la parte delle chiamate del client sul server

    void takeDevCard(String towerColour, int floor, String familyMemberColour){
        FamilyMember familyMember = findFamilyMember(familyMemberColour);
        try {
            clientTakeDevelopementCard(towerColour,floor,familyMember);
        } catch (CantDoActionException e) {
            cantDoAction(new OkOrNo(false));
        } catch (CanUseBothPaymentMethodException e) {
            canUseBothPaymentMethod(new BothCostCanBeSatisfied());
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
            cantDoAction(new OkOrNo(false));
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
            cantDoAction(new OkOrNo(false));
        }
    }

    void goToMarketRequest(int position, String familyMemberColour)  {
        FamilyMember familyMember = findFamilyMember(familyMemberColour);
        try {
            goToMarket(position, familyMember);
        } catch (CantDoActionException e) {
            cantDoAction(new OkOrNo(false));
        }
    }

    void playLeaderCardRequest(String leaderCardName)  {
        try {
            playLeaderCard(leaderCardName);
        } catch (CantDoActionException e) {
            cantDoAction(new OkOrNo(false));
        }
    }

    void discardLeaderCardRequest(String leaderCardName)  {
        try {
            discardLeaderCard(leaderCardName);
        } catch (CantDoActionException e) {
            cantDoAction(new OkOrNo(false));
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
