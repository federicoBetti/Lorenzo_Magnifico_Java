package project.controller.effects.realeffects;

import project.controller.cardsfactory.TotalCost;
import project.messages.BonusInteraction;
import project.messages.OkOrNo;
import project.server.network.PlayerHandler;

/**
 * special effect of a building card
 */
public class ExchangeServantsForThreeMilitaryPointsAndOneVictoryPoints implements ExchangeEffects {
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


    @Override
    public void addResourceRequested(TotalCost cost) {
        cost.addServants(servantsRequired);
    }
}
