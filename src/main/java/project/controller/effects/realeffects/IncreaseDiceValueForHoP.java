package project.controller.effects.realeffects;

import project.controller.Constants;
import project.messages.BonusInteraction;
import project.messages.OkOrNo;
import project.model.Bonus;
import project.server.network.PlayerHandler;

/**
 * This class represents IncreaseDiceValueForHoP effect
 */
public class IncreaseDiceValueForHoP implements Effects {

    private String kindOfAction;
    private int increasingDiceValue;

    /**
     * Constructor
     *
     * @param quantity increased
     * @param kindOfAction action increased
     */
    public IncreaseDiceValueForHoP(int quantity, String kindOfAction) {
        this.increasingDiceValue = quantity;
        this.kindOfAction = kindOfAction;
    }

    /**
     * Perform the effect
     *
     * @param player playerHandler's reference
     * @return okOrNo instance for saying that the effect has been applied correctly
     */
    @Override
    public BonusInteraction doEffect(PlayerHandler player) {
        Bonus b =  player.getPersonalBoardReference().getBonusOnActions();
        if (kindOfAction.equals(Constants.HARVESTER))
            b.setHarvesterBonus(b.getHarvesterBonus() + increasingDiceValue);
        else
            b.setProductionBonus(b.getProductionBonus() + increasingDiceValue);
        return new OkOrNo();
    }

    /**
     * Build a string for describing the effect
     *
     * @return the description's String
     */
    @Override
    public String toScreen() {
        return "Increase the dice value for perfoming " + kindOfAction + " of " + increasingDiceValue;
    }
}
