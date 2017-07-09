package project.controller.effects.realeffects;

import project.messages.BonusInteraction;
import project.messages.OkOrNo;
import project.server.network.PlayerHandler;

/**
 * Created by raffaelebongo on 17/06/17.
 */
public class AddMilitaryPointsAndCoins implements Effects {
    @Override
    public BonusInteraction doEffect(PlayerHandler player) {
        player.getScore().setMilitaryPoints(player.getScore().getMilitaryPoints() + 3 + player.getPersonalBoardReference().getBonusOnActions().getMilitaryPointsBonus());
        player.getPersonalBoardReference().setCoins(player.getPersonalBoardReference().getCoins() + 2 + player.getPersonalBoardReference().getBonusOnActions().getCoinsBonus());

        return new OkOrNo();
    }

    @Override
    public String toScreen(){
        return "Add 3 military points and 2 coins";
    }
}
