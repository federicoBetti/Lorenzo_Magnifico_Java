package project.controller.effects.realeffects;

import project.controller.cardsfactory.TotalCost;
import project.messages.BonusInteraction;
import project.messages.OkOrNo;
import project.server.network.PlayerHandler;

/**
 * Created by raffaelebongo on 10/05/17.
 */
public class FaithPointForTwoCoinsAndTwoVictoryPoints implements ExchangeEffects {
    private int faithPointsrequired;
    private int coinsEarned;
    private int victoryPointsEarned;

    public FaithPointForTwoCoinsAndTwoVictoryPoints(){
        faithPointsrequired = 1;
        coinsEarned = 2;
        victoryPointsEarned = 2;
    }


    @Override
    public BonusInteraction doEffect(PlayerHandler player) {
        if ((player.getScore().getFaithPoints() >= faithPointsrequired )) {
            player.getScore().setFaithPoints(player.getScore().getFaithPoints() - faithPointsrequired);
            player.getPersonalBoardReference().setCoins(player.getPersonalBoardReference().getCoins() + coinsEarned);
            player.getScore().setVictoryPoints(player.getScore().getVictoryPoints() + victoryPointsEarned);
        }

        return new OkOrNo();
    }

    @Override
    public String toScreen() {
        return "Exchange " + faithPointsrequired + " faith points" + " for taking " + coinsEarned + " of " + "coins\n" +
                "and " + victoryPointsEarned + " of victory points";
    }

    public int getFaithPointsrequired() {
        return faithPointsrequired;
    }

    @Override
    public void addResourceRequested(TotalCost cost) {
        cost.addFaithPoints(faithPointsrequired);
    }
}
