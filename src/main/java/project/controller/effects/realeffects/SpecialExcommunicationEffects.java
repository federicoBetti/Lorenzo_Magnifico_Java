package project.controller.effects.realeffects;

import project.controller.checkfunctions.CantPlaceFamiliarInMarket;
import project.controller.checkfunctions.DoubleServantsValue;
import project.controller.effects.effectsfactory.EffectsConstants;
import project.controller.supportfunctions.DoubleServantsPayment;
import project.server.network.PlayerHandler;
import project.messages.BonusInteraction;
import project.messages.OkOrNo;

import java.io.Serializable;
import java.util.HashMap;

/**
 * This class represent the SpecialExcommunicationEffects effects
 */
public class SpecialExcommunicationEffects implements Effects, Serializable {
    private String parameter;
    int quantity;
    private HashMap<String, SpecialExcommunicationEffectsBuilder> parameterEffect;

    /**
     * Constructor
     *
     * @param parameter sparameter for finding the correct effect
     */
    public SpecialExcommunicationEffects(String parameter){
        this.parameter = parameter;
        parameterEffect = new HashMap<>(3);
        fillParameterEffect();
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
        parameterEffect.put(EffectsConstants.MARKET,this::cantPlaceFamiliarInMarket);
        parameterEffect.put(EffectsConstants.SERVANTS,this::doubleServantsValue);
        parameterEffect.put(EffectsConstants.TURN,this::skipMyFirstTurn);
    }

    /**
     * Perform the effect
     *
     * @param player playerHandler's reference
     * @return okOrNo instance for saying that the effect has been applied correctly
     */
    private BonusInteraction skipMyFirstTurn (PlayerHandler player){
        player.getRoom().getBoard().getTurn().addSkipTurn(player);
        return new OkOrNo();
    }

    /**
     * Perform the right effect
     *
     * @param player playerHandler's reference
     * @return okOrNo instance for saying that the effect has been applied correctly
     */
    private BonusInteraction doubleServantsValue(PlayerHandler player) { //ho decorato sia la check sia la support function
        player.setCheckFunctions(new DoubleServantsValue(player.getCheckFunctions()));
        player.getRoom().setMySupportFunction(new DoubleServantsPayment(player.getRoom().getMySupportFunction(player)),player);
        return new OkOrNo();
    }

    /**
     * Perform the right effect
     *
     * @param player playerHandler's reference
     * @return okOrNo instance for saying that the effect has been applied correctly
     */
    private BonusInteraction cantPlaceFamiliarInMarket(PlayerHandler player){
        player.setCheckFunctions(new CantPlaceFamiliarInMarket(player.getCheckFunctions()));
        return new OkOrNo();
    }

    /**
     * The functional interface for calls the right method
     */
    @FunctionalInterface
    private interface SpecialExcommunicationEffectsBuilder{
        BonusInteraction realEffect(PlayerHandler player);
    }


    /**
     * Perform the right effect
     *
     * @param player playerHandler's reference
     * @return okOrNo instance for saying that the effect has been applied correctly
     */
    @Override
    public BonusInteraction doEffect(PlayerHandler player) {
        return parameterEffect.get(parameter).realEffect(player);
    }



}
