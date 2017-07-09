package project.model;


import project.configurations.Configuration;
import project.controller.Constants;

import java.io.FileNotFoundException;
import java.util.*;

/**
 * This class represent the game board
 */
public class Board {


    //todo fare iteratore per riempire le torri
    private Tower[][] towers;

    private CouncilPrivilege[] councilPrivileges;

    /**
     *
     */

    private List<Harvester> harvesterZone;

    /**
     *
     */
    private List<Production> productionZone;

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
    private int round;


    private int[] diceValue;


    private Map<String, Position[]> zones;

    /**
     * qiesto array di interi rappresenta i punti vittori ottenuti pregando per ognuno dei 15 posti del tracciato di punti fede
     */
    private Deck decks;

    private int[] victoryPointsInFaithTrack;

    private int[] finalPointsFromTerritoryCards;

    private int[] finalPointsFromCharacterCards;

    private int[] faithPointsRequiredEveryPeriod;

    private int[] militaryPointsForTerritories;

    private Configuration configuration;

    /**
     * COnstructor
     *
     * @param numberOfPlayer number of players
     * @throws FileNotFoundException Signals that an attempt to open the file denoted by a specified pathname
     *                               has failed.
     */
    public Board(int numberOfPlayer) throws FileNotFoundException {
        round = 0;
        period = 0;
        configuration = new Configuration();

        this.councilPrivileges = new CouncilPrivilege[Constants.PRIVILEDGE_NUMBER];
        this.faithPointsRequiredEveryPeriod = new int[Constants.PERIOD_NUMBER];
        configuration.loadFaithPointsTracks(this);
        configuration.loadMilitaryPointsForTerritories(this);
        configuration.loadFaithPointsRequiredEveryPeriod(this);
        configuration.loadFinalPointsFromCharacterCards(this);
        configuration.loadFinalPointsFromTerritoryCards(this);
        this.turn = new Turn();
        this.decks = new Deck();

        //questi sotto sono da importare da file, li inizializzo poer testare

        if (numberOfPlayer == Constants.FOUR_PLAYERS) {
            marketZone = new Market[Constants.FOUR_PLAYERS];
            configuration.loadMarketBonus(this, "/fileJson/marketZone4.json");
        } else {
            marketZone = new Market[Constants.TWO_PLAYERS];
            configuration.loadMarketBonus(this, "/fileJson/marketZone2.json");
        }
        if (numberOfPlayer > Constants.TWO_PLAYERS) {
            harvesterZone = new ArrayList<>();
            productionZone = new ArrayList<>();
        } else {
            harvesterZone = new ArrayList<>(1);
            productionZone = new ArrayList<>(1);
        }

        excommunicationZone = new ExcommunicationZone[3];
        councilZone = new ArrayList<>();

        this.councilPrivileges = new CouncilPrivilege[5];
        configuration.loadCouncilZonePriviledges(this);


        this.towers = new Tower[4][4];
        configuration.loadBonusTower(this);

        this.zones = new HashMap<>();
        fillHashMap();

        configuration.loadDevelopmentCards(decks);
        configuration.loadLeaderCard(decks);
        configuration.loadExcommunicationTiles(decks);
        configuration.loadBonusTile(getDeckCard());
    }

    /**
     * Constructor
     */
    public Board() {
        towers = new Tower[0][0];
        councilPrivileges = new CouncilPrivilege[0];
        harvesterZone = new ArrayList<>();
        productionZone = new ArrayList<>();
        marketZone = new Market[0];
        councilZone = new ArrayList<>();
        excommunicationZone = new ExcommunicationZone[3];
        turn = new Turn();
        diceValue = new int[3];
    }

    /**
     * This method fills the class' HashMap
     */
    private void fillHashMap() {
        zones.put(Constants.COLOUR_OF_TOWER_WITH_TERRITORY_CARD, getTower(0));
        zones.put(Constants.COLOUR_OF_TOWER_WITH_CHARACTER_CARD, getTower(1));
        zones.put(Constants.COLOUR_OF_TOWER_WITH_BUILDING_CARD, getTower(2));
        zones.put(Constants.COLOUR_OF_TOWER_WITH_VENTURES_CARD, getTower(3));
    }

    /**
     * This method increse the round variable
     */
    public void nextRound() {
        round++;
        if (round == 2) round = 0;
    }

    /**
     * Set marketZone
     *
     * @param marketZone marketZone
     */
    public void setMarketZone(Market[] marketZone) {
        this.marketZone = marketZone;

    }

    /**
     * Get victoryPointsInFaithTrack
     *
     * @return victoryPointsInFaithTrack
     */
    public int[] getVictoryPointsInFaithTrack() {
        return victoryPointsInFaithTrack;
    }

    /**
     * Get towers
     *
     * @return towers
     */
    private Tower[] getTower(int i) {
        return towers[i];
    }

    /**
     * Get marketZone
     *
     * @return marketZone
     */
    public Market[] getMarketZone() {
        return marketZone;
    }

    /**
     * Get councilZone
     *
     * @return councilZone
     */
    public List<Council> getCouncilZone() {
        return councilZone;
    }

    /**
     * Set councilZone
     *
     * @param councilZone councilZone
     */
    public void setCouncilZone(List<Council> councilZone) {
        this.councilZone = councilZone;
    }

    /**
     * Get excommunicationZone
     *
     * @return excommunicationZone
     */
    public ExcommunicationZone[] getExcommunicationZone() {
        return excommunicationZone;
    }

    // todo public ArrayList<Player> getTurnOrder() { return TurnOrder; } public void setTurnOrder(ArrayList<Player> turnOrder) { TurnOrder = turnOrder;}


    /**
     * Get period
     *
     * @return period
     */
    public int getPeriod() {
        return period;
    }

    /**
     * Get round
     *
     * @return round
     */
    public int getRound() {
        return round;
    }

    /**
     * Set diceValue
     *
     * @param diceValue diceValue
     */
    public void setDiceValue(int[] diceValue) {
        this.diceValue = diceValue;
    }

    /**
     * Get Tower
     *
     * @return Tower
     */
    public Tower[] getTrueArrayList(String whichZone) {
        return (Tower[]) zones.get(whichZone);
    }

    /**
     * Get towers
     *
     * @return towers
     */
    public Tower[][] getAllTowers() {
        return towers;
    }

    /**
     * This method increments the period variable
     */
    public void nextPeriod() {
        period++;
    }

    /**
     * Set towers
     *
     * @param towers towers
     */
    public void setTowers(Tower[][] towers) {
        this.towers = towers;
    }

    /**
     * Get finalPointsFromTerritoryCards
     *
     * @return finalPointsFromTerritoryCards
     */
    public int[] getFinalPointsFromTerritoryCards() {
        return finalPointsFromTerritoryCards;
    }

    /**
     * Get finalPointsFromCharacterCards
     *
     * @return finalPointsFromCharacterCards
     */
    public int[] getFinalPointsFromCharacterCards() {
        return finalPointsFromCharacterCards;
    }

    /**
     * Get faithPointsRequiredEveryPeriod
     *
     * @return faithPointsRequiredEveryPeriod
     */
    public int[] getFaithPointsRequiredEveryPeriod() {
        return faithPointsRequiredEveryPeriod;
    }

    /**
     * Get turn
     *
     * @return turn
     */
    public Turn getTurn() {
        return turn;
    }

    /**
     * Get diceValue
     *
     * @return diceValue
     */
    public int[] getDiceValue() {
        return diceValue;
    }

    /**
     * Get decks
     *
     * @return decks
     */
    public Deck getDeckCard() {
        return decks;
    }

    /**
     * Set excommunicationZone
     *
     * @param excommunicationZone excommunicationZone
     */
    public void setExcommunicationZone(ExcommunicationZone[] excommunicationZone) {
        this.excommunicationZone = excommunicationZone;
    }

    /**
     * Get councilPrivileges
     *
     * @return councilPrivileges
     */
    public CouncilPrivilege[] getCouncilPrivileges() {
        return councilPrivileges;
    }



    public void setCouncilPrivileges(CouncilPrivilege[] councilPrivileges) {
        this.councilPrivileges = councilPrivileges;
    }

    /**
     * Set tower in towers
     *
     * @param towerNumber tower number
     * @param floor floor's number
     * @param tower tower's reference
     */
    public void setTowerInTowers(int towerNumber, int floor, Tower tower) {
        this.towers[towerNumber][floor] = tower;
    }

    /**
     * Get militaryPointsForTerritories
     *
     * @return militaryPointsForTerritories
     */
    public int[] getMilitaryPointsForTerritories() {
        return militaryPointsForTerritories;
    }

    /**
     * Get towers
     *
     * @return towers
     */
    public Tower[][] getTowers() {
        return towers;
    }

    /**
     * Get harvesterZone
     *
     * @return harvesterZone
     */
    public List<Harvester> getHarvesterZone() {
        return harvesterZone;
    }

    /**
     * Get victoryPointsInFaproductionZoneithTrack
     *
     * @return productionZone
     */
    public List<Production> getProductionZone() {
        return productionZone;
    }

    /**
     * Set harvesterZone
     *
     * @param harvesterZone harvesterZone
     */
    public void setHarvesterZone(List<Harvester> harvesterZone) {
        this.harvesterZone = harvesterZone;
    }

    /**
     * Set productionZone
     *
     * @param productionZone productionZone
     */
    public void setProductionZone(List<Production> productionZone) {
        this.productionZone = productionZone;
    }

    /**
     * Set victoryPointsInFaithTrack
     *
     * @param victoryPointsInFaithTrack victoryPointsInFaithTrack
     */
    public void setVictoryPointsInFaithTrack(int[] victoryPointsInFaithTrack) {
        this.victoryPointsInFaithTrack = victoryPointsInFaithTrack;
    }

    /**
     * Set finalPointsFromTerritoryCards
     *
     * @param finalPointsFromTerritoryCards finalPointsFromTerritoryCards
     */
    public void setFinalPointsFromTerritoryCards(int[] finalPointsFromTerritoryCards) {
        this.finalPointsFromTerritoryCards = finalPointsFromTerritoryCards;
    }

    /**
     * Set finalPointsFromCharacterCards
     *
     * @param finalPointsFromCharacterCards finalPointsFromCharacterCards
     */
    public void setFinalPointsFromCharacterCards(int[] finalPointsFromCharacterCards) {
        this.finalPointsFromCharacterCards = finalPointsFromCharacterCards;
    }

    /**
     * Set faithPointsRequiredEveryPeriod
     *
     * @param faithPointsRequiredEveryPeriod faithPointsRequiredEveryPeriod
     */
    public void setFaithPointsRequiredEveryPeriod(int[] faithPointsRequiredEveryPeriod) {
        this.faithPointsRequiredEveryPeriod = faithPointsRequiredEveryPeriod;
    }

    /**
     * Set militaryPointsForTerritories
     *
     * @param militaryPointsForTerritories militaryPointsForTerritories
     */
    public void setMilitaryPointsForTerritories(int[] militaryPointsForTerritories) {
        this.militaryPointsForTerritories = militaryPointsForTerritories;
    }

    /**
     * Set turn
     *
     * @param turn turn
     */
    public void setTurn(Turn turn) {
        this.turn = turn;
    }

    /**
     * Set deckCard
     *
     * @param deckCard deckCard
     */
    void setDeckCard(Deck deckCard) {
        this.decks = deckCard;
    }
}