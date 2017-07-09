package project.controller.effects.realeffects;

import project.messages.BonusInteraction;
import project.messages.OkOrNo;
import project.server.network.PlayerHandler;

/**
 * This class represents add coins effect
 */
public class AddCoin implements TakeRoPEffects {
    private int quantity;

    /**
     * Constructor
     *
     * @param quantity coins to add
     */
    public AddCoin (int quantity){
        this.quantity = quantity;
    }

    /**
     * Perform the effect
     *
     * @param player palyerHandle' reference
     * @return okOrNo instance for saying that the effect has been applied correctly
     */
    @Override
    public BonusInteraction doEffect(PlayerHandler player) {
        quantity += player.getPersonalBoardReference().getBonusOnActions().getCoinsBonus();
        if (quantity<0)
            quantity = 0;
        player.getPersonalBoardReference().setCoins(player.getPersonalBoardReference().getCoins() + quantity);
        OkOrNo ok = new OkOrNo();
        return ok;
    }

    /**
     * Build a string for describing the effect
     *
     * @return the description's String
     */
    @Override
    public String toScreen() {
        return "Add " + quantity + " coins";
    }
}
