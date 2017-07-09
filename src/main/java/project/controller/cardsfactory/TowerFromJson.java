package project.controller.cardsfactory;

import project.controller.effects.effectsfactory.TrisIE;

/**
 * This class represents the tower's position loaded from Json
 */
public class TowerFromJson {

    private String colour;

    private int diceValueOfThisFloor;

    private TrisIE trisIE;

    private int towerNumber;


    /**
     * Get tower's number
     *
     * @return towerNumber
     */
    public int getTowerNumber() {
        return towerNumber;
    }

    /**
     * Get tower's colour
     *
     * @return colour
     */
    public String getColour() {
        return colour;
    }

    /**
     * Get dice value of a specific floor
     *
     * @return diceValueOfThisFloor
     */
    public int getDiceValueOfThisFloor() {
        return diceValueOfThisFloor;
    }

    /**
     * Get trieIE
     *
     * @return trisIE
     */
    public TrisIE getTrisIE() {
        return trisIE;
    }
}
