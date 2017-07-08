package project.controller.effects.realeffects;

import project.controller.cardsfactory.TotalCost;
import project.messages.BonusInteraction;
import project.server.network.PlayerHandler;

/**
 * interface implemented by all effects that exchange resources
 */
public interface ExchangeEffects extends Effects {

    void addResourceRequested(TotalCost cost);

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
