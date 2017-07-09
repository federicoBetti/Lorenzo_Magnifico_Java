package project.controller.effects.realeffects;

import project.controller.cardsfactory.TotalCost;
import project.messages.BonusInteraction;
import project.server.network.PlayerHandler;

/**
 * This class represents the ExchangeEffects effects interface
 */
public interface ExchangeEffects extends Effects {

    /**
     * Abstract addResourceRequested
     *
     * @param cost total cost variable
     */
    void addResourceRequested(TotalCost cost);

    /**
     * Perform the effect the right effect according to the parameter
     *
     * @param player playerHandler's reference
     * @return okOrNo instance for saying that the effect has been applied correctly
     */
    default BonusInteraction addResources(int resourceEarned, String resourceReordered, PlayerHandler player) {

        switch (resourceReordered) {
            case "wood":
                player.getPersonalBoardReference().addWood(resourceEarned);
                break;
            case "stone":
                player.getPersonalBoardReference().addStone(resourceEarned);
                break;
            case "servant":
                player.getPersonalBoardReference().addServants(resourceEarned);
                break;
            case "victoryPoint":
                player.getScore().setVictoryPoints(player.getScore().getVictoryPoints() + resourceEarned);
                break;
            case "faithPoint":
                player.getScore().setFaithPoints(player.getScore().getFaithPoints() + resourceEarned);
                break;
            case "privilege":
                UsePrivilege p = new UsePrivilege(resourceEarned); //resource earned is the quantity of priviledges
                return p.doEffect(player);
            default:
                return null;
        }
        return null;
    }
}
