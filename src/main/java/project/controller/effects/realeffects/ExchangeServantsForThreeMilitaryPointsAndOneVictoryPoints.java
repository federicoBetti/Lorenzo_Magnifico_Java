package project.controller.effects.realeffects;

import project.messages.BonusInteraction;
import project.messages.OkOrNo;
import project.server.network.PlayerHandler;

/**
 * Created by raffaelebongo on 10/05/17.
 */
public class ExchangeServantsForThreeMilitaryPointsAndOneVictoryPoints implements Effects {
    int servantsRequired;
    int victoryPointsEarned;
    int militaryPointsEarned;

    public ExchangeServantsForThreeMilitaryPointsAndOneVictoryPoints( ) {
        servantsRequired = 1;
        victoryPointsEarned = 1;
        militaryPointsEarned = 3;
    }

    @Override
    public BonusInteraction doEffect(PlayerHandler player) {
        player.getPersonalBoardReference().setServants(player.getPersonalBoardReference().getServants() - servantsRequired);
        player.getScore().setVictoryPoints(player.getScore().getVictoryPoints() - victoryPointsEarned);
        player.getScore().setMilitaryPoints(player.getScore().getMilitaryPoints() - militaryPointsEarned);

        return new OkOrNo();
    }
}
