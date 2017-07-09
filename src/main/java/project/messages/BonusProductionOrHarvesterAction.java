package project.messages;

/**
 * Bonus production and harvester object
 */
public class BonusProductionOrHarvesterAction extends BonusInteraction {
	private int diceValue;
	private String kindOfAction;

	/**
	 * COnstructor
	 *
	 * @param parameter kind of action
	 * @param quantity dice value
	 */
	public BonusProductionOrHarvesterAction(String parameter, int quantity){
		this.kindOfAction = parameter;
		this.diceValue = quantity;
	}

	/**
	 * Get dice value
	 *
	 * @return diceValue
	 */
	public int getDiceValue() {
		return diceValue;
	}

	/**
	 * Get kindOfAction
	 *
	 * @return kindOfAction
	 */
	private String getKindOfAction() {
		return kindOfAction;
	}

	/**
	 * String the descibe the class
	 *
	 * @return the constants
	 */
	@Override
	public String toString(){
		return "bonus" + "-" + getKindOfAction();
	}

	/**
	 * Create a string that describe the bonus action
	 *
	 * @return the string
	 */
	public String actionString(){
		return ("Do one " + getKindOfAction() + "with the " +
                           "starting dice value of " + getDiceValue());
	}
}
