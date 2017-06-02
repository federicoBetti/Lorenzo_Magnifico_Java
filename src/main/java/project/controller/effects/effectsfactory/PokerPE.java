package project.controller.effects.effectsfactory;

import project.controller.cardsfactory.TotalCost;

/**
 * 
 */
public class PokerPE extends TrisIE {

    private String resourceEarnedFromExchange;

    private TotalCost effectCost;

    public PokerPE(String type, String parameter, int quantity) {
        super(type, parameter, quantity);
    }


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