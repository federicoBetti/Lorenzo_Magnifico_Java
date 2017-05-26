package Project.Server.Network.Socket;

import Project.Server.Network.PlayerHandler;
import Project.toDelete.BonusInteraction;
import Project.toDelete.OkOrNo;

/**
 * questa classe rappresenta la casse ponte tra il model e la view. da qua ogni volta che vinee aggiornato qualcosa il player dice al suo
 * client che qualcosa è stato aggiornato e quindi fa aggiornare la UI
 */

public class SocketPlayerHandler extends PlayerHandler implements Runnable {



    public SocketPlayerHandler(){

    }

    @Override
    public void sendAnswer(BonusInteraction returnFromEffect) {
        switch (returnFromEffect.getClass()){
            case OKo:
        }
    }

    @Override
    protected void cantDoAction(OkOrNo okOrNo) {

    }

    @Override
    public void sendAskForPraying() {

    }

    @Override
    public void run() {


    }

    @Override
    /**
     * TODO metodi di risposta
     */
}
