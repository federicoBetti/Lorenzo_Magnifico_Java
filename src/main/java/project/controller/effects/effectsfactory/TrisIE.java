package project.controller.effects.effectsfactory;

/**
 * This class is build parsing a json's file. It contains attributes that are useful for buildind immediate effects
 */
public class TrisIE {

	private String type;

	private String parameter;

	private int quantity;

	/**
	 * Constructor
	 *
	 * @param type for specifying the effect
	 * @param parameter for specifying the effect
	 * @param quantity for specifying the effect
	 */
    public TrisIE(String type, String parameter, int quantity){
    	this.type = type;
		this.parameter = parameter;
    	this.quantity = quantity;
	}

	/**
	 * Get type
	 *
	 * @return type
	 */
	public String getType() {
		return type;
	}

	/**
	 * Set type
	 *
	 * @return type
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * Get parameter
	 *
	 * @return parameter
	 */
	public String getParameter() {
		return parameter;
	}

	/**
	 * Set parameter
	 *
	 * @return parameter
	 */
	public void setParameter(String parameter) {
		this.parameter = parameter;
	}

	/**
	 * Get quantity
	 *
	 * @return quantity
	 */
	public int getQuantity() {
		return quantity;
	}

	/**
	 * Set quantity
	 *
	 * @return quantity
	 */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
}