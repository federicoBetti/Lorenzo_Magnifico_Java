package project.model;


import project.configurations.Configuration;
import project.controller.Constants;

import java.io.FileNotFoundException;
import java.util.*;

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
    private int isPlaying;

    /**
     *
     */
    private int round;


    private int[] diceValue;


    private Map<String, Position[]> zones;

    /**
     * questa variabile rappresenta se siamo alla fine di un turno e aspettiamo il lancio dei dadi
     */
    private boolean endRound = false;

    /**
     * qiesto array di interi rappresenta i punti vittori ottenuti pregando per ognuno dei 15 posti del tracciato di punti fede
     */
    private int[] victoryPointsInFaithTrack;

    private Deck decks;

    private List<Integer> finalPointsFromTerritoryCards;

    private List<Integer> finalPointsFromCharacterCards;

    private int[] faithPointsRequiredEveryPeriod; //todo bisogna scrivere dentro i numeri, in teoria sono 3 4 5

    private int[] militaryPointsForTerritories;

    Configuration configuration;


    public Board(int numberOfPlayer) throws FileNotFoundException {
        this.councilPrivileges = new CouncilPrivilege[Constants.PRIVILEDGE_NUMBER];
        this.faithPointsRequiredEveryPeriod = new int[Constants.PERIOD_NUMBER];
        this.victoryPointsInFaithTrack = new int[Constants.FAITH_TRACK];
        this.turn = new Turn();
        this.decks = new Deck();

        //questi sotto sono da importare da file, li inizializzo poer testare
        militaryPointsForTerritories = new int[]{0, 0, 0, 0, 0, 0};

        Configuration configuration = new Configuration();

        if (numberOfPlayer == Constants.FOUR_PLAYERS) {
            marketZone = new Market[Constants.FOUR_PLAYERS];
            configuration.loadMarketBonus(this, "/fileJson/marketZone4.json");
        } else {
            marketZone = new Market[Constants.TWO_PLAYERS];
            marketZone = new Market[Constants.FOUR_PLAYERS];
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

        //configuration.loadDevelopmentCards(decks);
       // configuration.loadLeaderCard(decks);
       // configuration.loadExcommunicationTiles(decks);
     //   configuration.loadBonusTile(getDeckCard());


        round = 0;
        period = 0;
    }

    public Board() {
        towers = new Tower[0][0];
        councilPrivileges = new CouncilPrivilege[0];
        harvesterZone = new ArrayList<>();
        productionZone = new ArrayList<>();
        marketZone = new Market[0];
        councilZone = new ArrayList<>();
        excommunicationZone = new ExcommunicationZone[3];
        turn = new Turn();
    }


    private void fillHashMap() {
        zones.put(Constants.COLOUR_OF_TOWER_WITH_TERRITORY_CARD, getTower(0));
        zones.put(Constants.COLOUR_OF_TOWER_WITH_CHARACTER_CARD, getTower(1));
        zones.put(Constants.COLOUR_OF_TOWER_WITH_BUILDING_CARD, getTower(2));
        zones.put(Constants.COLOUR_OF_TOWER_WITH_VENTURES_CARD, getTower(3));
    }

    public void nextRound() {
        round++;
        if (round == 3) round = 1;
    }

    public void setMarketZone(Market[] marketZone) {
        this.marketZone = marketZone;

    }

    public int[] getVictoryPointsInFaithTrack() {
        return victoryPointsInFaithTrack;
    }

    public Tower[] getTower(int i) {
        return towers[i];
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

    public Tower[] getTrueArrayList(String whichZone) {
        return (Tower[]) zones.get(whichZone);
    }

    public boolean getEndRound() {
        return endRound;
    }

    public void setEndRound(boolean endRound) {
        this.endRound = endRound;
    }

    public Tower[][] getAllTowers() {
        return towers;
    }

    public void nextPeriod() {
        period++;
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
        return decks;
    }

    public void setExcommunicationZone(ExcommunicationZone[] excommunicationZone) {
        this.excommunicationZone = excommunicationZone;
    }

    public CouncilPrivilege[] getCouncilPrivileges() {
        return councilPrivileges;
    }

    public void setMarketPosition(int positionNumber, Market market) {
        this.marketZone[positionNumber] = market;
    }

    public void setCouncilPrivileges(CouncilPrivilege[] councilPrivileges) {
        this.councilPrivileges = councilPrivileges;
    }

    public void setTowerInTowers(int towerNumber, int floor, Tower tower) {
        this.towers[towerNumber][floor] = tower;
    }

    public int[] getMilitaryPointsForTerritories() {
        return militaryPointsForTerritories;
    }

    public Tower[][] getTowers() {
        return towers;
    }

    public List<Harvester> getHarvesterZone() {
        return harvesterZone;
    }

    public List<Production> getProductionZone() {
        return productionZone;
    }

    public Map<String, Position[]> getZones() {
        return zones;
    }

    public boolean isEndRound() {
        return endRound;
    }

    public Deck getDecks() {
        return decks;
    }

    public void setHarvesterZone(List<Harvester> harvesterZone) {
        this.harvesterZone = harvesterZone;
    }

    public void setProductionZone(List<Production> productionZone) {
        this.productionZone = productionZone;
    }

    public Tower[][] getAllTowersUpdate() {
        return towers.clone();
    }
}