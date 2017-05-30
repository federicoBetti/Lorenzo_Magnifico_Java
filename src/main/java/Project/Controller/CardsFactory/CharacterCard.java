package Project.Controller.CardsFactory;

import Project.Controller.Effects.EffectsFactory.PokerPE;
import Project.Controller.Effects.EffectsFactory.TrisIE;
import Project.MODEL.DevelopmentCard;
import Project.MODEL.PersonalBoard;

import java.util.*;

/**
 * 
 */
public class CharacterCard extends DevelopmentCard {

    private CharactersCost cardCost;
    /**
     * Default constructor
     */
    public CharacterCard(String name, int period, CharactersCost cost, ArrayList<TrisIE> immediate_Effects, ArrayList<PokerPE> permanent_Effects) {
        super(name, period, immediate_Effects, permanent_Effects);
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