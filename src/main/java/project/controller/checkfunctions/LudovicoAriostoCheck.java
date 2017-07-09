package project.controller.checkfunctions;


import project.model.*;

/**
 * This class is the decoration of the checkPosition position method
 */
public class LudovicoAriostoCheck extends CheckFunctionsDecorator {

    public LudovicoAriostoCheck(AllCheckFunctions allCheckFunctions){
        super(allCheckFunctions);
    }

    /**
     * This method checks if the spot where you want to place the familiar is occuped or not and if there are other your familiars
     *
     * @param position number of position to check
     * @param zone group of positions where to check
     * @param familyMember familiar that you have placed
     * @return false only if there is a familiar of my family in the tower
     */
    @Override
    public boolean checkPosition(int position, Position[] zone, FamilyMember familyMember) {

        if (nobodyOfMyFamily(zone, familyMember.getFamilyColour())){
            if (zone[position].isOccupied()) {
                zone[position].ludovicoAriosto();
                return true;
            }
            else return true;
        }
        return false;
    }

    /**
     * This method check if there is a familiar of mine in a tower
     *
     * @param zone tower
     * @param familyColour familiar colour
     * @return true if there is no one of my family, else false
     */
    private boolean nobodyOfMyFamily(Position[] zone, String familyColour){
        for (int i=0;i<zone.length;i++){
            if ( zone[i].getFamiliarOnThisPosition() != null )
                if (zone[i].getFamiliarOnThisPosition().getFamilyColour().equals(familyColour))
                    return false;
        }
        return true;
    }

}
