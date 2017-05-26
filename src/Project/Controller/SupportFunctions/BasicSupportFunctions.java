package Project.Controller.SupportFunctions;

import Project.Controller.CardsFactory.BuildingCard;
import Project.Controller.CardsFactory.CharacterCard;
import Project.Controller.CardsFactory.TerritoryCard;
import Project.Controller.CardsFactory.VenturesCard;
import Project.Controller.Constants;
import Project.Controller.Effects.RealEffects.*;
import Project.MODEL.*;
import Project.Server.Network.PlayerHandler;
import Project.toDelete.BonusInteraction;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * attenzione forse devo mettere nell'interfaccia tutte le funzioni non solo quelle da decorare
 */
public class BasicSupportFunctions implements AllSupportFunctions {

    private PlayerHandler player;

    private HashMap<Integer,PrivilegeTaker> privileges;

    private HashMap<Integer,MarketTaker> takeFromMarket;


    /**
     * Default constructor
     */


    public BasicSupportFunctions(PlayerHandler player) {
        this.player = player;
        privileges = new HashMap<>(5);
        takeFromMarket = new HashMap<>(4);
        fillHashMapPrivileges();
        fillHashMapTakeFromMarket();
        fillHashMapPayments();
    }

    private void fillHashMapPayments() {
        payments.put(TerritoryCard.class.toString(),this::payTerritoryCard);
        payments.put(BuildingCard.class.toString(),this::payBuildingCard);
        payments.put(CharacterCard.class.toString(),this::payCharacterCard);
    }

    public void payVenturesCard(VenturesCard card, Player player, boolean coinsFee, int zoneDiceCost, int valueOfFamilyMember, int paymentChoosen){
        int coinsMore = 0;
        int i=0;
        if (coinsFee)
            coinsMore = Constants.ADD_COINS_IF_TOWER_IS_OCCUPIED;
        int diceBonus = player.getPersonalBoardReference().getBonusOnActions().getVenturesBonus();
        player.getPersonalBoardReference().setWood(player.getPersonalBoardReference().getWood() - (card.getVenturesCost().get(paymentChoosen).getWoodRequired()));
        player.getPersonalBoardReference().setStone(player.getPersonalBoardReference().getStone() - (card.getVenturesCost().get(paymentChoosen).getStoneRequired()));
        player.getPersonalBoardReference().setCoins(player.getPersonalBoardReference().getCoins() - (coinsMore + card.getVenturesCost().get(paymentChoosen).getCoinsRequired()));
        player.getScore().setMilitaryPoints(player.getScore().getMilitaryPoints() - (card.getVenturesCost().get(paymentChoosen).getMilitaryCost()));
        int servantsUsed = payServants(zoneDiceCost, valueOfFamilyMember + diceBonus);
        player.getPersonalBoardReference().setServants(player.getPersonalBoardReference().getServants() - servantsUsed);
    }

    private void payTerritoryCard(DevelopmentCard DevCard, Player player, boolean coinsFee, int zoneDiceCost, int valueOfFamilyMember){
        TerritoryCard card = (TerritoryCard)DevCard;
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

    private void payBuildingCard(DevelopmentCard DevCard, Player player, boolean coinsFee, int zoneDiceCost, int valueOfFamilyMember){
        BuildingCard card = (BuildingCard)DevCard;
        int coinsMore = 0;
        int diceBonus = player.getPersonalBoardReference().getBonusOnActions().getBuildingsBonus().getDiceValue();
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

    private void payCharacterCard(DevelopmentCard DevCard, Player player, boolean coinsFee, int zoneDiceCost, int valueOfFamilyMember){
        CharacterCard card = (CharacterCard)DevCard;
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

    /**
     * to decorate in case of excommunication tile
     * @param cost
     * @param value
     * @return
     */
    private int payServants(int cost, int value) {
        if ((cost - value) < 0)
            return 0;
        else
            return cost - value;
    }


    private void fillHashMapPrivileges (){
        privileges.put(0,this::WoodStonePrivilege);
        privileges.put(1,this::servantsPrivilege);
        privileges.put(2,this::coinsPrivilege);
        privileges.put(3,this::militaryPointsPrivilege);
        privileges.put(4,this::faithPointsPrivilege);
    }

    private void fillHashMapTakeFromMarket() {
        takeFromMarket.put(0,this::marketCoins);
        takeFromMarket.put(1,this::marketServants);
        takeFromMarket.put(2,this::marketMilitaryCoins);
        takeFromMarket.put(3,this::marketPrivileges);
    }

    private interface PrivilegeTaker{
        void takePrivilege();
    }


    void WoodStonePrivilege(){
        Effects e;
        e = new AddWood(1);
        e.doEffect(player);
        e = new AddCoin(1);
        e.doEffect(player);
    }

    void faithPointsPrivilege(){
        Effects e = new AddFaithPoints(1);
        e.doEffect(player);
    }

    void servantsPrivilege(){
        Effects e = new AddServants(2);
        e.doEffect(player);
    }

    void coinsPrivilege(){
        Effects e = new AddCoin(2);
        e.doEffect(player);
    }

    void militaryPointsPrivilege(){
        Effects e = new AddMilitaryPoints(2);
        e.doEffect(player);
    }


    private interface MarketTaker{
        void takeMarketAction();
    }

    void marketCoins(){
        Effects e = new AddCoin(5);
        e.doEffect(player);
    }

    void marketServants(){
        Effects e = new AddServants(5);
        e.doEffect(player);
    }
    void marketMilitaryCoins(){
        Effects e = new AddCoin(2);
        e.doEffect(player);
        e = new AddMilitaryPoints(3);
        e.doEffect(player);
    }
    void marketPrivileges(){
        Effects e = new UsePrivilege(2);
        BonusInteraction bonusInteraction = e.doEffect(player);
        player.sendAnswer(bonusInteraction);
    }


    public BonusInteraction ApplyEffects (DevelopmentCard card, Player player){
        return card.makeImmediateEffects(player);
    }

    @Override
    public void setFamiliar(Position zone, FamilyMember familyMember) {
        zone.setFamiliarOnThisPosition(familyMember);
        zone.setOccupied(true);
        return;
    }

    @Override
    public void placeCardInPersonalBoard(DevelopmentCard card) {
        //guardare se si puo fare in un altro modo, questa è la cosa comoda in java
        card.addToPersonalBoard(player.getPersonalBoardReference());
    }

    @Override
    public void setDicesValue(int[] newDiceValue, Player p) {
        for (int i = 0; i<newDiceValue.length; i++)
            p.getPedone()[i].setMyValue(newDiceValue[i]);
    }

    @Override
    public void setFamiliarInTheCouncilPalace(ArrayList<Council> councilZone, FamilyMember familyMember) {
        councilZone.add(new Council(familyMember));
    }



    @Override
    public void takeCouncilPrivilege(int privilegeNumber) {
        privileges.get((Integer)privilegeNumber).takePrivilege();
    }

    @Override
    public void takeMarketAction(int position) {
        takeFromMarket.get((Integer)position).takeMarketAction();
    }

    @Override
    public void payCard(DevelopmentCard cardOnThisFloor,  PlayerHandler playerHandler, boolean coinsFee, int zoneDiceCost, int valueOfFamilyMember) {
        payments.get(cardOnThisFloor.getClass().toString()).pay(cardOnThisFloor,playerHandler, coinsFee, zoneDiceCost, valueOfFamilyMember);
    }
    private HashMap<String,CardPayment> payments;

    private interface CardPayment{
        void pay(DevelopmentCard card, PlayerHandler playerHandler, boolean coinsFee, int zoneDiceCost, int valueOfFamilyMember);
    }






    public int Pray(Player player){

        return  0;
    }

}