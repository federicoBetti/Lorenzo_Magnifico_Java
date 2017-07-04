package project.controller.checkfunctions;

import project.model.*;

/**
 * this is a class used for the effect of the leader card ...
 */
public class CantPlaceFamiliarInMarket extends CheckFunctionsDecorator {

    public CantPlaceFamiliarInMarket(AllCheckFunctions allCheckFunctions){
        super(allCheckFunctions);
    }


    @Override
    public boolean checkPosition(int position, Position[] zone, FamilyMember familyMember) {
        if (zone instanceof Market[]){
            return false;
        }
        else
            return allCheckFunctions.checkPosition(position,zone,familyMember);
    }

}
