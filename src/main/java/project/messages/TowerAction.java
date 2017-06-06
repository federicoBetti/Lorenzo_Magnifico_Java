package project.messages;

import project.client.ui.cli.CliConstants;

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
	
	public int getNewCardDicevalue() {
		return newCardDicevalue;
	}
	public void setNewCardDicevalue(int newCardDicevalue) {
		this.newCardDicevalue = newCardDicevalue;
	}
	public String getKindOfCard() {
		return kindOfCard;
	}
	public void setKindOfCard(String kindOfCard) {
		this.kindOfCard = kindOfCard;
	}
	public String getDiscountedResource1() {
		return discountedResource1;
	}
	public void setDiscountedResource1(String discountedResource1) {
		this.discountedResource1 = discountedResource1;
	}
	public int getQuantityDiscounted1() {
		return quantityDiscounted1;
	}
	public void setQuantityDiscounted1(int quantityDiscounted1) {
		this.quantityDiscounted1 = quantityDiscounted1;
	}
	public String getDiscountedResource2() {
		return discountedResource2;
	}
	public void setDiscountedResource2(String discountedResource2) {
		this.discountedResource2 = discountedResource2;
	}
	public int getQuantityDiscounted2() {
		return quantityDiscounted2;
	}
	public void setQuantityDiscounted2(int quantityDiscounted2) {
		this.quantityDiscounted2 = quantityDiscounted2;
	}

	@Override
	public String toString() {
		return CliConstants.TOWER_ACTION;
	}
}
