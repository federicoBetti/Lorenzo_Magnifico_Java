package ServerModel;

import java.util.*;

/**
 * 
 */
public class TrisIE {

    /**
     * Default constructor
     */
    public TrisIE() {
    }

    public TrisIE (String type, String parameter, int quantity){
    	this.type = type;
		this.parameter = parameter;
    	this.quantity = quantity;
	}

    /** 
     * 
     */
    private String type;

    public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getParameter() {
		return parameter;
	}

	public void setParameter(String parameter) {
		this.parameter = parameter;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	/** type of action: 1) takeRop ( take resource or point )	OK
     * 					2) exchangeRes ( exchange resources )	OK
     * 					3) getDiscount ( get a discount )
     * 					4) performAct ( perform action )
     * 					5) pointsForEachCardOrMP ( receive coins or points )  OK
     * 					6) increaseAct ( increase your action )
     */
    private String parameter;

    /**		1) wood
     * 		2) slave
     * 		3) coin
     * 		4) stone
     * 		5) privilege
     * 		6) faithPoint
     * 		7) victoryPoint
     * 		8) militaryPoint
     * 		9) diceValue
     * 
     */
    private int quantity;

}