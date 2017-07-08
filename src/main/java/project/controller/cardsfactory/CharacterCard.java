package project.controller.cardsfactory;

import project.controller.effects.effectsfactory.PokerPE;
import project.controller.effects.effectsfactory.TrisIE;
import project.model.DevelopmentCard;
import project.model.PersonalBoard;

import java.io.Serializable;
import java.util.*;

/**
 * 
 */
public class CharacterCard extends DevelopmentCard implements Serializable{

    private CharactersCost cardCost;

    public CharacterCard(){}

    public CharacterCard(String name, int period, boolean choicePe, CharactersCost cost, List<TrisIE> immediateEffects, List<PokerPE> permanentEffects) {
        super(name, period, choicePe, immediateEffects, permanentEffects);
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

    @Override
    public void setCost(Cost cost) {
        cardCost = (CharactersCost)cost;
    }
}