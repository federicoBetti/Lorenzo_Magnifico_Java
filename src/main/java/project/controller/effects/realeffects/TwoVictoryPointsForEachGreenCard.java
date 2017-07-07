package project.controller.effects.realeffects;


import project.messages.BonusInteraction;
import project.messages.OkOrNo;
import project.server.network.PlayerHandler;

/**
 * effect of development card
 */
public class TwoVictoryPointsForEachGreenCard implements Effects {

    @Override
    public BonusInteraction doEffect(PlayerHandler player) {
        int i = player.getPersonalBoardReference().getTerritories().size();
        player.getScore().setVictoryPoints(player.getScore().getVictoryPoints() + 2*i);
        return new OkOrNo();
    }

    @Override
    public String toScreen() {
        return "Take 2 victory points for each green card";
    }
}
