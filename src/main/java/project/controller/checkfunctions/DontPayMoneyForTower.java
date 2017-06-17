package project.controller.checkfunctions;

import project.model.*;

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
