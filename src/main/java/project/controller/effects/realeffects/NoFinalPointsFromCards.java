package project.controller.effects.realeffects;

import project.controller.effects.effectsfactory.EffectsConstants;
import project.controller.supportfunctions.NoFinalPointsFromCharacter;
import project.controller.supportfunctions.NoFinalPointsFromTerritory;
import project.controller.supportfunctions.NoFinalPointsFromVentures;
import project.server.network.PlayerHandler;
import project.messages.BonusInteraction;
import project.messages.OkOrNo;

import java.io.Serializable;
import java.util.HashMap;

/**
 * This class represent the NoFinalPointsFromCards effect
 */
public class NoFinalPointsFromCards implements Effects, Serializable {

    private String parameter;
    int quantity;
    private HashMap<String, NoFinalPointsFromCardsBuilder> parameterEffect;

    /**
     * Constructor
     *
     * @param parameter kind of card
     */
    public NoFinalPointsFromCards(String parameter){
        this.parameter = parameter;
        parameterEffect = new HashMap<>(3);
        fillParameterEffect();
    }

    /**
     * Perform the right effect according to the parameter
     *
     * @param player playerHandler's reference
     * @return okOrNo instance for saying that the effect has been applied correctly
     */
    @Override
    public BonusInteraction doEffect(PlayerHandler player) {
        return parameterEffect.get(parameter).realEffect(player);
    }

    /**
     * Build a string for describing the effect
     *
     * @return the description's String
     */
    @Override
    public String toScreen() {
        return null;
    }

    private void fillParameterEffect() {
        parameterEffect.put(EffectsConstants.CHARACTER_CARD,this::noPointsFromCharacter);
        parameterEffect.put(EffectsConstants.VENTURES_CARD,this::noPointsFromVentures);
        parameterEffect.put(EffectsConstants.TERRITORY_CARD,this::noPointsFromTerritory);
    }

    /**
     * Perform the noPointsFromCharacter effect
     *
     * @param player playerHandler's reference
     * @return okOrNo instance for saying that the effect has been applied correctly
     */
    private BonusInteraction noPointsFromCharacter(PlayerHandler player){
        player.getRoom().setMySupportFunction(new NoFinalPointsFromCharacter(player.getRoom().getMySupportFunction(player)),player);
        return new OkOrNo();
    }

    /**
     * Perform the noPointsFromVentures effect
     *
     * @param player playerHandler's reference
     * @return okOrNo instance for saying that the effect has been applied correctly
     */
    private BonusInteraction noPointsFromVentures(PlayerHandler player){
        player.getRoom().setMySupportFunction(new NoFinalPointsFromVentures(player.getRoom().getMySupportFunction(player)),player);
        return new OkOrNo();
    }

    /**
     * Perform the noPointsFromTerritory effect
     *
     * @param player playerHandler's reference
     * @return okOrNo instance for saying that the effect has been applied correctly
     */
    private BonusInteraction noPointsFromTerritory(PlayerHandler player){
        player.getRoom().setMySupportFunction(new NoFinalPointsFromTerritory(player.getRoom().getMySupportFunction(player)),player);
        return new OkOrNo();
    }

    /**
     *
     */
    @FunctionalInterface
    private interface NoFinalPointsFromCardsBuilder{
        BonusInteraction realEffect(PlayerHandler player);
    }

}
