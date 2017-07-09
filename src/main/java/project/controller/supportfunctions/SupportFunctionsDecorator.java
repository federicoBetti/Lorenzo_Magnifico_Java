package project.controller.supportfunctions;


import project.controller.cardsfactory.VenturesCard;
import project.controller.effects.realeffects.Effects;
import project.model.*;
import project.server.network.PlayerHandler;

import java.util.List;

/**
 * Abstract class of support function decorator
 */
public abstract class SupportFunctionsDecorator implements AllSupportFunctions {

    AllSupportFunctions allSupportFunctions = null;

    /**
     * Constructor
     *
     * @param allSupportFunctions allSupportFunctions' reference
     */
    SupportFunctionsDecorator(AllSupportFunctions allSupportFunctions){
        this.allSupportFunctions = allSupportFunctions;
    }

    /**
     * Apply the card's immediate effect
     *
     * @param card card's reference
     * @param player playerHandler's reference
     */
    @Override
    public void applyEffects(Effects card, PlayerHandler player) {
        allSupportFunctions.applyEffects(card,player);
    }

    /**
     * This method set a familiar in a position
     *
     * @param zone in which place a familiar
     * @param familyMember familiar to place
     */
    @Override
    public void setFamiliar(Position zone, FamilyMember familyMember) {
        allSupportFunctions.setFamiliar(zone,familyMember);
    }

    /**
     * This method place a card in the personal board
     *
     * @param card card to place in the personal board
     */
    @Override
    public void placeCardInPersonalBoard(DevelopmentCard card) {
        allSupportFunctions.placeCardInPersonalBoard(card);
    }

    /**
     * This method set the dices' value
     *
     * @param newDiceValue array of dices'value
     * @param p playerHandler's reference
     */
    @Override
    public void setDicesValue(int[] newDiceValue, Player p) {
        allSupportFunctions.setDicesValue(newDiceValue,p);
    }

    /**
     * This method set a familiar in the council zone
     *
     * @param councilZone list of council places
     * @param familyMember familiar to place
     */
    @Override
    public void setFamiliarInTheCouncilPalace(List<Council> councilZone, FamilyMember familyMember) {
        allSupportFunctions.setFamiliarInTheCouncilPalace(councilZone,familyMember);
    }

    /**
     * This method is used for paying the cost of a development card
     *
     * @param cardOnThisFloor card's reference
     * @param towerIsOccupied boolean that says if the tower is already occupied by another familiar
     * @param zoneDiceCost dice's cost
     * @param valueOfFamilyMember familiar dice's value
     */
    @Override
    public void payCard(DevelopmentCard cardOnThisFloor, boolean towerIsOccupied, int zoneDiceCost, int valueOfFamilyMember) {
        allSupportFunctions.payCard(cardOnThisFloor,towerIsOccupied,zoneDiceCost,valueOfFamilyMember);
    }

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
    @Override
    public void payVenturesCard(VenturesCard card, Player player, boolean coinsFee, int zoneDiceCost, int valueOfFamilyMember, int paymentChoosen) {
        allSupportFunctions.payVenturesCard(card,player,coinsFee,zoneDiceCost,valueOfFamilyMember,paymentChoosen);
    }

    /**
     * This method adds the victory points after the prayer
     *
     * @param victoryPointsToAdd victory points to add
     */
    @Override
    public void pray(int victoryPointsToAdd) {
        allSupportFunctions.pray(victoryPointsToAdd);
    }

    /**
     * It is used for using the right number of servants
     *
     * @param cost dice's cost
     * @param value servants that the player put
     * @return cost - value
     */
    @Override
    public int payServants(int cost, int value) {
        return allSupportFunctions.payServants(cost,value);
    }

    /**
     * this method add the right number of victory points at the ned of the match according to the number of
     * territory cards owned
     *
     * @param victoryPoints array of victory points for territory cards
     * @return victory points to add
     */
    @Override
    public int finalPointsFromTerritoryCard(int[] victoryPoints) {
        return allSupportFunctions.finalPointsFromTerritoryCard(victoryPoints);
    }

    /**
     * this method add the right number of victory points at the ned of the match according to the number of
     * Venture cards owned
     */
    @Override
    public void finalPointsFromVenturesCard() {
        allSupportFunctions.finalPointsFromVenturesCard();
    }

    /**
     * this method add the right number of victory points at the ned of the match according to the number of
     * Character cards owned
     *
     * @param victoryPoints array of victory points for territory cards
     * @return victory points to add
     */
    @Override
    public int finalPointsFromCharacterCard(int[] victoryPoints) {
        return allSupportFunctions.finalPointsFromCharacterCard(victoryPoints);
    }

    /**
     * This method subtract points for malus
     *
     * @param playerHandler playerHandler's reference
     * @return number of points to subtract
     */
    @Override
    public int extraLostOfPoints(PlayerHandler playerHandler) {
        return allSupportFunctions.extraLostOfPoints(playerHandler);
    }

    /**
     * Apply the tower zone's effect
     *
     * @param zone tower zone
     * @param player playerHandler's reference
     */
    @Override
    public void towerZoneEffect(Tower zone, PlayerHandler player) {
        allSupportFunctions.towerZoneEffect(zone,player);
    }
}
