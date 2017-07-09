package project.controller.effects.realeffects;

import project.controller.supportfunctions.NoBonusFromTower;
import project.messages.BonusInteraction;
import project.messages.OkOrNo;
import project.server.network.PlayerHandler;

/**
 * This class represent the NoImmediateEffectTwoHighestPositionsTower effect
 */
public class NoImmediateEffectTwoHighestPositionsTower implements Effects {

    /**
     * Perform the effect
     *
     * @param player playerHandler's reference
     * @return okOrNo instance for saying that the effect has been applied correctly
     */
    @Override
    public BonusInteraction doEffect(PlayerHandler player) {
        player.getRoom().setMySupportFunction(new NoBonusFromTower(player.getRoom().getMySupportFunction(player)),player);
        return new OkOrNo();
    }

    /**
     * Build a string for describing the effect
     *
     * @return the description's String
     */
    @Override
    public String toScreen() {
        return "You will not take more the two highest places in each tower";
    }
}
