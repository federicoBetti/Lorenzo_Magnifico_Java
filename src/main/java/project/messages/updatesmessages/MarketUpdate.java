package project.messages.updatesmessages;

import project.controller.Constants;
import project.model.Board;
import project.model.Market;

import java.io.Serializable;

/**
 * Created by raffaelebongo on 14/06/17.
 */
public class MarketUpdate extends Updates implements Serializable{

    private Market[] marketZone;

    public MarketUpdate(Board market, String nickname ){
        super(nickname);
        marketZone = market.getMarketZone();
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
        return getNicknameCurrentPlayer() + " has placed a familiar in the market!";
    }

    public void stamp(){
        for (Market m: marketZone)
            System.out.println(m.getFamiliarOnThisPosition());
    }
}
