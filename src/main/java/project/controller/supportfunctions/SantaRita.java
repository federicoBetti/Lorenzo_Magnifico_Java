package project.controller.supportfunctions;

import project.controller.cardsfactory.VenturesCard;
import project.controller.effects.realeffects.Effects;
import project.controller.effects.realeffects.TakeRoPEffects;
import project.model.*;
import project.messages.BonusInteraction;
import project.server.network.PlayerHandler;

import java.util.List;


public class SantaRita implements SupportFunctionsDecorator {
    AllSupportFunctions allSupportFunctions = null;

    SantaRita(AllSupportFunctions allSupportFunctions){
        this.allSupportFunctions = allSupportFunctions;
    }


   // public int pray(PlayerHandler player) {
       // return allSupportFunctions.pray(player);
    //}
    //todo che problemi ha pray?


    @Override
    public BonusInteraction applyEffects(DevelopmentCard card, PlayerHandler player) {
        for (Effects e: card.getImmediateCardEffects()){
            e.doEffect(player);
            if (e instanceof TakeRoPEffects) {
                e.doEffect(player);
            }
        }
        return null;
    }

    @Override
    public void setFamiliar(Position zone, FamilyMember familyMember) {
        //to implement
    }

    @Override
    public void placeCardInPersonalBoard(DevelopmentCard card) {
        //to implement
    }

    @Override
    public void setDicesValue(int[] newDiceValue, Player p) {
        //to implement
    }

    @Override
    public void setFamiliarInTheCouncilPalace(List<Council> councilZone, FamilyMember familyMember) {
        //to implement
    }

    @Override
    public void takeCouncilPrivilege(int privelgeNumber) {
        //to implement
    }

    @Override
    public void takeMarketAction(int position) {
        //to implement
    }

    @Override
    public void payCard(DevelopmentCard cardOnThisFloor, boolean towerIsOccupied, int zoneDiceCost, int valueOfFamilyMember) {
        //to implement
    }

    @Override
    public void payVenturesCard(VenturesCard card, Player player, boolean coinsFee, int zoneDiceCost, int valueOfFamilyMember, int paymentChoosen) {
        //to implement
    }

    @Override
    public void pray(int victoryPointsToAdd) {
        //to implement
    }

    @Override
    public int payServants(int cost, int value) {
        return 0;
    }

    @Override
    public int finalPointsFromTerritoryCard(List<Integer> victoryPoints) {
        return 0;
    }

    @Override
    public void finalPointsFromVenturesCard() {
        //to implement
    }

    @Override
    public int finalPointsFromCharacterCard(List<Integer> victoryPoints) {
        return 0;
    }

    @Override
    public int extraLostOfPoints(PlayerHandler playerHandler) {
        return 0;
    }

}
