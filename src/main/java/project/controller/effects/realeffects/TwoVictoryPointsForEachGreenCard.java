package project.controller.effects.realeffects;


import project.messages.BonusInteraction;
import project.messages.OkOrNo;
import project.server.network.PlayerHandler;

public class TwoVictoryPointsForEachGreenCard implements Effects {

    @Override
    public BonusInteraction doEffect(PlayerHandler player) {
        int i = 0;
        while (player.getPersonalBoardReference().getTerritories().get(i) != null){
            i++;
        }
        player.getScore().setVictoryPoints(player.getScore().getVictoryPoints() + 2*i);
        OkOrNo ok = new OkOrNo(false);
        ok.setOk(true);
        return ok;
    }
}
