package Project.Controller;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;


public class LoadCards {


    void loadingCardsFromJson(){

        Gson gson = new Gson();
        try {
            JsonStreamParser parser = new JsonStreamParser(new FileReader("/Users/raffaelebongo/Desktop/provaConCosto.Json"));

            CartaFromJson carta = gson.fromJson(parser.next(), CartaFromJson.class);


            if ( carta.anagrafic.getType().equals("building")){
                System.out.println("ciao");
                String json = gson.toJson(carta.anagrafic.getCost());
                System.out.println(json);
                BuildingCost buildingCost = gson.fromJson(json, BuildingCost.class);
                System.out.println(buildingCost.getCoinsRequired());
            }


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }
    }
}
