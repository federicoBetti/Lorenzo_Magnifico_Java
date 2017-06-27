package project.messages;

import project.controller.Constants;

/**
 * Created by federico on 24/05/17.
 */
public class TakePrivilegesAction extends BonusInteraction {

    private int quantityOfDifferentPrivileges;

    public TakePrivilegesAction(int quantityOfDifferentPrivileges){
        this.quantityOfDifferentPrivileges = quantityOfDifferentPrivileges;
    }

    @Override
    public String toString() {
        return Constants.TAKE_PRIVILEGE_ACTION;
    }

    public int getQuantityOfDifferentPrivileges() {
        return quantityOfDifferentPrivileges;
    }


}
