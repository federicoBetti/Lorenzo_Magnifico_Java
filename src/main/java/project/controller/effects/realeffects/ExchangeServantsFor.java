package project.controller.effects.realeffects;

import project.messages.BonusInteraction;
import project.messages.OkOrNo;
import project.controller.cardsfactory.TotalCost;
import project.server.network.PlayerHandler;

/**
 * Created by raffaelebongo on 10/05/17.
 */

//todo fare con HashMap
public class ExchangeServantsFor implements Effects {
    private int servantsRequired;
    private int resourceEarned;
    private String resourceRewardered;

    public ExchangeServantsFor (int quantity, TotalCost effectCost, String resourceRewardered ){
        this.servantsRequired = effectCost.getStoneRequired();
        this.resourceEarned = quantity;
        this.resourceRewardered = resourceRewardered;
    }

    @Override
    public BonusInteraction doEffect(PlayerHandler player) {

        if (player.getPersonalBoardReference().getStone() >= servantsRequired) {
            player.getPersonalBoardReference().setStone(player.getPersonalBoardReference().getStone() - servantsRequired);

            switch (resourceRewardered) {
                case "wood":
                    player.getPersonalBoardReference().setWood(player.getPersonalBoardReference().getWood() + resourceEarned);
                    break;
                case "stone":
                    player.getPersonalBoardReference().setStone(player.getPersonalBoardReference().getStone() + resourceEarned);
                    break;
                case "servant":
                    player.getPersonalBoardReference().setServants(player.getPersonalBoardReference().getServants() + resourceEarned);
                    break;
                case "victoryPoint":
                    player.getScore().setVictoryPoints(player.getScore().getVictoryPoints() + resourceEarned);
                    break;
                case "faithPoint":
                    player.getScore().setFaithPoints(player.getScore().getFaithPoints() + resourceEarned);
                    break;
                case "coin":
                    player.getPersonalBoardReference().setCoins(player.getPersonalBoardReference().getCoins() + resourceEarned);
                    break;
                default:
                    return null;
            }
        }

        return new OkOrNo();
    }

    @Override
    public String toScreen() {
        return "Exchange " + servantsRequired + " servants" + " for taking " + resourceEarned + " of " + resourceRewardered;
    }
}
