package Project.Controller.SupportFunctions;


import Project.Controller.CardsFactory.VenturesCard;
import Project.MODEL.*;
import Project.Server.Network.PlayerHandler;
import Project.toDelete.BonusInteraction;

import java.util.ArrayList;

public interface AllSupportFunctions {

    BonusInteraction ApplyEffects(DevelopmentCard card, PlayerHandler player);

    void setFamiliar(Position zone, FamilyMember familyMember);

    void placeCardInPersonalBoard(DevelopmentCard card);

    void setDicesValue(int[] newDiceValue, Player p);

    void setFamiliarInTheCouncilPalace(ArrayList<Council> councilZone, FamilyMember familyMember);

    void takeCouncilPrivilege(int privelgeNumber);

    void takeMarketAction(int position);

    void payCard(DevelopmentCard cardOnThisFloor, boolean towerIsOccupied, int zoneDiceCost, int valueOfFamilyMember);

    void payVenturesCard(VenturesCard card, Player player, boolean coinsFee, int zoneDiceCost, int valueOfFamilyMember, int paymentChoosen);

    void pray(int victoryPointsToAdd);

    int payServants(int cost, int value);

    int finalPointsFromTerritoryCard(ArrayList<Integer> victoryPoints);

    void finalPointsFromVenturesCard();

    int finalPointsFromCharacterCard(ArrayList<Integer> victoryPoints);

    int extraLostOfPoints(PlayerHandler playerHandler);

}
