package Project.Controller.Effects.EffectsFactory;

import Project.Controller.CardsFactory.TotalCost;

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

    private String resourceEarnedFromExchange;

    private TotalCost effectCost;



    public String getResourceEarned() {
        return resourceEarnedFromExchange;
    }

    public void setResourceEarned(String resourceEarned) {
        this.resourceEarnedFromExchange = resourceEarned;
    }

    public void setEffectCost(TotalCost effectCost) {
        this.effectCost = effectCost;
    }

    public TotalCost getEffectCost(){
        return this.effectCost;
    }
}