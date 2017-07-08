package project.controller.effects.realeffects;

import project.messages.BonusInteraction;
import project.messages.OkOrNo;
import project.controller.cardsfactory.TotalCost;
import project.messages.TakePrivilegesAction;
import project.server.network.PlayerHandler;

/**
 * effect that exchange stone for other resources
 */
public class ExchangeStoneFor implements ExchangeEffects {

    private int stoneRequired;
    private int resourceEarned;
    private String resourceRewardered;

    public ExchangeStoneFor (int quantity, TotalCost effectCost, String resourceRewardered ){
        this.stoneRequired = effectCost.getStoneRequired();
        this.resourceEarned = quantity;
        this.resourceRewardered = resourceRewardered;
    }


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

    @Override
    public String toScreen() {
        return "Exchange " + stoneRequired + " stones" + " for taking " + resourceEarned + " of " + resourceRewardered;
    }

    @Override
    public void addResourceRequested(TotalCost cost) {
        cost.addStone(stoneRequired);
    }
}
