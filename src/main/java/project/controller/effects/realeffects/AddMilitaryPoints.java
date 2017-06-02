package project.controller.effects.realeffects;

import project.messages.BonusInteraction;
import project.messages.OkOrNo;
import project.model.*;
import project.server.network.PlayerHandler;

/**
 * Created by Rita1 on 09/05/2017.
 */
public class AddMilitaryPoints implements TakeRoPEffects {

    private int quantity;

    public AddMilitaryPoints (int quantity){
        this.quantity = quantity;
    }

    @Override
    public BonusInteraction doEffect(PlayerHandler player) {
        player.getScore().setMilitaryPoints(player.getScore().getMilitaryPoints() + quantity);
        OkOrNo ok = new OkOrNo(false);
        ok.setOk(true);
        return ok;
    }
}
