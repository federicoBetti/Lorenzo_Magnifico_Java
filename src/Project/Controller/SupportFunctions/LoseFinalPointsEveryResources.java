package Project.Controller.SupportFunctions;

import Project.Controller.CardsFactory.VenturesCard;
import Project.MODEL.*;
import Project.Server.Network.PlayerHandler;
import Project.toDelete.BonusInteraction;

import java.util.ArrayList;

/**
 * Created by federico on 26/05/17.
 */
public class LoseFinalPointsEveryResources implements SupportFunctionsDecorator {


    AllSupportFunctions allSupportFunctions = null;

    public LoseFinalPointsEveryResources(AllSupportFunctions allSupportFunctions){
        this.allSupportFunctions = allSupportFunctions;
    }


    @Override
    public int Pray(Player player) {
        return allSupportFunctions.Pray(player);
    }

    @Override
    public BonusInteraction ApplyEffects(DevelopmentCard card, PlayerHandler player) {
        return allSupportFunctions.ApplyEffects(card,player);
    }

    @Override
    public void setFamiliar(Position zone, FamilyMember familyMember) {
        allSupportFunctions.setFamiliar(zone,familyMember);
    }

    @Override
    public void placeCardInPersonalBoard(DevelopmentCard card) {
        allSupportFunctions.placeCardInPersonalBoard(card);
    }

    @Override
    public void setDicesValue(int[] newDiceValue, Player p) {
        allSupportFunctions.setDicesValue(newDiceValue,p);
    }

    @Override
    public void setFamiliarInTheCouncilPalace(ArrayList<Council> councilZone, FamilyMember familyMember) {
        allSupportFunctions.setFamiliarInTheCouncilPalace(councilZone,familyMember);
    }

    @Override
    public void takeCouncilPrivilege(int privelgeNumber) {
        allSupportFunctions.takeCouncilPrivilege(privelgeNumber);
    }

    @Override
    public void takeMarketAction(int position) {
        allSupportFunctions.takeMarketAction(position);
    }

    @Override
    public void payCard(DevelopmentCard cardOnThisFloor, boolean towerIsOccupied, int zoneDiceCost, int valueOfFamilyMember) {
        allSupportFunctions.payCard(cardOnThisFloor,towerIsOccupied,zoneDiceCost,valueOfFamilyMember);
    }

    @Override
    public void payVenturesCard(VenturesCard card, Player player, boolean coinsFee, int zoneDiceCost, int valueOfFamilyMember, int paymentChoosen) {
        allSupportFunctions.payVenturesCard(card,player,coinsFee,zoneDiceCost,valueOfFamilyMember,paymentChoosen);
    }

    @Override
    public void pray(int victoryPointsToAdd) {
        allSupportFunctions.pray(victoryPointsToAdd);
    }

    @Override
    public int payServants(int cost, int value) {
        return allSupportFunctions.payServants(cost,value);
    }

    @Override
    public int finalPointsFromTerritoryCard(ArrayList<Integer> victoryPoints) {
        return allSupportFunctions.finalPointsFromTerritoryCard(victoryPoints);
    }

    @Override
    public void finalPointsFromVenturesCard() {
        allSupportFunctions.finalPointsFromVenturesCard();
    }

    @Override
    public int finalPointsFromCharacterCard(ArrayList<Integer> victoryPoints) {
        return allSupportFunctions.finalPointsFromCharacterCard(victoryPoints);
    }

    @Override
    public int extraLostOfPoints(PlayerHandler playerHandler) {
        int numberOfResources;
        numberOfResources = playerHandler.getPersonalBoardReference().getCoins();
        numberOfResources = numberOfResources + playerHandler.getPersonalBoardReference().getServants();
        numberOfResources = numberOfResources + playerHandler.getPersonalBoardReference().getStone();
        numberOfResources = numberOfResources + playerHandler.getPersonalBoardReference().getWood();
        return numberOfResources;
    }
}
