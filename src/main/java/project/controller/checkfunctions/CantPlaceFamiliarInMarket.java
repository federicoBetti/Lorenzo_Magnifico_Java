package project.controller.checkfunctions;

import project.model.*;

/**
 * thia class ia used for an excommunication effect
 */
public class CantPlaceFamiliarInMarket extends CheckFunctionsDecorator {

    public CantPlaceFamiliarInMarket(AllCheckFunctions allCheckFunctions){
        super(allCheckFunctions);
    }

    /**
     * This method check if is possible to place a familiar in a zone
     *
     * @param position index of the position's array
     * @param zone position's array
     * @param familyMember familiar
     * @return false if is a market zone or call the Basic check position method
     */
    @Override
    public boolean checkPosition(int position, Position[] zone, FamilyMember familyMember) {
        if (zone instanceof Market[]){
            return false;
        }
        else
            return allCheckFunctions.checkPosition(position,zone,familyMember);
    }

}
