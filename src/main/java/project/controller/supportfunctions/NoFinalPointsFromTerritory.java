package project.controller.supportfunctions;

import java.util.List;

/**
 * Created by federico on 26/05/17.
 */
public class NoFinalPointsFromTerritory extends SupportFunctionsDecorator {


    public NoFinalPointsFromTerritory(AllSupportFunctions allSupportFunctions) {
        super(allSupportFunctions);
    }

    @Override
    public int finalPointsFromTerritoryCard(List<Integer> victoryPoints) {
        //to do implements
        return 0; //this is true
    }
}