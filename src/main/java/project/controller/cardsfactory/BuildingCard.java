package project.controller.cardsfactory;

import project.controller.effects.effectsfactory.PokerPE;
import project.controller.effects.effectsfactory.TrisIE;
import project.model.*;


import java.util.*;

/**
 * 
 */
public class BuildingCard extends DevelopmentCard {
    /**
     *
     */
    private BuildingCost cardCost;
    private ArrayList<TotalCost> effectCost;
    /**
     * Default constructor
     */
    public BuildingCard(String name, int period, BuildingCost cost, List<TrisIE> immediateEffects, List<PokerPE> permanentEffects) {
        super(name, period, immediateEffects, permanentEffects);
        effectCost = new ArrayList<>();
        this.cardCost = cost;
        for (PokerPE p: permanentEffects) {
            if (p.getType() == "exchangeRes") {
                effectCost.add(p.getEffectCost());
            }
        }

    }


    @Override
    public void addToPersonalBoard(PersonalBoard personalBoardReference) {
        personalBoardReference.getBuildings().add(this);
    }

    @Override
    public BuildingCost getCost() {
        return cardCost;
    }
}