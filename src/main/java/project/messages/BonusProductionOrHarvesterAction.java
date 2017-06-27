package project.messages;

public class BonusProductionOrHarvesterAction extends BonusInteraction {
	private int diceValue;
	private String kindOfAction;

	public BonusProductionOrHarvesterAction(String parameter, int quantity){
		this.kindOfAction = parameter;
		this.diceValue = quantity;
	}

	public int getDiceValue() {
		return diceValue;
	}
	private String getKindOfAction() {
		return kindOfAction;
	}

	@Override
	public String toString(){
		return "bonus" + "-" + getKindOfAction();
	}

	public String actionString(){
		return ("Do one " + getKindOfAction() + "with the " +
                           "starting dice value of " + getDiceValue());
	}
}
