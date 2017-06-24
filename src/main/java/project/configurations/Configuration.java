package project.configurations;

import project.DevelopmentDeckIterator;
import project.TowerIterator;
import project.controller.Constants;
import project.controller.cardsfactory.*;
import project.model.*;
import com.google.gson.Gson;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.JsonStreamParser;
import project.server.Server;

public class Configuration {

    Gson gson;
    CardFromJson[] cardsFromJson;
    Map<String, BuilderHandler> map;
    BuilderHandler builderHandler;

    public Configuration() {
        this.map = new HashMap<>();
        this.gson = new Gson();
        this.loadMap();
    }

    public Map<String, BuilderHandler> getMap() {
        return map;
    }


    public void loadMap() {
        map.put(Constants.BUILDING_CARD, this::buildBuildingCard);
        map.put(Constants.TERRITORY_CARD, this::buildTerritoryCard);
        map.put(Constants.CHARACTER_CARD, this::buildCharacterCard);
        map.put(Constants.VENTURE_CARD, this::buildVentureCard);
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

        JsonStreamParser parser = new JsonStreamParser(new FileReader("/file/giusto"));

        while (parser.hasNext()) {
            ExTileFromJson exTileFromJson = gson.fromJson(parser.next(), ExTileFromJson.class);
            ExcommunicationTile excommunicationTile = new ExcommunicationTile(exTileFromJson.getIdCard(), exTileFromJson.getPeriod(), exTileFromJson.getExcomunicationEffectsFromJson());

            deck.getExcomunicationCard()[exTileFromJson.getPeriod()][exTileFromJson.getIdCard()] = excommunicationTile;
        }
    }

    public void loadBonusTile(Deck deck) throws FileNotFoundException {

        InputStream is = getClass().getResourceAsStream("/fileJson/bonusTile.json");
        Reader reader = new InputStreamReader(is);

        TileBonusFromJson tileBonusFromJson = gson.fromJson(reader, TileBonusFromJson.class);
        Tile tile = new Tile(tileBonusFromJson);
        deck.setProdHaarvTile(tileBonusFromJson.getTileNumber(), tile);

    }


    public void loadLeaderCard() throws FileNotFoundException {
        InputStream is = getClass().getResourceAsStream("/fileJson/bonusTile.json");
        Reader reader = new InputStreamReader(is);

        LeaderCard leaderCard = gson.fromJson(reader, LeaderCard.class);
        //todo inserirlo nel deck
    }

    public void loadCouncilZonePriviledges(Board board) throws FileNotFoundException {

        InputStream is = getClass().getResourceAsStream("/fileJson/councilpriviledge.json");
        Reader reader = new InputStreamReader(is);

        CouncilPrivilege[] councilPrivilege = gson.fromJson(reader, CouncilPrivilege[].class);
        board.setCouncilPrivileges(councilPrivilege);

    }

    /**
     * @param board
     * @param jsonFile depends on the players'number
     * @throws FileNotFoundException
     */
    public void loadMarketBonus(Board board, String jsonFile) throws FileNotFoundException {

        InputStream is = getClass().getResourceAsStream(jsonFile);
        Reader reader = new InputStreamReader(is);


        Market[] marketZone = gson.fromJson(reader, Market[].class);
        board.setMarketZone(marketZone);
    }

    public void loadBonusTower(Board board) throws FileNotFoundException {
        InputStream is = getClass().getResourceAsStream("/fileJson/newTowerZone.json");
        Reader reader = new InputStreamReader(is);
        JsonStreamParser parser = new JsonStreamParser(reader);
        TowerIterator iterator = new TowerIterator();

        TowerFromJson[][] towersFromJson = gson.fromJson(reader, TowerFromJson[][].class);

        while (iterator.hasNext()) {

            Tower tower = new Tower(towersFromJson[iterator.getTowerNumber()][iterator.getFloor()].getColour(), towersFromJson[iterator.getTowerNumber()][iterator.getFloor()].getDiceValueOfThisFloor(), towersFromJson[iterator.getTowerNumber()][iterator.getFloor()].getTowerNumber(), towersFromJson[iterator.getTowerNumber()][iterator.getFloor()].getTrisIE());
            board.setTowerInTowers(iterator.getTowerNumber(), iterator.getFloor(), tower);
            iterator.next();
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

    DevelopmentCard buildVentureCard(CardFromJson cardFromJson) {
        String jsonCost = gson.toJson(cardFromJson.getAnagrafic().getCost());
        VenturesCost[] venturesCosts = gson.fromJson(jsonCost, VenturesCost[].class);
        VentureCostArray ventureCostArray = new VentureCostArray();

        for (VenturesCost cost : venturesCosts)
            ventureCostArray.getCostArray().add(cost);

        return new VenturesCard(cardFromJson.getAnagrafic().getName(), cardFromJson.getAnagrafic().getPeriod(), cardFromJson.getAnagrafic().isChoicePe(), ventureCostArray.getCostArray(), cardFromJson.getImmediateEffect().getTris(), cardFromJson.getPermanentEffect().getPoker());
    }

    DevelopmentCard buildBuildingCard(CardFromJson cardFromJson) {
        String jsonCost = gson.toJson(cardFromJson.getAnagrafic().getCost());
        BuildingCost[] aCost = gson.fromJson(jsonCost, BuildingCost[].class);
        BuildingCost cost = aCost[0];
        return new BuildingCard(cardFromJson.getAnagrafic().getName(), cardFromJson.getAnagrafic().getPeriod(), cardFromJson.getAnagrafic().isChoicePe(), cost, cardFromJson.getImmediateEffect().getTris(), cardFromJson.getPermanentEffect().getPoker());
    }

    DevelopmentCard buildCharacterCard(CardFromJson cardFromJson) {
        String jsonCost = gson.toJson(cardFromJson.getAnagrafic().getCost());
        CharactersCost[] aCost = gson.fromJson(jsonCost, CharactersCost[].class);
        CharactersCost cost = aCost[0];
        return new CharacterCard(cardFromJson.getAnagrafic().getName(), cardFromJson.getAnagrafic().getPeriod(), cardFromJson.getAnagrafic().isChoicePe(), cost, cardFromJson.getImmediateEffect().getTris(), cardFromJson.getPermanentEffect().getPoker());
    }

    DevelopmentCard buildTerritoryCard(CardFromJson cardFromJson) {
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


