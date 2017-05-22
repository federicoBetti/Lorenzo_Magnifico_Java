package Project.Network;

import Project.MODEL.Player;

/**
 * questa classe rappresenta la casse ponte tra il model e la view. da qua ogni volta che vinee aggiornato qualcosa il player dice al suo
 * client che qualcosa è stato aggiornato e quindi fa aggiornare la UI
 */

public class SocketPlayerHandler extends PlayerHandler implements Runnable {



    public SocketPlayerHandler(){

    }

    @Override
    public void run() {

    }
}
