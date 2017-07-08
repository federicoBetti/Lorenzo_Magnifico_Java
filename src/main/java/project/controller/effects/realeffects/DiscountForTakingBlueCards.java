package project.controller.effects.realeffects;

import project.controller.effects.realeffects.Effects;
import project.messages.BonusInteraction;
import project.messages.OkOrNo;
import project.server.network.PlayerHandler;

/**
 * effect that apply a discount on taking character card
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

    @Override
    public String toScreen() {
        return "You have a discount of " + quantityDiscounted + " coin for taking blue cards";
    }
}
