package project.controller.effects.realeffects;

import project.messages.BonusInteraction;
import project.messages.TowerAction;
import project.server.network.PlayerHandler;

/**
 * This class represent the BonusTowerActionBlue effect
 */
public class BonusTowerActionBlue implements Effects {

    private String cardColour;
    private int diceValue;
    private String[] discountedResources;
    private int quantityDiscounted;

    /**
     * Constructor
     *
     * @param parameter discounted resources
     * @param quantity discounted
     */
    public BonusTowerActionBlue(String parameter, int quantity ){
        cardColour = "blu";
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

        return new TowerAction(cardColour, diceValue, discRes1, quantityDiscounted );
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
}

