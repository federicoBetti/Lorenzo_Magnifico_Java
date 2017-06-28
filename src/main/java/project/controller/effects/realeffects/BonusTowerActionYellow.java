package project.controller.effects.realeffects;

import project.controller.Constants;
import project.messages.BonusInteraction;
import project.messages.TowerAction;
import project.server.network.PlayerHandler;

/**
 * Created by raffaelebongo on 06/06/17.
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

        TowerAction towerAction = new TowerAction(cardColour, diceValue, discRes1, quantityDiscounted, discRes2, quantityDiscounted);

        return  towerAction;
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