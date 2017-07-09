package project.controller.effects.realeffects;

import project.controller.effects.effectsfactory.EffectsConstants;
import project.server.network.PlayerHandler;
import project.messages.BonusInteraction;
import project.messages.OkOrNo;

import java.io.Serializable;
import java.util.HashMap;

/**
 * This class represents LoseResourceEffect effects
 */
public class LoseResourceEffect implements Effects, Serializable {
    private String parameter;
    private int quantity;
    private HashMap<String, LoseResourceEffectBuilder> parameterEffect;

    /**
     * Constructor
     *
     * @param parameter kinf of resource to lose
     * @param quantity quantity
     */
    public LoseResourceEffect(String parameter, int quantity){
        this.parameter = parameter;
        this.quantity = quantity;
        parameterEffect = new HashMap<>(4);
        fillParameterEffect();
    }

    /**
     * This method fill the HashMap with the entries
     */
    private void fillParameterEffect() {
        parameterEffect.put(EffectsConstants.MILITARY_POINTS,this::loseMilitaryPoints);
        parameterEffect.put(EffectsConstants.COINS,this::loseCoins);
        parameterEffect.put(EffectsConstants.SERVANTS,this::loseServants);
        parameterEffect.put(EffectsConstants.WOOD_STONE,this::loseWoodStone);
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

    /**
     * Perform the loseMilitaryPoints effect
     *
     * @param player playerHandler's reference
     * @return okOrNo instance for saying that the effect has been applied correctly
     */
    private BonusInteraction loseMilitaryPoints(PlayerHandler player) {
        player.getPersonalBoardReference().getBonusOnActions().setMilitaryPointsBonus(player.getPersonalBoardReference().getBonusOnActions().getMilitaryPointsBonus() - quantity);
        return new OkOrNo();
    }

    /**
     * Perform the loseCoins effect
     *
     * @param player playerHandler's reference
     * @return okOrNo instance for saying that the effect has been applied correctly
     */
    private BonusInteraction loseCoins(PlayerHandler player) {
        player.getPersonalBoardReference().getBonusOnActions().setCoinsBonus(player.getPersonalBoardReference().getBonusOnActions().getCoinsBonus() - quantity);
        return new OkOrNo();
    }

    /**
     * Perform the loseServants effect
     *
     * @param player playerHandler's reference
     * @return okOrNo instance for saying that the effect has been applied correctly
     */
    private BonusInteraction loseServants(PlayerHandler player) {
        player.getPersonalBoardReference().getBonusOnActions().setServantsBonus(player.getPersonalBoardReference().getBonusOnActions().getServantsBonus() - quantity);
        return new OkOrNo();
    }

    /**
     * Perform the loseWoodStone effect
     *
     * @param player playerHandler's reference
     * @return okOrNo instance for saying that the effect has been applied correctly
     */
    private BonusInteraction loseWoodStone(PlayerHandler player) {
        player.getPersonalBoardReference().getBonusOnActions().setWoodBonus(player.getPersonalBoardReference().getBonusOnActions().getWoodBonus() - quantity);
        player.getPersonalBoardReference().getBonusOnActions().setStoneBonus(player.getPersonalBoardReference().getBonusOnActions().getStoneBonus() - quantity);
        return new OkOrNo();
    }

    /**
     * Functional interface for calling the right method
     */
    @FunctionalInterface
    private interface LoseResourceEffectBuilder{
        BonusInteraction realEffect(PlayerHandler player);
    }
}
