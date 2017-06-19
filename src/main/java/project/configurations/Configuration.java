package project.configurations;

import project.DevelopmentDeckIterator;
import project.TowerIterator;
import project.controller.Constants;
import project.controller.cardsfactory.*;
import project.model.*;
import com.google.gson.Gson;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.JsonStreamParser;
import project.server.Server;

public class Configuration {

    Gson gson;
    CardFromJson cardFromJson;
    Map< String, BuilderHandler > map;
    BuilderHandler builderHandler;

    public Configuration() {
        this.map = new HashMap<>();
        this.gson = new Gson();
        this.loadMap();
    }

    public Map< String, BuilderHandler> getMap() {
        return map;
    }


    public void loadMap(){
        map.put( Constants.BUILDING_CARD.toString(), this::buildBuildingCard );
        map.put( Constants.TERRITORY_CARD.toString(), this::buildTerritoryCard);
        map.put( Constants.CHARACTER_CARD.toString(), this::buildCharacterCard );
        map.put( Constants.VENTURE_CARD.toString(), this::buildVentureCard );
    }


    public void loadDevelopmentCards(Deck deck ) throws FileNotFoundException {

        DevelopmentDeckIterator iterator = new DevelopmentDeckIterator();


        JsonStreamParser parser = new JsonStreamParser(new FileReader("/file/giusto"));

            while (parser.hasNext() && iterator.hasNext() ) {

                this.cardFromJson = gson.fromJson(parser.next(), CardFromJson.class);

                builderHandler =  map.get(cardFromJson.getAnagrafic().getType());
                DevelopmentCard card = builderHandler.build();

                deck.getDevelopmentDeck()[ iterator.getColor() ][ iterator.getPeriod() ][ iterator.getCards() ] = card;
                iterator.next();
            }
        }

    public void loadExcommunicationTiles( Deck deck ) throws FileNotFoundException {

        JsonStreamParser parser = new JsonStreamParser(new FileReader("/file/giusto"));

        while (parser.hasNext() ) {
            ExTileFromJson exTileFromJson = gson.fromJson(parser.next(),ExTileFromJson.class );
            ExcommunicationTile excommunicationTile = new ExcommunicationTile(exTileFromJson.getIdCard(), exTileFromJson.getPeriod(), exTileFromJson.getExcomunicationEffectsFromJson());

            deck.getExcomunicationCard()[exTileFromJson.getPeriod()][exTileFromJson.getIdCard()] = excommunicationTile;
        }
    }

    public void loadCouncilZonePriviledges(Board board) throws FileNotFoundException {

        JsonStreamParser parser = new JsonStreamParser(new FileReader("/file/giusto"));

        while (parser.hasNext() ) {
            CouncilPrivilegesFromJson councilPrivilegesFromJson = gson.fromJson(parser.next(), CouncilPrivilegesFromJson.class );
            CouncilPrivilege councilPrivilege = new CouncilPrivilege(councilPrivilegesFromJson.getTrisIEL(), councilPrivilegesFromJson.getPriviledgeNumber());
            board.setCouncilPrivilege(councilPrivilegesFromJson.getPriviledgeNumber(), councilPrivilege);
        }
    }

    /**
     *
     * @param board
     * @param jsonFile depends on the players'number
     * @throws FileNotFoundException
     */
    public void loadMarketBonus(Board board, String jsonFile ) throws FileNotFoundException {

        JsonStreamParser parser = new JsonStreamParser(new FileReader("/file/giusto"));

        while (parser.hasNext() ) {
            MarketFromJson marketFromJson = gson.fromJson(parser.next(), MarketFromJson.class );
            Market market = new Market(marketFromJson.getTrisIE());
            board.setMarketPosition(marketFromJson.getPosition(), market);
        }
    }

    public void loadBonusTile( Deck deck ) throws FileNotFoundException {

        JsonStreamParser parser = new JsonStreamParser(new FileReader("/file/giusto"));

        while ( parser.hasNext() ){
            TileBonusFromJson tileBonusFromJson = gson.fromJson( parser.next(), TileBonusFromJson.class );
            Tile tile = new Tile(tileBonusFromJson);
            deck.setProdHaarvTile(tileBonusFromJson.getTileNumber(), tile);
        }
    }

    public void loadBonusTower(Board board) throws FileNotFoundException {
        JsonStreamParser parser = new JsonStreamParser(new FileReader("/file/giusto"));
        TowerIterator iterator = new TowerIterator();

        while (parser.hasNext()){
            TowerFromJson towerFromJson = gson.fromJson(parser.next(), TowerFromJson.class);
            Tower tower = new Tower(towerFromJson.getColour(), towerFromJson.getDiceValueOfThisFloor(), towerFromJson.getTrisIE());
            board.setTowerInTowers(iterator.getTowerNumber(), iterator.getFloor(), tower);
            iterator.next();
         }
    }

    public void loadLeaderCard() throws FileNotFoundException {
        JsonStreamParser parser = new JsonStreamParser(new FileReader("/file/giusto"));

        while ( parser.hasNext() ){
            LeaderCard leaderCard = gson.fromJson(parser.next(), LeaderCard.class );
        }
    }

    public TimerSettings loadTimer() throws FileNotFoundException {
        JsonStreamParser parser = new JsonStreamParser(new FileReader("/file/giusto"));
        TimerSettings timerSettings = gson.fromJson(parser.next(), TimerSettings.class);

        return timerSettings;

    }

    DevelopmentCard buildVentureCard( ) {
        String jsonCost = gson.toJson(cardFromJson.getAnagrafic().getCost());
        VentureCostArray cost = gson.fromJson(jsonCost, VentureCostArray.class);
        return new VenturesCard(cardFromJson.getAnagrafic().getName(), cardFromJson.getAnagrafic().getPeriod(), cardFromJson.getAnagrafic().isChoicePe(), cost.getCostArray(), cardFromJson.getImmediateEffect().getTris(), cardFromJson.getPermanentEffect().getPoker());
    }

    DevelopmentCard buildBuildingCard( ){
        String jsonCost = gson.toJson(cardFromJson.getAnagrafic().getCost());
        BuildingCost cost = gson.fromJson(jsonCost, BuildingCost.class);
        return new BuildingCard(cardFromJson.getAnagrafic().getName(), cardFromJson.getAnagrafic().getPeriod(), cardFromJson.getAnagrafic().isChoicePe(), cost, cardFromJson.getImmediateEffect().getTris(), cardFromJson.getPermanentEffect().getPoker());
    }

    DevelopmentCard buildCharacterCard( ){
        String jsonCost = gson.toJson(cardFromJson.getAnagrafic().getCost());
        CharactersCost cost = gson.fromJson(jsonCost, CharactersCost.class);
        return new CharacterCard(cardFromJson.getAnagrafic().getName(), cardFromJson.getAnagrafic().getPeriod(), cardFromJson.getAnagrafic().isChoicePe(), cost, cardFromJson.getImmediateEffect().getTris(), cardFromJson.getPermanentEffect().getPoker());
    }

    DevelopmentCard buildTerritoryCard( ){
        String jsonCost = gson.toJson(cardFromJson.getAnagrafic().getCost());
        TerritoryCost cost = gson.fromJson(jsonCost, TerritoryCost.class);
        return new TerritoryCard(cardFromJson.getAnagrafic().getName(), cardFromJson.getAnagrafic().getPeriod(), cardFromJson.getAnagrafic().isChoicePe(), cost, cardFromJson.getImmediateEffect().getTris(), cardFromJson.getPermanentEffect().getPoker());
    }

    @FunctionalInterface
    private interface BuilderHandler {

        DevelopmentCard build( );
    }

}


