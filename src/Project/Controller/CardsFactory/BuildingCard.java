package Project.Controller.CardsFactory;

import Project.Controller.Effects.EffectsFactory.PokerPE;
import Project.Controller.Effects.EffectsFactory.TrisIE;
import Project.MODEL.*;


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
    public BuildingCard(String name, int period, BuildingCost cost, ArrayList<TrisIE> immediate_Effects, ArrayList<PokerPE> permanent_Effects) {
        super(name, period, immediate_Effects, permanent_Effects);
        effectCost = new ArrayList<>();
        this.cardCost = cost;
        for (PokerPE p: permanent_Effects) {
            if (p.getType() == "exchangeRes") {
                effectCost.add(p.getEffectCost());
            }
        }

    }




}