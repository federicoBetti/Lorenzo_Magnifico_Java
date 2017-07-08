package project.controller.effects.realeffects;

import project.controller.effects.realeffects.Effects;
import project.messages.BonusInteraction;
import project.messages.TowerAction;
import project.server.network.PlayerHandler;

/**
 * effect that allow you to take a character bonus card
 */
public class BonusTowerActionBlue implements Effects {

    private String cardColour;
    private int diceValue;
    private String[] discountedResources;
    private int quantityDiscounted;

    public BonusTowerActionBlue(String parameter, int quantity ){
        cardColour = "blu";
        diceValue = 6;
        discountedResources = parameter.split("-"); //wood-stone
        quantityDiscounted = quantity;
    }

    @Override
    public BonusInteraction doEffect(PlayerHandler player) {
        String discRes1 = discountedResources[0];

        return new TowerAction(cardColour, diceValue, discRes1, quantityDiscounted );
    }

    @Override
    public String toScreen() {
        return null;
    }
}

