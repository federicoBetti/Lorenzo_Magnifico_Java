package project.controller.effects.realeffects;

import project.messages.BonusInteraction;
import project.messages.OkOrNo;
import project.server.network.PlayerHandler;

/**
 * This class represent the DiscountForTakingBlueCards effect
 */
public class DiscountForTakingBlueCards implements Effects {

    private String kindOfReasourceDiscounted;
    private int quantityDiscounted;

    /**
     * Constructor
     *
     * @param quantity discounted
     * @param parameter useless
     */
    public DiscountForTakingBlueCards(int quantity, String parameter) {
       
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

        player.getPersonalBoardReference().getBonusOnActions().getCharactersBonus().setCoinsBonus(player.getPersonalBoardReference().getBonusOnActions().getCharactersBonus().getCoinsBonus() + quantityDiscounted);

        return new OkOrNo();
    }

    /**
     * Build a string for describing the effect
     *
     * @return the description's String
     */
    @Override
    public String toScreen() {
        return "You have a discount of " + quantityDiscounted + " coin for taking blue cards";
    }
}
