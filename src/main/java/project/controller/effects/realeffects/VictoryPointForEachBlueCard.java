package project.controller.effects.realeffects;

import project.messages.BonusInteraction;
import project.messages.OkOrNo;
import project.server.network.PlayerHandler;

/**
 * Created by raffaelebongo on 10/05/17.
 */
public class VictoryPointForEachBlueCard implements Effects {

    private int quantity;
    public VictoryPointForEachBlueCard ( int quantity ){
        this.quantity = quantity;
    }

    @Override
    public BonusInteraction doEffect(PlayerHandler player) {

        int blueCards = player.getPersonalBoardReference().getCharacters().size();
        player.getScore().setVictoryPoints( player.getScore().getVictoryPoints() + this.quantity*blueCards);

        return new OkOrNo();
    }
}
