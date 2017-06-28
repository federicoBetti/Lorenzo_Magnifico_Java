package project.controller.effects.realeffects;

import project.messages.BonusInteraction;
import project.messages.OkOrNo;
import project.server.network.PlayerHandler;

public class VictoryPointsForEachTwoMilitaryPoints implements Effects {

    @Override
    public BonusInteraction doEffect(PlayerHandler player) {
        int bonus = player.getScore().getMilitaryPoints() / 2;
        player.getScore().setVictoryPoints(player.getScore().getVictoryPoints() + bonus);
        return new OkOrNo();
    }

    @Override
    public String toScreen() {
        return "Take 1 military points for every 2 military points.";
    }
}
