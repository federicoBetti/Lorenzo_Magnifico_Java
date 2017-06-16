package project.model;

import project.controller.effects.realeffects.Effects;

/**
 * 
 */
public class Tower extends Position {


    private String colour;

    private int diceValueOfThisFloor;

    private DevelopmentCard cardOnThisFloor;

    private boolean used;

    private Effects towerZoneEffect;


    public Tower() {
        setOccupied(false);
    }


    public DevelopmentCard getCardOnThisFloor() {
        return cardOnThisFloor;
    }

    public void setCardOnThisFloor(DevelopmentCard cardOnThisFloor) {
        this.cardOnThisFloor = cardOnThisFloor;
    }

    public int getDiceValueOfThisFloor() {
        return diceValueOfThisFloor;
    }

    public Effects getTowerZoneEffect() {
        return towerZoneEffect;
    }
}