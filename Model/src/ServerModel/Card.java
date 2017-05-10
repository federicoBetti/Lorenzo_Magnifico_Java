package ServerModel;

import java.util.*;
//bisogna fare che il meotod doEffect riitorna la rispooata a ritornare al client, che puo essere un oggetto che è ok, oppure un oggetto tipo tower action
//ritorna una responde cioè bonusinterface

public abstract class Card {

    private String name;

    private Cost CardCost;

    private int period;

    private ArrayList<TrisIE> Immediate_Effects;

    private ArrayList<TrisIE> Permanent_Effects;

    private ArrayList<Effects> cardEffects;


    // ci saranno altre cose e parametri nella carta ma gli effetti vengono gestiti così
    public Card(String name, ArrayList<TrisIE> Immediate_Effects){
        ImmediateEffects Ie = new ImmediateEffects();
        for (TrisIE x: Immediate_Effects){
            Effects e = Ie.SearchImmediateEffects(x.getType(), x.getParameter(), x.getQuantity());
            cardEffects.add(e);
        }
    }
    public void MakeImmediateEffects(Player player) {
        for (Effects x: cardEffects) {
            x.doEffect(player);
        }
	}

    public void MakePermannetEffects(Player player) {
	}

}