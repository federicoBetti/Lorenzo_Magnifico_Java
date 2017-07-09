package project.controller.supportfunctions;

import project.model.FamilyMember;
import project.model.Position;

/**
 * This class is the setFamiliar's decorator applyed when Ludovico Ariosto is activated
 */
public class LudovicoAriostoSupport extends SupportFunctionsDecorator {

    /**
     * Constructor
     *
     * @param allSupportFunctions allSupportFunctions's reference
     */
    public LudovicoAriostoSupport(AllSupportFunctions allSupportFunctions){
        super(allSupportFunctions);
    }

    /**
     * This method allow the player to place his familiar in every position, also if it is already occupied
     *
     * @param zone in which place a familiar
     * @param familyMember familiar to place
     */
    @Override
    public void setFamiliar(Position zone, FamilyMember familyMember){
        zone.addFamiliar(familyMember);
    }
}
