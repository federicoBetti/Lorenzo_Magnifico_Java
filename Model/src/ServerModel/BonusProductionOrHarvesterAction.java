package ServerModel;

public class BonusProductionOrHarvesterAction extends BonusInteraction{
	private int diceValue;
	private String kinfOfAction;
	
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
