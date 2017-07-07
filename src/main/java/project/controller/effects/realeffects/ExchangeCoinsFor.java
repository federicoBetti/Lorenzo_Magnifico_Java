package project.controller.effects.realeffects;

import project.controller.cardsfactory.TotalCost;
import project.messages.BonusInteraction;
import project.messages.OkOrNo;
import project.server.network.PlayerHandler;

/**
 * effect that exchange coins for other resources
 */

//todo fare con HAshMap
public class ExchangeCoinsFor implements ExchangeEffects {

    private int coinsRequired;
    private int resourceEarned;
    private String resourceReordered;

    public ExchangeCoinsFor(int quantity, TotalCost effectCost, String resourceRewardered) {
        this.coinsRequired = effectCost.getCoinsRequired();
        this.resourceEarned = quantity;
        this.resourceReordered = resourceRewardered;
    }

    @Override
    public BonusInteraction doEffect(PlayerHandler player) {

        if (player.getPersonalBoardReference().getCoins() >= coinsRequired) {
            player.getPersonalBoardReference().setCoins(player.getPersonalBoardReference().getCoins() - coinsRequired);

            switch (resourceReordered) {
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
                case "privilege":
                    UsePrivilege p = new UsePrivilege(resourceEarned); //resource earned is the quantity of priviledges
                    return p.doEffect(player); //todo controllare
                default:
                    return null;
            }

            return new OkOrNo();

        }
        return new OkOrNo();
    }

    @Override
    public String toScreen() {
        return "Exchange " + coinsRequired + " coins" + " for taking " + resourceEarned + " of " + resourceReordered;
    }


    @Override
    public void addResourceRequested(TotalCost cost) {
        cost.addCoin(coinsRequired);
    }
}

