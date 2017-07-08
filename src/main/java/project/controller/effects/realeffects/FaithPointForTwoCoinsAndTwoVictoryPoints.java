package project.controller.effects.realeffects;

import project.controller.cardsfactory.TotalCost;
import project.messages.BonusInteraction;
import project.messages.OkOrNo;
import project.server.network.PlayerHandler;

/**
 * special permanent effect of a building card
 */
public class FaithPointForTwoCoinsAndTwoVictoryPoints implements ExchangeEffects {
    private int faithPointsRequired;
    private int coinsEarned;
    private int victoryPointsEarned;

    public FaithPointForTwoCoinsAndTwoVictoryPoints(){
        faithPointsRequired = 1;
        coinsEarned = 2;
        victoryPointsEarned = 2;
    }


    @Override
    public BonusInteraction doEffect(PlayerHandler player) {
        if ((player.getScore().getFaithPoints() >= faithPointsRequired)) {
            player.getScore().setFaithPoints(player.getScore().getFaithPoints() - faithPointsRequired);
            player.getPersonalBoardReference().addCoins(coinsEarned);
            player.getScore().setVictoryPoints(player.getScore().getVictoryPoints() + victoryPointsEarned);
        }

        return new OkOrNo();
    }

    @Override
    public String toScreen() {
        return "Exchange " + faithPointsRequired + " faith points" + " for taking " + coinsEarned + " of " + "coins\n" +
                "and " + victoryPointsEarned + " of victory points";
    }

    @Override
    public void addResourceRequested(TotalCost cost) {
        cost.addFaithPoints(faithPointsRequired);
    }
}
