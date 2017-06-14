package project.controller.effects.realeffects;

import project.messages.BonusInteraction;
import project.messages.OkOrNo;
import project.server.network.PlayerHandler;

/**
 * Created by raffaelebongo on 10/05/17.
 */
public class IncreaseDiceValueForHoP implements Effects {

    String kindOfAction;
    int increasingDiceValue;

    public IncreaseDiceValueForHoP(int quantity, String kindOfAction) {
        this.increasingDiceValue = quantity;
        this.kindOfAction = kindOfAction;
    }

    @Override
    public BonusInteraction doEffect(PlayerHandler player) {
        return new OkOrNo();
    }
}
