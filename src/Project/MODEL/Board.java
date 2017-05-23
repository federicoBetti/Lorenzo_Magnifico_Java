package Project.MODEL;


import Project.Controller.CardsFactory.ExcommunicationZone;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * 
 */
public final class Board {


    private Tower[][] towers;

    /**
     * 
     */
    private Harvester[] harvesterZone;

    /**
     * 
     */
    private ArrayList<Production> productionZone;

    /**
     * 
     */
    private ArrayList<Market> marketZone;

    /**
     * 
     */
    private ArrayList<Council> councilZone;

    /**
     * 
     */
    private ArrayList<ExcommunicationZone> excommunicationZone;

    /**
     * 
     */
    private ArrayList<Player> TurnOrder;

    /**
     * 
     */
    private int period;

    /**
     * 
     */
    private int isPlaying;

    /**
     * 
     */
    private int round;

    /**
     * 
     */
    private Deck deckCard;

    /**
     * 
     */
    private int[] diceValue;


    private HashMap<String,Position[]> zones;


    Board(int numberOfPlayer){
        if (numberOfPlayer == 4){
            marketZone = new ArrayList<>(4);
        }
        else
            marketZone = new ArrayList<>(2);
        if (numberOfPlayer >= 3){
            harvesterZone = new Harvester[4];
            productionZone = new ArrayList<>(4);
        }
        else {
            harvesterZone = new ArrayList<>(1);
            productionZone = new ArrayList<>(1);
        }
        excommunicationZone = new ArrayList<>(3);
        councilZone = new ArrayList<>(numberOfPlayer * 4);
        towers = new Tower[4][4];
        fillHashMap();

    }


    private void fillHashMap() {
        zones.put("green",getTower(0));
        zones.put("blue",getTower(1));
        zones.put("yellow",getTower(2));
        zones.put("purple",getTower(3));
        zones.put("harvester",getHarvesterZone());
        zones.put("pruction",getProduction_zone());
    }

    /**
     * @return
     */
    public void EndTunr() {
        // TODO implement here
        return null;
    }

    /**
     * @return
     */
    public void EndGame() {
        // TODO implement here
        return null;
    }

    /**
     * @return
     */
    public void FillTowers() {
        // TODO implement here
        return null;
    }

    /**
     * @return
     */
    public void ResetFamilyMembers() {
        // TODO implement here
        return null;
    }

    /**
     * @return
     */
    public void ThrowDice() {
        // TODO implement here
        return null;
    }

    /**
     * @return
     */
    public void CheckFaithTruck() {
        // TODO implement here
        return null;
    }

    /**
     * @return
     */
    public void CheckFinalPointsMilitary() {
        // TODO implement here
        return null;
    }

    /**
     * @return
     */
    public void CheckWinner() {
        // TODO implement here
        return null;
    }


    public Tower[] getTower (int i){
        return towers[i];
    }
    public Tower getTowers() {
        return towers;
    }

    public void setTowers(Tower towers) {
        this.towers = towers;
    }

    public Harvester[] getHarvesterZone() {
        return harvesterZone;
    }

    public void setHarvesterZone(Harvester[] harvesterZone) {
        this.harvesterZone = harvesterZone;
    }

    public ArrayList<Production> getProduction_zone() {
        return productionZone;
    }

    public void setProduction_zone(ArrayList<Production> production_zone) {
        productionZone = production_zone;
    }

    public ArrayList<Market> getMarketZone() {
        return marketZone;
    }

    public void setMarketZone(ArrayList<Market> marketZone) {
        this.marketZone = marketZone;
    }

    public ArrayList<Council> getCouncilZone() {
        return councilZone;
    }

    public void setCouncilZone(ArrayList<Council> councilZone) {
        this.councilZone = councilZone;
    }

    public ArrayList<ExcommunicationZone> getExcommunicationZone() {
        return excommunicationZone;
    }

    public void setExcommunicationZone(ArrayList<ExcommunicationZone> excommunicationZone) {
        this.excommunicationZone = excommunicationZone;
    }

    public ArrayList<Player> getTurnOrder() {
        return TurnOrder;
    }

    public void setTurnOrder(ArrayList<Player> turnOrder) {
        TurnOrder = turnOrder;
    }

    public int getPeriod() {
        return period;
    }

    public void setPeriod(int period) {
        this.period = period;
    }

    public int getIsPlaying() {
        return isPlaying;
    }

    public void setIsPlaying(int isPlaying) {
        this.isPlaying = isPlaying;
    }

    public int getRound() {
        return round;
    }

    public void setRound(int round) {
        this.round = round;
    }

    public Deck getDeckCard() {
        return deckCard;
    }

    public void setDeckCard(Deck deckCard) {
        this.deckCard = deckCard;
    }

    public int[] getDiceValue() {
        return diceValue;
    }

    public void setDiceValue(int[] diceValue) {
        this.diceValue = diceValue;
    }

    public Tower[] getTrueArrayList(String whichZone){
        return zones.get(whichZone);
    }
}