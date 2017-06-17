package project.model;


import project.controller.Constants;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 
 */
public final class Board {


    //todo fare iteratore per riempire le torri
    private Tower[][] towers;

    private CouncilPrivilege[] councilPrivileges;

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
    private List<Council> councilZone;

    /**
     * 
     */
    private ExcommunicationZone[] excommunicationZone;

    /**
     * 
     */
    private Turn turn;

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

    private List<Integer> finalPointsFromTerritoryCards;

    private List<Integer> finalPointsFromCharacterCards;

    private int[] faithPointsRequiredEveryPeriod; //todo bisogna scrivere dentro i numeri, in teoria sono 3 4 5
    private Deck deckCard;


    public Board(int numberOfPlayer){
        councilPrivileges = new CouncilPrivilege[5];
        this.faithPointsRequiredEveryPeriod = new int[Constants.PERIOD_NUMBER];
        victoryPointsInFaithTrack = new int[15];
        if (numberOfPlayer == 4){
            marketZone = new Market[4];
        }
        else
            marketZone = new Market[2];
        if (numberOfPlayer >= 3){
            harvesterZone = new Harvester[4]; //todo attenzione qua in verità al max ci possono essere 8 giocatori per ogni
            // round di harv o prod, facciamo cosi con 8 giocatori (cioe numberofplayers * 2) oppure facciamo arraylist?
            productionZone = new Production[4];
        }
        else {
            harvesterZone = new Harvester[1];
            productionZone = new Production[1];
        }
        excommunicationZone = new ExcommunicationZone[3];
        councilZone = new ArrayList<>(numberOfPlayer * 4);
        towers = new Tower[4][4];
        zones = new HashMap<>();
        fillHashMap();
        round = 1;
        period = 1;
    }

    public Board() {
        //ui construcutor
    }


    private void fillHashMap() {
        zones.put(Constants.COLOUR_OF_TOWER_WITH_TERRITORY_CARD,getTower(0));
        zones.put(Constants.COLOUR_OF_TOWER_WITH_CHARACTER_CARD,getTower(1));
        zones.put(Constants.COLOUR_OF_TOWER_WITH_BUILDING_CARD,getTower(2));
        zones.put(Constants.COLOUR_OF_TOWER_WITH_VENTURES_CARD,getTower(3));
        zones.put(Constants.HARVESTER,getHarvesterZone());
        zones.put(Constants.PRODUCTION, getProductionZone());
    }

    public void nextRound(){
        round ++;
        if (round == 3)
            round = 1;
    }

    public void setHarvesterZone(Harvester[] harvesterZone) {
        this.harvesterZone = harvesterZone;
    }

    public void setProductionZone(Production[] productionZone) {
        this.productionZone = productionZone;
    }

    public void setMarketZone(Market[] marketZone) {
        this.marketZone = marketZone;
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

    public Production[] getProductionZone() {
        return productionZone;
    }

    public Market[] getMarketZone() {
        return marketZone;
    }

    public List<Council> getCouncilZone() {
        return councilZone;
    }

    public void setCouncilZone(List<Council> councilZone) {
        this.councilZone = councilZone;
    }

    public ExcommunicationZone[] getExcommunicationZone() {
        return excommunicationZone;
    }

    // todo public ArrayList<Player> getTurnOrder() { return TurnOrder; } public void setTurnOrder(ArrayList<Player> turnOrder) { TurnOrder = turnOrder;}


    public int getPeriod() {
        return period;
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

    public void nextPeriod(){
        period ++;
    }

    public void setTowers(Tower[][] towers) {
        this.towers = towers;
    }

    public List<Integer> getFinalPointsFromTerritoryCards() {
        return finalPointsFromTerritoryCards;
    }

    public List<Integer> getFinalPointsFromCharacterCards() {
        return finalPointsFromCharacterCards;
    }

    public int[] getFaithPointsRequiredEveryPeriod() {
        return faithPointsRequiredEveryPeriod;
    }

    public Turn getTurn() {
        return turn;
    }

    public int[] getDiceValue() {
        return diceValue;
    }

    public Deck getDeckCard() {
        return deckCard;
    }

    public void setExcommunicationZone(ExcommunicationZone[] excommunicationZone) {
        this.excommunicationZone = excommunicationZone;
    }

    public CouncilPrivilege[] getCouncilPrivileges() {
        return councilPrivileges;
    }

    public void setMarketPosition(int positionNumber, Market market){
        this.marketZone[positionNumber] = market;
    }

    public void setCouncilPrivilege(int priviledgeNumber, CouncilPrivilege councilPrivilege) {
        this.councilPrivileges[priviledgeNumber] = councilPrivilege;
    }

    public void setTowerInTowers( int towerNumber, int floor, Tower tower ){
        this.towers[towerNumber][floor] = tower;
    }
}