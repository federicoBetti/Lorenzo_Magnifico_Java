package project.server.network.rmi;

import project.messages.BothCostCanBeSatisfied;
import project.server.network.PlayerHandler;
import project.messages.BonusInteraction;
import project.messages.OkOrNo;

/**
 * questo esetende playerHandler, quindi deve fare un override dei metodi di ritorno. da qua passeranno i metodi di
 * ritorno. la game actions chiamerà i metodi di ritorno che sono sul player handler e verranno usati quelli in socket
 * o rmi in base alla conessione del giocatore
 * per quanto riguarda client -> server: da qui chiamo i metodi sulla classe PlayerHandler, che faranno i contrlli e
 * chiamerà il metodo effettivo sulla gameactions.
 */
public class RMIPlayerHandler extends PlayerHandler{


    @Override
    public void sendAnswer(BonusInteraction returnFromEffect) {
        // to implement
    }

    @Override
    public void cantDoAction(OkOrNo okOrNo) {
        // to implement
    }

    @Override
    public void canUseBothPaymentMethod(BothCostCanBeSatisfied bothCosts) {
        // to implement
    }

    @Override
    public void itsMyTurn() {
        // to implement
    }

    @Override
    public void sendAskForPraying() {
        // to implement
    }
}
