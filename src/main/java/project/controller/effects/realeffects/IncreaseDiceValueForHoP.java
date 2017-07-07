package project.controller.effects.realeffects;

import project.controller.Constants;
import project.messages.BonusInteraction;
import project.messages.OkOrNo;
import project.model.Bonus;
import project.server.network.PlayerHandler;

/**
 * effects that increase the bonus value of harvester or production
 */
public class IncreaseDiceValueForHoP implements Effects {

    private String kindOfAction;
    private int increasingDiceValue;

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

    @Override
    public String toScreen() {
        return "Increase the dice value for perfoming " + kindOfAction + " of " + increasingDiceValue;
    }
}
