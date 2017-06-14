package project.controller.effects.realeffects;

import project.messages.BonusInteraction;
import project.messages.OkOrNo;
import project.server.network.PlayerHandler;

/**
 * Created by raffaelebongo on 10/05/17.
 */

//todo fare con HashMap
public class DiscountForTakingCards implements Effects {

    String kindOfReasourceDiscounted;
    String kindOfCardDiscounted;
    int quantityDiscounted;

    public DiscountForTakingCards(int quantity, String parameter1, String parameter2 ) {
        this.kindOfReasourceDiscounted = parameter1;
        this.quantityDiscounted = quantity;
        this.kindOfCardDiscounted = parameter2;
    }

    @Override
    public BonusInteraction doEffect(PlayerHandler player) {
        switch (kindOfCardDiscounted){
            case"buildings":
                switch(kindOfReasourceDiscounted) {
                    case "stone":
                        player.getPersonalBoardReference().getBonusOnActions().getBuildingsBonus().setStoneBonus(player.getPersonalBoardReference().getBonusOnActions().getBuildingsBonus().getStoneBonus() + quantityDiscounted);
                        break;
                    case "wood":
                        player.getPersonalBoardReference().getBonusOnActions().getBuildingsBonus().setWoodBonus(player.getPersonalBoardReference().getBonusOnActions().getBuildingsBonus().getWoodBonus() + quantityDiscounted);
                        break;
                    default:
                        return  null;
                }

            case"characters":
                player.getPersonalBoardReference().getBonusOnActions().getCharactersBonus().setCoinsBonus(player.getPersonalBoardReference().getBonusOnActions().getCharactersBonus().getCoinsBonus() + quantityDiscounted);
                break;

            default:
                return null;
        }

        return new OkOrNo();
    }
}
