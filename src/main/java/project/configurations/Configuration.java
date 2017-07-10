package project.configurations;

import project.controller.Constants;
import project.controller.cardsfactory.*;
import project.controller.effects.realeffects.Effects;
import project.model.*;
import com.google.gson.Gson;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.JsonStreamParser;

/**
 * This class contains the methods for loading the configurables objects in the software
 */
public class Configuration {

    private Gson gson;
    private CardFromJson[] cardsFromJson;
    private Map<String, BuilderHandler> map;
    private BuilderHandler builderHandler;

    /**
     * Constructor
     */
    public Configuration() {
        this.map = new HashMap<>();
        this.gson = new Gson();
        this.loadMap();
    }

    /**
     * Get the map reference
     * @return map reference
     */
    public Map<String, BuilderHandler> getMap() {
        return map;
    }

    /**
     * Fill the map with the corrects entries
     */
    private void loadMap() {
        map.put(Constants.BUILDING_CARD, this::buildBuildingCard);
        map.put(Constants.TERRITORY_CARD, this::buildTerritoryCard);
        map.put(Constants.CHARACTER_CARD, this::buildCharacterCard);
        map.put(Constants.VENTURE_CARD, this::buildVentureCard);
    }

    /**
     * This method is used for loading the faith points track
     *
     * @param board board's reference
     */
    public void loadFaithPointsTracks(Board board ){
        InputStream is = getClass().getResourceAsStream("/fileJson/victoryPointsInFaithTrack.json");
        Reader reader = new InputStreamReader(is);

        board.setVictoryPointsInFaithTrack(gson.fromJson(reader, int[].class));

    }

    /**
     * This method is used for loading the final points from terriotry cards array
     *
     * @param board board's reference
     */
    public void loadFinalPointsFromTerritoryCards(Board board) {
        InputStream is = getClass().getResourceAsStream("/fileJson/finalPointsFromTerritoryCards.json");
        Reader reader = new InputStreamReader(is);

        board.setFinalPointsFromTerritoryCards(gson.fromJson(reader, int[].class));

    }

    /**
     * This method is used for loading the final poits form character cards
     *
     * @param board board's reference
     */
    public void loadFinalPointsFromCharacterCards(Board board) {
        InputStream is = getClass().getResourceAsStream("/fileJson/finalPointsFromCharacterCards.json");
        Reader reader = new InputStreamReader(is);

        board.setFinalPointsFromCharacterCards(gson.fromJson(reader, int[].class));

    }

    /**
     * This method is used for loading the faith points required for every period of game
     *
     * @param board board's reference
     */
    public void loadFaithPointsRequiredEveryPeriod(Board board) {
        InputStream is = getClass().getResourceAsStream("/fileJson/faithPointsRequiredEveryPeriod.json");
        Reader reader = new InputStreamReader(is);

        board.setFaithPointsRequiredEveryPeriod(gson.fromJson(reader, int[].class));
    }

    /**
     * This method is used for load the military points required for taking territory cards
     *
     * @param board board's reference
     */
    public void loadMilitaryPointsForTerritories(Board board) {
        InputStream is = getClass().getResourceAsStream("/fileJson/militaryPointsForTerritories.json");
        Reader reader = new InputStreamReader(is);

        board.setMilitaryPointsForTerritories(gson.fromJson(reader, int[].class));
    }

    /**
     * This method is used for loading the development cards
     *
     * @param deck deck's reference
     * @throws FileNotFoundException Signals that an attempt to open the file denoted by a specified pathname
     *                               has failed.
     */
    public void loadDevelopmentCards(Deck deck) throws FileNotFoundException {

        DevelopmentDeckIterator iterator = new DevelopmentDeckIterator();
        InputStream is = getClass().getResourceAsStream("/fileJson/developmentCards.json");
        Reader reader = new InputStreamReader(is);

        this.cardsFromJson = gson.fromJson(reader, CardFromJson[].class);
        for (CardFromJson cardFJ : cardsFromJson) {
            builderHandler = map.get(cardFJ.getAnagrafic().getType());
            DevelopmentCard card = builderHandler.build(cardFJ);

            deck.getDevelopmentDeck()[iterator.getColor()][iterator.getPeriod()][iterator.getCards()] = card;
            iterator.next();
        }
    }

    /**
     * This method is used for loading the excommunication tiles
     *
     * @param deck deck's reference
     * @throws FileNotFoundException Signals that an attempt to open the file denoted by a specified pathname
     *                               has failed.
     */
    public void loadExcommunicationTiles(Deck deck) throws FileNotFoundException {

        InputStream is = getClass().getResourceAsStream("/fileJson/excommunicationTile.json");
        Reader reader = new InputStreamReader(is);

        ExTileFromJson[] exTilesFromJson = gson.fromJson(reader, ExTileFromJson[].class);

        for ( ExTileFromJson tile : exTilesFromJson ) {
            ExcommunicationTile excommunicationTile = new ExcommunicationTile(tile.getIdCard(), tile.getPeriod(), tile.getExcomunicationEffectsFromJson(), tile.getEffectDescription());
            deck.getExcommunicationCard()[tile.getPeriod() - 1][tile.getIdCard() - 1] = excommunicationTile;
        }

    }

    /**
     * This method is used for loadinf the bonus tiles
     *
     * @param deck deck's reference
     * @throws FileNotFoundException Signals that an attempt to open the file denoted by a specified pathname
     *                               has failed.
     */
    public void loadBonusTile(Deck deck) throws FileNotFoundException {

        InputStream is = getClass().getResourceAsStream("/fileJson/bonusTile.json");
        Reader reader = new InputStreamReader(is);

        TileBonusFromJson[] tilesBonusFromJson = gson.fromJson(reader, TileBonusFromJson[].class);

        for ( TileBonusFromJson tileJ : tilesBonusFromJson ) {
            Tile tile = new Tile(tileJ);
            for (Effects effects : tile.takeProductionResource() )
                System.out.println("effetto tile " + effects.toScreen());
            deck.setProdHaarvTile(tileJ.getTileNumber(), tile);
        }

    }

    /**
     * This method is use for loading the leader cards
     *
     * @param deck deck's reference
     * @throws FileNotFoundException Signals that an attempt to open the file denoted by a specified pathname
     *                               has failed.
     */
    public void loadLeaderCard(Deck deck) throws FileNotFoundException {
        InputStream is = getClass().getResourceAsStream("/fileJson/leaderCards.json");
        Reader reader = new InputStreamReader(is);

        LeaderCard[] leaderCards = gson.fromJson(reader, LeaderCard[].class);
        List<LeaderCard> leaders = new ArrayList<>();

        for ( LeaderCard card : leaderCards )
            leaders.add(card);


        deck.setLeaderCardDeck(leaders);

    }

    /**
     * This method is use for loading the council zone privileges
     *
     * @param board board's reference
     * @throws FileNotFoundException Signals that an attempt to open the file denoted by a specified pathname
     *                               has failed.
     */
    public void loadCouncilZonePriviledges(Board board) throws FileNotFoundException {

        InputStream is = getClass().getResourceAsStream("/fileJson/councilpriviledge.json");
        Reader reader = new InputStreamReader(is);

        CouncilPrivilegesFromJson[] councilPrivilegesJ = gson.fromJson(reader, CouncilPrivilegesFromJson[].class);
        CouncilPrivilege[] councilPrivilege = new CouncilPrivilege[5];

        for ( CouncilPrivilegesFromJson cpj : councilPrivilegesJ ) {
            councilPrivilege[cpj.getPriviledgeNumber()] = new CouncilPrivilege(cpj.getTrisIEL(), cpj.getPriviledgeNumber());

            board.setCouncilPrivileges(councilPrivilege);

        }
    }

    /**
     * This method is used for loading the market position's effects
     *
     * @param board board's reference
     * @param jsonFile depends on the players'number
     * @throws FileNotFoundException Signals that an attempt to open the file denoted by a specified pathname
     *                               has failed.
     */
    public void loadMarketBonus(Board board, String jsonFile) throws FileNotFoundException {

        InputStream is = getClass().getResourceAsStream(jsonFile);
        Reader reader = new InputStreamReader(is);

        List<Market> marketZone = new ArrayList<>();
        MarketFromJson[] marketFromJsons = gson.fromJson(reader, MarketFromJson[].class);
        for ( MarketFromJson marketFromJson : marketFromJsons ) {
            marketZone.add(new Market(marketFromJson.getTrisIE()));
        }
        Market[] market = new Market[marketZone.size()];
        int i = 0;
        for ( Market marketInList : marketZone ){
            market[i] = marketInList;
            i++;
        }
        board.setMarketZone(market);
    }

    /**
     * This method is used for loading the bonus on the towers
     *
     * @param board board's reference
     * @throws FileNotFoundException Signals that an attempt to open the file denoted by a specified pathname
     *                               has failed.
     */
    public void loadBonusTower(Board board) throws FileNotFoundException {
        InputStream is = getClass().getResourceAsStream("/fileJson/newTowerZone.json");
        Reader reader = new InputStreamReader(is);

        TowerFromJson[][] towersFromJson = gson.fromJson(reader, TowerFromJson[][].class);


        for ( int i = 0; i < 4; i++ ) {
            for ( int j = 0; j < 4; j++ ) {
                Tower tower = new Tower(towersFromJson[i][j].getColour(), towersFromJson[i][j].getDiceValueOfThisFloor(), towersFromJson[i][j].getTowerNumber(), towersFromJson[i][j].getTrisIE());
                board.setTowerInTowers(i, j, tower);
            }
        }
    }

    /**
     * This method is used for loading the family member's colour
     *
     * @param player palyer's reference
     * @throws FileNotFoundException Signals that an attempt to open the file denoted by a specified pathname
     *                               has failed.
     */
    public void loadFamilyMembers(Player player) throws FileNotFoundException {
        InputStream is = getClass().getResourceAsStream("/fileJson/familyMember.json");
        Reader reader = new InputStreamReader(is);

        player.setAllFamilyMembers(gson.fromJson(reader, FamilyMember[].class));
    }

    /**
     * This method is used for loading the timers's configuration
     *
     * @return the timer's reference
     * @throws FileNotFoundException Signals that an attempt to open the file denoted by a specified pathname
     *                               has failed.
     */
    public TimerSettings loadTimer() throws FileNotFoundException {
        InputStream is = getClass().getResourceAsStream("/fileJson/timer.json");
        Reader reader = new InputStreamReader(is);
        JsonStreamParser parser = new JsonStreamParser(reader);

        TimerSettings timerSettings = gson.fromJson(parser.next(), TimerSettings.class);

        return timerSettings;

    }

    /**
     * This method is used for building a Venture card
     *
     * @param cardFromJson the generic card object built with the json file using Gson
     * @return the VentureCard's reference
     */
    private DevelopmentCard buildVentureCard(CardFromJson cardFromJson) {
        String jsonCost = gson.toJson(cardFromJson.getAnagrafic().getCost());
        VenturesCost[] venturesCosts = gson.fromJson(jsonCost, VenturesCost[].class);
        VentureCostArray ventureCostArray = new VentureCostArray();

        for (VenturesCost cost : venturesCosts)
            ventureCostArray.getCostArray().add(cost);

        return new VenturesCard(cardFromJson.getAnagrafic().getName(), cardFromJson.getAnagrafic().getPeriod(), cardFromJson.getAnagrafic().isChoicePe(), ventureCostArray.getCostArray(), cardFromJson.getImmediateEffect().getTris(), cardFromJson.getPermanentEffect().getPoker());
    }

    /**
     * This method is used for building a building card
     *
     * @param cardFromJson the generic card object built with the json file using Gson
     * @return the buildingCard's reference
     */
    private DevelopmentCard buildBuildingCard(CardFromJson cardFromJson) {
        String jsonCost = gson.toJson(cardFromJson.getAnagrafic().getCost());
        BuildingCost[] aCost = gson.fromJson(jsonCost, BuildingCost[].class);
        BuildingCost cost = aCost[0];
        return new BuildingCard(cardFromJson.getAnagrafic().getName(), cardFromJson.getAnagrafic().getPeriod(), cardFromJson.getAnagrafic().isChoicePe(), cost, cardFromJson.getImmediateEffect().getTris(), cardFromJson.getPermanentEffect().getPoker());
    }

    /**
     * This method is used for building a Character card
     *
     * @param cardFromJson the generic card object built with the json file using Gson
     * @return the Character card's reference
     */
    private DevelopmentCard buildCharacterCard(CardFromJson cardFromJson) {
        String jsonCost = gson.toJson(cardFromJson.getAnagrafic().getCost());
        CharactersCost[] aCost = gson.fromJson(jsonCost, CharactersCost[].class);
        CharactersCost cost = aCost[0];
        return new CharacterCard(cardFromJson.getAnagrafic().getName(), cardFromJson.getAnagrafic().getPeriod(), cardFromJson.getAnagrafic().isChoicePe(), cost, cardFromJson.getImmediateEffect().getTris(), cardFromJson.getPermanentEffect().getPoker());
    }

    /**
     * This method is used for building a Territory cards
     *
     * @param cardFromJson the generic card object built with the json file using Gson
     * @return the territory card's reference
     */
    private DevelopmentCard buildTerritoryCard(CardFromJson cardFromJson) {
        String jsonCost = gson.toJson(cardFromJson.getAnagrafic().getCost());
        TerritoryCost[] aCost = gson.fromJson(jsonCost, TerritoryCost[].class);
        TerritoryCost cost = aCost[0];
        return new TerritoryCard(cardFromJson.getAnagrafic().getName(), cardFromJson.getAnagrafic().getPeriod(), cardFromJson.getAnagrafic().isChoicePe(), cost, cardFromJson.getImmediateEffect().getTris(), cardFromJson.getPermanentEffect().getPoker());
    }

    /**
     * Functional interface for building the cards
     */
    @FunctionalInterface
    private interface BuilderHandler {

        DevelopmentCard build(CardFromJson cardFromJson);
    }

}


