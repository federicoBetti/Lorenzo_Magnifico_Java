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
    int servantsRequired;
    int resourceEarned;
    String resourceRewardered;

    public ExchangeServantsFor (int quantity, TotalCost effectCost, String resourceRewardered ){
        this.servantsRequired = effectCost.getStoneRequired();
        this.resourceEarned = quantity;
        this.resourceRewardered = resourceRewardered;
    }

    @Override
    public BonusInteraction doEffect(PlayerHandler player) {

        player.getPersonalBoardReference().setStone(player.getPersonalBoardReference().getStone() - servantsRequired);

        switch ( resourceRewardered ){
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
            case"faithPoints":
                player.getScore().setFaithPoints(player.getScore().getFaithPoints() + resourceEarned);
                break;
            case"coins":
                player.getPersonalBoardReference().setCoins(player.getPersonalBoardReference().getCoins() + resourceEarned);
                break;
            default:
                return null;
        }

        return new OkOrNo();
    }
}
