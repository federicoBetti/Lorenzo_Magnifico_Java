package ServerModel;

import java.util.*;
//bisogna fare che il meotod doEffect riitorna la rispooata a ritornare al client, che puo essere un oggetto che è ok, oppure un oggetto tipo tower action
//ritorna una responde cioè bonusinterface

public abstract class Card {

    private String name;

    private Cost CardCost;

    private int period;

    private ArrayList<TrisIE> immediate_Effects;

    private ArrayList<PokerPE> permanent_Effects;

    private ArrayList<Effects> immediateCardEffects;

    private ArrayList<Effects> permanentCardEffects;


    // ci saranno altre cose e parametri nella carta ma gli effetti vengono gestiti così
    public Card(String name, ArrayList<TrisIE> immediate_Effects, ArrayList<PokerPE> permanent_Effects){
        ImmediateEffects Ie = new ImmediateEffects();
        for (TrisIE x: immediate_Effects){
            Effects e = Ie.SearchImmediateEffects(x.getType(), x.getParameter(), x.getQuantity());
            immediateCardEffects.add(e);
        }
        PermanentEffects Pe = new PermanentEffects();
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
        // TO DO
	}

}