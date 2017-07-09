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
        int woodQuantity = quantity;
        woodQuantity += player.getPersonalBoardReference().getBonusOnActions().getWoodBonus();
        if (woodQuantity<0)
            woodQuantity = 0;
        player.getPersonalBoardReference().setWood(player.getPersonalBoardReference().getWood() + woodQuantity);


        int stoneQuantity = quantity;
        stoneQuantity += player.getPersonalBoardReference().getBonusOnActions().getStoneBonus();
        if (stoneQuantity<0)
            stoneQuantity = 0;

        player.getPersonalBoardReference().setStone(player.getPersonalBoardReference().getStone() + stoneQuantity);
        return new OkOrNo();
    }

    @Override
    public String toScreen(){
        return "Add " + quantity + " stones and " + quantity + " wood";
    }
}
