package project.messages.updatesmessages;

import project.controller.Constants;
import project.model.Board;
import project.model.Production;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by raffaelebongo on 14/06/17.
 */
public class ProductionUpdate extends Updates implements Serializable {

    private ArrayList<Production> productionZone;

    public ProductionUpdate(ArrayList<Production> production, String nickname ){
        super(nickname);
        productionZone = production;
    }

    @Override
    public String toString() {
        return Constants.BOARD_UPDATE;
    }

    @Override
    public ArrayList<Production> doUpdateProduction(){
        return productionZone;
    }

    @Override
    public String toScreen() {
       return "The current player has performed a production action!";
    }
}
