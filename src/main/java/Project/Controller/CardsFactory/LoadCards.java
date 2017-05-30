package Project.Controller.CardsFactory;

import Project.Controller.Constants;
import Project.Iterator;
import Project.MODEL.Deck;
import Project.MODEL.DevelopmentCard;
import com.google.gson.Gson;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;

import com.google.gson.JsonStreamParser;


//TODO Vedere se funziona davvero
public class LoadCards {

    Gson gson;
    CardFromJson cardFromJson;
    HashMap< String, BuilderHandler > map;
    BuilderHandler builderHandler;


    public LoadCards() {
        this.gson = new Gson();
        this.loadMap();
    }

    public HashMap< String, BuilderHandler> getMap() {
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
        JsonStreamParser parser = null;

        parser = new JsonStreamParser(new FileReader("/Users/raffaelebongo/Desktop/cardToUpload.Json"));

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
        VenturesCard card = new VenturesCard(cardFromJson.getAnagrafic().getName(), cardFromJson.getAnagrafic().getPeriod(), cost.getCostArray(), cardFromJson.getImmediateEffect().getTris(), cardFromJson.getPermanentEffect().getPoker());
        return card;
    }

    DevelopmentCard buildBuildingCard( ){
        String jsonCost = gson.toJson(cardFromJson.getAnagrafic().getCost());
        BuildingCost cost = gson.fromJson(jsonCost, BuildingCost.class);
        BuildingCard card = new BuildingCard(cardFromJson.getAnagrafic().getName(), cardFromJson.getAnagrafic().getPeriod(), cost, cardFromJson.getImmediateEffect().getTris(), cardFromJson.getPermanentEffect().getPoker());
        return card;
    }

    DevelopmentCard buildCharacterCard( ){
        String jsonCost = gson.toJson(cardFromJson.getAnagrafic().getCost());
        CharactersCost cost = gson.fromJson(jsonCost, CharactersCost.class);
        CharacterCard card = new CharacterCard(cardFromJson.getAnagrafic().getName(), cardFromJson.getAnagrafic().getPeriod(), cost, cardFromJson.getImmediateEffect().getTris(), cardFromJson.getPermanentEffect().getPoker());
        return card;
    }


    DevelopmentCard buildTerritoryCard(  ){
        String jsonCost = gson.toJson(cardFromJson.getAnagrafic().getCost());
        TerritoryCost cost = gson.fromJson(jsonCost, TerritoryCost.class);
        TerritoryCard card = new TerritoryCard(cardFromJson.getAnagrafic().getName(), cardFromJson.getAnagrafic().getPeriod(), cost, cardFromJson.getImmediateEffect().getTris(), cardFromJson.getPermanentEffect().getPoker());
        return card;
    }

    private interface BuilderHandler {

        DevelopmentCard build( );
    }

}


