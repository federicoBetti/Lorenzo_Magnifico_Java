package project.controller.effects.realeffects;

import project.controller.cardsfactory.TotalCost;
import project.messages.BonusInteraction;
import project.messages.OkOrNo;
import project.server.network.PlayerHandler;

/**
 * Created by raffaelebongo on 10/05/17.
 */
public class ServantWoodStoneForSixVictoryPoints implements ExchangeEffects {
    private int servantsRequired;
    private int woodrequired;
    private int stoneRequired;
    private int victoryPointsEarned;

    public ServantWoodStoneForSixVictoryPoints() {
        servantsRequired = 1;
        woodrequired = 1;
        stoneRequired = 1;
        victoryPointsEarned = 6;
    }

    @Override
    public BonusInteraction doEffect(PlayerHandler player) {
        if (player.getPersonalBoardReference().getServants() >=  servantsRequired &&
                player.getPersonalBoardReference().getWood() >= woodrequired &&
                player.getPersonalBoardReference().getServants() >= stoneRequired) {

            player.getPersonalBoardReference().setServants(player.getPersonalBoardReference().getServants() - servantsRequired);
            player.getPersonalBoardReference().setWood(player.getPersonalBoardReference().getWood() - woodrequired);
            player.getPersonalBoardReference().setServants(player.getPersonalBoardReference().getServants() - stoneRequired);
            player.getScore().setVictoryPoints(player.getScore().getVictoryPoints() + victoryPointsEarned);
        }

        return new OkOrNo();
    }

    @Override
    public String toScreen() {
        return "Exchange 1 servant, 1 wood and 1 stone for 6 victory points.";
    }

    @Override
    public void addResourceRequested(TotalCost cost) {
        cost.addServants(servantsRequired);
        cost.addWood(woodrequired);
        cost.addStone(stoneRequired);
    }
}
