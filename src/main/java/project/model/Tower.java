package project.model;

import project.controller.effects.effectsfactory.BuildImmediateEffects;
import project.controller.effects.effectsfactory.TrisIE;
import project.controller.effects.realeffects.Effects;
import project.messages.BonusInteraction;
import project.server.network.PlayerHandler;

import java.io.Serializable;

/**
 * This class represents a Tower position
 */
public class Tower extends Position implements Serializable {


    private String colour;

    private int diceValueOfThisFloor;

    private DevelopmentCard cardOnThisFloor;

    private int towerNumber;

    private boolean used;

    private Effects towerZoneEffect;

    /**
     * Constructor
     *
     * @param colour              tower colour
     * @param diceValueOfhisFloor dice value of the floor
     * @param towerNumber         tower's number
     * @param trisIE              object that represents the effect
     */
    public Tower(String colour, int diceValueOfhisFloor, int towerNumber, TrisIE trisIE) {
        super();
        this.colour = colour;
        this.diceValueOfThisFloor = diceValueOfhisFloor;

        if (diceValueOfThisFloor >= 5) {
            BuildImmediateEffects buildImmediateEffects = new BuildImmediateEffects();
            towerZoneEffect = buildImmediateEffects.searchImmediateEffects(trisIE.getType(), trisIE.getParameter(), trisIE.getQuantity());
        } else {
            towerZoneEffect = new Effects() {
                @Override
                public BonusInteraction doEffect(PlayerHandler player) {
                    return null;
                }

                @Override
                public String toScreen() {
                    return "";
                }
            };
        }

        setOccupied(false);
    }

    /**
     * Constructor for testing
     */
    public Tower() {
        //for testing
    }

    /**
     * Get cardOnThisFloor
     *
     * @return cardOnThisFloor
     */
    public DevelopmentCard getCardOnThisFloor() {
        return cardOnThisFloor;
    }

    /**
     * Set cardOnThisFloor
     *
     * @param cardOnThisFloor cardOnThisFloor
     */
    public void setCardOnThisFloor(DevelopmentCard cardOnThisFloor) {
        this.cardOnThisFloor = cardOnThisFloor;
    }

    /**
     * Get diceValueOfThisFloor
     *
     * @return diceValueOfThisFloor
     */
    public int getDiceValueOfThisFloor() {
        return diceValueOfThisFloor;
    }

    /**
     * Get towerZoneEffect
     *
     * @return towerZoneEffect
     */
    public Effects getTowerZoneEffect() {
        return towerZoneEffect;
    }

    /**
     * Get colour
     *
     * @return colour
     */
    public String getColour() {
        return colour;
    }

}