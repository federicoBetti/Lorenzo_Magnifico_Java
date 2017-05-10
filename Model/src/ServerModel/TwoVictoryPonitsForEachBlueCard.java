package ServerModel;


public class TwoVictoryPonitsForEachBlueCard implements Effects {
    public BonusInteraction doEffect(Player player){
        int i = 0;
        while (player.getPersonalBoardReference().getCharacters()[i] != null){
            i++;
        }
        player.getScore().setVictoryPoints(player.getScore().getVictoryPoints() + 2*i);
        OkOrNo Ok = new OkOrNo();
        Ok.setOk(true);
        return Ok;
    }
}
