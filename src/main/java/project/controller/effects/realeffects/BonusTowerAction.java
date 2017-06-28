package project.controller.effects.realeffects;


import project.messages.BonusInteraction;
import project.messages.TowerAction;
import project.server.network.PlayerHandler;

public class BonusTowerAction implements Effects {

    private TowerAction towerAction;

    public BonusTowerAction(String parameter, int quantity) {
        towerAction = new TowerAction(parameter,quantity);
    }

    @Override
    public BonusInteraction doEffect(PlayerHandler player) {
        return towerAction;
    }

    @Override
    public String toScreen() {
        return "Do a bonus tower action with the parameters that follows:\n" + towerAction.printBonusAction();
    }


}
