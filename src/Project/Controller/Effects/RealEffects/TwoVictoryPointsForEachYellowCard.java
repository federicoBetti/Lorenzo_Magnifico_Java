package Project.Controller.Effects.RealEffects;


import Project.toDelete.BonusInteraction;
import Project.toDelete.OkOrNo;
import Project.MODEL.Player;

public class TwoVictoryPointsForEachYellowCard implements Effects {
    public BonusInteraction doEffect(Player player){
        int i = 0;
        while (player.getPersonalBoardReference().getBuildings()[i] != null){
            i++;
        }
        player.getScore().setVictoryPoints(player.getScore().getVictoryPoints() + 2*i);
        OkOrNo Ok = new OkOrNo(false);
        Ok.setOk(true);
        return Ok;
    }
}
