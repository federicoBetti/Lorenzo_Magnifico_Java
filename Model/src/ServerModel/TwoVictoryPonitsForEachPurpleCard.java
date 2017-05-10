package ServerModel;

public class TwoVictoryPonitsForEachPurpleCard implements Effects {

    public BonusInteraction doEffect(Player player){
        int i = 0;
        while (player.getPersonalBoardReference().getVentures()[i] != null){
            i++;
        }
        player.getScore().setVictoryPoints(player.getScore().getVictoryPoints() + 2*i);
        OkOrNo Ok = new OkOrNo();
        Ok.setOk(true);
        return Ok;
    }
}
