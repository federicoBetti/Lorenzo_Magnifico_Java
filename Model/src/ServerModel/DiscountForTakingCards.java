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
            case"characters":
            switch(kindOfReasourceDiscounted){
                case"stone":
                    player.getPersonalBoardReference().getBonusOnActions().getCharactersBonus().setStoneBonus(player.getPersonalBoardReference().getBonusOnActions().getCharactersBonus().getStoneBonus() + quantityDiscounted);

                case"wood":
                    player.getPersonalBoardReference().getBonusOnActions().getCharactersBonus().setWoodBonus(player.getPersonalBoardReference().getBonusOnActions().getCharactersBonus().getWoodBonus() + quantityDiscounted);

            }
            case"buildings":
                player.getPersonalBoardReference().getBonusOnActions().getBuildingsBonus().setCoinsBonus(player.getPersonalBoardReference().getBonusOnActions().getBuildingsBonus().getCoinsBonus() + quantityDiscounted);

        }

        OkOrNo Ok = new OkOrNo();
        Ok.setOk(true);
        return Ok;
    }
}
