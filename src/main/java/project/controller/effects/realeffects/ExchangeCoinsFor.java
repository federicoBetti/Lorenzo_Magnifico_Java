package project.controller.effects.realeffects;

import project.controller.cardsfactory.TotalCost;
import project.messages.BonusInteraction;
import project.messages.OkOrNo;
import project.messages.TakePrivilegesAction;
import project.server.network.PlayerHandler;

/**
 * This class represents the ExchangeCoinsFor effects
 */

public class ExchangeCoinsFor implements ExchangeEffects {

    private int coinsRequired;
    private int resourceEarned;
    private String resourceReordered;

    /**
     * Constructor
     *
     * @param quantity earned
     * @param effectCost cost required
     * @param resourceRewardered resourceRewardered
     */
    public ExchangeCoinsFor(int quantity, TotalCost effectCost, String resourceRewardered) {
        this.coinsRequired = effectCost.getCoinsRequired();
        this.resourceEarned = quantity;
        this.resourceReordered = resourceRewardered;
    }

    /**
     * Perform the effect the right effect according to the parameter
     *
     * @param player playerHandler's reference
     * @return okOrNo instance for saying that the effect has been applied correctly
     */
    @Override
    public BonusInteraction doEffect(PlayerHandler player) {

        if (player.getPersonalBoardReference().getCoins() >= coinsRequired) {
            player.getPersonalBoardReference().addCoins(-coinsRequired);

            BonusInteraction returnFromEffect = addResources(resourceEarned, resourceReordered, player);
            if (returnFromEffect instanceof TakePrivilegesAction)
                return returnFromEffect;
            else
                return new OkOrNo();

        }
        return new OkOrNo();
    }

    /**
     * Build a string for describing the effect
     *
     * @return the description's String
     */
    @Override
    public String toScreen() {
        return "Exchange " + coinsRequired + " coins" + " for taking " + resourceEarned + " of " + resourceReordered;
    }

    /**
     * This method add the coins required in cost
     *
     * @param cost total cost variable
     */
    @Override
    public void addResourceRequested(TotalCost cost) {
        cost.addCoin(coinsRequired);
    }
}

