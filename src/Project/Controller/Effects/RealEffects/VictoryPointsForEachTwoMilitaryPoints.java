package Project.Controller.Effects.RealEffects;

import Project.Controller.MessageObjects.BonusInteraction;
import Project.Controller.MessageObjects.OkOrNo;
import Project.MODEL.Player;

public class VictoryPointsForEachTwoMilitaryPoints implements Effects {

    public BonusInteraction doEffect(Player player){
        int bonus = player.getScore().getMilitaryPoints() / 2;
        player.getScore().setVictoryPoints(player.getScore().getVictoryPoints() + bonus);
        OkOrNo Ok = new OkOrNo();
        Ok.setOk(true);
        return Ok;
    }
}
