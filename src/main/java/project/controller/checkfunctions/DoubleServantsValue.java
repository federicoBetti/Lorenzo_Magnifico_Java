package project.controller.checkfunctions;

import project.server.network.PlayerHandler;

/**
 * This class is the decoration of the getServants method
 */
public class DoubleServantsValue extends CheckFunctionsDecorator {


    public DoubleServantsValue(AllCheckFunctions allCheckFunctions){
        super(allCheckFunctions);
    }

    /**
     * Get servants
     *
     * @param player playerHandler's reference
     * @return half servants' number
     */
    @Override
    public int getServants(PlayerHandler player) {
        return player.getPersonalBoardReference().getServants() / 2;
    }
}
