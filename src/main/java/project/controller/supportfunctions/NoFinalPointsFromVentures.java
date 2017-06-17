package project.controller.supportfunctions;

/**
 * Created by federico on 26/05/17.
 */
public class NoFinalPointsFromVentures extends SupportFunctionsDecorator {


    public NoFinalPointsFromVentures(AllSupportFunctions allSupportFunctions){
        super(allSupportFunctions);
    }


    @Override
    public void finalPointsFromVenturesCard() {
        return;
        //this is true
    }


}
