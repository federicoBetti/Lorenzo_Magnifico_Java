package project.messages;


import project.controller.effects.realeffects.Effects;
import project.server.network.PlayerHandler;

public class ProductionHarvesterAction implements Effects {
    BonusProductionOrHarvesterAction bph;
    public ProductionHarvesterAction(String parameter, int quantity){
        bph = new BonusProductionOrHarvesterAction(parameter,quantity);
    }

    @Override
    public BonusInteraction doEffect(PlayerHandler player) {
        return bph;
    }
}
