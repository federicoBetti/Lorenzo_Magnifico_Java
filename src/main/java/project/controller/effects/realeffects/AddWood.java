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
        quantity += player.getPersonalBoardReference().getBonusOnActions().getWoodBonus();
        if (quantity<0)
            quantity = 0;
        player.getPersonalBoardReference().setWood(player.getPersonalBoardReference().getWood() + quantity);
        return new OkOrNo();
    }
}
