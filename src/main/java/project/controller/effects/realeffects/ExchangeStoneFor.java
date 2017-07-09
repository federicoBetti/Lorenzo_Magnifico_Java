package project.controller.effects.realeffects;

import project.messages.BonusInteraction;
import project.messages.OkOrNo;
import project.controller.cardsfactory.TotalCost;
import project.messages.TakePrivilegesAction;
import project.server.network.PlayerHandler;

/**
 * This class represents the effect that exchange stones for other resources
 */
public class ExchangeStoneFor implements ExchangeEffects {

    private int stoneRequired;
    private int resourceEarned;
    private String resourceRewardered;

    /**
     * Constructor
     *
     * @param quantity earned
     * @param effectCost cost
     * @param resourceRewardered resourceRewardered
     */
    public ExchangeStoneFor (int quantity, TotalCost effectCost, String resourceRewardered ){
        this.stoneRequired = effectCost.getStoneRequired();
        this.resourceEarned = quantity;
        this.resourceRewardered = resourceRewardered;
    }

    /**
     * Perform the effect
     *
     * @param player playerHandler's reference
     * @return okOrNo instance for saying that the effect has been applied correctly
     */
    @Override
    public BonusInteraction doEffect(PlayerHandler player) {

        if (player.getPersonalBoardReference().getStone() >= stoneRequired) {
            player.getPersonalBoardReference().setStone(player.getPersonalBoardReference().getStone() - stoneRequired);


            BonusInteraction returnFromEffect = addResources(resourceEarned, resourceRewardered, player);
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
        return "Exchange " + stoneRequired + " stones" + " for taking " + resourceEarned + " of " + resourceRewardered;
    }

    /**
     * This method add the servants required in cost
     *
     * @param cost total cost variable
     */
    @Override
    public void addResourceRequested(TotalCost cost) {
        cost.addStone(stoneRequired);
    }
}
