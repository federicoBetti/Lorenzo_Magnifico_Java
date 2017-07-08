package project.controller.effects.realeffects;

import project.controller.Constants;
import project.messages.BonusInteraction;
import project.messages.TakePrivilegesAction;
import project.server.network.PlayerHandler;

/**
 * this is the effect hat allows you to take decided number of different council privileges
 */
public class UsePrivilege implements Effects {
    private int quantityOfDifferentPrivilege;

    public UsePrivilege (int quantityOfDifferentPrivilege){
        this.quantityOfDifferentPrivilege = quantityOfDifferentPrivilege;
    }

    @Override
    public BonusInteraction doEffect(PlayerHandler player) {
        return new TakePrivilegesAction(quantityOfDifferentPrivilege);
    }

    @Override
    public String toScreen() {
        if ( quantityOfDifferentPrivilege == 1 )
            return "Take 1 priviledge";
        return "Take " + quantityOfDifferentPrivilege + " different priviledges";
    }

    @Override
    public String toString(){
        return Constants.TAKE_PRIVILEDGE;
    }
}
