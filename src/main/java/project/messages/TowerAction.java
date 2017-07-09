package project.messages;

import project.controller.Constants;

/**
 * This is the class of the object that the methods return when the effect is a bonus tower action
 */
public class TowerAction extends BonusInteraction {
    private int newCardDicevalue;
    private String kindOfCard;
    private String discountedResource1;
    private int quantityDiscounted1;
    private String discountedResource2;
    private int quantityDiscounted2;

    public TowerAction() {
        newCardDicevalue = 0;
        discountedResource1 = "empty";
        quantityDiscounted1 = 0;
        discountedResource2 = "empty";
        quantityDiscounted2 = 0;
    }

    public TowerAction(String parameter, int quantity) {
        this.kindOfCard = parameter;
        this.newCardDicevalue = quantity;
        discountedResource1 = "empty";
        quantityDiscounted1 = 0;
        discountedResource2 = "empty";
        quantityDiscounted2 = 0;
    }

    public TowerAction(String parameter, int quantity, String discountedResource1, int quantityDiscounted1) {
        this.kindOfCard = parameter;
        this.newCardDicevalue = quantity;
        this.discountedResource1 = discountedResource1;
        this.quantityDiscounted1 = quantityDiscounted1;
        discountedResource2 = "empty";
        quantityDiscounted2 = 0;
    }

    public TowerAction(String parameter, int quantity, String discountedResource1, int quantityDiscounted1, String discountedResource2, int quantityDiscounted2) {
        this.kindOfCard = parameter;
        this.newCardDicevalue = quantity;
        this.discountedResource1 = discountedResource1;
        this.quantityDiscounted1 = quantityDiscounted1;
        this.discountedResource2 = discountedResource2;
        this.quantityDiscounted2 = quantityDiscounted2;
    }

    public String printBonusAction() {

        //todo controllare se i campi vuoti sono ad empty nel json

        String first = "   kindOfCard: " + kindOfCard + "\n" +
                "   Bonus dice value: " + newCardDicevalue + "\n";

        String second = "   Resource discounted: " + discountedResource1 + "\n" +
                "   Quantity discounted: " + quantityDiscounted1 + "\n";


        String third = "   Second resource discounted: " + discountedResource2 + "\n" +
                "   Quantity discounted: " + quantityDiscounted2;

        if (discountedResource1.equals("empty"))
            return first;

        if (discountedResource2.equals("empty"))
            return first + second;

        return first + second + third;

    }

    public String getKindOfCard() {
        return kindOfCard;
    }

    @Override
    public String toString() {
        return Constants.TOWER_ACTION;
    }

    public int getNewCardDicevalue() {
        return newCardDicevalue;
    }

    public String getDiscountedResource1() {
        return discountedResource1;
    }

    public int getQuantityDiscounted1() {
        return quantityDiscounted1;
    }

    public String getDiscountedResource2() {
        return discountedResource2;
    }

    public int getQuantityDiscounted2() {
        return quantityDiscounted2;
    }
}
