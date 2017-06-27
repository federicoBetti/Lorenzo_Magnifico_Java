package project.messages.updatesmessages;

import project.controller.Constants;
import project.model.Board;

/**
 * Created by raffaelebongo on 14/06/17.
 */
public class DiceValueUpdate extends Updates {

    private int[] diceValue;

    public DiceValueUpdate( int[] diceValue ){
        super(Constants.TO_EVERYONE);
        this.diceValue = diceValue;
    }

    @Override
    public String toString() {
        return Constants.BOARD_UPDATE;
    }

    @Override
    public void doUpdate(Board board) {
        board.setDiceValue(this.diceValue);
        //todo nnela gui si devono mettere quelli giusti dei giocatori con scene builder
    }

    @Override
    public String toScreen() {
        return "The dices has been rolled!";
    }
}
