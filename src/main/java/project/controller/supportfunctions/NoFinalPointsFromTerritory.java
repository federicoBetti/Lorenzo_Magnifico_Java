package project.controller.supportfunctions;

/**
 * Created by federico on 26/05/17.
 */
public class NoFinalPointsFromTerritory extends SupportFunctionsDecorator {


    public NoFinalPointsFromTerritory(AllSupportFunctions allSupportFunctions) {
        super(allSupportFunctions);
    }

    @Override
    public int finalPointsFromTerritoryCard(int[] victoryPoints) {
        //to do implements
        return 0; //this is true
    }
}