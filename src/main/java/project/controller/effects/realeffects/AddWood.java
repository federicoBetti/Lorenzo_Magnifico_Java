package project.controller.effects.realeffects;

import project.messages.BonusInteraction;
import project.messages.OkOrNo;
import project.server.network.PlayerHandler;

/**
 * This class represent the AddWood effect
 */
public class AddWood implements TakeRoPEffects {
    private int quantity;

    /**
     * Constructor
     *
     * @param quantity of wood to add
     */
    public AddWood (int quantity){
        this.quantity = quantity;
    }

    /**
     * Perform the effect
     *
     * @param player playerHandler's reference
     * @return okOrNo instance for saying that the effect has been applied correctly
     */
    @Override
    public BonusInteraction doEffect(PlayerHandler player) {
        quantity += player.getPersonalBoardReference().getBonusOnActions().getWoodBonus();
        if (quantity<0)
            quantity = 0;
        player.getPersonalBoardReference().setWood(player.getPersonalBoardReference().getWood() + quantity);
        return new OkOrNo();
    }

    /**
     * Build a string for describing the effect
     *
     * @return the description's String
     */
    @Override
    public String toScreen() {
        return "Add " + quantity + " wood";
    }


}
