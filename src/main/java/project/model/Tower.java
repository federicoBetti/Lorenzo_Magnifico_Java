package project.model;

import project.controller.effects.realeffects.Effects;
import project.server.network.PlayerHandler;

/**
 * 
 */
public class Tower extends Position {


    private String colour;

    private int diceValueOfThisFloor;

    private DevelopmentCard cardOnThisFloor;

    private boolean used;

    private Effects bonusThisPosition;


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

    public void makeEffect(PlayerHandler player){
        bonusThisPosition.doEffect(player);
    }
}