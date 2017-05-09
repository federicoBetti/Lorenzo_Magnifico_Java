package ServerModel;

import java.util.*;

public abstract class Card {

    private String name;

    private Cost CardCost;

    private int period;

    private ArrayList<Effects> Immediate_Effects;

    private ArrayList<Effects> Permanent_Effects;

    private ArrayList<Effects> cardEffects;

    /**
     * @param player 
     * @return
     */
    public Card(ArrayList<TrisIE> triplette){


        int i = 0;
        ImmediateEffects Ie = new ImmediateEffects();
        for (TrisIE x: triplette){
            Effects e1 = new Ie.SearchImmediateEffects(x.getType(),x.getParameter(),x.getQuantity());
            Immediate_Effects.add(e1);
        }
    }
    public void MakeImmediateEffects(Player player) {
        for (TrisIE x: Immediate_Effects) {
            ImmediateEffects.SearchImmediateEffects(x, player);
        }
	}

    /**
     * @param player 
     * @return
     */
    public void MakePermannetEffects(Player player) {
	}

}