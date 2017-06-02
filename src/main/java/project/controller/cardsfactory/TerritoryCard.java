package project.controller.cardsfactory;

import project.controller.effects.effectsfactory.PokerPE;
import project.controller.effects.effectsfactory.TrisIE;
import project.model.*;


import java.util.*;

/**
 * 
 */
public class TerritoryCard extends DevelopmentCard {

    private TerritoryCost cardCost;

    public TerritoryCard(String name, int period, TerritoryCost cost, List<TrisIE> immediateEffects, List<PokerPE> permanentEffects) {
        super(name, period, immediateEffects, permanentEffects);
        this.cardCost = cost;
    }


    @Override
    public void addToPersonalBoard(PersonalBoard personalBoardReference) {
        personalBoardReference.getTerritories().add(this);
    }

    @Override
    public TerritoryCost getCost() {
        return cardCost;
    }
}