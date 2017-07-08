package project.controller.effects.realeffects;

import project.controller.Constants;
import project.messages.BonusInteraction;
import project.messages.TowerAction;
import project.server.network.PlayerHandler;

/**
 * effect tat allows you to take a building card bonus
 */
public class BonusTowerActionYellow implements Effects {

    private String cardColour;
    private int diceValue;
    private String[] discountedResources;
    private int quantityDiscounted;

    public BonusTowerActionYellow(String parameter, int quantity ){
        cardColour = "yellow";
        diceValue = 6;
        discountedResources = parameter.split("-"); //wood-stone
        quantityDiscounted = quantity;
    }


    @Override
    public BonusInteraction doEffect(PlayerHandler player) {
        String discRes1 = discountedResources[0];
        String discRes2 = discountedResources[1];

        return new TowerAction(cardColour, diceValue, discRes1, quantityDiscounted, discRes2, quantityDiscounted);
    }

    @Override
    public String toScreen() {
        return null;
    }

    @Override
    public String toString(){
        return Constants.TOWER_ACTION_YELLOW;
    }
}