package project.controller.effects.realeffects;

import project.controller.cardsfactory.TotalCost;
import project.messages.BonusInteraction;
import project.messages.OkOrNo;
import project.server.network.PlayerHandler;

/**
 * This class represents FaithPointForTwoCoinsAndTwoVictoryPoints effect
 */
public class FaithPointForTwoCoinsAndTwoVictoryPoints implements ExchangeEffects {
    private int faithPointsRequired;
    private int coinsEarned;
    private int victoryPointsEarned;

    /**
     * Constructor
     */
    public FaithPointForTwoCoinsAndTwoVictoryPoints(){
        faithPointsRequired = 1;
        coinsEarned = 2;
        victoryPointsEarned = 2;
    }

    /**
     * Perform the effect
     *
     * @param player playerHandler's reference
     * @return okOrNo instance for saying that the effect has been applied correctly
     */
    @Override
    public BonusInteraction doEffect(PlayerHandler player) {
        if ((player.getScore().getFaithPoints() >= faithPointsRequired)) {
            player.getScore().setFaithPoints(player.getScore().getFaithPoints() - faithPointsRequired);
            player.getPersonalBoardReference().addCoins(coinsEarned);
            player.getScore().setVictoryPoints(player.getScore().getVictoryPoints() + victoryPointsEarned);
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
        return "Exchange " + faithPointsRequired + " faith points" + " for taking " + coinsEarned + " of " + "coins\n" +
                "and " + victoryPointsEarned + " of victory points";
    }

    /**
     * This method add the faith points required in cost
     *
     * @param cost total cost variable
     */
    @Override
    public void addResourceRequested(TotalCost cost) {
        cost.addFaithPoints(faithPointsRequired);
    }
}
