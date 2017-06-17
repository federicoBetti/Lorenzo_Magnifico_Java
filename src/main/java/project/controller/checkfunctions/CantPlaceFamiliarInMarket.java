package project.controller.checkfunctions;

import project.model.*;

/**
 * TODO METTERE TUTTI I RITORNI DELLE ALTRE FUNZIONI COME ALLCHECKFUNCTION.altrafunzione
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
