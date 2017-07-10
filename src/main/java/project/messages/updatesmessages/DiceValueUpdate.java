package project.messages.updatesmessages;

import project.controller.Constants;
import project.model.Board;
import project.model.Turn;

/**
 * Object sends to the client for notifying that the dices' value have been modified
 */
public class DiceValueUpdate extends Updates {

    private final Turn turn;
    private int[] diceValue;

    /**
     * Constructore
     *
     * @param diceValue dice values' zone
     * @param turn turn's reference
     */
    public DiceValueUpdate(int[] diceValue, Turn turn){
        super(Constants.TO_EVERYONE);
        this.diceValue = diceValue;
        this.turn = turn;
        turn.fillLists();
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
        board.setDiceValue(this.diceValue);
        board.setTurn(turn);
    }

    /**
     * This method build a string that describes the update
     *
     * @return the description
     */
    @Override
    public String toScreen() {
        return "The dices has been rolled!";
    }
}
