package project.controller.effects.realeffects;

import project.messages.BonusInteraction;
import project.messages.OkOrNo;
import project.server.network.PlayerHandler;

/**
 * Created by raffaelebongo on 10/05/17.
 */
public class FaithPointForTwoCoinsAndTwoVictoryPoints implements Effects {
    int faithPointsrequired;
    int coinsEarned;
    int victoryPointsEarned;

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
}
