package project.messages;

import project.controller.Constants;

public class TowerAction extends BonusInteraction {
	private int newCardDicevalue;
	private String kindOfCard;
	private String discountedResource1;
	private int quantityDiscounted1;
	private String discountedResource2;
	private int quantityDiscounted2;

	public TowerAction(){
		newCardDicevalue = 0;
		discountedResource1 = "empty";
		quantityDiscounted1 = 0;
		discountedResource2 = "empty";
		quantityDiscounted2 = 0;
	}

	public TowerAction(String parameter, int quantity){
		this.kindOfCard = parameter;
		this.newCardDicevalue = quantity;
	}

	public TowerAction(String parameter, int quantity, String discountedResource1, int quantityDiscounted1 ){
        this.kindOfCard = parameter;
        this.newCardDicevalue = quantity;
        this.discountedResource1 = discountedResource1;
        this.quantityDiscounted1 = quantityDiscounted1;
    }

    public TowerAction(String parameter, int quantity, String discountedResource1, int quantityDiscounted1, String discountedResource2, int quantityDiscounted2 ){
        this.kindOfCard = parameter;
        this.newCardDicevalue = quantity;
        this.discountedResource1 = discountedResource1;
        this.quantityDiscounted1 = quantityDiscounted1;
        this.discountedResource2 = discountedResource2;
        this.quantityDiscounted2 = quantityDiscounted2;
    }



	public int getNewCardDicevalue() {
		return newCardDicevalue;
	}
	public String getKindOfCard() {
		return kindOfCard;
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


	public void printBonusAction(){
    	System.out.println("Bonus tower action:\n" +
						   "Dice value: " +newCardDicevalue +"\n" +
						   "Kind of card" + kindOfCard +"\n" +
						   "Resource discounted:"+ discountedResource1 + "\n" +
						   "Quantity discounted"+quantityDiscounted1 + "\n");
    	if ( discountedResource2 != "empty")
    		System.out.println("Second resource discounted" + discountedResource2 +"\n" +
							   "Second resource discounted" + quantityDiscounted2);
	}
	@Override
	public String toString() {
		return Constants.TOWER_ACTION;
	}
}
