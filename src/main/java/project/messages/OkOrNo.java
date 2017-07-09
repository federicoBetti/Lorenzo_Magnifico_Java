package project.messages;


import project.controller.Constants;

/**
 * This is the class of the object that the methods return when an effect is applied in a correct way
 */
public class OkOrNo extends BonusInteraction {

    /**
     * String the descibe the class
     *
     * @return the constants
     */
    @Override
    public String toString() {
        return Constants.OK_OR_NO;
    }
}
