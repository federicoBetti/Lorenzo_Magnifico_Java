package ServerModel;

import org.omg.CosNaming.NamingContextExtPackage.StringNameHelper;

/**
 * Created by raffaelebongo on 10/05/17.
 */
public class IncreaseDicevalueForTakingCards implements Effects {

    int quantityIncreased;
    String kindOfCard;

    public IncreaseDicevalueForTakingCards(int quantity, String parameter1) {
        this.quantityIncreased = quantity;
        this.kindOfCard = parameter1;
    }

    @Override
    public BonusInteraction doEffect(Player player) {

        switch (kindOfCard){
            case"character":
                player.getPersonalBoardReference().getBonusOnActions().getCharactersBonus(player.getPersonalBoardReference().getBonusOnActions().getCharactersBonus() + quantityIncreased);

            case"territory":
                player.getPersonalBoardReference().getBonusOnActions().setTerritoryBonus(player.getPersonalBoardReference().getBonusOnActions().getTerritoryBonus() + quantityIncreased);

            case"venture":
                player.getPersonalBoardReference().getBonusOnActions().setVenturesBonus(player.getPersonalBoardReference().getBonusOnActions().getVenturesBonus() + quantityIncreased);

            case"building":
                player.getPersonalBoardReference().getBonusOnActions().setBuildingsBonus(player.getPersonalBoardReference().getBonusOnActions().getBuildingsBonus() + quantityIncreased);
        }

        OkOrNo Ok = new OkOrNo();
        Ok.setOk(true);
        return Ok;
    }
}
