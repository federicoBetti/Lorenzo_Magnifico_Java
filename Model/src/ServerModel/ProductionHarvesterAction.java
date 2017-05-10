package ServerModel;


public class ProductionHarvesterAction implements Effects {
    BonusProductionOrHarvesterAction bph;
    public ProductionHarvesterAction(String parameter, int quantity){
        bph = new BonusProductionOrHarvesterAction(parameter,quantity);
    }

    public BonusInteraction doEffect(Player player){
        return bph;
    }
}
