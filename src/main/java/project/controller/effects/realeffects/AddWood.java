package project.controller.effects.realeffects;

import project.messages.BonusInteraction;
import project.messages.OkOrNo;
import project.server.network.PlayerHandler;

public class AddWood implements TakeRoPEffects {
    private int quantity;

    public AddWood (int quantity){
        this.quantity = quantity;
    }

    @Override
    public BonusInteraction doEffect(PlayerHandler player) {
        player.getPersonalBoardReference().setWood(player.getPersonalBoardReference().getWood() + quantity);
        OkOrNo ok = new OkOrNo(false);
        ok.setOk(true);
        return ok;
    }
}
