package project.controller.effects.realeffects;


import project.messages.BonusInteraction;
import project.messages.OkOrNo;
import project.server.network.PlayerHandler;

/**
 * special effect of building card
 */
public class TwoVictoryPointsForEachYellowCard implements Effects {

    @Override
    public BonusInteraction doEffect(PlayerHandler player) {

        int yellowCards = player.getPersonalBoardReference().getBuildings().size();
        player.getScore().setVictoryPoints(player.getScore().getVictoryPoints() + 2*yellowCards);

        return new OkOrNo();
    }

    @Override
    public String toScreen() {
        return "Take 2 victory points for each yellow card";
    }
}
