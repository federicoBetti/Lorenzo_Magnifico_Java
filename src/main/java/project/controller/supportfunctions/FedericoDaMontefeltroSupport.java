package project.controller.supportfunctions;

import project.model.Player;

/**
 * This class is the setDicesValue's decorator applyed when Federico da Montefeltro is activated
 */
public class FedericoDaMontefeltroSupport extends SupportFunctionsDecorator {

    /**
     * Constructor
     *
     * @param allSupportFunctions allSupportFunctions'reference
     */
    public FedericoDaMontefeltroSupport(AllSupportFunctions allSupportFunctions) {
        super(allSupportFunctions);
    }

    /**
     * The dice with the minimum value is set to 6
     *
     * @param newDiceValue array of dices'value
     * @param p playerHandler's reference
     */
    @Override
    public void setDicesValue(int[] newDiceValue, Player p) {
        for (int i = 1; i<newDiceValue.length + 1; i++)
            p.getAllFamilyMembers()[i].setMyValue(newDiceValue[i-1]);
        int minIndex = findMin(newDiceValue);
        p.getAllFamilyMembers()[minIndex+1].setMyValue(6);
    }

    /**
     * This method finds the minimum between the dices' values
     *
     * @param newDiceValue array of dices' values
     * @return the dice's index with the minimum value
     */
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
