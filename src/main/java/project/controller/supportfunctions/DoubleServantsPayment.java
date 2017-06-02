package project.controller.supportfunctions;

import project.controller.cardsfactory.VenturesCard;
import project.model.*;
import project.messages.BonusInteraction;
import project.server.network.PlayerHandler;

import java.util.List;

/**
 * todo modificare i ritorni degli altri
 */
public class DoubleServantsPayment implements SupportFunctionsDecorator{
    AllSupportFunctions allSupportFunctions = null;

    public DoubleServantsPayment(AllSupportFunctions allSupportFunctions){
        this.allSupportFunctions = allSupportFunctions;
    }


    @Override
    public BonusInteraction applyEffects(DevelopmentCard card, PlayerHandler player) {
        return null;
    }

    @Override
    public void setFamiliar(Position zone, FamilyMember familyMember) {
        //to complete
    }

    @Override
    public void placeCardInPersonalBoard(DevelopmentCard card) {
        //to complete
    }

    @Override
    public void setDicesValue(int[] newDiceValue, Player p) {
        //to complete
    }

    @Override
    public void setFamiliarInTheCouncilPalace(List<Council> councilZone, FamilyMember familyMember) {
        //to complete
    }


    @Override
    public void takeCouncilPrivilege(int privelgeNumber) {
        //to complete
    }

    @Override
    public void takeMarketAction(int position) {
        //to complete
    }

    @Override
    public void payCard(DevelopmentCard cardOnThisFloor, boolean towerIsOccupied, int zoneDiceCost, int valueOfFamilyMember) {
        //to complete
    }

    @Override
    public void payVenturesCard(VenturesCard card, Player player, boolean coinsFee, int zoneDiceCost, int valueOfFamilyMember, int paymentChoosen) {
        //to complete
    }

    @Override
    public void pray(int victoryPointsToAdd) {
        //to complete
    }

    @Override
    public int payServants(int cost, int value) {
        if ((cost - value) < 0)
            return 0;
        else
            return (cost - value) * 2;
        }

    @Override
    public int finalPointsFromTerritoryCard(List<Integer> victoryPoints) {
        //to complete
        return 0;
    }

    @Override
    public void finalPointsFromVenturesCard() {
        //to complete
    }

    @Override
    public int finalPointsFromCharacterCard(List<Integer> victoryPoints) {
        //to complete
        return 0;
    }

    @Override
    public int extraLostOfPoints(PlayerHandler playerHandler) {
        //to complete
        return 0;
    }

}

