package project.controller.supportfunctions;

import project.server.network.PlayerHandler;

/**
 * Created by federico on 26/05/17.
 */
public class LoseFinalPointsEveryMilitary extends SupportFunctionsDecorator {


    public LoseFinalPointsEveryMilitary(AllSupportFunctions allSupportFunctions){
        super(allSupportFunctions);
    }


    @Override
    public int extraLostOfPoints(PlayerHandler playerHandler) {
        return playerHandler.getScore().getMilitaryPoints();
    }
}
