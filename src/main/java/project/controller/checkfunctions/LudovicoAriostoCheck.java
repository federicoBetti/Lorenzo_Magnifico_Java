package project.controller.checkfunctions;


import project.model.*;

public class LudovicoAriostoCheck extends CheckFunctionsDecorator {

    public LudovicoAriostoCheck(AllCheckFunctions allCheckFunctions){
        super(allCheckFunctions);
    }

    /**
     * dovrebbe essere di una carta leader, ludovico ariosto
     * todo check che questo è facile da implementare perche basta mettere che ritorna true qua ma il roblema è che poi nella zona ci dovrebbero stare due familiari
     * @param position
     * @param zone
     * @param familyMember
     * @return
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

    private boolean nobodyOfMyFamily(Position[] zone, String familyColour){
        for (int i=0;i<zone.length;i++){
            if ( zone[i].getFamiliarOnThisPosition() != null )
                if (zone[i].getFamiliarOnThisPosition().getFamilyColour().equals(familyColour))
                    return false;
        }
        return true;
    }

}
