package project.server.network.exception;

import project.server.network.PlayerHandler;
import project.messages.BothCostCanBeSatisfied;

/**
 * Exception thrown when both of payment method can be satisfied by a player
 */
public class CanUseBothPaymentMethodException extends Exception {

    public CanUseBothPaymentMethodException(PlayerHandler playerHandler, String s){
        super(s);
        playerHandler.canUseBothPaymentMethod(new BothCostCanBeSatisfied());
    }
}
