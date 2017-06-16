package project.controller.effects.realeffects;

import project.messages.BonusInteraction;
import project.messages.OkOrNo;
import project.controller.cardsfactory.TotalCost;
import project.server.network.PlayerHandler;

/**
 * Created by raffaelebongo on 10/05/17.
 */
public class ExchangeStoneFor implements Effects {

    int stoneRequired;
    int resourceEarned;
    String resourceRewardered;

    public ExchangeStoneFor (int quantity, TotalCost effectCost, String resourceRewardered ){
        this.stoneRequired = effectCost.getStoneRequired();
        this.resourceEarned = quantity;
        this.resourceRewardered = resourceRewardered;
    }


    @Override
    public BonusInteraction doEffect(PlayerHandler player) {

        if (player.getPersonalBoardReference().getStone() >= stoneRequired) {
            player.getPersonalBoardReference().setStone(player.getPersonalBoardReference().getStone() - stoneRequired);

            switch (resourceRewardered) {
                case "woods":
                    player.getPersonalBoardReference().setWood(player.getPersonalBoardReference().getWood() + resourceEarned);
                    break;
                case "stones":
                    player.getPersonalBoardReference().setStone(player.getPersonalBoardReference().getStone() + resourceEarned);
                    break;
                case "servants":
                    player.getPersonalBoardReference().setServants(player.getPersonalBoardReference().getServants() + resourceEarned);
                    break;
                case "victoryPoints":
                    player.getScore().setVictoryPoints(player.getScore().getVictoryPoints() + resourceEarned);
                    break;
                case "faithPoints":
                    player.getScore().setFaithPoints(player.getScore().getFaithPoints() + resourceEarned);
                    break;
                case "coins":
                    player.getPersonalBoardReference().setCoins(player.getPersonalBoardReference().getCoins() + resourceEarned);
                    break;
                case "militaryPoints":
                    player.getScore().setMilitaryPoints(player.getScore().getMilitaryPoints() + resourceEarned);
                    break;
                default:
                    return null;
            }
        }

        return new OkOrNo();
    }
}
