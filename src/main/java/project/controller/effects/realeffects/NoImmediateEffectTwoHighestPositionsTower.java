package project.controller.effects.realeffects;

import project.controller.supportfunctions.NoBonusFromTower;
import project.messages.BonusInteraction;
import project.messages.OkOrNo;
import project.server.network.PlayerHandler;

/**
 * class used for effect of card preacher
 */


public class NoImmediateEffectTwoHighestPositionsTower implements Effects {

    @Override
    public BonusInteraction doEffect(PlayerHandler player) {
        player.getRoom().setMySupportFunction(new NoBonusFromTower(player.getRoom().getMySupportFunction(player)),player);
        return new OkOrNo();
    }

    @Override
    public String toScreen() {
        return "You will not take more the two highest places in each tower";
    }
}
