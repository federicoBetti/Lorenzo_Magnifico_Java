package project.controller.supportfunctions;

/**
 *
 * This class is the finalPointsFromVenturesCard's decorator applyed when NoFinalPointsFromVentures
 * excommunication is activated
 */
public class NoFinalPointsFromVentures extends SupportFunctionsDecorator {

    /**
     * Constructor
     *
     * @param allSupportFunctions allSupportFunctions's reference
     */
    public NoFinalPointsFromVentures(AllSupportFunctions allSupportFunctions){
        super(allSupportFunctions);
    }

    /**
     * This method doesn't allow the player to receive bonus final victory points from ventures cards
     *
     * @return 0
     */
    @Override
    public void finalPointsFromVenturesCard() {
        return;
        //this is true
    }


}
