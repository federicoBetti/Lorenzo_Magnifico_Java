package project.controller.effects.realeffects;

import project.messages.BonusInteraction;
import project.messages.OkOrNo;
import project.server.network.PlayerHandler;

import java.io.Serializable;

/**
 * This class represents IncreaseDicevalueForTakingCards effect
 */
public class IncreaseDicevalueForTakingCards implements Effects, Serializable {

    private int quantityIncreased;
    private String kindOfCard;

    /**
     * Constructor
     *
     * @param quantity   increased
     * @param parameter1 kind of card taking value increased
     */
    public IncreaseDicevalueForTakingCards(int quantity, String parameter1) {
        this.quantityIncreased = quantity;
        this.kindOfCard = parameter1;
    }

    /**
     * Perform the right effect according to the parameter
     *
     * @param player playerHandler's reference
     * @return okOrNo instance for saying that the effect has been applied correctly
     */
    @Override
    public BonusInteraction doEffect(PlayerHandler player) {
        switch (kindOfCard) {
            case "blue":
                player.getPersonalBoardReference().getBonusOnActions().getCharactersBonus().setDiceBonus(player.getPersonalBoardReference().getBonusOnActions().getCharactersBonus().getDiceBonus() + quantityIncreased);
                break;
            case "green":
                player.getPersonalBoardReference().getBonusOnActions().setTerritoryBonus(player.getPersonalBoardReference().getBonusOnActions().getTerritoryBonus() + quantityIncreased);
                break;
            case "purple":
                player.getPersonalBoardReference().getBonusOnActions().setVenturesBonus(player.getPersonalBoardReference().getBonusOnActions().getVenturesBonus() + quantityIncreased);
                break;
            case "yellow":
                player.getPersonalBoardReference().getBonusOnActions().getBuildingsBonus().setDiceBonus(player.getPersonalBoardReference().getBonusOnActions().getBuildingsBonus().getDiceBonus() + quantityIncreased);
                break;
            default:
                return null;
        }

        return new OkOrNo();
    }

    /**
     * Build a string for describing the effect
     *
     * @return the description's String
     */
    @Override
    public String toScreen() {
        return "Increase dice value for taking " + kindOfCard + " card of " + quantityIncreased;
    }
}
