package Project.Server.Network.RMI;

import Project.Server.Network.PlayerHandler;
import Project.toDelete.BonusInteraction;
import Project.toDelete.OkOrNo;

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

    }

    @Override
    protected void cantDoActionException(OkOrNo okOrNo) {

    }

    @Override
    public void sendAskForPraying() {

    }
}
