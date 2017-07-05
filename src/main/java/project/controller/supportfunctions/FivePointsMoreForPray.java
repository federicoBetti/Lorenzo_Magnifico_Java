package project.controller.supportfunctions;


public class FivePointsMoreForPray extends SupportFunctionsDecorator {

    public FivePointsMoreForPray(AllSupportFunctions allSupportFunctions){
        super(allSupportFunctions);
    }

    @Override
    public void pray(int victoryPointsToAdd) {
        victoryPointsToAdd += 5;
        allSupportFunctions.pray(victoryPointsToAdd);
    }
}
