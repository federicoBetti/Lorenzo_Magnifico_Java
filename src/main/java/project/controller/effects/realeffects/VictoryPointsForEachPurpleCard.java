package project.controller.effects.realeffects;

import project.messages.BonusInteraction;
import project.messages.OkOrNo;
import project.server.network.PlayerHandler;

/**
 * Created by raffaelebongo on 10/05/17.
 */
public class VictoryPointsForEachPurpleCard implements Effects {

    private int quantity;
    public VictoryPointsForEachPurpleCard( int quantity ){
        this.quantity = quantity;
    }


    @Override
    public BonusInteraction doEffect(PlayerHandler player) {

        int i = 0;
        while ( player.getPersonalBoardReference().getVentures().get(i) != null ) {
            i++;
        }

        player.getScore().setVictoryPoints( player.getScore().getVictoryPoints() + this.quantity*i);

        return new OkOrNo();
    }

    @Override
    public String toScreen() {
        return "Take " + quantity + " victory points for each purple card.";
    }
}
