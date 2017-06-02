package project.model;

import project.controller.cardsfactory.Cost;
import project.controller.effects.effectsfactory.PokerPE;
import project.controller.effects.realeffects.Effects;
import project.controller.effects.effectsfactory.BuildImmediateEffects;
import project.controller.effects.effectsfactory.*;
import project.server.network.PlayerHandler;
import project.messages.BonusInteraction;


import java.util.*;
//bisogna fare che il meotod doEffect riitorna la rispooata a ritornare al client, che puo essere un oggetto che è ok, oppure un oggetto tipo tower action
//ritorna una responde cioè bonusinterface

public abstract class DevelopmentCard {

    private String name;

    private int period;

    private List<Effects> immediateCardEffects;

    private List<Effects> permanentCardEffects;

    private Cost cardCost;


    // ci saranno altre cose e parametri nella carta ma gli effetti vengono gestiti così
    public DevelopmentCard(String name, int period, List<TrisIE> immediateEffects, List<PokerPE> permanentEffects){
        this.name = name;
        this.period = period;
        immediateCardEffects = new ArrayList<>();
        permanentCardEffects = new ArrayList<>();

        BuildImmediateEffects ie = new BuildImmediateEffects();
        for (TrisIE x: immediateEffects){
            Effects e = ie.SearchImmediateEffects(x.getType(), x.getParameter(), x.getQuantity());
            immediateCardEffects.add(e);
        }
        BuildPermanentEffects pe = new BuildPermanentEffects();
        for ( PokerPE x: permanentEffects){
            Effects e = pe.searchPermanentEffects(x.getType(), x.getParameter(), x.getResourceEarned(),  x.getQuantity(), x.getEffectCost());
            permanentCardEffects.add(e);
        }
        
    }
    public BonusInteraction makeImmediateEffects(PlayerHandler player) {
        BonusInteraction bonusInteraction = null;
        for (Effects x: immediateCardEffects ) {
            bonusInteraction = x.doEffect(player);
        }
        //posso fare quest cosa di riscrivere e prendere l'ultimo tanto sono dello stesso tipo se gli effetti appartengono alla stessa carta
        return bonusInteraction;
	}

    public void makePermannetEffects(Player player) {
       //TODO: può essere scelto l'effetto permanente da attivare
        }

    public List<Effects> getImmediateCardEffects() {
        return immediateCardEffects;
    }

    public abstract void addToPersonalBoard(PersonalBoard personalBoardReference);

    public abstract Cost getCost();

    public String getName() {
        return name;
    }
}

