package project.controller.effects.effectsfactory;

import project.controller.effects.realeffects.Effects;
import project.messages.BonusInteraction;
import project.messages.TowerAction;
import project.server.network.PlayerHandler;

/**
 * Created by raffaelebongo on 06/06/17.
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

        TowerAction towerAction = new TowerAction(cardColour, diceValue, discRes1, quantityDiscounted );

        return  towerAction;
    }

    @Override
    public String toScreen() {
        return null;
    }
}

