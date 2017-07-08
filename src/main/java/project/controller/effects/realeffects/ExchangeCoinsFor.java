package project.controller.effects.realeffects;

import project.controller.cardsfactory.TotalCost;
import project.messages.BonusInteraction;
import project.messages.OkOrNo;
import project.messages.TakePrivilegesAction;
import project.server.network.PlayerHandler;

/**
 * effect that exchange coins for other resources
 */

public class ExchangeCoinsFor implements ExchangeEffects {

    private int coinsRequired;
    private int resourceEarned;
    private String resourceReordered;

    public ExchangeCoinsFor(int quantity, TotalCost effectCost, String resourceRewardered) {
        this.coinsRequired = effectCost.getCoinsRequired();
        this.resourceEarned = quantity;
        this.resourceReordered = resourceRewardered;
    }

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


    @Override
    public String toScreen() {
        return "Exchange " + coinsRequired + " coins" + " for taking " + resourceEarned + " of " + resourceReordered;
    }


    @Override
    public void addResourceRequested(TotalCost cost) {
        cost.addCoin(coinsRequired);
    }
}

