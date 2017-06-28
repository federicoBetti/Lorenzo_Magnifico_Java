package project.controller.effects.realeffects;

import project.controller.Constants;
import project.messages.BonusInteraction;
import project.messages.OkOrNo;
import project.server.network.PlayerHandler;

/**
 * Created by raffaelebongo on 10/05/17.
 */

//todo fare con HashMap
public class DiscountForTakingYellowCards implements Effects {

    private String kindOfReasourceDiscounted;
    private int quantityDiscounted;

    public DiscountForTakingYellowCards(int quantity, String parameter1 ) {
        this.kindOfReasourceDiscounted = parameter1;
        this.quantityDiscounted = quantity;

    }

    @Override
    public BonusInteraction doEffect(PlayerHandler player) {

        if(kindOfReasourceDiscounted.equals(Constants.STONE))
            player.getPersonalBoardReference().getBonusOnActions().getBuildingsBonus().setStoneBonus(player.getPersonalBoardReference().getBonusOnActions().getBuildingsBonus().getStoneBonus() + quantityDiscounted);

        else if (kindOfReasourceDiscounted.equals(Constants.WOOD))
            player.getPersonalBoardReference().getBonusOnActions().getBuildingsBonus().setWoodBonus(player.getPersonalBoardReference().getBonusOnActions().getBuildingsBonus().getWoodBonus() + quantityDiscounted);

        return new OkOrNo();
    }
}
