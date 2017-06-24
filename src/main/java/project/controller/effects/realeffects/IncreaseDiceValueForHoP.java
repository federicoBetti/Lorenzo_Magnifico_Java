package project.controller.effects.realeffects;

import project.controller.Constants;
import project.messages.BonusInteraction;
import project.messages.OkOrNo;
import project.model.Bonus;
import project.server.network.PlayerHandler;

/**
 * Created by raffaelebongo on 10/05/17.
 */
public class IncreaseDiceValueForHoP implements Effects {

    String kindOfAction;
    int increasingDiceValue;

    public IncreaseDiceValueForHoP(int quantity, String kindOfAction) {
        this.increasingDiceValue = quantity;
        this.kindOfAction = kindOfAction;
    }

    @Override
    public BonusInteraction doEffect(PlayerHandler player) {
        Bonus b =  player.getPersonalBoardReference().getBonusOnActions();
        if (kindOfAction.equals(Constants.HARVESTER))
            b.setHarvesterBonus(b.getHarvesterBonus() + increasingDiceValue);
        else
            b.setProductionBonus(b.getProductionBonus() + increasingDiceValue);
        return new OkOrNo();
    }
}
