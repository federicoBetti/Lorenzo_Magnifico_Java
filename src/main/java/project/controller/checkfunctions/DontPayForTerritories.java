package project.controller.checkfunctions;

import project.server.network.PlayerHandler;


public class DontPayForTerritories extends CheckFunctionsDecorator {

    public DontPayForTerritories(AllCheckFunctions allCheckFunctions){
        super(allCheckFunctions);
    }

    @Override
    public boolean checkMilitaryPointsForTerritory(PlayerHandler player, int length) {
        return true;
    }

}
