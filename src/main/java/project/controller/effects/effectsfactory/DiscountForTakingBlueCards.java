package project.controller.effects.effectsfactory;

import project.controller.Constants;
import project.controller.effects.realeffects.Effects;
import project.messages.BonusInteraction;
import project.messages.OkOrNo;
import project.server.network.PlayerHandler;

/**
 * Created by raffaelebongo on 28/06/17.
 */
public class DiscountForTakingBlueCards implements Effects {

    private String kindOfReasourceDiscounted;
    private int quantityDiscounted;

    public DiscountForTakingBlueCards(int quantity, String parameter) {
       
        quantityDiscounted = quantity;
    }

    @Override
    public BonusInteraction doEffect(PlayerHandler player) {

        player.getPersonalBoardReference().getBonusOnActions().getCharactersBonus().setCoinsBonus(player.getPersonalBoardReference().getBonusOnActions().getCharactersBonus().getCoinsBonus() + quantityDiscounted);

        return new OkOrNo();
    }
}
