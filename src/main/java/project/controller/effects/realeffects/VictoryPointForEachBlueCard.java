package project.controller.effects.realeffects;

import project.messages.BonusInteraction;
import project.messages.OkOrNo;
import project.server.network.PlayerHandler;

/**
 * This class represents the VictoryPointForEachBlueCard effect
 */
public class VictoryPointForEachBlueCard implements Effects {

    private int quantity;

    /**
     * Constructor
     *
     * @param quantity of points for each card
     */
    public VictoryPointForEachBlueCard ( int quantity ){
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

        int blueCards = player.getPersonalBoardReference().getCharacters().size();
        player.getScore().setVictoryPoints( player.getScore().getVictoryPoints() + this.quantity*blueCards);

        return new OkOrNo();
    }

    /**
     * Build a string for describing the effect
     *
     * @return the description's String
     */
    @Override
    public String toScreen() {
        return "Take " + quantity + " victory points " + "for each blue card";
    }
}
