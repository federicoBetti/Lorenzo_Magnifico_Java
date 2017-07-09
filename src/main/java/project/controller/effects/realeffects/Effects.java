package project.controller.effects.realeffects;

import project.server.network.PlayerHandler;
import project.messages.BonusInteraction;

import java.io.Serializable;

/**
 * Effects interface
 */
public interface Effects extends Serializable {
    int quantity = 0 ;

    /**
     * This method is used for applying the effect
     *
     * @param player playerHandler's reference
     * @return okOrNo instance for saying that the effect has been applied correctly
     */
    BonusInteraction doEffect(PlayerHandler player);

    /**
     * Build a string for describing the effect
     *
     * @return the description's String
     */
    String toScreen();

}
