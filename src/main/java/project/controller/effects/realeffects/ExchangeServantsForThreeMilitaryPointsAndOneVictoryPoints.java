package project.controller.effects.realeffects;

import project.controller.cardsfactory.TotalCost;
import project.messages.BonusInteraction;
import project.messages.OkOrNo;
import project.server.network.PlayerHandler;

/**
 * This class represents ExchangeServantsForThreeMilitaryPointsAndOneVictoryPoints effect for other resources
 */
public class ExchangeServantsForThreeMilitaryPointsAndOneVictoryPoints implements ExchangeEffects {
    private int servantsRequired;
    private int victoryPointsEarned;
    private int militaryPointsEarned;

    /**
     * Constructor
     */
    public ExchangeServantsForThreeMilitaryPointsAndOneVictoryPoints( ) {
        servantsRequired = 1;
        victoryPointsEarned = 1;
        militaryPointsEarned = 3;
    }

    /**
     * Perform the effect
     *
     * @param player playerHandler's reference
     * @return okOrNo instance for saying that the effect has been applied correctly
     */
    @Override
    public BonusInteraction doEffect(PlayerHandler player) {
        if (player.getPersonalBoardReference().getServants() >= servantsRequired) {
            player.getPersonalBoardReference().setServants(player.getPersonalBoardReference().getServants() - servantsRequired);
            player.getScore().setVictoryPoints(player.getScore().getVictoryPoints() + victoryPointsEarned);
            player.getScore().setMilitaryPoints(player.getScore().getMilitaryPoints() + militaryPointsEarned);
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
        return "Exchange " + servantsRequired + " servants" + " for taking " + victoryPointsEarned + " of " + " victory points\n" +
                "and " +  militaryPointsEarned + " of military points";

    }

    /**
     * This method add the servants required in cost
     *
     * @param cost total cost variable
     */
    @Override
    public void addResourceRequested(TotalCost cost) {
        cost.addServants(servantsRequired);
    }
}
