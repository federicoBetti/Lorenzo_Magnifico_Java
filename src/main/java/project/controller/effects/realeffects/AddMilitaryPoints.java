package project.controller.effects.realeffects;

import project.messages.BonusInteraction;
import project.messages.OkOrNo;
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
        quantity += player.getPersonalBoardReference().getBonusOnActions().getMilitaryPointsBonus();
        if (quantity<0)
            quantity = 0;
        player.getScore().setMilitaryPoints(player.getScore().getMilitaryPoints() + quantity);
        return new OkOrNo();

    }
}
