package project.controller.effects.realeffects;


import project.messages.BonusInteraction;
import project.messages.OkOrNo;
import project.server.network.PlayerHandler;

/**
 * This class represent the AddStone effect
 */
public class AddStone implements TakeRoPEffects {

    private int quantity;

    /**
     * Constructor
     *
     * @param quantity stones to add
     */
    public AddStone (int quantity){
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
        quantity += player.getPersonalBoardReference().getBonusOnActions().getStoneBonus();
        if (quantity<0)
            quantity = 0;
        player.getPersonalBoardReference().setStone(player.getPersonalBoardReference().getStone() + quantity);
        return new OkOrNo();
    }

    /**
     * Build a string for describing the effect
     *
     * @return the description's String
     */
    @Override
    public String toScreen(){
        return "Add " + quantity + " stones";
    }
}
