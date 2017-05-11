package ServerModel;

import java.util.*;

/**
 * 
 */
public class LeaderCard {

    /**
     * Default constructor
     */

    /**
     * 
     */
    private String name;

    /**
     * 
     */
    private boolean onePerTurn;


    private boolean isPlayed = false;


    private ArrayList<Effects> immediateCardEffects;


    public LeaderCard(ArrayList<TrisIE> immediate_Effects) {
        ImmediateEffects Ie = new ImmediateEffects();
        for (TrisIE x: immediate_Effects){
            Effects e = Ie.SearchImmediateEffects(x.getType(), x.getParameter(), x.getQuantity());
            immediateCardEffects.add(e);
        }
    }


    public void Discard() {
        // TODO implement here
    }

    /**
     * @return
     */
    public void PlayCard(Player player) {
        for (Effects x: immediateCardEffects ) {
            x.doEffect(player);
        }
    }

    /**
     * @return
     */
    private void CheckRequirements() {
        // TODO implement here
    }

    public boolean isPlayed() {
        return isPlayed;
    }

    public void setPlayed(boolean played) {
        isPlayed = played;
    }
}