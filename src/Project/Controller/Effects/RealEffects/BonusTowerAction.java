package Project.Controller.Effects.RealEffects;


import Project.toDelete.BonusInteraction;
import Project.toDelete.TowerAction;
import Project.MODEL.Player;

public class BonusTowerAction implements Effects {
    TowerAction towerAction;
    public BonusTowerAction(String parameter, int quantity) {
        towerAction = new TowerAction(parameter,quantity);
    }

    public BonusInteraction doEffect (Player player){
        return towerAction;
    }

}
