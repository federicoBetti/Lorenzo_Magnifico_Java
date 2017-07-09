package project.controller.supportfunctions;


/**
 * This class is the finalPointsFromCharacterCard's decorator applyed when NoFinalPointsFromCharacter
 * excommunication is activated
 */
public class NoFinalPointsFromCharacter extends SupportFunctionsDecorator {

    /**
     * Constructor
     *
     * @param allSupportFunctions allSupportFunctions's reference
     */
    public NoFinalPointsFromCharacter(AllSupportFunctions allSupportFunctions){
        super(allSupportFunctions);
    }

    /**
     * This method doesn't allow the player to receive bonus final victory points from territory cards
     *
     * @param victoryPoints array of victory points for territory cards
     * @return 0
     */
    @Override
    public int finalPointsFromCharacterCard(int[] victoryPoints) {
        return 0;
    }


}
