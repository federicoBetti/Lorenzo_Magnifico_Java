package Project.MODEL;


import Project.Controller.CardsFactory.ExcommunicationZone;

import java.util.ArrayList;

/**
 * 
 */
public final class Board {


    private Tower towers;

    /**
     * 
     */
    private ArrayList<Harvester> harvester_zone;

    /**
     * 
     */
    private ArrayList<Production> Production_zone;

    /**
     * 
     */
    private ArrayList<Market> Market_zone;

    /**
     * 
     */
    private ArrayList<Council> Council_zone;

    /**
     * 
     */
    private ExcommunicationZone[] Excommunication_zone;

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

    public Tower getTowers() {
        return towers;
    }

    public void setTowers(Tower towers) {
        this.towers = towers;
    }

    public ArrayList<Harvester> getHarvester_zone() {
        return harvester_zone;
    }

    public void setHarvester_zone(ArrayList<Harvester> harvester_zone) {
        this.harvester_zone = harvester_zone;
    }

    public ArrayList<Production> getProduction_zone() {
        return Production_zone;
    }

    public void setProduction_zone(ArrayList<Production> production_zone) {
        Production_zone = production_zone;
    }

    public ArrayList<Market> getMarket_zone() {
        return Market_zone;
    }

    public void setMarket_zone(ArrayList<Market> market_zone) {
        Market_zone = market_zone;
    }

    public ArrayList<Council> getCouncil_zone() {
        return Council_zone;
    }

    public void setCouncil_zone(ArrayList<Council> council_zone) {
        Council_zone = council_zone;
    }

    public ExcommunicationZone[] getExcommunication_zone() {
        return Excommunication_zone;
    }

    public void setExcommunication_zone(ExcommunicationZone[] excommunication_zone) {
        Excommunication_zone = excommunication_zone;
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
}