package Project.Messages;


import Project.Controller.Effects.RealEffects.Effects;
import Project.Server.Network.PlayerHandler;

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
