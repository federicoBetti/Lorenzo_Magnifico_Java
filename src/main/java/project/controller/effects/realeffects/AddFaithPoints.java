package project.controller.effects.realeffects;

import project.messages.BonusInteraction;
import project.messages.OkOrNo;
import project.server.network.PlayerHandler;


public class AddFaithPoints implements TakeRoPEffects {
    private int quantity;

    public AddFaithPoints (int quantity){
        this.quantity = quantity;
    }


    @Override
    public BonusInteraction doEffect(PlayerHandler player) {
        player.getScore().setFaithPoints(player.getScore().getFaithPoints() + quantity);
        OkOrNo ok = new OkOrNo(false);
        ok.setOk(true);
        return ok;
    }
}
