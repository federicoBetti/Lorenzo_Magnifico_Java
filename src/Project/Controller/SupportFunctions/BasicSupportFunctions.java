package Project.Controller.SupportFunctions;

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
    public void placeCardInPersonalBoard(DevelopmentCard card, Player player) {
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



    public int Pray(Player player){

        return  0;
    }

}