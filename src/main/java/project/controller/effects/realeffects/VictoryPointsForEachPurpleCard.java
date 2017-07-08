package project.controller.effects.realeffects;

import project.messages.BonusInteraction;
import project.messages.OkOrNo;
import project.server.network.PlayerHandler;

/**
 *  effect that give you victory points according to the number of ventures card that you have
 */
public class VictoryPointsForEachPurpleCard implements Effects {

    private int quantity;
    public VictoryPointsForEachPurpleCard( int quantity ){
        this.quantity = quantity;
    }


    @Override
    public BonusInteraction doEffect(PlayerHandler player) {

        int i = player.getPersonalBoardReference().getVentures().size();

        player.getScore().setVictoryPoints( player.getScore().getVictoryPoints() + this.quantity*i);

        return new OkOrNo();
    }

    @Override
    public String toScreen() {
        return "Take " + quantity + " victory points for each purple card";
    }
}
