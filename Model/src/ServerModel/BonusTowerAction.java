package ServerModel;


public class BonusTowerAction implements Effects {
    TowerAction TA;
    public BonusTowerAction(String parameter, int quantity) {
        TA = new TowerAction(parameter,quantity);
    }

    public BonusInteraction doEffect (Player player){
        return TA;
    }

}
