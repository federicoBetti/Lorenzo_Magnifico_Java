package project.controller.effects.realeffects;

import project.messages.BonusInteraction;
import project.messages.OkOrNo;
import project.controller.cardsfactory.TotalCost;
import project.server.network.PlayerHandler;

/**
 * Created by raffaelebongo on 10/05/17.
 */

//todo HashMap, si potrebbe mettere le cose sotto come un addWood, addoStone e cose cosi
public class ExchangeWoodFor implements Effects {

    private int woodRequired;
    private int resourceEarned;
    private String resourceRewardered;

    public ExchangeWoodFor (int quantity, TotalCost effectCost, String resourceRewardered ){
        this.woodRequired = effectCost.getStoneRequired();
        this.resourceEarned = quantity;
        this.resourceRewardered = resourceRewardered;
    }

    @Override
    public BonusInteraction doEffect(PlayerHandler player) {

        if (player.getPersonalBoardReference().getWood() >= woodRequired) {
            player.getPersonalBoardReference().setWood(player.getPersonalBoardReference().getWood() - woodRequired);

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
        return "Exchange " + woodRequired + " wood" + " for taking " + resourceEarned + " of " + resourceRewardered;
    }
}

