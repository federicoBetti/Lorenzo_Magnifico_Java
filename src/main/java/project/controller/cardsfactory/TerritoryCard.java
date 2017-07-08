package project.controller.cardsfactory;

import project.controller.effects.effectsfactory.PokerPE;
import project.controller.effects.effectsfactory.TrisIE;
import project.model.*;


import java.io.Serializable;
import java.util.*;

/**
 * This class contains the specific territory Cards characteristics
 */
public class TerritoryCard extends DevelopmentCard implements Serializable {

    private TerritoryCost cardCost;

    public TerritoryCard(String name, int period, boolean choicePe, TerritoryCost cost, List<TrisIE> immediateEffects, List<PokerPE> permanentEffects) {
        super(name, period, choicePe, immediateEffects, permanentEffects);
        this.cardCost = cost;
    }

    /**
     * for testing
     */
    public TerritoryCard() {
        //for testing
    }

    /**
     * This method add the territory cards to the personal Board
     *
     * @param personalBoardReference personal board's reference
     */
    @Override
    public void addToPersonalBoard(PersonalBoard personalBoardReference) {
        personalBoardReference.getTerritories().add(this);
    }

    /**
     * Get card cost
     *
     * @return cardCost
     */
    @Override
    public TerritoryCost getCost() {
        return cardCost;
    }
}