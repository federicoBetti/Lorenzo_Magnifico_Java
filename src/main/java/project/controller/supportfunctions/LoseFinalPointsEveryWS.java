package project.controller.supportfunctions;

import project.controller.cardsfactory.BuildingCard;
import project.server.network.PlayerHandler;

/**
 * Created by federico on 26/05/17.
 */
public class LoseFinalPointsEveryWS extends SupportFunctionsDecorator {


    public LoseFinalPointsEveryWS(AllSupportFunctions allSupportFunctions){
        super(allSupportFunctions);
    }


    @Override
    public int extraLostOfPoints(PlayerHandler playerHandler) {
        int numberOfWoodOrStone = 0;
        for (BuildingCard buildingCard: playerHandler.getPersonalBoardReference().getBuildings()){
            numberOfWoodOrStone = numberOfWoodOrStone + buildingCard.getCost().getStoneRequired() + buildingCard.getCost().getWoodRequired();
        }
        return numberOfWoodOrStone;
    }
}
