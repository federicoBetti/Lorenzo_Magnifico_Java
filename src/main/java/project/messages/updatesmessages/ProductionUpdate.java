package project.messages.updatesmessages;

import project.controller.Constants;
import project.model.Board;
import project.model.Production;

import java.io.Serializable;

/**
 * Created by raffaelebongo on 14/06/17.
 */
public class ProductionUpdate extends Updates implements Serializable {

    Production[] productionZone;

    public ProductionUpdate(Production[] production ){
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
}
