package project.controller.effects.realeffects;

import project.controller.effects.effectsfactory.EffectsConstants;
import project.controller.supportfunctions.*;
import project.server.network.PlayerHandler;
import project.messages.BonusInteraction;
import project.messages.OkOrNo;

import java.io.Serializable;
import java.util.HashMap;

/**
 * This class represents LoseFinalPointsFromResources effects
 */
public class LoseFinalPointsFromResources implements Effects, Serializable {


    private String effectDescription;
    private String parameter;
    int quantity;
    private HashMap<String, LoseFinalPointsFromResourcesBuilder> parameterEffect;

    /**
     * Constructor
     *
     * @param parameter
     */
    public LoseFinalPointsFromResources(String parameter){
        this.parameter = parameter;
        parameterEffect = new HashMap<>(3);
        fillParameterEffect();
    }

    /**
     * This method fill the HashMap with the entries
     */
    private void fillParameterEffect() {
        parameterEffect.put(EffectsConstants.VICTORY_POINTS,this::loseFinalPointsEveryFiveVictory);
        parameterEffect.put(EffectsConstants.MILITARY_POINTS,this::loseFinalPointsEveryMilitaryPoints);
        parameterEffect.put(EffectsConstants.WOOD_STONE,this::loseFinalPointsEveryWoodStone);
        parameterEffect.put(EffectsConstants.ALL_RESOURCES,this::loseFinalPointsEveryResources);
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
     * Perform the loseFinalPointsEveryFiveVictory effect
     *
     * @param player playerHandler's reference
     * @return okOrNo instance for saying that the effect has been applied correctly
     */
    private BonusInteraction loseFinalPointsEveryFiveVictory(PlayerHandler player){
        player.getRoom().setMySupportFunction(new LoseFinalPointsEveryFive(player.getRoom().getMySupportFunction(player)),player);
        return new OkOrNo();
    }

    /**
     * Perform the loseFinalPointsEveryMilitaryPoints effect
     *
     * @param player playerHandler's reference
     * @return okOrNo instance for saying that the effect has been applied correctly
     */
    private BonusInteraction loseFinalPointsEveryMilitaryPoints(PlayerHandler player){
        player.getRoom().setMySupportFunction(new LoseFinalPointsEveryMilitary(player.getRoom().getMySupportFunction(player)),player);
        return new OkOrNo();
    }

    /**
     * Perform the loseFinalPointsEveryWoodStone effect
     *
     * @param player playerHandler's reference
     * @return okOrNo instance for saying that the effect has been applied correctly
     */
    private BonusInteraction loseFinalPointsEveryWoodStone(PlayerHandler player){
        player.getRoom().setMySupportFunction(new LoseFinalPointsEveryWS(player.getRoom().getMySupportFunction(player)),player);
        return new OkOrNo();
    }

    /**
     * Perform the loseFinalPointsEveryResources effect
     *
     * @param player playerHandler's reference
     * @return okOrNo instance for saying that the effect has been applied correctly
     */
    private BonusInteraction loseFinalPointsEveryResources(PlayerHandler player){
        player.getRoom().setMySupportFunction(new LoseFinalPointsEveryResources(player.getRoom().getMySupportFunction(player)),player);
        return new OkOrNo();
    }

    /**
     * Functional interface for calling the right method
     */
    @FunctionalInterface
    private interface LoseFinalPointsFromResourcesBuilder{
        BonusInteraction realEffect(PlayerHandler player);
    }

}
