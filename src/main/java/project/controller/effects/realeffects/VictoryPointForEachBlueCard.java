package project.controller.effects.realeffects;

import project.messages.BonusInteraction;
import project.messages.OkOrNo;
import project.server.network.PlayerHandler;

/**
 * effect that give you victory points according to the number of character chard that you have
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

    @Override
    public String toScreen() {
        return "Take " + quantity + " victory points " + "for each blue card";
    }
}
