package project.controller.effects.realeffects;


import project.controller.effects.realeffects.Effects;
import project.messages.BonusInteraction;
import project.messages.BonusProductionOrHarvesterAction;
import project.server.network.PlayerHandler;

public class ProductionHarvesterAction implements Effects {
    private BonusProductionOrHarvesterAction bph;
    public ProductionHarvesterAction(String parameter, int quantity){
        bph = new BonusProductionOrHarvesterAction(parameter,quantity);
    }

    @Override
    public BonusInteraction doEffect(PlayerHandler player) {
        return bph;
    }

}
