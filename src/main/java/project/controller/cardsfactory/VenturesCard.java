package project.controller.cardsfactory;

import project.controller.effects.effectsfactory.PokerPE;
import project.controller.effects.effectsfactory.TrisIE;
import project.model.DevelopmentCard;
import project.model.PersonalBoard;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * This class contains the specific Venture Cards characteristics
 */
public class VenturesCard extends DevelopmentCard implements Serializable{

    private List<VenturesCost> possibleCost;

    /**
     * Constrctor
     *
     * @param name card's name
     * @param period period's number
     * @param choicePe choicePe
     * @param cost card's cost
     * @param immediateEffects immediateEffect's reference
     * @param permanentEffects permanentEffect's reference
     */
    public VenturesCard(String name, int period, boolean choicePe, List<VenturesCost> cost, List<TrisIE> immediateEffects, List<PokerPE> permanentEffects) {

        super(name, period, choicePe, immediateEffects, permanentEffects);
        possibleCost = new ArrayList<>();

        for ( VenturesCost venturesCost : cost ){
            possibleCost.add(venturesCost);
        }

    }

    /**
     * for testing
     */
    public VenturesCard(){
        //for testing
    }

    /**
     * This method adds the card to the personal board
     *
     * @param personalBoardReference personal board's reference
     */
    @Override
    public void addToPersonalBoard(PersonalBoard personalBoardReference) {
        personalBoardReference.getVentures().add(this);
    }

    /**
     * Get cost
     *
     * @return cost
     */
    @Override
    public Cost getCost() {
        return null;
    }

    /**
     * Set cost
     *
     * @param cost cost
     */
    @Override
    public void setCost(Cost cost) {
        // void
    }

    /**
     * Get possible cost's list
     *
     * @return possibleCost
     */
    public List<VenturesCost> getPossibleCost(){
        return possibleCost;
    }

    /**
     * Get possible ventures's cost list
     *
     * @return possibleCost
     */
    public List<VenturesCost> getVenturesCost() {
        return possibleCost;
    }
}