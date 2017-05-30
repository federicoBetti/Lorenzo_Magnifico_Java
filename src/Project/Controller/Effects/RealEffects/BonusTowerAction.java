package Project.Controller.Effects.RealEffects;


import Project.Messages.BonusInteraction;
import Project.Messages.TowerAction;
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
