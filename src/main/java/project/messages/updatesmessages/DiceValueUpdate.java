package project.messages.updatesmessages;

import project.model.Board;

/**
 * Created by raffaelebongo on 14/06/17.
 */
public class DiceValueUpdate extends Updates {

    int[] diceValue;

    public DiceValueUpdate( int[] diceValue){
        this.diceValue = diceValue;
    }

    @Override
    public String toString() {
        return null;
    }

    @Override
    public void doUpdate(Board board) {
        board.setDiceValue(this.diceValue);
    }
}
