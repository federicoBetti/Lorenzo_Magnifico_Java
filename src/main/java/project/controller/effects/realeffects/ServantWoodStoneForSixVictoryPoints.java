package project.controller.effects.realeffects;

import project.messages.BonusInteraction;
import project.messages.OkOrNo;
import project.server.network.PlayerHandler;

/**
 * Created by raffaelebongo on 10/05/17.
 */
public class ServantWoodStoneForSixVictoryPoints implements Effects {
    int servantsRequired;
    int woodrequired;
    int stoneRequired;
    int victoryPointsEarned;

    public ServantWoodStoneForSixVictoryPoints() {
        servantsRequired = 1;
        woodrequired = 1;
        stoneRequired = 1;
        victoryPointsEarned = 6;
    }

    @Override
    public BonusInteraction doEffect(PlayerHandler player) {

        player.getPersonalBoardReference().setServants(player.getPersonalBoardReference().getServants() -  servantsRequired);
        player.getPersonalBoardReference().setWood(player.getPersonalBoardReference().getWood() - woodrequired);
        player.getPersonalBoardReference().setServants(player.getPersonalBoardReference().getServants() - stoneRequired);
        player.getScore().setVictoryPoints(player.getScore().getVictoryPoints() + victoryPointsEarned);

        return new OkOrNo();
    }
}
