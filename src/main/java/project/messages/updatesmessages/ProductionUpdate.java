package project.messages.updatesmessages;

import project.controller.Constants;
import project.model.Board;
import project.model.Production;

import java.io.Serializable;
import java.util.List;

/**
 * Object sends to the client for notifying that the production's zone has been changed
 */
public class ProductionUpdate extends Updates implements Serializable {

    private List<Production> productionZone;

    /**
     * Constructor
     *
     * @param production listo of productions'zone
     * @param nickname player's nickname
     */
    public ProductionUpdate(List<Production> production, String nickname ){
        super(nickname);
        productionZone = production;
    }

    /**
     * String the descibe the class
     *
     * @return the constants
     */
    @Override
    public String toString() {
        return Constants.BOARD_UPDATE;
    }

    /**
     * This method act the board's update in the client
     *
     * @param board board's reference
     */
    @Override
    public void doUpdate(Board board) {
        board.setProductionZone(productionZone);
    }

    /**
     * This method build a string that describes the update
     *
     * @return the description
     */
    @Override
    public String toScreen() {
       return getNicknameCurrentPlayer() + " has performed a production action!";
    }
}
