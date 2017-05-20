package Project.Controller.Effects.RealEffects;

import Project.toDelete.BonusInteraction;
import Project.toDelete.OkOrNo;
import Project.MODEL.Player;

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
                player.getPersonalBoardReference().getBonusOnActions().getCharactersBonus().setDiceBonus(player.getPersonalBoardReference().getBonusOnActions().getCharactersBonus().getDiceBonus() + quantityIncreased);

            case"territory":
                player.getPersonalBoardReference().getBonusOnActions().setTerritoryBonus(player.getPersonalBoardReference().getBonusOnActions().getTerritoryBonus() + quantityIncreased);

            case"venture":
                player.getPersonalBoardReference().getBonusOnActions().setVenturesBonus(player.getPersonalBoardReference().getBonusOnActions().getVenturesBonus() + quantityIncreased);

            case"building":
                player.getPersonalBoardReference().getBonusOnActions().getBuildingsBonus().setDiceValue(player.getPersonalBoardReference().getBonusOnActions().getBuildingsBonus().getDiceValue() + quantityIncreased);
        }

        OkOrNo Ok = new OkOrNo();
        Ok.setOk(true);
        return Ok;
    }
}
