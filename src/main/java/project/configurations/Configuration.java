package project.configurations;

import project.DevelopmentDeckIterator;
import project.TowerIterator;
import project.controller.Constants;
import project.controller.cardsfactory.*;
import project.model.*;
import com.google.gson.Gson;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.JsonStreamParser;
import project.server.Server;

public class Configuration {

    private Gson gson;
    private CardFromJson[] cardsFromJson;
    private Map<String, BuilderHandler> map;
    private BuilderHandler builderHandler;

    public Configuration() {
        this.map = new HashMap<>();
        this.gson = new Gson();
        this.loadMap();
    }

    public Map<String, BuilderHandler> getMap() {
        return map;
    }


    private void loadMap() {
        map.put(Constants.BUILDING_CARD, this::buildBuildingCard);
        map.put(Constants.TERRITORY_CARD, this::buildTerritoryCard);
        map.put(Constants.CHARACTER_CARD, this::buildCharacterCard);
        map.put(Constants.VENTURE_CARD, this::buildVentureCard);
    }

    public void loadPointsTracks(){

    }

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

    public void loadExcommunicationTiles(Deck deck) throws FileNotFoundException {

        InputStream is = getClass().getResourceAsStream("/fileJson/excommunicationTile.json");
        Reader reader = new InputStreamReader(is);

        ExTileFromJson[] exTilesFromJson = gson.fromJson(reader, ExTileFromJson[].class);

        for ( ExTileFromJson tile : exTilesFromJson ) {
            ExcommunicationTile excommunicationTile = new ExcommunicationTile(tile.getIdCard(), tile.getPeriod(), tile.getExcomunicationEffectsFromJson(), tile.getEffectDescription());
            deck.getExcomunicationCard()[tile.getPeriod() - 1][tile.getIdCard() - 1] = excommunicationTile;
            System.out.println(excommunicationTile.getEffectDescription());
        }

    }

    public void loadBonusTile(Deck deck) throws FileNotFoundException {

        InputStream is = getClass().getResourceAsStream("/fileJson/bonusTile.json");
        Reader reader = new InputStreamReader(is);

        TileBonusFromJson[] tilesBonusFromJson = gson.fromJson(reader, TileBonusFromJson[].class);

        for ( TileBonusFromJson tileJ : tilesBonusFromJson ) {
            Tile tile = new Tile(tileJ);
            deck.setProdHaarvTile(tileJ.getTileNumber(), tile);
        }

    }


    public void loadLeaderCard(Deck deck) throws FileNotFoundException {
        InputStream is = getClass().getResourceAsStream("/fileJson/leaderCards.json");
        Reader reader = new InputStreamReader(is);

        LeaderCard[] leaderCards = gson.fromJson(reader, LeaderCard[].class);
        List<LeaderCard> leaders = new ArrayList<>();

        for ( LeaderCard card : leaderCards )
            leaders.add(card);


        deck.setLeaderCardeck(leaders);

    }

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
     * @param board
     * @param jsonFile depends on the players'number
     * @throws FileNotFoundException
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

    public void loadFamilyMembers(Player player) throws FileNotFoundException {
        InputStream is = getClass().getResourceAsStream("/fileJson/familyMember.json");
        Reader reader = new InputStreamReader(is);

        player.setAllFamilyMembers(gson.fromJson(reader, FamilyMember[].class));
    }

    public TimerSettings loadTimer() throws FileNotFoundException {
        InputStream is = getClass().getResourceAsStream("/fileJson/timer.json");
        Reader reader = new InputStreamReader(is);
        JsonStreamParser parser = new JsonStreamParser(reader);

        TimerSettings timerSettings = gson.fromJson(parser.next(), TimerSettings.class);

        return timerSettings;

    }

    private DevelopmentCard buildVentureCard(CardFromJson cardFromJson) {
        String jsonCost = gson.toJson(cardFromJson.getAnagrafic().getCost());
        VenturesCost[] venturesCosts = gson.fromJson(jsonCost, VenturesCost[].class);
        VentureCostArray ventureCostArray = new VentureCostArray();

        for (VenturesCost cost : venturesCosts)
            ventureCostArray.getCostArray().add(cost);

        return new VenturesCard(cardFromJson.getAnagrafic().getName(), cardFromJson.getAnagrafic().getPeriod(), cardFromJson.getAnagrafic().isChoicePe(), ventureCostArray.getCostArray(), cardFromJson.getImmediateEffect().getTris(), cardFromJson.getPermanentEffect().getPoker());
    }

    private DevelopmentCard buildBuildingCard(CardFromJson cardFromJson) {
        String jsonCost = gson.toJson(cardFromJson.getAnagrafic().getCost());
        BuildingCost[] aCost = gson.fromJson(jsonCost, BuildingCost[].class);
        BuildingCost cost = aCost[0];
        return new BuildingCard(cardFromJson.getAnagrafic().getName(), cardFromJson.getAnagrafic().getPeriod(), cardFromJson.getAnagrafic().isChoicePe(), cost, cardFromJson.getImmediateEffect().getTris(), cardFromJson.getPermanentEffect().getPoker());
    }

    private DevelopmentCard buildCharacterCard(CardFromJson cardFromJson) {
        String jsonCost = gson.toJson(cardFromJson.getAnagrafic().getCost());
        CharactersCost[] aCost = gson.fromJson(jsonCost, CharactersCost[].class);
        CharactersCost cost = aCost[0];
        return new CharacterCard(cardFromJson.getAnagrafic().getName(), cardFromJson.getAnagrafic().getPeriod(), cardFromJson.getAnagrafic().isChoicePe(), cost, cardFromJson.getImmediateEffect().getTris(), cardFromJson.getPermanentEffect().getPoker());
    }

    private DevelopmentCard buildTerritoryCard(CardFromJson cardFromJson) {
        String jsonCost = gson.toJson(cardFromJson.getAnagrafic().getCost());
        TerritoryCost[] aCost = gson.fromJson(jsonCost, TerritoryCost[].class);
        TerritoryCost cost = aCost[0];
        return new TerritoryCard(cardFromJson.getAnagrafic().getName(), cardFromJson.getAnagrafic().getPeriod(), cardFromJson.getAnagrafic().isChoicePe(), cost, cardFromJson.getImmediateEffect().getTris(), cardFromJson.getPermanentEffect().getPoker());
    }

    @FunctionalInterface
    private interface BuilderHandler {

        DevelopmentCard build(CardFromJson cardFromJson);
    }

}


