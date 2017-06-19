package project.controller.cardsfactory;

import project.controller.effects.effectsfactory.PokerPE;
import project.controller.effects.effectsfactory.TrisIE;
import project.model.*;


import java.io.Serializable;
import java.util.*;


public class BuildingCard extends DevelopmentCard implements Serializable {


    private BuildingCost cardCost;
    private ArrayList<TotalCost> effectCost;


    public BuildingCard(String name, int period, boolean choicePe, BuildingCost cost, List<TrisIE> immediateEffects, List<PokerPE> permanentEffects) {
        super(name, period, choicePe, immediateEffects, permanentEffects);
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