package project.controller.effects.realeffects;

import project.messages.BonusInteraction;
import project.messages.OkOrNo;
import project.server.network.PlayerHandler;

/**
 * Created by raffaelebongo on 10/05/17.
 */
public class ExchangeServantsForThreeMilitaryPointsAndOneVictoryPoints implements Effects {
    private int servantsRequired;
    private int victoryPointsEarned;
    private int militaryPointsEarned;

    public ExchangeServantsForThreeMilitaryPointsAndOneVictoryPoints( ) {
        servantsRequired = 1;
        victoryPointsEarned = 1;
        militaryPointsEarned = 3;
    }

    @Override
    public BonusInteraction doEffect(PlayerHandler player) {
        if (player.getPersonalBoardReference().getServants() >= servantsRequired) {
            player.getPersonalBoardReference().setServants(player.getPersonalBoardReference().getServants() - servantsRequired);
            player.getScore().setVictoryPoints(player.getScore().getVictoryPoints() + victoryPointsEarned);
            player.getScore().setMilitaryPoints(player.getScore().getMilitaryPoints() + militaryPointsEarned);
        }

        return new OkOrNo();
    }

    @Override
    public String toScreen() {
        return "Exchange " + servantsRequired + " servants" + " for taking " + victoryPointsEarned + " of " + " victory points\n" +
                "and " +  militaryPointsEarned + " of military points";

    }
}
