package project.controller.supportfunctions;

import project.server.network.PlayerHandler;

/**
 * This class is the extraLostOfPoints's decorator applyed when LoseFinalPointsEveryMilitary excommunication is activated
 */
public class LoseFinalPointsEveryMilitary extends SupportFunctionsDecorator {


    /**
     * Constructor
     *
     * @param allSupportFunctions allSupportFunctions's reference
     */
    public LoseFinalPointsEveryMilitary(AllSupportFunctions allSupportFunctions){
        super(allSupportFunctions);
    }

    /**
     * This methods makes lose one victory point to the player for each military point that he has
     *
     * @param playerHandler playerHandler's reference
     * @return number of military points
     */
    @Override
    public int extraLostOfPoints(PlayerHandler playerHandler) {
        return playerHandler.getScore().getMilitaryPoints();
    }
}
