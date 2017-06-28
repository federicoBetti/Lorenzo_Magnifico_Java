package project.controller.effects.realeffects;

import project.messages.BonusInteraction;
import project.messages.OkOrNo;
import project.server.network.PlayerHandler;

/**
 * Created by Rita1 on 09/05/2017.
 */
public class AddCoin implements TakeRoPEffects {
    private int quantity;

    public AddCoin (int quantity){
        this.quantity = quantity;
    }


    @Override
    public BonusInteraction doEffect(PlayerHandler player) {
        quantity += player.getPersonalBoardReference().getBonusOnActions().getCoinsBonus();
        if (quantity<0)
            quantity = 0;
        player.getPersonalBoardReference().setCoins(player.getPersonalBoardReference().getCoins() + quantity);
        OkOrNo ok = new OkOrNo();
        return ok;
    }

    @Override
    public String toScreen() {
        return "Add " + quantity + " coins";
    }
}
