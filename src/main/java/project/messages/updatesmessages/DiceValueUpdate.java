package project.messages.updatesmessages;

import project.controller.Constants;
import project.model.Board;
import project.model.Turn;

/**
 * Created by raffaelebongo on 14/06/17.
 */
public class DiceValueUpdate extends Updates {

    private final Turn turn;
    private int[] diceValue;

    public DiceValueUpdate(int[] diceValue, Turn turn){
        super(Constants.TO_EVERYONE);
        this.diceValue = diceValue;
        this.turn = turn;
        turn.fillLists();
    }

    @Override
    public String toString() {
        return Constants.BOARD_UPDATE;
    }

    @Override
    public void doUpdate(Board board) {
        board.setDiceValue(this.diceValue);
        board.setTurn(turn);
        //todo nnela gui si devono mettere quelli giusti dei giocatori con scene builder
    }

    @Override
    public String toScreen() {
        return "The dices has been rolled!";
    }
}
