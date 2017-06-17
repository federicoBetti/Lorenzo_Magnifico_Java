package project.controller.supportfunctions;

import java.util.List;

/**
 * Created by federico on 26/05/17.
 */
public class NoFinalPointsFromCharacter extends SupportFunctionsDecorator {


    public NoFinalPointsFromCharacter(AllSupportFunctions allSupportFunctions){
        super(allSupportFunctions);
    }


    @Override
    public int finalPointsFromCharacterCard(List<Integer> victoryPoints) {
        return 0; //this is true
    }


}
