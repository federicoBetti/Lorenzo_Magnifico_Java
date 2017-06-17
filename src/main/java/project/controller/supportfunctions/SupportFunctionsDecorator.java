package project.controller.supportfunctions;


import project.controller.cardsfactory.VenturesCard;
import project.messages.BonusInteraction;
import project.model.*;
import project.server.network.PlayerHandler;

import java.util.List;

public abstract class SupportFunctionsDecorator implements AllSupportFunctions {


    AllSupportFunctions allSupportFunctions = null;

    public SupportFunctionsDecorator(AllSupportFunctions allSupportFunctions){
        this.allSupportFunctions = allSupportFunctions;
    }

    @Override
    public BonusInteraction applyEffects(DevelopmentCard card, PlayerHandler player) {
        return allSupportFunctions.applyEffects(card,player);
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
    public void setFamiliarInTheCouncilPalace(List<Council> councilZone, FamilyMember familyMember) {
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
    public int finalPointsFromTerritoryCard(List<Integer> victoryPoints) {
        return allSupportFunctions.finalPointsFromTerritoryCard(victoryPoints);
    }

    @Override
    public void finalPointsFromVenturesCard() {
        allSupportFunctions.finalPointsFromVenturesCard();
    }

    @Override
    public int finalPointsFromCharacterCard(List<Integer> victoryPoints) {
        return allSupportFunctions.finalPointsFromCharacterCard(victoryPoints);
    }

    @Override
    public int extraLostOfPoints(PlayerHandler playerHandler) {
        return allSupportFunctions.extraLostOfPoints(playerHandler);
    }
}
