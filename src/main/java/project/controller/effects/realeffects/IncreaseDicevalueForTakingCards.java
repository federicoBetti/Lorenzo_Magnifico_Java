package project.controller.effects.realeffects;

import project.messages.BonusInteraction;
import project.messages.OkOrNo;
import project.server.network.PlayerHandler;

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
    public BonusInteraction doEffect(PlayerHandler player) {
            switch (kindOfCard){
                case"character":
                    player.getPersonalBoardReference().getBonusOnActions().getCharactersBonus().setDiceBonus(player.getPersonalBoardReference().getBonusOnActions().getCharactersBonus().getDiceBonus() + quantityIncreased);
                    break;
                case"territory":
                    player.getPersonalBoardReference().getBonusOnActions().setTerritoryBonus(player.getPersonalBoardReference().getBonusOnActions().getTerritoryBonus() + quantityIncreased);
                    break;
                case"venture":
                    player.getPersonalBoardReference().getBonusOnActions().setVenturesBonus(player.getPersonalBoardReference().getBonusOnActions().getVenturesBonus() + quantityIncreased);
                    break;
                case"building":
                    player.getPersonalBoardReference().getBonusOnActions().getBuildingsBonus().setDiceBonus(player.getPersonalBoardReference().getBonusOnActions().getBuildingsBonus().getDiceBonus() + quantityIncreased);
                    break;
                default:
                    return null;
            }

            OkOrNo ok = new OkOrNo(false);
            ok.setOk(true);
            return ok;
        }
    }
