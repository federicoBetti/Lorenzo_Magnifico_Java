package project.controller.supportfunctions;

import project.model.FamilyMember;
import project.model.Position;

/**
 * Created by federico on 18/06/17.
 */
public class LudovicoAriostoSupport extends SupportFunctionsDecorator {

    public LudovicoAriostoSupport(AllSupportFunctions allSupportFunctions){
        super(allSupportFunctions);
    }

    @Override
    public void setFamiliar(Position zone, FamilyMember familyMember){
        zone.addFamiliar(familyMember);
    }
}
