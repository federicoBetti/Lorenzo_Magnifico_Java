package project.controller.effects.realeffects;

import project.messages.BonusInteraction;
import project.messages.OkOrNo;
import project.controller.cardsfactory.TotalCost;
import project.server.network.PlayerHandler;

/**
 * Created by raffaelebongo on 10/05/17.
 */
public class ExchangeStoneFor implements ExchangeEffects {

    private int stoneRequired;
    private int resourceEarned;
    private String resourceRewardered;

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
                case "wood":
                    player.getPersonalBoardReference().addWood(resourceEarned);
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
                case "militaryPoint":
                    player.getScore().setMilitaryPoints(player.getScore().getMilitaryPoints() + resourceEarned);
                    break;
                default:
                    return null;
            }
        }

        return new OkOrNo();
    }

    @Override
    public String toScreen() {
        return "Exchange " + stoneRequired + " stones" + " for taking " + resourceEarned + " of " + resourceRewardered;
    }

    @Override
    public void addResourceRequested(TotalCost cost) {
        cost.addStone(stoneRequired);
    }
}
