package Project.Controller.CardsFactory;

import Project.Controller.Effects.EffectsFactory.PokerPE;
import Project.Controller.Effects.EffectsFactory.TrisIE;
import Project.MODEL.Card;
import Project.MODEL.Player;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 
 */
public class VenturesCard extends Card {

    private ArrayList<VenturesCost> possibleCost;
    private VenturesCost CardCost;

    public VenturesCard(String name, int period, ArrayList<VenturesCost> cost, ArrayList<TrisIE> immediate_Effects, ArrayList<PokerPE> permanent_Effects) {

        super(name, period, immediate_Effects, permanent_Effects);
        possibleCost = new ArrayList<VenturesCost>();

        for ( VenturesCost venturesCost : cost ){
            possibleCost.add(venturesCost);
        }

    }



}