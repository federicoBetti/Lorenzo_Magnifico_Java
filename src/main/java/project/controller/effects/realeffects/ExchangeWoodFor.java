package project.controller.effects.realeffects;

import project.messages.BonusInteraction;
import project.messages.OkOrNo;
import project.controller.cardsfactory.TotalCost;
import project.server.network.PlayerHandler;

/**
 * Created by raffaelebongo on 10/05/17.
 */

//todo HashMap
public class ExchangeWoodFor implements Effects {

    int woodRequired;
    int resourceEarned;
    String resourceRewardered;

    public ExchangeWoodFor (int quantity, TotalCost effectCost, String resourceRewardered ){
        this.woodRequired = effectCost.getStoneRequired();
        this.resourceEarned = quantity;
        this.resourceRewardered = resourceRewardered;
    }

    @Override
    public BonusInteraction doEffect(PlayerHandler player) {

            player.getPersonalBoardReference().setWood(player.getPersonalBoardReference().getWood() - woodRequired);

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

            OkOrNo ok = new OkOrNo(false);
            ok.setOk(true);
            return ok;
        }
    }

