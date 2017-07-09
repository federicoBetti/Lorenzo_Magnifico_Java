package project.controller.supportfunctions;

/**
 *
 * This class is the finalPointsFromTerritoryCard's decorator applyed when NoFinalPointsFromTerritory
 * excommunication is activated
 */
public class NoFinalPointsFromTerritory extends SupportFunctionsDecorator {

    /**
     * Constructor
     *
     * @param allSupportFunctions allSupportFunctions's reference
     */
    public NoFinalPointsFromTerritory(AllSupportFunctions allSupportFunctions) {
        super(allSupportFunctions);
    }

    /**
     * This method doesn't allow the player to receive bonus final victory points from territory cards
     *
     * @param victoryPoints array of victory points for territory cards
     * @return 0
     */
    @Override
    public int finalPointsFromTerritoryCard(int[] victoryPoints) {
        //to do implements
        return 0; //this is true
    }
}