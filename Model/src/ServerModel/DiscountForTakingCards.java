package ServerModel;

/**
 * Created by raffaelebongo on 10/05/17.
 */
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
    public BonusInteraction doEffect(Player player) {

        switch (kindOfCardDiscounted){
            case"buildings":
            switch(kindOfReasourceDiscounted){
                case"stone":
                    player.getPersonalBoardReference().getBonusOnActions().getBuildingsBonus().setStoneBonus(player.getPersonalBoardReference().getBonusOnActions().getBuildingsBonus().getStoneBonus() + quantityDiscounted);

                case"wood":
                    player.getPersonalBoardReference().getBonusOnActions().getBuildingsBonus().setWoodBonus(player.getPersonalBoardReference().getBonusOnActions().getBuildingsBonus().getWoodBonus() + quantityDiscounted);

            }
            case"characters":
                player.getPersonalBoardReference().getBonusOnActions().getCharactersBonus().setCoinsBonus(player.getPersonalBoardReference().getBonusOnActions().getCharactersBonus().getCoinsBonus() + quantityDiscounted);

            case "ventures":

        }

        OkOrNo Ok = new OkOrNo();
        Ok.setOk(true);
        return Ok;
    }
}
