package project.controller.effects.realeffects;

import project.controller.Constants;
import project.messages.BonusInteraction;
import project.messages.OkOrNo;
import project.server.network.PlayerHandler;

/**
 * This class represent the DiscountForTakingYellowCards effect
 */

//todo fare con HashMap
public class DiscountForTakingYellowCards implements Effects {

    private String kindOfReasourceDiscounted;
    private int quantityDiscounted;

    /**
     * Constructor
     *
     * @param quantity discounted
     * @param parameter1 discounted resource
     */
    public DiscountForTakingYellowCards(int quantity, String parameter1 ) {
        this.kindOfReasourceDiscounted = parameter1;
        this.quantityDiscounted = quantity;

    }

    /**
     * Perform the effect
     *
     * @param player playerHandler's reference
     * @return okOrNo instance for saying that the effect has been applied correctly
     */
    @Override
    public BonusInteraction doEffect(PlayerHandler player) {

        if(kindOfReasourceDiscounted.equals(Constants.STONE))
            player.getPersonalBoardReference().getBonusOnActions().getBuildingsBonus().setStoneBonus(player.getPersonalBoardReference().getBonusOnActions().getBuildingsBonus().getStoneBonus() + quantityDiscounted);

        else if (kindOfReasourceDiscounted.equals(Constants.WOOD))
            player.getPersonalBoardReference().getBonusOnActions().getBuildingsBonus().setWoodBonus(player.getPersonalBoardReference().getBonusOnActions().getBuildingsBonus().getWoodBonus() + quantityDiscounted);

        return new OkOrNo();
    }

    /**
     * Build a string for describing the effect
     *
     * @return the description's String
     */
    @Override
    public String toScreen() {
        return "Discount of " + quantityDiscounted +  " units of " +kindOfReasourceDiscounted + " for taking yellow cards";
    }
}
