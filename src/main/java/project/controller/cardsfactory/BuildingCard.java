package project.controller.cardsfactory;

import project.controller.effects.effectsfactory.PokerPE;
import project.controller.effects.effectsfactory.TrisIE;
import project.model.*;


import java.io.Serializable;
import java.util.*;

/**
 * This class contains the specific Building Cards characteristics
 */
public class BuildingCard extends DevelopmentCard implements Serializable {


    private BuildingCost cardCost;
    private ArrayList<TotalCost> effectCost;

    /**
     * for testing
     */
    public BuildingCard(){
        //for testing
    }

    /**
     * Constructor
     *
     * @param name card's name
     * @param period period's number
     * @param choicePe boolean that says if a card has two permanents effects alternatively
     * @param cost card's cost
     * @param immediateEffects card's immediate effects
     * @param permanentEffects card's permanent effectsb
     */
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

    /**
     * This method add the building card to the player's personal Board
     *
     * @param personalBoardReference personalBoard's reference
     */
    @Override
    public void addToPersonalBoard(PersonalBoard personalBoardReference) {
        personalBoardReference.getBuildings().add(this);
    }

    /**
     * Get the building cost
     *
     * @return building cost
     */
    @Override
    public BuildingCost getCost() {
        return cardCost;
    }

    @Override
    public void setCost(Cost cost) {
        cardCost = (BuildingCost)cost;
    }

    /**
     * Set the building cost
     *
     * @param cardCost building cost
     */
    public void setCardCost(BuildingCost cardCost) {
        this.cardCost = cardCost;
    }
}