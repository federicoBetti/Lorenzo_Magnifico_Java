package project.controller.effects.realeffects;


import project.messages.BonusInteraction;
import project.messages.OkOrNo;
import project.server.network.PlayerHandler;

public class AddStone implements TakeRoPEffects {

    private int quantity;

    public AddStone (int quantity){
        this.quantity = quantity;
    }

    @Override
    public BonusInteraction doEffect(PlayerHandler player) {
        quantity += player.getPersonalBoardReference().getBonusOnActions().getStoneBonus();
        if (quantity<0)
            quantity = 0;
        player.getPersonalBoardReference().setStone(player.getPersonalBoardReference().getStone() + quantity);
        return new OkOrNo();
    }
}
