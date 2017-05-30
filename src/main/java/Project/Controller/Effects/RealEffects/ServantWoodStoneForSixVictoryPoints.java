package Project.Controller.Effects.RealEffects;

import Project.MODEL.Player;
import Project.Messages.BonusInteraction;
import Project.Messages.OkOrNo;

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
    public BonusInteraction doEffect(Player player) {

        player.getPersonalBoardReference().setServants(player.getPersonalBoardReference().getServants() -  servantsRequired);
        player.getPersonalBoardReference().setWood(player.getPersonalBoardReference().getWood() - woodrequired);
        player.getPersonalBoardReference().setServants(player.getPersonalBoardReference().getServants() - stoneRequired);
        player.getScore().setVictoryPoints(player.getScore().getVictoryPoints() + victoryPointsEarned);

        OkOrNo Ok = new OkOrNo(false);
        Ok.setOk(true);
        return Ok;
    }
}
