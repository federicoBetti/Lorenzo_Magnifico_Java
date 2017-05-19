package Project.Controller.CardsFactory;

import Project.Controller.Effects.EffectsFactory.PokerPE;
import Project.Controller.Effects.EffectsFactory.TrisIE;
import Project.MODEL.*;


import java.util.*;

/**
 * 
 */
public class TerritoryCard extends Card {

    private TerritoryCost cardCost;

    /**
     * Default constructor
     */
    public TerritoryCard(String name, int period, TerritoryCost cost, ArrayList<TrisIE> immediate_Effects, ArrayList<PokerPE> permanent_Effects) {
        super(name, period, immediate_Effects, permanent_Effects);
        this.cardCost = cost;
    }

    @Override
    public TerritoryCost getCardCost() {
        return cardCost;
    }
}