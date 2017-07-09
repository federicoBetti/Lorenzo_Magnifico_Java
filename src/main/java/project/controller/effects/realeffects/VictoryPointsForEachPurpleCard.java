package project.controller.effects.realeffects;

import project.messages.BonusInteraction;
import project.messages.OkOrNo;
import project.server.network.PlayerHandler;

/**
 * This class represents the VictoryPointsForEachPurpleCard effect
 */
public class VictoryPointsForEachPurpleCard implements Effects {

    private int quantity;

    /**
     * Constructor
     *
     * @param quantity of points for each card
     */
    public VictoryPointsForEachPurpleCard( int quantity ){
        this.quantity = quantity;
    }

    /**
     * Perform the right effect
     *
     * @param player playerHandler's reference
     * @return okOrNo instance for saying that the effect has been applied correctly
     */
    @Override
    public BonusInteraction doEffect(PlayerHandler player) {

        int i = player.getPersonalBoardReference().getVentures().size();

        player.getScore().setVictoryPoints( player.getScore().getVictoryPoints() + this.quantity*i);

        return new OkOrNo();
    }

    /**
     * Build a string for describing the effect
     *
     * @return the description's String
     */
    @Override
    public String toScreen() {
        return "Take " + quantity + " victory points for each purple card";
    }
}
