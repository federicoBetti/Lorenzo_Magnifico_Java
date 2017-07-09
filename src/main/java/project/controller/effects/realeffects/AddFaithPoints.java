package project.controller.effects.realeffects;

import project.messages.BonusInteraction;
import project.messages.OkOrNo;
import project.server.network.PlayerHandler;

/**
 * This class represent the AddFaithPoints effect
 */
public class AddFaithPoints implements Effects {
    private int quantity;

    public AddFaithPoints (int quantity){
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
        player.getScore().setFaithPoints(player.getScore().getFaithPoints() + quantity);
        return new OkOrNo();
    }

    /**
     * Build a string for describing the effect
     *
     * @return the description's String
     */
    @Override
    public String toScreen(){
        return "Add " + quantity + " faith points";
    }
}
