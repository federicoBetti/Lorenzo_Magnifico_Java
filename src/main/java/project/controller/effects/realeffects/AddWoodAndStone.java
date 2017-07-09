package project.controller.effects.realeffects;

import project.messages.BonusInteraction;
import project.messages.OkOrNo;
import project.server.network.PlayerHandler;

/**
 * This class represent the AddWoodAndStone effect
 */
public class AddWoodAndStone implements Effects {
    private int quantity;

    /**
     * Constructor
     *
     * @param quantity of wood and stone to add
     */
    public AddWoodAndStone(int quantity) {
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
        int woodQuantity = quantity;
        woodQuantity += player.getPersonalBoardReference().getBonusOnActions().getWoodBonus();
        if (woodQuantity<0)
            woodQuantity = 0;
        player.getPersonalBoardReference().setWood(player.getPersonalBoardReference().getWood() + woodQuantity);


        int stoneQuantity = quantity;
        stoneQuantity += player.getPersonalBoardReference().getBonusOnActions().getStoneBonus();
        if (stoneQuantity<0)
            stoneQuantity = 0;

        player.getPersonalBoardReference().setStone(player.getPersonalBoardReference().getStone() + stoneQuantity);
        return new OkOrNo();
    }

    /**
     * Build a string for describing the effect
     *
     * @return the description's String
     */
    @Override
    public String toScreen(){
        return "Add " + quantity + " stones and " + quantity + " wood";
    }
}
