package project.model;

import project.controller.cardsfactory.Cost;
import project.controller.effects.realeffects.Effects;
import project.controller.effects.effectsfactory.*;


import java.io.Serializable;
import java.util.*;

/**
 * This class represent a general development card
 */
public abstract class DevelopmentCard implements Serializable{

    private String name;

    private int period;

    private List<Effects> immediateCardEffects;

    private List<Effects> permanentCardEffects;

    private Cost cardCost;

    private boolean choicePe;

    /**
     * Constructor
     *
     * @param name card's name
     * @param period card's period
     * @param choicePe boolean that says if the card has 2 alternatively permanent effects
     * @param immediateEffects immediate effect's reference
     * @param permanentEffects permanent effect's reference
     */
    protected DevelopmentCard(String name, int period, boolean choicePe, List<TrisIE> immediateEffects, List<PokerPE> permanentEffects){
        this.name = name;
        this.period = period;
        this.choicePe = choicePe;
        immediateCardEffects = new ArrayList<>();
        permanentCardEffects = new ArrayList<>();

        BuildImmediateEffects ie = new BuildImmediateEffects();
        for (TrisIE x: immediateEffects){
            Effects e = ie.searchImmediateEffects(x.getType(), x.getParameter(), x.getQuantity());
            immediateCardEffects.add(e);
        }
        BuildPermanentEffects pe = new BuildPermanentEffects();
        for ( PokerPE x: permanentEffects){
            Effects e = pe.searchPermanentEffects(x.getType(), x.getParameter(), x.getResourceEarned(),  x.getQuantity(), x.getEffectCost());
            permanentCardEffects.add(e);
        }
    }

    /**
     * Constructor
     */
    protected DevelopmentCard(){}

    /**
     * Get choicePe
     *
     * @return choicePe
     */
    public boolean isChoicePe() {
        return choicePe;
    }

    /**
     * Get permanentCardEffects
     *
     * @return permanentCardEffects
     */
    public List<Effects> getPermanentCardEffects() {
        return permanentCardEffects;
    }

    /**
     * Get immediateCardEffects
     *
     * @return immediateCardEffects
     */
    public List<Effects> getImmediateCardEffects() {
        return immediateCardEffects;
    }

    /**
     * Abstract addToPersonalBoard
     *
     * @param personalBoardReference addToPersonalBoard
     */
    public abstract void addToPersonalBoard(PersonalBoard personalBoardReference);

    /**
     * Abstract get cost
     *
     * @return cost
     */
    public abstract Cost getCost();

    /**
     * Get name
     *
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Abstract set cost
     *
     * @param cost cost
     */
    public abstract void setCost(Cost cost);

    /**
     * Abstract set name
     *
     * @param name name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Set choicePe
     *
     * @param choicePe choicePe
     */
    public void setChoicePe(boolean choicePe) {
        this.choicePe = choicePe;
    }
}

