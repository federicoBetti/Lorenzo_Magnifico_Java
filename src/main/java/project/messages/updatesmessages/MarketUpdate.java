package project.messages.updatesmessages;

import project.controller.Constants;
import project.model.Board;
import project.model.Market;

/**
 * Created by raffaelebongo on 14/06/17.
 */
public class MarketUpdate extends Updates {

    Market[] marketZone;

    public MarketUpdate(Market[] market ){
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
}
