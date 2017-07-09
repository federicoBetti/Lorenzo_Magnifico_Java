package project.controller.effects.realeffects;


import project.messages.BonusInteraction;
import project.messages.OkOrNo;
import project.server.network.PlayerHandler;

/**
 * This class represent the AddVictoryPoints effect
 */
public class AddVictoryPoints implements TakeRoPEffects {

    private int quantity;

    /**
     * Constructor
     *
     * @param quantity victory points to add
     */
    public AddVictoryPoints(int quantity){
        this.quantity = quantity;
    }

    /**
     * Perform the effect
     *
     * @param player playerHandler's reference
     * @return okOrNo instance for saying that the effect has been applied correctly
     */
    @Override
    public BonusInteraction doEffect(PlayerHandler player) {
        player.getScore().setVictoryPoints(player.getScore().getVictoryPoints() + quantity);
        return new OkOrNo();

    }

    /**
     * Build a string for describing the effect
     *
     * @return the description's String
     */
    @Override
    public String toScreen(){
        return "Add " + quantity + " victory points";
    }
}
