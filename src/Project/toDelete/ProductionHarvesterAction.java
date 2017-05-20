package Project.toDelete;


import Project.Controller.Effects.RealEffects.Effects;
import Project.MODEL.Player;
import Project.toDelete.BonusInteraction;
import Project.toDelete.BonusProductionOrHarvesterAction;

public class ProductionHarvesterAction implements Effects {
    BonusProductionOrHarvesterAction bph;
    public ProductionHarvesterAction(String parameter, int quantity){
        bph = new BonusProductionOrHarvesterAction(parameter,quantity);
    }

    public BonusInteraction doEffect(Player player){
        return bph;
    }
}
