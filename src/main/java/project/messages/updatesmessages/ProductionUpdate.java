package project.messages.updatesmessages;

import project.controller.Constants;
import project.model.Board;
import project.model.Production;

import java.io.Serializable;
import java.util.List;

/**
 * Created by raffaelebongo on 14/06/17.
 */
public class ProductionUpdate extends Updates implements Serializable {

    private List<Production> productionZone;

    public ProductionUpdate(List<Production> production ){
        super();
        productionZone = production;
    }

    @Override
    public String toString() {
        return Constants.BOARD_UPDATE;
    }

    @Override
    public void doUpdate(Board board) {
        board.setProductionZone(productionZone);
    }

    @Override
    public String toScreen() {
       return "The actual player has performed a production action!";
    }
}
