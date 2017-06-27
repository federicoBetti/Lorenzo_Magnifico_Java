package project.controller.effects.realeffects;

import project.controller.effects.realeffects.Effects;
import project.messages.BonusInteraction;
import project.messages.OkOrNo;
import project.server.network.PlayerHandler;

/**
 * Created by raffaelebongo on 17/06/17.
 */
public class AddWoodAndStone implements Effects {
    private int quantity;

    public AddWoodAndStone(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public BonusInteraction doEffect(PlayerHandler player) {
        player.getPersonalBoardReference().setStone(player.getPersonalBoardReference().getStone() + quantity);
        player.getPersonalBoardReference().setWood(player.getPersonalBoardReference().getWood() + quantity);
        return new OkOrNo();
    }
}
