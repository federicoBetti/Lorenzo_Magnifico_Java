package project.controller.effects.realeffects;

import project.controller.Constants;
import project.messages.BonusInteraction;
import project.messages.TowerAction;
import project.server.network.PlayerHandler;

/**
 * This class represent the BonusTowerActionYellow effect
 */
public class BonusTowerActionYellow implements Effects {

    private String cardColour;
    private int diceValue;
    private String[] discountedResources;
    private int quantityDiscounted;

    /**
     * Constructor
     *
     * @param parameter resources discounted
     * @param quantity discounted
     */
    public BonusTowerActionYellow(String parameter, int quantity ){
        cardColour = "yellow";
        diceValue = 6;
        discountedResources = parameter.split("-"); //wood-stone
        quantityDiscounted = quantity;
    }

    /**
     * Perform the effect
     *
     * @param player playerHandler's reference
     * @return okOrNo instance for saying that the effect has been applied correctly
     */
    @Override
    public BonusInteraction doEffect(PlayerHandler player) {
        String discRes1 = discountedResources[0];
        String discRes2 = discountedResources[1];

        return new TowerAction(cardColour, diceValue, discRes1, quantityDiscounted, discRes2, quantityDiscounted);
    }

    /**
     * Build a string for describing the effect
     *
     * @return the description's String
     */
    @Override
    public String toScreen() {
        return null;
    }

    /**
     * Constanst for identify the effect
     *
     * @return the constant TOWER_ACTION_YELLOW
     */
    @Override
    public String toString(){
        return Constants.TOWER_ACTION_YELLOW;
    }
}