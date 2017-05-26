package Project.MODEL;


import Project.Controller.Constants;

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
    private Production[] productionZone;

    /**
     * 
     */
    private Market[] marketZone;

    /**
     * 
     */
    private ArrayList<Council> councilZone;

    /**
     * 
     */
    private ExcommunicationZone[] excommunicationZone;

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

    /**
     * questa variabile rappresenta se siamo alla fine di un turno e aspettiamo il lancio dei dadi
     */
    private boolean endRound = false;

    /**
     * qiesto array di interi rappresenta i punti vittori ottenuti pregando per ognuno dei 15 posti del tracciato di punti fede
     */
    private int[] victoryPointsInFaithTrack;

    private Deck deck;

    private ArrayList<Integer> finalPointsFromTerritoryCards;

    private ArrayList<Integer> finalPointsFromCharacterCards;


    Board(int numberOfPlayer){
        victoryPointsInFaithTrack = new int[15];
        if (numberOfPlayer == 4){
            marketZone = new Market[4]
        }
        else
            marketZone = new Market[2];
        if (numberOfPlayer >= 3){
            harvesterZone = new Harvester[4];
            productionZone = new Production[4];
        }
        else {
            harvesterZone = new Harvester[1];
            productionZone = new Production[1];
        }
        excommunicationZone = new ExcommunicationZone[3];
        councilZone = new ArrayList<>(numberOfPlayer * 4);
        towers = new Tower[4][4];
        fillHashMap();
        round = 1;
        period = 1;

    }


    private void fillHashMap() {
        zones.put(Constants.COLOUR_OF_TOWER_WITH_TERRITORY_CARD,getTower(0));
        zones.put(Constants.COLOUR_OF_TOWER_WITH_CHARACTER_CARD,getTower(1));
        zones.put(Constants.COLOUR_OF_TOWER_WITH_BUILDING_CARD,getTower(2));
        zones.put(Constants.COLOUR_OF_TOWER_WITH_VENTURES_CARD,getTower(3));
        zones.put(Constants.HARVESTER,getHarvesterZone());
        zones.put(Constants.PRODUCTION,getProduction_zone());
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


    public int[] getVictoryPointsInFaithTrack() {
        return victoryPointsInFaithTrack;
    }

    public Tower[] getTower (int i){
        return towers[i];
    }

    public Harvester[] getHarvesterZone() {
        return harvesterZone;
    }

    public void setHarvesterZone(Harvester[] harvesterZone) {
        this.harvesterZone = harvesterZone;
    }

    public Production[] getProduction_zone() {
        return productionZone;
    }

    public void setProduction_zone(Production[] production_zone) {
        productionZone = production_zone;
    }

    public Market[] getMarketZone() {
        return marketZone;
    }

    public void setMarketZone(Market[] marketZone) {
        this.marketZone = marketZone;
    }

    public ArrayList<Council> getCouncilZone() {
        return councilZone;
    }

    public void setCouncilZone(ArrayList<Council> councilZone) {
        this.councilZone = councilZone;
    }

    public ExcommunicationZone[] getExcommunicationZone() {
        return excommunicationZone;
    }

    public void setExcommunicationZone(ExcommunicationZone[] excommunicationZone) {
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
        return (Tower[])zones.get(whichZone);
    }

    public boolean getEndRound() {
        return endRound;
    }

    public void setEndRound(boolean endRound) {
        this.endRound = endRound;
    }

    public Tower[][] getAllTowers(){
        return towers;
    }

    public Deck getDeck() {
        return deck;
    }

    public void nextRound(){
        round ++;
        if (round == 3)
            round = 1;
    }

    public void nextPeriod(){
        period ++;
    }

    public void setTowers(Tower[][] towers) {
        this.towers = towers;
    }

    public ArrayList<Integer> getFinalPointsFromTerritoryCards() {
        return finalPointsFromTerritoryCards;
    }

    public ArrayList<Integer> getFinalPointsFromCharacterCards() {
        return finalPointsFromCharacterCards;
    }
}