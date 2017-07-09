package project.controller.supportfunctions;

import project.server.network.PlayerHandler;

/**
 * This class is the extraLostOfPoints's decorator applyed when LoseFinalPointsEveryResources excommunication is activated
 */
public class LoseFinalPointsEveryResources extends SupportFunctionsDecorator {

    /**
     * Constructor
     *
     * @param allSupportFunctions allSupportFunctions's reference
     */
    public LoseFinalPointsEveryResources(AllSupportFunctions allSupportFunctions){
        super(allSupportFunctions);
    }

    /**
     * This method makes the player loses 1 points for each resource that he has
     *
     * @param playerHandler playerHandler's reference
     * @return number of resources
     */
    @Override
    public int extraLostOfPoints(PlayerHandler playerHandler) {
        int numberOfResources;
        numberOfResources = playerHandler.getPersonalBoardReference().getCoins();
        numberOfResources = numberOfResources + playerHandler.getPersonalBoardReference().getServants();
        numberOfResources = numberOfResources + playerHandler.getPersonalBoardReference().getStone();
        numberOfResources = numberOfResources + playerHandler.getPersonalBoardReference().getWood();
        return numberOfResources;
    }
}
