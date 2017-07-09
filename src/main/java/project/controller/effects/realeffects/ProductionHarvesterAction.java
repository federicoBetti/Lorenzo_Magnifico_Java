package project.controller.effects.realeffects;

import project.messages.BonusInteraction;
import project.messages.BonusProductionOrHarvesterAction;
import project.server.network.PlayerHandler;

/**
 * effect that let you do an harvester or production bonus
 */
public class ProductionHarvesterAction implements Effects {
    private BonusProductionOrHarvesterAction bph;

    public ProductionHarvesterAction(String parameter, int quantity){
        bph = new BonusProductionOrHarvesterAction(parameter,quantity);
    }

    @Override
    public BonusInteraction doEffect(PlayerHandler player) {
        return bph;
    }

    @Override
    public String toScreen() {
        return bph.actionString();
    }

}
