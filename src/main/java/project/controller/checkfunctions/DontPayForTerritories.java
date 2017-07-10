package project.controller.checkfunctions;

import project.server.network.PlayerHandler;

/**
 * This class is a decorator of checkMilitaryPointsForTerritory method
 */
public class DontPayForTerritories extends CheckFunctionsDecorator {

    /**
     * Constructor
     *
     * @param allCheckFunctions allCheckFunction's reference
     */
    public DontPayForTerritories(AllCheckFunctions allCheckFunctions){
        super(allCheckFunctions);
    }

    /**
     * This method check if the player has enough military points for taking the territory's card
     *
     * @param player playerHandler's reference
     * @param length index of my territory cards
     * @return true
     */
    @Override
    public boolean checkMilitaryPointsForTerritory(PlayerHandler player, int length) {
        return true;
    }

}
