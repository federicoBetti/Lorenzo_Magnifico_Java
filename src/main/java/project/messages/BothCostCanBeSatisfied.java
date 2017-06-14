package project.messages;


import project.controller.Constants;

public class BothCostCanBeSatisfied extends BonusInteraction {
    @Override
    public String toString() {
        return Constants.BOTH_PAYMENT_METHODS_AVAILABLE;
    }
}
