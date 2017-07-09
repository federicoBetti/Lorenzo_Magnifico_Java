package project.controller.supportfunctions;


import project.controller.cardsfactory.VenturesCard;
import project.controller.effects.realeffects.Effects;
import project.model.*;
import project.server.network.PlayerHandler;

import java.util.List;

/**
 * Interface implemented by all the support functions
 */
public interface AllSupportFunctions {

    /**
     * Apply the card's immediate effect
     *
     * @param card card's reference
     * @param player playerHandler's reference
     */
    void applyEffects(Effects card, PlayerHandler player);

    /**
     * This method set a familiar in a position
     *
     * @param zone in which place a familiar
     * @param familyMember familiar to place
     */
    void setFamiliar(Position zone, FamilyMember familyMember);

    /**
     * This method place a card in the personal board
     *
     * @param card card to place in the personal board
     */
    void placeCardInPersonalBoard(DevelopmentCard card);

    /**
     * This method set the dices' value
     *
     * @param newDiceValue array of dices'value
     * @param p playerHandler's reference
     */
    void setDicesValue(int[] newDiceValue, Player p);

    /**
     * This method set a familiar in the council zone
     *
     * @param councilZone list of council places
     * @param familyMember familiar to place
     */
    void setFamiliarInTheCouncilPalace(List<Council> councilZone, FamilyMember familyMember);

    /**
     * This method is used for paying the cost of a development card
     *
     * @param cardOnThisFloor card's reference
     * @param towerIsOccupied boolean that says if the tower is already occupied by another familiar
     * @param zoneDiceCost dice's cost
     * @param valueOfFamilyMember familiar dice's value
     */
    void payCard(DevelopmentCard cardOnThisFloor, boolean towerIsOccupied, int zoneDiceCost, int valueOfFamilyMember);

    /**
     * This method is used for paying the cost of a venture's card
     *
     * @param card card's reference
     * @param player playerHandler's reference
     * @param coinsFee coins more to pay
     * @param zoneDiceCost dice's cost
     * @param valueOfFamilyMember familiar dice's value
     * @param paymentChoosen paymentChoosen
     */
    void payVenturesCard(VenturesCard card, Player player, boolean coinsFee, int zoneDiceCost, int valueOfFamilyMember, int paymentChoosen);

    /**
     * This method adds the victory points after the prayer
     *
     * @param victoryPointsToAdd victory points to add
     */
    void pray(int victoryPointsToAdd);

    /**
     * It is used for using the right number of servants
     *
     * @param cost dice's cost
     * @param value servants that the player put
     * @return cost - value
     */
    int payServants(int cost, int value);

    /**
     * this method add the right number of victory points at the ned of the match according to the number of
     * territory cards owned
     *
     * @param victoryPoints array of victory points for territory cards
     * @return victory points to add
     */
    int finalPointsFromTerritoryCard(int[] victoryPoints);

    /**
     * this method add the right number of victory points at the ned of the match according to the number of
     * Venture cards owned
     */
    void finalPointsFromVenturesCard();

    /**
     * this method add the right number of victory points at the ned of the match according to the number of
     * Character cards owned
     *
     * @param victoryPoints array of victory points for territory cards
     * @return victory points to add
     */
    int finalPointsFromCharacterCard(int[] victoryPoints);

    /**
     * This method subtract points for malus
     *
     * @param playerHandler playerHandler's reference
     * @return number of points to subtract
     */
    int extraLostOfPoints(PlayerHandler playerHandler);

    /**
     * Apply the tower zone's effect
     *
     * @param zone tower zone
     * @param player playerHandler's reference
     */
    void towerZoneEffect(Tower zone, PlayerHandler player);
}
