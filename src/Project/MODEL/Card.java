package Project.MODEL;

import Project.Controller.CardsFactory.Cost;
import Project.Controller.Effects.EffectsFactory.PokerPE;
import Project.Controller.Effects.RealEffects.Effects;
import Project.Controller.Effects.EffectsFactory.BuildImmediateEffects;
import Project.Controller.Effects.EffectsFactory.*;



import java.util.*;
//bisogna fare che il meotod doEffect riitorna la rispooata a ritornare al client, che puo essere un oggetto che è ok, oppure un oggetto tipo tower action
//ritorna una responde cioè bonusinterface

public abstract class Card {

    private String name;

    private int period;

    private ArrayList<Effects> immediateCardEffects;

    private ArrayList<Effects> permanentCardEffects;


    // ci saranno altre cose e parametri nella carta ma gli effetti vengono gestiti così
    public Card(String name, int period, ArrayList<TrisIE> immediate_Effects, ArrayList<PokerPE> permanent_Effects){
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
    public void MakeImmediateEffects(Player player) {
        for (Effects x: immediateCardEffects ) {
            x.doEffect(player);
        }
	}

    public void MakePermannetEffects(Player player) {
       //TODO: può essere scelto l'effetto permanente da attivare
        }

    public ArrayList<Effects> getImmediateCardEffects() {
        return immediateCardEffects;
    }

    public Cost getCardCost() {
        return CardCost;
    }
}

