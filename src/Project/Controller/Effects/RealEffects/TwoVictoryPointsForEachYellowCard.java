package Project.Controller.Effects.RealEffects;


import Project.Controller.MessageObjects.BonusInteraction;
import Project.Controller.MessageObjects.OkOrNo;
import Project.MODEL.Player;

public class TwoVictoryPointsForEachYellowCard implements Effects {
    public BonusInteraction doEffect(Player player){
        int i = 0;
        while (player.getPersonalBoardReference().getBuildings()[i] != null){
            i++;
        }
        player.getScore().setVictoryPoints(player.getScore().getVictoryPoints() + 2*i);
        OkOrNo Ok = new OkOrNo();
        Ok.setOk(true);
        return Ok;
    }
}
