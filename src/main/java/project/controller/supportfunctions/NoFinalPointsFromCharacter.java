package project.controller.supportfunctions;

import project.controller.cardsfactory.VenturesCard;
import project.model.*;
import project.messages.BonusInteraction;
import project.server.network.PlayerHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by federico on 26/05/17.
 */
public class NoFinalPointsFromCharacter implements SupportFunctionsDecorator {


    AllSupportFunctions allSupportFunctions = null;

    public NoFinalPointsFromCharacter(AllSupportFunctions allSupportFunctions){
        this.allSupportFunctions = allSupportFunctions;
    }

    @Override
    public BonusInteraction applyEffects(DevelopmentCard card, PlayerHandler player) {
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
        //to implement
        return 0;
    }


    @Override
    public int finalPointsFromTerritoryCard(List<Integer> victoryPoints) {
        return 0;
    }

    @Override
    public void finalPointsFromVenturesCard() {
        return;
    }


    @Override
    public int finalPointsFromCharacterCard(List<Integer> victoryPoints) {
        return 0; //this is true
    }

    @Override
    public int extraLostOfPoints(PlayerHandler playerHandler) {
        return 0;
    }


}
