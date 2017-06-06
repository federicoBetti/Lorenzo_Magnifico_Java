package project.controller.effects.effectsfactory;

import project.controller.effects.realeffects.Effects;
import project.messages.BonusInteraction;
import project.messages.TowerAction;
import project.server.network.PlayerHandler;

/**
 * Created by raffaelebongo on 06/06/17.
 */
public class BonusTowerActionBlue implements Effects {

    String cardColour;
    int diceValue;
    String[] discountedResources;
    int quantityDiscounted;

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
}

