package project.controller.effects.realeffects;

import project.controller.cardsfactory.TotalCost;
import project.messages.BonusInteraction;
import project.messages.OkOrNo;
import project.server.network.PlayerHandler;

/**
 * Created by raffaelebongo on 10/05/17.
 */

//todo fare con HAshMap
public class ExchangeCoinsFor implements Effects {

    int coinsRequired;
    int resourceEarned;
    String resourceRewardered;

    public ExchangeCoinsFor (int quantity, TotalCost effectCost, String resourceRewardered ){
         this.coinsRequired = effectCost.getCoinsRequired();
         this.resourceEarned = quantity;
         this.resourceRewardered = resourceRewardered;
    }

    @Override
    public BonusInteraction doEffect(PlayerHandler player) {

        if (player.getPersonalBoardReference().getCoins() >= coinsRequired) {
            player.getPersonalBoardReference().setCoins(player.getPersonalBoardReference().getCoins() - coinsRequired);

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
            case"privilege":
                UsePrivilege p = new UsePrivilege(resourceEarned); //resource earned is the quantity of priviledges
                return p.doEffect(player); //todo controllare
            default:
                return null;
        }

        return new OkOrNo();

    }
}
