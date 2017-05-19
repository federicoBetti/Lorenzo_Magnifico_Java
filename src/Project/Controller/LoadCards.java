package Project.Controller;

import Project.Iterator;
import Project.MODEL.Deck;
import com.google.gson.Gson;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;

import Project.Controller.CardsFactory.*;
import com.google.gson.JsonStreamParser;
import jdk.nashorn.internal.parser.JSONParser;


public class LoadCards {

    HashMap<Object, ConstructorHandler> map;

    public HashMap<Object, ConstructorHandler> getMap() {
        return map;
    }

    public void setMap(HashMap<Object, ConstructorHandler> map) {
        this.map = map;
    }

    void loadingCardsFromJson( Deck deck ) throws FileNotFoundException {

        Iterator iterator = new Iterator();
        Anagrafic anagrafic;
        Gson gson = new Gson();
        JsonStreamParser parser = null;

        parser = new JsonStreamParser(new FileReader("/Users/raffaelebongo/Desktop/cardToUpload.Json"));

        if ( iterator.hasNext() ){

            CardFromJson cardFromJson = gson.fromJson(parser.next(), CardFromJson.class);
            anagrafic = cardFromJson.getAnagrafic();

            while (parser.hasNext()) {

                switch (cardFromJson.getAnagrafic().getType()) {
                    case "buildingCard": {
                        String jsonCost = gson.toJson(anagrafic.getCost());
                        BuildingCost cost = gson.fromJson(jsonCost, BuildingCost.class);
                        BuildingCard card = new BuildingCard(anagrafic.getName(), anagrafic.getPeriod(), cost, cardFromJson.getImmediateEffect().getTris(), cardFromJson.getPermanentEffect().getPoker());
                        deck.getDevelopmentdeck()[ iterator.getPeriod1() ][ iterator.getPeriod2() ][ iterator.getPeriod3() ] = card;
                    }

                    case "characterCard": {
                        String jsonCost = gson.toJson(anagrafic.getCost());
                        CharactersCost cost = gson.fromJson(jsonCost, CharactersCost.class);
                        CharacterCard card = new CharacterCard(anagrafic.getName(), anagrafic.getPeriod(), cost, cardFromJson.getImmediateEffect().getTris(), cardFromJson.getPermanentEffect().getPoker());
                        deck.getDevelopmentdeck()[ iterator.getPeriod1() ][ iterator.getPeriod2() ][ iterator.getPeriod3() ] = card;
                    }

                    case "territoryCard": {
                        String jsonCost = gson.toJson(anagrafic.getCost());
                        TerritoryCost cost = gson.fromJson(jsonCost, TerritoryCost.class);
                        TerritoryCard card = new TerritoryCard(anagrafic.getName(), anagrafic.getPeriod(), cost, cardFromJson.getImmediateEffect().getTris(), cardFromJson.getPermanentEffect().getPoker());
                        deck.getDevelopmentdeck()[ iterator.getPeriod1() ][ iterator.getPeriod2() ][ iterator.getPeriod3() ] = card;
                    }

                    case "ventureCard": { //TODO controllare il funzionamento con questo costo ( arraylist )
                        String jsonCost = gson.toJson(anagrafic.getCost());
                        VentureCostArray cost = gson.fromJson(jsonCost, VentureCostArray.class);
                        VenturesCard card = new VenturesCard(anagrafic.getName(), anagrafic.getPeriod(), cost.getCostArray(), cardFromJson.getImmediateEffect().getTris(), cardFromJson.getPermanentEffect().getPoker());
                        deck.getDevelopmentdeck()[ iterator.getPeriod1() ][ iterator.getPeriod2() ][ iterator.getPeriod3() ] = card;
                    }

                    iterator.next();
                }


            }

        }
    }

}

