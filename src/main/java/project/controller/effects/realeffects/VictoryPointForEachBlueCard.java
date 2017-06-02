package project.controller.effects.realeffects;

import project.messages.BonusInteraction;
import project.messages.OkOrNo;
import project.server.network.PlayerHandler;

/**
 * Created by raffaelebongo on 10/05/17.
 */
public class VictoryPointForEachBlueCard implements Effects {

    int quantity;
    public VictoryPointForEachBlueCard ( int quantity ){
        this.quantity = quantity;
    }

    @Override
    public BonusInteraction doEffect(PlayerHandler player) {

        int i = 0;
        while ( player.getPersonalBoardReference().getCharacters().get(i) != null ) {
            i++;
        }

        player.getScore().setVictoryPoints( player.getScore().getVictoryPoints() + this.quantity*i);

        OkOrNo ok = new OkOrNo(false);
        ok.setOk(true);
        return ok;
    }
}
