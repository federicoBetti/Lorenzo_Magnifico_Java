package Project.Controller.CardsFactory;

import Project.Controller.Effects.RealEffects.Effects;
import Project.Controller.Effects.EffectsFactory.BuildImmediateEffects;
import Project.Controller.Effects.EffectsFactory.TrisIE;
import Project.MODEL.Player;

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
        BuildImmediateEffects Ie = new BuildImmediateEffects();
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