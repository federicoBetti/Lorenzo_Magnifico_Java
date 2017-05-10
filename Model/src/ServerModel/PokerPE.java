package ServerModel;

import java.util.*;

/**
 * 
 */
public class PokerPE extends TrisIE {

    /**
     * Default constructor
     */
    public PokerPE() {
    }

    /**
     * 
     */

    private String resourceEarned;
    private TotalCost EffectCost;

    public String getResourceEarned() {
        return resourceEarned;
    }

    public void setResourceEarned(String resourceEarned) {
        this.resourceEarned = resourceEarned;
    }

    public void setEffectCost(TotalCost effectCost) {
        EffectCost = effectCost;
    }

    public TotalCost getEffectCost(){
        return this.EffectCost;
    }
}