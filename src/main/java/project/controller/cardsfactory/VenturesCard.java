package project.controller.cardsfactory;

import project.controller.effects.effectsfactory.PokerPE;
import project.controller.effects.effectsfactory.TrisIE;
import project.model.DevelopmentCard;
import project.model.PersonalBoard;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class VenturesCard extends DevelopmentCard implements Serializable{

    private List<VenturesCost> possibleCost;

    public VenturesCard(String name, int period, boolean choicePe, List<VenturesCost> cost, List<TrisIE> immediateEffects, List<PokerPE> permanentEffects) {

        super(name, period, choicePe, immediateEffects, permanentEffects);
        possibleCost = new ArrayList<>();

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

    public List<VenturesCost> getPossibleCost(){
        return possibleCost;
    }

    public List<VenturesCost> getVenturesCost() {
        return possibleCost;
    }
}