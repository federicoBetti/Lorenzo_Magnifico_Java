package project.messages;

public class BonusProductionOrHarvesterAction extends BonusInteraction {
	private int diceValue;
	private String kinfOfAction;

	public BonusProductionOrHarvesterAction(String parameter, int quantity){
		this.kinfOfAction = parameter;
		this.diceValue = quantity;
	}

	public int getDiceValue() {
		return diceValue;
	}
	public void setDiceValue(int diceValue) {
		this.diceValue = diceValue;
	}
	public String getKinfOfAction() {
		return kinfOfAction;
	}
	public void setKinfOfAction(String kinfOfAction) {
		this.kinfOfAction = kinfOfAction;
	}
}
