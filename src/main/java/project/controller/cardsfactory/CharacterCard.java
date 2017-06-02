package project.controller.cardsfactory;

import project.controller.effects.effectsfactory.PokerPE;
import project.controller.effects.effectsfactory.TrisIE;
import project.model.DevelopmentCard;
import project.model.PersonalBoard;

import java.util.*;

/**
 * 
 */
public class CharacterCard extends DevelopmentCard {

    private CharactersCost cardCost;
    /**
     * Default constructor
     */
    public CharacterCard(String name, int period, CharactersCost cost, List<TrisIE> immediateEffects, List<PokerPE> permanentEffects) {
        super(name, period, immediateEffects, permanentEffects);
        this.cardCost = cost;
    }


    @Override
    public void addToPersonalBoard(PersonalBoard personalBoardReference) {
        personalBoardReference.getCharacters().add(this);
    }

    @Override
    public CharactersCost getCost() {
        return cardCost;
    }
}