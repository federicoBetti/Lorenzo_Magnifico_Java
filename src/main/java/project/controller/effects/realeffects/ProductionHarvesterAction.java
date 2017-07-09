package project.controller.effects.realeffects;

import project.messages.BonusInteraction;
import project.messages.BonusProductionOrHarvesterAction;
import project.server.network.PlayerHandler;

/**
 * This class represent the ProductionHarvesterAction effect
 */
public class ProductionHarvesterAction implements Effects {
    private BonusProductionOrHarvesterAction bph;

    /**
     * Constructor
     *
     * @param parameter kind of action
     * @param quantity dice value
     */
    public ProductionHarvesterAction(String parameter, int quantity){
        bph = new BonusProductionOrHarvesterAction(parameter,quantity);
    }

    /**
     * Perform the effect
     *
     * @param player playerHandler's reference
     * @return okOrNo instance for saying that the effect has been applied correctly
     */
    @Override
    public BonusInteraction doEffect(PlayerHandler player) {
        return bph;
    }

    /**
     * Build a string for describing the effect
     *
     * @return the description's String
     */
    @Override
    public String toScreen() {
        return bph.actionString();
    }

}
