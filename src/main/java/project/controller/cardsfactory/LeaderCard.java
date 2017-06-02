package project.controller.cardsfactory;

import project.controller.effects.realeffects.Effects;
import project.controller.effects.effectsfactory.BuildImmediateEffects;
import project.controller.effects.effectsfactory.TrisIE;
import project.model.Player;
import project.server.network.PlayerHandler;

import java.util.*;

/**
 * 
 */
public class LeaderCard {

    private String name;

    private boolean onePerTurn;

    private boolean isPlayed;

    private List<Effects> immediateCardEffects;


    public LeaderCard(){
        this.immediateCardEffects = new ArrayList<>();
    }

    public LeaderCard(List<TrisIE> immediateEffects) {
        BuildImmediateEffects ie = new BuildImmediateEffects();
        for (TrisIE x: immediateEffects){
            Effects e = ie.SearchImmediateEffects(x.getType(), x.getParameter(), x.getQuantity());
            immediateCardEffects.add(e);
        }
    }


    public void discard() {
        // TODO implement here
    }

    /**
     * @return
     */
    public void playCard(Player player) {
        for (Effects x: immediateCardEffects ) {
            x.doEffect((PlayerHandler) player);
        }
    }

    /**
     * @return
     */
    private void checkRequirements() {
        // TODO implement here
    }

    public String getName() {
        return name;
    }

    public boolean isPlayed() {
        return isPlayed;
    }

    public void setPlayed(boolean played) {
        isPlayed = played;
    }
}