package ServerModel;

import java.util.*;

/**
 * 
 */
public abstract class Card {

    /**
     * 
     */
    private String name;

    /**
     * 
     */
    private Cost CardCost;

    /**
     * 
     */
    private int period;

    /**
     * 
     */
    private ImmediateEffects IEReference;

    /**
     * 
     */
    private PermanentEffects PEReference;

    /**
     * 
     */
    private ArrayList<TrisIE> Immediate_Effects;

    /**
     * @param player 
     * @return
     */
    public void MakeImmediateEffects(Player player) {
	}

    /**
     * @param player 
     * @return
     */
    public void MakePermannetEffects(Player player) {
	}

}