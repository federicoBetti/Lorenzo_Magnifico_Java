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
        zone[position].ludovicoAriosto();
        return true;

    }

}
