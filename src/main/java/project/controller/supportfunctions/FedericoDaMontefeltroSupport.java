package project.controller.supportfunctions;

import project.model.Player;

/**
 * Created by federico on 05/07/17.
 */
public class FedericoDaMontefeltroSupport extends SupportFunctionsDecorator {

    public FedericoDaMontefeltroSupport(AllSupportFunctions allSupportFunctions) {
        super(allSupportFunctions);
    }

    @Override
    public void setDicesValue(int[] newDiceValue, Player p) {
        for (int i = 1; i<newDiceValue.length + 1; i++)
            p.getAllFamilyMembers()[i].setMyValue(newDiceValue[i-1]);
        int minIndex = findMin(newDiceValue);
        p.getAllFamilyMembers()[minIndex+1].setMyValue(6);
    }

    private int findMin(int[] newDiceValue) {
        int minIndex = -1;
        int minValue = 6 + 1;
        for (int i = 0; i < newDiceValue.length; i++){
            if (newDiceValue[i] < minValue)
                minIndex = i;
        }
        return minIndex;
    }
}
