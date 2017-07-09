package project.controller.supportfunctions;

import project.server.network.PlayerHandler;

/**
 * This class is the pray's decorator applyed when LoseFinalPointsEveryFive excommunication is activated
 */
public class LoseFinalPointsEveryFive extends SupportFunctionsDecorator {

    /**
     * Constructor
     *
     * @param allSupportFunctions allSupportFunctions's reference
     */
    public LoseFinalPointsEveryFive(AllSupportFunctions allSupportFunctions){
        super(allSupportFunctions);
    }

    /**
     * Lose a victory points every 5
     *
     * @param playerHandler playerHandler's reference
     * @return number of points gained
     */
    @Override
    public int extraLostOfPoints(PlayerHandler playerHandler) {
        return playerHandler.getScore().getVictoryPoints()/5;
    }
}
