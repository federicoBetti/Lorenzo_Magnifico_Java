package project.controller.cardsfactory;

import project.controller.effects.effectsfactory.PokerPE;
import project.controller.effects.effectsfactory.TrisIE;
import project.model.DevelopmentCard;
import project.model.PersonalBoard;

import java.io.Serializable;
import java.util.*;

/**
 * This class contains the specific Character Cards characteristics
 */
public class CharacterCard extends DevelopmentCard implements Serializable{

    private CharactersCost cardCost;

    public CharacterCard(){}

    public CharacterCard(String name, int period, boolean choicePe, CharactersCost cost, List<TrisIE> immediateEffects, List<PokerPE> permanentEffects) {
        super(name, period, choicePe, immediateEffects, permanentEffects);
        this.cardCost = cost;
    }

    /**
     * This method add the character card to the personal board
     *
     * @param personalBoardReference personalBoard's reference
     */
    @Override
    public void addToPersonalBoard(PersonalBoard personalBoardReference) {
        personalBoardReference.getCharacters().add(this);
    }

    /**
     * Get the character cost
     *
     * @return character cost
     */
    @Override
    public CharactersCost getCost() {
        return cardCost;
    }

    @Override
    public void setCost(Cost cost) {
        cardCost = (CharactersCost)cost;
    }
}