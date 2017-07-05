package project.controller.supportfunctions;


import project.controller.cardsfactory.VenturesCard;
import project.controller.effects.realeffects.Effects;
import project.model.*;
import project.server.network.PlayerHandler;

import java.util.List;

public interface AllSupportFunctions {

    void applyEffects(Effects card, PlayerHandler player);

    void setFamiliar(Position zone, FamilyMember familyMember);

    void placeCardInPersonalBoard(DevelopmentCard card);

    void setDicesValue(int[] newDiceValue, Player p);

    void setFamiliarInTheCouncilPalace(List<Council> councilZone, FamilyMember familyMember);

    void payCard(DevelopmentCard cardOnThisFloor, boolean towerIsOccupied, int zoneDiceCost, int valueOfFamilyMember);

    void payVenturesCard(VenturesCard card, Player player, boolean coinsFee, int zoneDiceCost, int valueOfFamilyMember, int paymentChoosen);

    void pray(int victoryPointsToAdd);

    int payServants(int cost, int value);

    int finalPointsFromTerritoryCard(int[] victoryPoints);

    void finalPointsFromVenturesCard();

    int finalPointsFromCharacterCard(int[] victoryPoints);

    int extraLostOfPoints(PlayerHandler playerHandler);

    void towerZoneEffect(Tower zone, PlayerHandler player);
}
