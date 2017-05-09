package ServerModel;

import java.util.*;

public abstract class Card {

    private String name;

    private Cost CardCost;

    private int period;

    private ArrayList<TrisIE> Immediate_Effects;

    private ArrayList<TrisIE> Permanent_Effects;

    private ArrayList<Effects> cardEffects;

    /**
     * @param player 
     * @return
     */
    public Card(){

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