package project.controller.effects.realeffects;

import project.controller.Constants;
import project.messages.BonusInteraction;
import project.messages.TakePrivilegesAction;
import project.server.network.PlayerHandler;

/**
 * ho considato questa calasse cme quella che bisogna mettere come effetto delle carte. negli effetti delle carte
 * ci sono solo "prendi 3 privilegi diversi" quindi ci sta l'effetto cosi che chiamerà sul client una deteriminata cosa
 * sui privilegi
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
    public String toString(){
        return Constants.TAKE_PRIVILEDGE;
    }
}
