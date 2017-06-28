package project.controller.effects.realeffects;

import project.messages.BonusInteraction;
import project.server.network.PlayerHandler;

/**
 * Created by raffaelebongo on 15/05/17.
 */


public class NoImmediateEffectTwoHighestPositionsTower implements Effects {

    //todo
    @Override
    public BonusInteraction doEffect(PlayerHandler player) {
        return null;
    }

    @Override
    public String toScreen() {
        return "You will not take more the two highest places in each tower";
    }
}
