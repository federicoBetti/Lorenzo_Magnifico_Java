package project.messages;

import project.controller.Constants;

/**
 * This is the class of the object that the methods return when the effect is a bonus privilege
 */
public class TakePrivilegesAction extends BonusInteraction {

    private int quantityOfDifferentPrivileges;

    /**
     * Constructor
     *
     * @param quantityOfDifferentPrivileges quantity of privileges
     */
    public TakePrivilegesAction(int quantityOfDifferentPrivileges){
        this.quantityOfDifferentPrivileges = quantityOfDifferentPrivileges;
    }

    /**
     * String the descibe the class
     *
     * @return the constants
     */
    @Override
    public String toString() {
        return Constants.TAKE_PRIVILEGE_ACTION;
    }

    /**
     * Get quantityOfDifferentPrivileges
     *
     * @return quantityOfDifferentPrivileges
     */
    public int getQuantityOfDifferentPrivileges() {
        return quantityOfDifferentPrivileges;
    }


}
