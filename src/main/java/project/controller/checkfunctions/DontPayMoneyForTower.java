package project.controller.checkfunctions;

import project.model.*;

/**
 * This class is the decoration of the checkTowerOccupied check function
 */
public class DontPayMoneyForTower extends CheckFunctionsDecorator {

    /**
     * Constructor
     *
     * @param allCheckFunctions allCheckFunctions's reference
     */
    public DontPayMoneyForTower(AllCheckFunctions allCheckFunctions){
        super(allCheckFunctions);
    }


    /**
     * This method checks if the tower is occupied by at least another familiar
     *
     * @param tower array of tower
     * @return false
     */
    @Override
    public boolean checkTowerOccupied(Tower[] tower) {
        return false;
    }

}
