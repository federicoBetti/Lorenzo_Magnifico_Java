package Project.MODEL;

import Project.Controller.CardsFactory.Cost;
import Project.Controller.CardsFactory.TerritoryCost;
import Project.Controller.Effects.EffectsFactory.PokerPE;
import Project.Controller.Effects.RealEffects.Effects;
import Project.Controller.Effects.EffectsFactory.BuildImmediateEffects;
import Project.Controller.Effects.EffectsFactory.*;
import Project.toDelete.BonusInteraction;


import java.util.*;
//bisogna fare che il meotod doEffect riitorna la rispooata a ritornare al client, che puo essere un oggetto che è ok, oppure un oggetto tipo tower action
//ritorna una responde cioè bonusinterface

public abstract class DevelopmentCard {

    private String name;

    private int period;

    private ArrayList<Effects> immediateCardEffects;

    private ArrayList<Effects> permanentCardEffects;

    private Cost cardCost;


    // ci saranno altre cose e parametri nella carta ma gli effetti vengono gestiti così
    public DevelopmentCard(String name, int period, ArrayList<TrisIE> immediate_Effects, ArrayList<PokerPE> permanent_Effects){
        this.name = name;
        this.period = period;
        immediateCardEffects = new ArrayList<Effects>();
        permanentCardEffects = new ArrayList<Effects>();

        BuildImmediateEffects Ie = new BuildImmediateEffects();
        for (TrisIE x: immediate_Effects){
            Effects e = Ie.SearchImmediateEffects(x.getType(), x.getParameter(), x.getQuantity());
            immediateCardEffects.add(e);
        }
        BuildPermanentEffects Pe = new BuildPermanentEffects();
        for ( PokerPE x: permanent_Effects){
            Effects e = Pe.SearchPermanentEffects(x.getType(), x.getParameter(), x.getResourceEarned(),  x.getQuantity(), x.getEffectCost());
            permanentCardEffects.add(e);
        }
        
    }
    public BonusInteraction makeImmediateEffects(Player player) {
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

    public ArrayList<Effects> getImmediateCardEffects() {
        return immediateCardEffects;
    }

    public abstract void addToPersonalBoard(PersonalBoard personalBoardReference);

    public abstract Cost getCost();

    public String getName() {
        return name;
    }
}

