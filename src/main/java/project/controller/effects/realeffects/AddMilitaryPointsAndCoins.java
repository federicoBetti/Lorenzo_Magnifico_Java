package project.controller.effects.realeffects;

import project.messages.BonusInteraction;
import project.messages.OkOrNo;
import project.server.network.PlayerHandler;

/**
 * This class represent the AddMilitaryPointsAndCoins effect
 */
public class AddMilitaryPointsAndCoins implements Effects {

    /**
     * Perform the effect
     *
     * @param player playerHandler's reference
     * @return okOrNo instance for saying that the effect has been applied correctly
     */
    @Override
    public BonusInteraction doEffect(PlayerHandler player) {
        player.getScore().setMilitaryPoints(player.getScore().getMilitaryPoints() + 3 + player.getPersonalBoardReference().getBonusOnActions().getMilitaryPointsBonus());
        player.getPersonalBoardReference().setCoins(player.getPersonalBoardReference().getCoins() + 2 + player.getPersonalBoardReference().getBonusOnActions().getCoinsBonus());

        return new OkOrNo();
    }

    /**
     * Build a string for describing the effect
     *
     * @return the description's String
     */
    @Override
    public String toScreen(){
        return "Add 3 military points and 2 coins";
    }
}
