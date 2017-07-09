package project.controller.cardsfactory;

import project.controller.effects.effectsfactory.TrisIE;

/**
 * This class represents the 
 */
public class TowerFromJson {

    private String colour;

    private int diceValueOfThisFloor;

    private TrisIE trisIE;

    private int towerNumber;


    public int getTowerNumber() {
        return towerNumber;
    }

    public String getColour() {
        return colour;
    }

    public int getDiceValueOfThisFloor() {
        return diceValueOfThisFloor;
    }

    public TrisIE getTrisIE() {
        return trisIE;
    }
}
