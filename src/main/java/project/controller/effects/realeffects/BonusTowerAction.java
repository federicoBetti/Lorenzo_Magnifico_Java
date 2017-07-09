package project.controller.effects.realeffects;


import project.messages.BonusInteraction;
import project.messages.TowerAction;
import project.server.network.PlayerHandler;

/**
 * This class represent the BonusTowerAction effect
 */
public class BonusTowerAction implements Effects {

    private TowerAction towerAction;

    /**
     * Constructor
     *
     * @param parameter tower's colour
     * @param quantity action's dice value
     */
    public BonusTowerAction(String parameter, int quantity) {
        towerAction = new TowerAction(parameter,quantity);
    }

    /**
     * Perform the effect
     *
     * @param player playerHandler's reference
     * @return okOrNo instance for saying that the effect has been applied correctly
     */
    @Override
    public BonusInteraction doEffect(PlayerHandler player) {
        return towerAction;
    }

    /**
     * Build a string for describing the effect
     *
     * @return the description's String
     */
    @Override
    public String toScreen() {
        return "Do a bonus tower action with the parameters that follows:\n" + towerAction.printBonusAction();
    }
}
