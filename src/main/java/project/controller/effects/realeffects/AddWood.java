package project.controller.effects.realeffects;

import project.messages.BonusInteraction;
import project.messages.OkOrNo;
import project.messages.updatesmessages.Updates;
import project.server.network.PlayerHandler;

public class AddWood implements TakeRoPEffects {
    private int quantity;

    public AddWood (int quantity){
        this.quantity = quantity;
    }

    @Override
    public BonusInteraction doEffect(PlayerHandler player) {
        player.getPersonalBoardReference().setWood(player.getPersonalBoardReference().getWood() + quantity);
        return new OkOrNo();
    }
}
