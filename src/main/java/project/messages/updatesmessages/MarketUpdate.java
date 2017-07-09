package project.messages.updatesmessages;

import project.controller.Constants;
import project.model.Board;
import project.model.Market;

import java.io.Serializable;

/**
 * Object sends to the client for notifying that the market's zone has been changed
 */
public class MarketUpdate extends Updates implements Serializable{

    private Market[] marketZone;

    /**
     * Constructor
     *
     * @param market market's zone reference
     * @param nickname palyer's nickname
     */
    public MarketUpdate(Board market, String nickname ){
        super(nickname);
        marketZone = market.getMarketZone();
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
        board.setMarketZone(marketZone);
    }

    /**
     * This method build a string that describes the update
     *
     * @return the description
     */
    @Override
    public String toScreen() {
        return getNicknameCurrentPlayer() + " has placed a familiar in the market!";
    }

}
