package project.controller.supportfunctions;

import project.controller.cardsfactory.BuildingCard;
import project.server.network.PlayerHandler;

/**
 * This class is the extraLostOfPoints's decorator applyed when LoseFinalPointsEveryWS excommunication is activated
 */
public class LoseFinalPointsEveryWS extends SupportFunctionsDecorator {

    /**
     * Constructor
     *
     * @param allSupportFunctions allSupportFunctions's reference
     */
    public LoseFinalPointsEveryWS(AllSupportFunctions allSupportFunctions){
        super(allSupportFunctions);
    }

    /**
     * This method makes the player loses victory points for every wood and stone that the player has
     *
     * @param playerHandler playerHandler's reference
     * @return the number of points to lose 
     */
    @Override
    public int extraLostOfPoints(PlayerHandler playerHandler) {
        int numberOfWoodOrStone = 0;
        for (BuildingCard buildingCard: playerHandler.getPersonalBoardReference().getBuildings()){
            numberOfWoodOrStone = numberOfWoodOrStone + buildingCard.getCost().getStoneRequired() + buildingCard.getCost().getWoodRequired();
        }
        return numberOfWoodOrStone;
    }
}
