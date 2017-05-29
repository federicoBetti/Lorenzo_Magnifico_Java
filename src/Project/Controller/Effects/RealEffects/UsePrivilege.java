package Project.Controller.Effects.RealEffects;

import Project.Messages.BonusInteraction;
import Project.MODEL.Player;
import Project.Messages.TakePrivilegesAction;

/**
 * ho considato questa calasse cme quella che bisogna mettere come effetto delle carte. negli effetti delle carte
 * ci sono solo "prendi 3 privilegi diversi" quindi ci sta l'effetto cosi che chiamerà sul client una deteriminata cosa
 * sui privilegi
 */
public class UsePrivilege implements Effects {
    int quantityOfDifferentPrivilege;

    public UsePrivilege (int quantityOfDifferentPrivilege){
        this.quantityOfDifferentPrivilege = quantityOfDifferentPrivilege;
    }

    @Override
    public BonusInteraction doEffect(Player player) {
        BonusInteraction bonusInteraction = new TakePrivilegesAction(quantityOfDifferentPrivilege);
        return bonusInteraction;
    }
}
