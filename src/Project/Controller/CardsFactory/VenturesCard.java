package Project.Controller.CardsFactory;

import Project.Controller.Effects.EffectsFactory.PokerPE;
import Project.Controller.Effects.EffectsFactory.TrisIE;
import Project.MODEL.DevelopmentCard;
import Project.MODEL.PersonalBoard;
import Project.MODEL.Player;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 
 */
public class VenturesCard extends DevelopmentCard {

    private ArrayList<VenturesCost> possibleCost;

    public VenturesCard(String name, int period, ArrayList<VenturesCost> cost, ArrayList<TrisIE> immediate_Effects, ArrayList<PokerPE> permanent_Effects) {

        super(name, period, immediate_Effects, permanent_Effects);
        possibleCost = new ArrayList<VenturesCost>();

        for ( VenturesCost venturesCost : cost ){
            possibleCost.add(venturesCost);
        }

    }


    @Override
    public void addToPersonalBoard(PersonalBoard personalBoardReference) {
        personalBoardReference.getVentures().add(this);
    }

    @Override
    public Cost getCost() {
        return null;
    }

    public ArrayList<VenturesCost> getVenturesCost() {
        return possibleCost;
    }
}