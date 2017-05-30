package Project.Server.NetworkException;

import Project.Server.Network.PlayerHandler;
import Project.Messages.BothCostCanBeSatisfied;

/**
 * Exception thrown when both of payment method can be satisfied by a player
 */
public class canUseBothPaymentMethodException extends Exception {
    public canUseBothPaymentMethodException(PlayerHandler playerHandler, String s){
        super(s);
        playerHandler.canUseBothPaymentMethod(new BothCostCanBeSatisfied());
    }
}
