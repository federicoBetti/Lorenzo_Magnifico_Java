package Project.Controller.CardsFactory;

import Project.Controller.Effects.EffectsFactory.PokerPE;
import Project.Controller.Effects.EffectsFactory.TrisIE;
import Project.MODEL.*;


import java.util.*;

/**
 * 
 */
public class BuildingCard extends Card {

    /**
     * Default constructor
     */
    public BuildingCard(String name, int period, BuildingCost cost, ArrayList<TrisIE> immediate_Effects, ArrayList<PokerPE> permanent_Effects) {
        super(name, period, immediate_Effects, permanent_Effects);
        this.cardCost = cost;

    }

    /**
     * 
     */
    private BuildingCost cardCost;


}