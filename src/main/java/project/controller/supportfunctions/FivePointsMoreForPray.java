package project.controller.supportfunctions;

/**
 * This class is the pray's decorator applyed when SistoIV is activated
 */
public class FivePointsMoreForPray extends SupportFunctionsDecorator {

    /**
     * Constructor
     *
     * @param allSupportFunctions allSupportFunctions's reference
     */
    public FivePointsMoreForPray(AllSupportFunctions allSupportFunctions){
        super(allSupportFunctions);
    }

    /**
     * Every time that pray the player earn 5 victory points more
     *
     * @param victoryPointsToAdd victory points to add
     */
    @Override
    public void pray(int victoryPointsToAdd) {
        victoryPointsToAdd += 5;
        allSupportFunctions.pray(victoryPointsToAdd);
    }
}
