package project.model;

import project.controller.effects.effectsfactory.BuildImmediateEffects;
import project.controller.effects.effectsfactory.TrisIE;
import project.controller.effects.realeffects.Effects;
import project.messages.BonusInteraction;
import project.server.network.PlayerHandler;

import java.io.Serializable;

/**
 * portare tutte le torri della board da file
 */
public class Tower extends Position implements Serializable{


    private String colour;

    private int diceValueOfThisFloor;

    private DevelopmentCard cardOnThisFloor;

    private int towerNumber;

    private boolean used;

    private Effects towerZoneEffect;  //todo riempire con effetto


    public Tower(String colour, int diceValueOfhisFloor, int towerNumber, TrisIE trisIE ) {
        super();
        this.colour = colour;
        this.diceValueOfThisFloor = diceValueOfhisFloor;

        if(diceValueOfThisFloor >= 5 ) {
            BuildImmediateEffects buildImmediateEffects = new BuildImmediateEffects();
            towerZoneEffect = buildImmediateEffects.searchImmediateEffects(trisIE.getType(), trisIE.getParameter(), trisIE.getQuantity());
        }
        else {
            towerZoneEffect = (Effects) player -> null;
        }

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

    public String getColour() {
        return colour;
    }

}