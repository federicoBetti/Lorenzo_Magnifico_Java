package project.controller.effects.effectsfactory;

import project.controller.cardsfactory.TotalCost;

/**
 * This class is build parsing a json's file. It contains attributes that are useful for building permanent effects
 */
public class PokerPE extends TrisIE {

    private String resourceEarnedFromExchange;

    private TotalCost effectCost;

    /**
     * constructor
     * @param type type
     * @param parameter parameter
     * @param quantity quantity
     */
    public PokerPE(String type, String parameter, int quantity) {
        super(type, parameter, quantity);
    }

    /**
     * constructor
     * @param effectCost parameter constructor
     * @param resourceEarnedFromExchange parameter constructor
     * @param type parameter constructor
     * @param parameter parameter constructor
     * @param quantity parameter constructor
     */
    public PokerPE(TotalCost effectCost, String resourceEarnedFromExchange, String type, String parameter, int quantity) {
        super(type, parameter, quantity);
        this.resourceEarnedFromExchange = resourceEarnedFromExchange;
        this.effectCost = effectCost;
    }

    /**
     * Get resourceEarnedFromExchange
     *
     * @return resourceEarnedFromExchange
     */
    public String getResourceEarned() {
        return resourceEarnedFromExchange;
    }

    /**
     * Get effectCost
     *
     * @return effectCost
     */
    public TotalCost getEffectCost(){
        return this.effectCost;
    }
}