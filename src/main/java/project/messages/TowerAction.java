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

    /**
     * Constructor
     */
    public TowerAction() {
        newCardDicevalue = 0;
        discountedResource1 = "empty";
        quantityDiscounted1 = 0;
        discountedResource2 = "empty";
        quantityDiscounted2 = 0;
    }

    /**
     * Constructor
     *
     * @param parameter kind of card
     * @param quantity dice value
     */
    public TowerAction(String parameter, int quantity) {
        this.kindOfCard = parameter;
        this.newCardDicevalue = quantity;
        discountedResource1 = "empty";
        quantityDiscounted1 = 0;
        discountedResource2 = "empty";
        quantityDiscounted2 = 0;
    }

    /**
     * Constructor
     *
     * @param parameter kind of card
     * @param quantity dice value
     * @param discountedResource1 resource discounted
     * @param quantityDiscounted1 quantity discounted
     */
    public TowerAction(String parameter, int quantity, String discountedResource1, int quantityDiscounted1) {
        this.kindOfCard = parameter;
        this.newCardDicevalue = quantity;
        this.discountedResource1 = discountedResource1;
        this.quantityDiscounted1 = quantityDiscounted1;
        discountedResource2 = "empty";
        quantityDiscounted2 = 0;
    }

    /**
     * Constructor
     *
     * @param parameter kindo of card
     * @param quantity dice value
     * @param discountedResource1 resource disocunted
     * @param quantityDiscounted1 quantity discounted
     * @param discountedResource2 resource discounted 2
     * @param quantityDiscounted2 quantity disocunted 2
     */
    public TowerAction(String parameter, int quantity, String discountedResource1, int quantityDiscounted1, String discountedResource2, int quantityDiscounted2) {
        this.kindOfCard = parameter;
        this.newCardDicevalue = quantity;
        this.discountedResource1 = discountedResource1;
        this.quantityDiscounted1 = quantityDiscounted1;
        this.discountedResource2 = discountedResource2;
        this.quantityDiscounted2 = quantityDiscounted2;
    }

    /**
     * Create a string that describe the bonus tower action
     *
     * @return the string
     */
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

    /**
     * Get kindOfCard
     *
     * @return kindOfCard
     */
    public String getKindOfCard() {
        return kindOfCard;
    }

    /**
     * String the descibe the class
     *
     * @return the constants
     */
    @Override
    public String toString() {
        return Constants.TOWER_ACTION;
    }

    /**
     * Get newCardDicevalue
     *
     * @return newCardDicevalue
     */
    public int getNewCardDicevalue() {
        return newCardDicevalue;
    }

    /**
     * Get discountedResource1
     *
     * @return discountedResource1
     */
    public String getDiscountedResource1() {
        return discountedResource1;
    }

    /**
     * Get quantityDiscounted1
     *
     * @return quantityDiscounted1
     */
    public int getQuantityDiscounted1() {
        return quantityDiscounted1;
    }

    /**
     * Get discountedResource2
     *
     * @return discountedResource2
     */
    public String getDiscountedResource2() {
        return discountedResource2;
    }

    /**
     * Get quantityDiscounted2
     *
     * @return quantityDiscounted2
     */
    public int getQuantityDiscounted2() {
        return quantityDiscounted2;
    }
}
