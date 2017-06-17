package project.controller.checkfunctions;

import project.controller.cardsfactory.BuildingCard;
import project.controller.cardsfactory.CharacterCard;
import project.controller.cardsfactory.TerritoryCard;
import project.controller.cardsfactory.VenturesCard;
import project.model.*;
import project.server.network.PlayerHandler;

import java.util.List;

/**
 * effect of a leader card that allow you not to pay when the tower is occupied
 */
public class DontPayMoneyForTower extends CheckFunctionsDecorator {

    public DontPayMoneyForTower(AllCheckFunctions allCheckFunctions){
        super(allCheckFunctions);
    }


    @Override
    public boolean checkTowerOccupied(Tower[] tower) {
        return false;
    }

}
