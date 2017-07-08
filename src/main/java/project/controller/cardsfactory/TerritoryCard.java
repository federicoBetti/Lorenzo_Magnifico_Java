package project.controller.cardsfactory;

import project.controller.effects.effectsfactory.PokerPE;
import project.controller.effects.effectsfactory.TrisIE;
import project.model.*;


import java.io.Serializable;
import java.util.*;

/**
 * 
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


    @Override
    public void addToPersonalBoard(PersonalBoard personalBoardReference) {
        personalBoardReference.getTerritories().add(this);
    }

    @Override
    public TerritoryCost getCost() {
        return cardCost;
    }

    @Override
    public void setCost(Cost cost) {
        cardCost = (TerritoryCost)cost;
    }
}