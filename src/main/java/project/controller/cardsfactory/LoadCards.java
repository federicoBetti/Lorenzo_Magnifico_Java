package project.controller.cardsfactory;

import project.controller.Constants;
import project.model.Deck;
import project.model.DevelopmentCard;
import com.google.gson.Gson;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.JsonStreamParser;



//TODO Iteratore
public class LoadCards {
/*
    Gson gson;
    CardFromJson cardFromJson;
    Map< String, BuilderHandler > map;
    BuilderHandler builderHandler;

    public LoadCards() {
        this.map = new HashMap<>();
        this.gson = new Gson();
        this.loadMap();
    }

    public Map< String, BuilderHandler> getMap() {
        return map;
    }


    void loadMap(){
        map.put( Constants.BUILDING_CARD.toString(), this::buildBuildingCard );
        map.put( Constants.TERRITORY_CARD.toString(), this::buildTerritoryCard);
        map.put( Constants.CHARACTER_CARD.toString(), this::buildCharacterCard );
        map.put( Constants.VENTURE_CARD.toString(), this::buildVentureCard );
    }


    void loadingCardsFromJson( Deck deck ) throws FileNotFoundException {

        Iterator iterator = new Iterator();


        JsonStreamParser parser = new JsonStreamParser(new FileReader("/Users/raffaelebongo/Desktop/cardToUpload.Json"));

            while (parser.hasNext() && iterator.hasNext() ) {

                this.cardFromJson = gson.fromJson(parser.next(), CardFromJson.class);

                builderHandler =  map.get(cardFromJson.getAnagrafic().getType());
                DevelopmentCard card = builderHandler.build();

                deck.getDevelopmentdeck()[ iterator.getPeriod1() ][ iterator.getPeriod2() ][ iterator.getPeriod3() ] = card;
                iterator.next();
            }

        }


    DevelopmentCard buildVentureCard( ) {
        String jsonCost = gson.toJson(cardFromJson.getAnagrafic().getCost());
        VentureCostArray cost = gson.fromJson(jsonCost, VentureCostArray.class);
        return new VenturesCard(cardFromJson.getAnagrafic().getName(), cardFromJson.getAnagrafic().getPeriod(), cost.getCostArray(), cardFromJson.getImmediateEffect().getTris(), cardFromJson.getPermanentEffect().getPoker());
    }

    DevelopmentCard buildBuildingCard( ){
        String jsonCost = gson.toJson(cardFromJson.getAnagrafic().getCost());
        BuildingCost cost = gson.fromJson(jsonCost, BuildingCost.class);
        return new BuildingCard(cardFromJson.getAnagrafic().getName(), cardFromJson.getAnagrafic().getPeriod(), cost, cardFromJson.getImmediateEffect().getTris(), cardFromJson.getPermanentEffect().getPoker());
    }

    DevelopmentCard buildCharacterCard( ){
        String jsonCost = gson.toJson(cardFromJson.getAnagrafic().getCost());
        CharactersCost cost = gson.fromJson(jsonCost, CharactersCost.class);
        return new CharacterCard(cardFromJson.getAnagrafic().getName(), cardFromJson.getAnagrafic().getPeriod(), cost, cardFromJson.getImmediateEffect().getTris(), cardFromJson.getPermanentEffect().getPoker());
    }


    DevelopmentCard buildTerritoryCard(  ){
        String jsonCost = gson.toJson(cardFromJson.getAnagrafic().getCost());
        TerritoryCost cost = gson.fromJson(jsonCost, TerritoryCost.class);
        return new TerritoryCard(cardFromJson.getAnagrafic().getName(), cardFromJson.getAnagrafic().getPeriod(), cost, cardFromJson.getImmediateEffect().getTris(), cardFromJson.getPermanentEffect().getPoker());
    }

    @FunctionalInterface
    private interface BuilderHandler {

        DevelopmentCard build( );
    }
*/
}


