package project.controller.effects.realeffects;

import project.messages.BonusInteraction;
import project.messages.OkOrNo;
import project.controller.cardsfactory.TotalCost;
import project.messages.TakePrivilegesAction;
import project.server.network.PlayerHandler;

/**
 * This class represents the effect that exchange servants for other resources
 */
public class ExchangeServantsFor implements ExchangeEffects {
    private int servantsRequired;
    private int resourceEarned;
    private String resourceRewardered;

    /**
     * Constructor
     *
     * @param quantity earned
     * @param effectCost cost
     * @param resourceRewardered resourceRewardered
     */
    public ExchangeServantsFor (int quantity, TotalCost effectCost, String resourceRewardered ){
        this.servantsRequired = effectCost.getServantsRequired();
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

        if (player.getPersonalBoardReference().getServants() >= servantsRequired) {
            player.getPersonalBoardReference().setServants(player.getPersonalBoardReference().getServants() - servantsRequired);

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
        return "Exchange " + servantsRequired + " servants" + " for taking " + resourceEarned + " of " + resourceRewardered;
    }

    /**
     * This method add the servants required in cost
     *
     * @param cost total cost variable
     */
    @Override
    public void addResourceRequested(TotalCost cost) {
        cost.addServants(servantsRequired);
    }
}
