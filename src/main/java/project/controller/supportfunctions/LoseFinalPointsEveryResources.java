package project.controller.supportfunctions;

import project.server.network.PlayerHandler;

/**
 * Created by federico on 26/05/17.
 */
public class LoseFinalPointsEveryResources extends SupportFunctionsDecorator {


    public LoseFinalPointsEveryResources(AllSupportFunctions allSupportFunctions){
        super(allSupportFunctions);
    }

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
