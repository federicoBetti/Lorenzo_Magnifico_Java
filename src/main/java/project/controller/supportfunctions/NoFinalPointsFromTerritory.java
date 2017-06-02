package project.controller.supportfunctions;

import project.controller.cardsfactory.VenturesCard;
import project.model.*;
import project.messages.BonusInteraction;
import project.server.network.PlayerHandler;
import java.util.List;

/**
 * Created by federico on 26/05/17.
 */
public class NoFinalPointsFromTerritory implements SupportFunctionsDecorator {



    AllSupportFunctions allSupportFunctions = null;

    public NoFinalPointsFromTerritory(AllSupportFunctions allSupportFunctions){
        this.allSupportFunctions = allSupportFunctions;
    }

    //todo public int Pray(Player player) {return 0;}

    @Override
    public BonusInteraction applyEffects(DevelopmentCard card, PlayerHandler player) {
        //to do implements
        return null;
    }

    @Override
    public void setFamiliar(Position zone, FamilyMember familyMember) {
        //to do implements
    }

    @Override
    public void placeCardInPersonalBoard(DevelopmentCard card) {
        //to do implements
    }

    @Override
    public void setDicesValue(int[] newDiceValue, Player p) {
        //to do implements
    }

    @Override
    public void setFamiliarInTheCouncilPalace(List<Council> councilZone, FamilyMember familyMember) {
        //to do implements
    }

    @Override
    public void takeCouncilPrivilege(int privelgeNumber) {
        //to do implements
    }

    @Override
    public void takeMarketAction(int position) {
        //to do implements
    }

    @Override
    public void payCard(DevelopmentCard cardOnThisFloor, boolean towerIsOccupied, int zoneDiceCost, int valueOfFamilyMember) {
        //to do implements
    }

    @Override
    public void payVenturesCard(VenturesCard card, Player player, boolean coinsFee, int zoneDiceCost, int valueOfFamilyMember, int paymentChoosen) {
        //to do implements
    }

    @Override
    public void pray(int victoryPointsToAdd) {
        //to do implements
    }

    @Override
    public int payServants(int cost, int value) {
        //to do implements
        return 0;
    }


    @Override
    public int finalPointsFromTerritoryCard(List<Integer> victoryPoints) {
        //to do implements
        return 0; //this is true
    }

    @Override
    public void finalPointsFromVenturesCard() {
        //to do implements
        return;
    }


    @Override
    public int finalPointsFromCharacterCard(List<Integer> victoryPoints) {
        //to do implements
        return 0;
    }

    @Override
    public int extraLostOfPoints(PlayerHandler playerHandler) {
        //to do implements
        return 0;
    }


}
