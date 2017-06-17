package project.controller.checkfunctions;

import project.controller.cardsfactory.BuildingCard;
import project.controller.cardsfactory.CharacterCard;
import project.controller.cardsfactory.TerritoryCard;
import project.controller.cardsfactory.VenturesCard;
import project.model.*;
import project.server.network.PlayerHandler;

import java.util.List;


public class DontPayForTerritories extends CheckFunctionsDecorator {

    public DontPayForTerritories(AllCheckFunctions allCheckFunctions){
        super(allCheckFunctions);
    }

    @Override
    public boolean checkMilitaryPointsForTerritory(PlayerHandler player, int length) {
        return true;
    }

}
