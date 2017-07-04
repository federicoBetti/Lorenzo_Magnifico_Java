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
 * attenzione forse devo mettere nell'interfaccia tutte le funzioni non solo quelle da decorare
 * non ho usato come parametro il player perchè ho gia il riferimento al giusto player all'interno della classe
 */
public class BasicSupportFunctions implements AllSupportFunctions {

    private PlayerHandler player;

    private Map<Integer,PrivilegeTaker> privileges;

    private Map<Integer,MarketTaker> takeFromMarket;

    private Map<String,CardPayment> payments;



    public BasicSupportFunctions(PlayerHandler player) {
        this.player = player;
        this.payments = new HashMap<>();
        privileges = new HashMap<>(5);
        takeFromMarket = new HashMap<>(4);
        fillHashMapPayments();
    }

    /**
     * for testing
     */
    public BasicSupportFunctions(){
        //for testing
    }

    private void fillHashMapPayments() {
        payments.put(TerritoryCard.class.toString(), this::payTerritoryCard);
        payments.put(BuildingCard.class.toString(),  this::payBuildingCard);
        payments.put(CharacterCard.class.toString(), this::payCharacterCard);
    }


    private void payTerritoryCard(DevelopmentCard devCard, boolean coinsFee, int zoneDiceCost, int valueOfFamilyMember){
        TerritoryCard card = (TerritoryCard)devCard;
        int coinsMore = 0;
        if (coinsFee)
            coinsMore = Constants.ADD_COINS_IF_TOWER_IS_OCCUPIED;
        int diceBonus = player.getPersonalBoardReference().getBonusOnActions().getTerritoryBonus();
        player.getPersonalBoardReference().setWood(player.getPersonalBoardReference().getWood() - (card.getCost().getWoodRequired()));
        player.getPersonalBoardReference().setStone(player.getPersonalBoardReference().getStone() - (card.getCost().getStoneRequired()));
        player.getPersonalBoardReference().setCoins(player.getPersonalBoardReference().getCoins() - (coinsMore));
        int servantsUsed = payServants(zoneDiceCost, valueOfFamilyMember + diceBonus);
        player.getPersonalBoardReference().setServants(player.getPersonalBoardReference().getServants() - servantsUsed);
    }

    //TODO CONTROLLARE CHE I COSTI VADANO BENE, AD ESEMPIO CONTROLLARE CHE SE I BONUS O VALORI DEI DADI SONO MAGGIORI DEI COSTI NON MI VADA AD AGGIUNGERE RISORSE, MA E NE TOLGA 0

    private void payBuildingCard(DevelopmentCard devCard, boolean coinsFee, int zoneDiceCost, int valueOfFamilyMember){
        BuildingCard card = (BuildingCard)devCard;
        int coinsMore = 0;
        int diceBonus = player.getPersonalBoardReference().getBonusOnActions().getBuildingsBonus().getDiceBonus();
        int woodBonus = player.getPersonalBoardReference().getBonusOnActions().getBuildingsBonus().getWoodBonus();
        int stoneBonus = player.getPersonalBoardReference().getBonusOnActions().getBuildingsBonus().getStoneBonus();
        if (coinsFee)
            coinsMore = Constants.ADD_COINS_IF_TOWER_IS_OCCUPIED;
        player.getPersonalBoardReference().setWood(player.getPersonalBoardReference().getWood() - (card.getCost().getWoodRequired() + woodBonus));
        player.getPersonalBoardReference().setStone(player.getPersonalBoardReference().getStone() - (card.getCost().getStoneRequired() + stoneBonus));
        player.getPersonalBoardReference().setCoins(player.getPersonalBoardReference().getCoins() - (card.getCost().getCoinsRequired() + coinsMore));
        int servantsUsed = payServants(zoneDiceCost, valueOfFamilyMember + diceBonus);
        player.getPersonalBoardReference().setServants(player.getPersonalBoardReference().getServants() - servantsUsed);
    }

    private void payCharacterCard(DevelopmentCard devCard, boolean coinsFee, int zoneDiceCost, int valueOfFamilyMember){
        CharacterCard card = (CharacterCard)devCard;
        int coinsMore = 0;
        int coinsBonus = player.getPersonalBoardReference().getBonusOnActions().getCharactersBonus().getCoinsBonus();
        int diceBonus = player.getPersonalBoardReference().getBonusOnActions().getCharactersBonus().getDiceBonus();
        if (coinsFee)
            coinsMore = Constants.ADD_COINS_IF_TOWER_IS_OCCUPIED;
        int coinsPaid = card.getCost().getCoinsRequired() + coinsMore - coinsBonus;
        if (coinsPaid < 0)
            coinsPaid = 0;
        player.getPersonalBoardReference().setCoins(player.getPersonalBoardReference().getCoins() - (coinsPaid));
        int servantsUsed = payServants(zoneDiceCost, valueOfFamilyMember + diceBonus);
        player.getPersonalBoardReference().setServants(player.getPersonalBoardReference().getServants() - servantsUsed);
    }

    @Override
    public int payServants(int cost, int value) {
        if ((cost - value) < 0)
            return 0;
        else
            return cost - value;
    }

    @Override
    public int finalPointsFromTerritoryCard(int[] victoryPoints) {
        int cardNumber = player.getPersonalBoardReference().getTerritories().size() - 1;
        return victoryPoints[(cardNumber)];

    }

    @Override
    public int finalPointsFromCharacterCard(int[] victoryPoints) {
        int cardNumber = player.getPersonalBoardReference().getCharacters().size() - 1;
        return victoryPoints[(cardNumber)];

    }

    @Override
    public int extraLostOfPoints(PlayerHandler playerHandler) {
        return 0;
    }

    @Override
    public void towerZoneEffect(Tower zone, PlayerHandler player) {
        zone.getTowerZoneEffect().doEffect(player);
    }

    @Override
    public void finalPointsFromVenturesCard() {
        for (VenturesCard venturesCard: player.getPersonalBoardReference().getVentures()){
            for (Effects e: venturesCard.getPermanentCardEffects())
                applyEffects(e,player);
        }
    }


/*
    @Override
    public BonusInteraction applyEffects(DevelopmentCard card, PlayerHandler player){
        return card.makeImmediateEffects(player);
    }
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

    @Override
    public void setFamiliar(Position zone, FamilyMember familyMember) {
        zone.setFamiliarOnThisPosition(familyMember);
        zone.setOccupied(true);
        familyMember.setPlayed(true);
        System.out.println("sono nel set familiar e ho appena messo " + familyMember);
        return;
    }

    @Override
    public void placeCardInPersonalBoard(DevelopmentCard card) {
        //guardare se si puo fare in un altro modo, questa è la cosa comoda in java
        card.addToPersonalBoard(player.getPersonalBoardReference());
    }

    @Override
    public void setDicesValue(int[] newDiceValue, Player p) {
        for (int i = 1; i<newDiceValue.length + 1; i++)
            p.getAllFamilyMembers()[i].setMyValue(newDiceValue[i-1]);
    }

    @Override
    public void setFamiliarInTheCouncilPalace(List<Council> councilZone, FamilyMember familyMember) {
        councilZone.add(new Council(familyMember));
        familyMember.setPlayed(true);

    }


    @Override
    public void takeMarketAction(int position) {
        takeFromMarket.get((Integer)position).takeMarketAction();
    }

    @Override
    public void payCard(DevelopmentCard cardOnThisFloor, boolean coinsFee, int zoneDiceCost, int valueOfFamilyMember) {
        payments.get(cardOnThisFloor.getClass().toString()).pay(cardOnThisFloor, coinsFee, zoneDiceCost, valueOfFamilyMember);
    }

    @Override
    public void payVenturesCard(VenturesCard card, Player player, boolean coinsFee, int zoneDiceCost, int valueOfFamilyMember, int paymentChoosen) {
        int coinsMore = 0;
        VenturesCost cost = card.getVenturesCost().get(paymentChoosen);
        if (coinsFee)
            coinsMore = Constants.ADD_COINS_IF_TOWER_IS_OCCUPIED;
        int diceBonus = player.getPersonalBoardReference().getBonusOnActions().getVenturesBonus();
        player.getPersonalBoardReference().setWood(player.getPersonalBoardReference().getWood() - (cost.getWoodRequired()));
        player.getPersonalBoardReference().setStone(player.getPersonalBoardReference().getStone() - (cost.getStoneRequired()));
        player.getPersonalBoardReference().setCoins(player.getPersonalBoardReference().getCoins() - (coinsMore + cost.getCoinsRequired()));
        player.getScore().setMilitaryPoints(player.getScore().getMilitaryPoints() - (cost.getMilitaryCost()));
        int servantsUsed = payServants(zoneDiceCost, valueOfFamilyMember + diceBonus);
        player.getPersonalBoardReference().setServants(player.getPersonalBoardReference().getServants() - servantsUsed);
    }


    @Override
    public void pray(int victoryPointsToAdd){
        player.getScore().setFaithPoints(0);
        player.getScore().setVictoryPoints(player.getScore().getVictoryPoints() + victoryPointsToAdd);
    }

    @FunctionalInterface
    private interface PrivilegeTaker{
        void takePrivilege();
    }

    @FunctionalInterface
    private interface CardPayment{
        void pay(DevelopmentCard card, boolean coinsFee, int zoneDiceCost, int valueOfFamilyMember);
    }

    @FunctionalInterface
    private interface MarketTaker{
        void takeMarketAction();
    }

    public void setPlayer(PlayerHandler player) {
        this.player = player;
    }
}