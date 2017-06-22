package project.messages.updatesmessages;

import project.controller.Constants;
import project.model.Board;
import project.model.Market;

import java.io.Serializable;

/**
 * Created by raffaelebongo on 14/06/17.
 */
public class MarketUpdate extends Updates implements Serializable{

    Market[] marketZone;

    public MarketUpdate(Market[] market ){
        super();
        marketZone = market;
    }

    @Override
    public String toString() {
        return Constants.BOARD_UPDATE;
    }

    @Override
    public void doUpdate(Board board) {
        board.setMarketZone(marketZone);
    }

    @Override
    public String toScreen() {

    }
}
