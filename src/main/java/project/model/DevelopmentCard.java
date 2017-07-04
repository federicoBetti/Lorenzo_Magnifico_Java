package project.model;

import project.controller.cardsfactory.Cost;
import project.controller.effects.realeffects.Effects;
import project.controller.effects.effectsfactory.*;


import java.io.Serializable;
import java.util.*;
//bisogna fare che il meotod doEffect riitorna la rispooata a ritornare al client, che puo essere un oggetto che è ok, oppure un oggetto tipo tower action
//ritorna una responde cioè bonusinterface

public abstract class DevelopmentCard implements Serializable{

    private String name;

    private int period;

    private List<Effects> immediateCardEffects;

    private List<Effects> permanentCardEffects;

    private Cost cardCost;

    private boolean choicePe;

    // ci saranno altre cose e parametri nella carta ma gli effetti vengono gestiti così
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

    protected DevelopmentCard(){}

    public boolean isChoicePe() {
        return choicePe;
    }

    public List<Effects> getPermanentCardEffects() {
        return permanentCardEffects;
    }

    public List<Effects> getImmediateCardEffects() {
        return immediateCardEffects;
    }

    public abstract void addToPersonalBoard(PersonalBoard personalBoardReference);

    public abstract Cost getCost();

    public String getName() {
        return name;
    }

    public void setCost(Cost cost) {
        this.cardCost = cost;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setChoicePe(boolean choicePe) {
        this.choicePe = choicePe;
    }
}

