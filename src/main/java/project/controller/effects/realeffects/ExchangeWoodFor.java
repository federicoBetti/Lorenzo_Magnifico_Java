package project.controller.effects.realeffects;

import project.messages.BonusInteraction;
import project.messages.OkOrNo;
import project.controller.cardsfactory.TotalCost;
import project.messages.TakePrivilegesAction;
import project.server.network.PlayerHandler;

/**
 * effect that exchange wood for other resources
 */


public class ExchangeWoodFor implements ExchangeEffects {

    private int woodRequired;
    private int resourceEarned;
    private String resourceRewardered;

    public ExchangeWoodFor (int quantity, TotalCost effectCost, String resourceRewardered ){
        this.woodRequired = effectCost.getWoodRequired();
        this.resourceEarned = quantity;
        this.resourceRewardered = resourceRewardered;
    }

    @Override
    public BonusInteraction doEffect(PlayerHandler player) {

        if (player.getPersonalBoardReference().getWood() >= woodRequired) {
            player.getPersonalBoardReference().setWood(player.getPersonalBoardReference().getWood() - woodRequired);


            BonusInteraction returnFromEffect = addResources(resourceEarned, resourceRewardered, player);
            if (returnFromEffect instanceof TakePrivilegesAction)
                return returnFromEffect;
            else
                return new OkOrNo();

        }

            return new OkOrNo();
        }

    @Override
    public String toScreen() {
        return "Exchange " + woodRequired + " wood" + " for taking " + resourceEarned + " of " + resourceRewardered;
    }

    @Override
    public void addResourceRequested(TotalCost cost) {
        cost.addWood(woodRequired);
    }
}

