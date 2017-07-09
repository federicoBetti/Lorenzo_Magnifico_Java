package project.controller.effects.realeffects;

import project.controller.Constants;
import project.messages.BonusInteraction;
import project.messages.TakePrivilegesAction;
import project.server.network.PlayerHandler;

/**
 * This class represents the UsePrivilege effect
 */
public class UsePrivilege implements Effects {
    private int quantityOfDifferentPrivilege;

    /**
     * Constructor
     *
     * @param quantityOfDifferentPrivilege quantity of privileges
     */
    public UsePrivilege (int quantityOfDifferentPrivilege){
        this.quantityOfDifferentPrivilege = quantityOfDifferentPrivilege;
    }

    /**
     * Perform the right effect
     *
     * @param player playerHandler's reference
     * @return okOrNo instance for saying that the effect has been applied correctly
     */
    @Override
    public BonusInteraction doEffect(PlayerHandler player) {
        return new TakePrivilegesAction(quantityOfDifferentPrivilege);
    }

    /**
     * Build a string for describing the effect
     *
     * @return the description's String
     */
    @Override
    public String toScreen() {
        if ( quantityOfDifferentPrivilege == 1 )
            return "Take 1 priviledge";
        return "Take " + quantityOfDifferentPrivilege + " different priviledges";
    }

    /**
     * @return a constant that identify the class
     */
    @Override
    public String toString(){
        return Constants.TAKE_PRIVILEDGE;
    }
}
