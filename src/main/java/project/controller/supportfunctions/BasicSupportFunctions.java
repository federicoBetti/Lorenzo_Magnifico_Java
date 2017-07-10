package project.controller.supportfunctions;

import project.controller.cardsfactory.*;
import project.controller.Constants;
import project.controller.effects.realeffects.Effects;
import project.messages.BonusProductionOrHarvesterAction;
import project.messages.TakePrivilegesAction;
import project.messages.TowerAction;
import project.model.*;
import project.server.network.PlayerHandler;
import project.messages.BonusInteraction;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class contains the basic methods that support the main operations
 */
public class BasicSupportFunctions implements AllSupportFunctions {

    private PlayerHandler player;

    private Map<String, CardPayment> payments;

    /**
     * Constructor
     *
     * @param player PlayerHandler's reference
     */
    public BasicSupportFunctions(PlayerHandler player) {
        this.player = player;
        this.payments = new HashMap<>();
        fillHashMapPayments();
    }

    /**
     * for testing
     */
    public BasicSupportFunctions() {
        //for testing
    }

    /**
     * This method is used for filling the HashMap
     */
    private void fillHashMapPayments() {
        payments.put(TerritoryCard.class.toString(), this::payTerritoryCard);
        payments.put(BuildingCard.class.toString(), this::payBuildingCard);
        payments.put(CharacterCard.class.toString(), this::payCharacterCard);
    }

    /**
     * This method acts the terriotry's card payment
     *
     * @param devCard card's reference
     * @param coinsFee says if there are coins to pay more if the tower is occupied
     * @param zoneDiceCost zone dice's cost
     * @param valueOfFamilyMember familiar dice's value
     */
    private void payTerritoryCard(DevelopmentCard devCard, boolean coinsFee, int zoneDiceCost, int valueOfFamilyMember) {
        TerritoryCard card = (TerritoryCard) devCard;
        int coinsMore = 0;
        if (coinsFee)
            coinsMore = Constants.ADD_COINS_IF_TOWER_IS_OCCUPIED;
        int diceBonus = player.getPersonalBoardReference().getBonusOnActions().getTerritoryBonus();

        player.getPersonalBoardReference().addWood( - card.getCost().getWoodRequired());
        player.getPersonalBoardReference().addStone( - card.getCost().getStoneRequired());
        player.getPersonalBoardReference().addCoins( - coinsMore);

        int servantsUsed = payServants(zoneDiceCost, valueOfFamilyMember + diceBonus);
        player.getPersonalBoardReference().addServants( - servantsUsed);
    }

    //TODO CONTROLLARE CHE I COSTI VADANO BENE, AD ESEMPIO CONTROLLARE CHE SE I BONUS O VALORI DEI DADI SONO MAGGIORI DEI COSTI NON MI VADA AD AGGIUNGERE RISORSE, MA E NE TOLGA 0

    /**
     * This method acts the building's card payment
     *
     * @param devCard card's reference
     * @param coinsFee says if there are coins to pay more if the tower is occupied
     * @param zoneDiceCost zone dice's cost
     * @param valueOfFamilyMember familiar dice's value
     */
    private void payBuildingCard(DevelopmentCard devCard, boolean coinsFee, int zoneDiceCost, int valueOfFamilyMember) {
        BuildingCard card = (BuildingCard) devCard;
        int coinsMore = 0;
        int diceBonus = player.getPersonalBoardReference().getBonusOnActions().getBuildingsBonus().getDiceBonus();
        int woodBonus = player.getPersonalBoardReference().getBonusOnActions().getBuildingsBonus().getWoodBonus();
        int stoneBonus = player.getPersonalBoardReference().getBonusOnActions().getBuildingsBonus().getStoneBonus();
        if (coinsFee)
            coinsMore = Constants.ADD_COINS_IF_TOWER_IS_OCCUPIED;

        if (woodBonus - card.getCost().getWoodRequired() < 0)
            player.getPersonalBoardReference().addWood(woodBonus - card.getCost().getWoodRequired());

        if (stoneBonus - card.getCost().getWoodRequired() < 0)
            player.getPersonalBoardReference().addStone(stoneBonus - card.getCost().getStoneRequired());

        player.getPersonalBoardReference().addCoins(- coinsMore - card.getCost().getCoinsRequired());

        int servantsUsed = payServants(zoneDiceCost, valueOfFamilyMember + diceBonus);
        player.getPersonalBoardReference().addServants( - (servantsUsed + card.getCost().getServantsRequired()));

    }

    /**
     * This method acts the Character's card payment
     *
     * @param devCard card's reference
     * @param coinsFee says if there are coins to pay more if the tower is occupied
     * @param zoneDiceCost zone dice's cost
     * @param valueOfFamilyMember familiar dice's value
     */
    private void payCharacterCard(DevelopmentCard devCard, boolean coinsFee, int zoneDiceCost, int valueOfFamilyMember) {
        CharacterCard card = (CharacterCard) devCard;
        int coinsMore = 0;
        int coinsBonus = player.getPersonalBoardReference().getBonusOnActions().getCharactersBonus().getCoinsBonus();
        int diceBonus = player.getPersonalBoardReference().getBonusOnActions().getCharactersBonus().getDiceBonus();
        if (coinsFee)
            coinsMore = Constants.ADD_COINS_IF_TOWER_IS_OCCUPIED;
        int coinsPaid = card.getCost().getCoinsRequired() + coinsMore - coinsBonus;

        if (coinsPaid > 0)
            player.getPersonalBoardReference().addCoins( - (coinsPaid));

        int servantsUsed = payServants(zoneDiceCost, valueOfFamilyMember + diceBonus);
        player.getPersonalBoardReference().addServants( - servantsUsed);
    }

    /**
     *  Method used for paying the right number of servants
     *
     * @param cost dice's cost
     * @param value servants that the player put
     * @return
     */
    @Override
    public int payServants(int cost, int value) {
        if ((cost - value) < 0)
            return 0;
        else
            return cost - value;
    }

    /**
     * This method add the right number of victory points at the ned of the match according to the number of
     * territory cards owned
     *
     * @param victoryPoints array of victory points for territory cards
     * @return the right number of victory points
     */
    @Override
    public int finalPointsFromTerritoryCard(int[] victoryPoints) {
        int cardNumber = player.getPersonalBoardReference().getTerritories().size() - 1;
        if ( cardNumber < 0 )
            return 0;
        return victoryPoints[(cardNumber)];

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
        int cardNumber = player.getPersonalBoardReference().getCharacters().size() - 1;
        if ( cardNumber < 0 )
            return 0;
        return victoryPoints[(cardNumber)];

    }

    /**
     * This method subtract points for malus
     *
     * @param playerHandler playerHandler's reference
     * @return number of points to subtract
     */
    @Override
    public int extraLostOfPoints(PlayerHandler playerHandler) {
        return 0;
    }

    /**
     * Apply the tower zone's effect
     *
     * @param zone tower zone
     * @param player playerHandler's reference
     */
    @Override
    public void towerZoneEffect(Tower zone, PlayerHandler player) {
        zone.getTowerZoneEffect().doEffect(player);
    }

    /**
     * this method add the right number of victory points at the ned of the match according to the number of
     * Venture cards owned
     */
    @Override
    public void finalPointsFromVenturesCard() {
        for (VenturesCard venturesCard : player.getPersonalBoardReference().getVentures()) {
            for (Effects e : venturesCard.getPermanentCardEffects())
                applyEffects(e, player);
        }
    }


/*
    @Override
    public BonusInteraction applyEffects(DevelopmentCard card, PlayerHandler player){
        return card.makeImmediateEffects(player);
    }
    */

    /**
     * Apply the card's immediate effect
     *
     * @param effect card's reference
     * @param player playerHandler's reference
     */
    @Override
    public void applyEffects(Effects effect, PlayerHandler player) {
        BonusInteraction returnFromEffect = effect.doEffect(player);
        System.out.println("stampo la return from effect: " + returnFromEffect);

        if (returnFromEffect instanceof TowerAction) {
            System.out.println("if towerAction");
            player.sendBonusTowerAction((TowerAction) returnFromEffect);
            System.out.println("stampo la return from effect: " + returnFromEffect);

        } else if (returnFromEffect instanceof BonusProductionOrHarvesterAction) {
            System.out.println("if BonusInteractionHarv");
            player.sendBonusProdOrHarv((BonusProductionOrHarvesterAction) returnFromEffect);
            System.out.println("stampo la return from effect: " + returnFromEffect);

        } else if (returnFromEffect instanceof TakePrivilegesAction) {
            System.out.println("if TakePrivilege");
            player.sendRequestForPriviledges((TakePrivilegesAction) returnFromEffect);
            System.out.println("stampo la return from effect: " + returnFromEffect);
        }
    }

    /**
     * This method set a familiar in a position
     *
     * @param zone in which place a familiar
     * @param familyMember familiar to place
     */
    @Override
    public void setFamiliar(Position zone, FamilyMember familyMember) {
        zone.setFamiliarOnThisPosition(familyMember);
        zone.setOccupied(true);
        familyMember.setPlayed(true);
        System.out.println("sono nel set familiar e ho appena messo " + familyMember);
    }

    /**
     * This method place a card in the personal board
     *
     * @param card card to place in the personal board
     */
    @Override
    public void placeCardInPersonalBoard(DevelopmentCard card) {
        //guardare se si puo fare in un altro modo, questa è la cosa comoda in java
        card.addToPersonalBoard(player.getPersonalBoardReference());
    }

    /**
     * This method set the dices' value
     *
     * @param newDiceValue array of dices'value
     * @param p playerHandler's reference
     */
    @Override
    public void setDicesValue(int[] newDiceValue, Player p) {
        for (int i = 1; i < newDiceValue.length + 1; i++)
            p.getAllFamilyMembers()[i].setMyValue(newDiceValue[i - 1]);
    }

    /**
     * This method set a familiar in the council zone
     *
     * @param councilZone list of council places
     * @param familyMember familiar to place
     */
    @Override
    public void setFamiliarInTheCouncilPalace(List<Council> councilZone, FamilyMember familyMember) {
        councilZone.add(new Council(familyMember));
        familyMember.setPlayed(true);

    }

    /**
     * This method is used for paying the cost of a development card
     *
     * @param cardOnThisFloor card's reference
     * @param coinsFee boolean that says if the tower is already occupied by another familiar
     * @param zoneDiceCost dice's cost
     * @param valueOfFamilyMember familiar dice's value
     */
    @Override
    public void payCard(DevelopmentCard cardOnThisFloor, boolean coinsFee, int zoneDiceCost, int valueOfFamilyMember) {
        payments.get(cardOnThisFloor.getClass().toString()).pay(cardOnThisFloor, coinsFee, zoneDiceCost, valueOfFamilyMember);
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
        int coinsMore = 0;
        VenturesCost cost = card.getVenturesCost().get(paymentChoosen);
        if (coinsFee)
            coinsMore = Constants.ADD_COINS_IF_TOWER_IS_OCCUPIED;
        int diceBonus = player.getPersonalBoardReference().getBonusOnActions().getVenturesBonus();

        player.getPersonalBoardReference().addWood( - (cost.getWoodRequired()));
        player.getPersonalBoardReference().addStone( - (cost.getStoneRequired()));
        player.getPersonalBoardReference().addCoins( - (coinsMore + cost.getCoinsRequired()));

        player.getScore().setMilitaryPoints(player.getScore().getMilitaryPoints() - (cost.getMilitaryCost()));

        int servantsUsed = payServants(zoneDiceCost, valueOfFamilyMember + diceBonus);
        player.getPersonalBoardReference().addServants( - servantsUsed);
    }

    /**
     * This method adds the victory points after the prayer
     *
     * @param victoryPointsToAdd victory points to add
     */
    @Override
    public void pray(int victoryPointsToAdd) {
        player.getScore().setFaithPoints(0);
        System.out.println(victoryPointsToAdd);
        player.getScore().setVictoryPoints(player.getScore().getVictoryPoints() + victoryPointsToAdd);
    }

    /**
     * Functional interface for the card payment
     */
    @FunctionalInterface
    private interface CardPayment {
        void pay(DevelopmentCard card, boolean coinsFee, int zoneDiceCost, int valueOfFamilyMember);
    }

    /**
     * Set player
     *
     * @param player playerHandler's reference
     */
    public void setPlayer(PlayerHandler player) {
        this.player = player;
    }
}