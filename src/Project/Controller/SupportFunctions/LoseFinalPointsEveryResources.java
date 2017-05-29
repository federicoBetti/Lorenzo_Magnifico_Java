package Project.Controller.SupportFunctions;

import Project.Controller.CardsFactory.VenturesCard;
import Project.MODEL.*;
import Project.Server.Network.PlayerHandler;
import Project.Messages.BonusInteraction;

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
        return 0;
    }

    @Override
    public BonusInteraction ApplyEffects(DevelopmentCard card, Player player) {
        return null;
    }

    @Override
    public void setFamiliar(Position zone, FamilyMember familyMember) {

    }

    @Override
    public void placeCardInPersonalBoard(DevelopmentCard card) {

    }

    @Override
    public void setDicesValue(int[] newDiceValue, Player p) {

    }

    @Override
    public void setFamiliarInTheCouncilPalace(ArrayList<Council> councilZone, FamilyMember familyMember) {

    }

    @Override
    public void takeCouncilPrivilege(int privelgeNumber) {

    }

    @Override
    public void takeMarketAction(int position) {

    }

    @Override
    public void payCard(DevelopmentCard cardOnThisFloor, boolean towerIsOccupied, int zoneDiceCost, int valueOfFamilyMember) {

    }

    @Override
    public void payVenturesCard(VenturesCard card, Player player, boolean coinsFee, int zoneDiceCost, int valueOfFamilyMember, int paymentChoosen) {

    }

    @Override
    public void pray(int victoryPointsToAdd) {

    }

    @Override
    public int payServants(int cost, int value) {
        return 0;
    }

    @Override
    public int finalPointsFromTerritoryCard(ArrayList<Integer> victoryPoints) {
        return 0;
    }

    @Override
    public void finalPointsFromVenturesCard() {

    }

    @Override
    public int finalPointsFromCharacterCard(ArrayList<Integer> victoryPoints) {
        return 0;
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
