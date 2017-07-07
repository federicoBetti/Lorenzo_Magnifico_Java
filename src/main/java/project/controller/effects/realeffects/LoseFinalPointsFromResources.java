package project.controller.effects.realeffects;

import project.controller.effects.effectsfactory.EffectsConstants;
import project.controller.supportfunctions.*;
import project.server.network.PlayerHandler;
import project.messages.BonusInteraction;
import project.messages.OkOrNo;

import java.io.Serializable;
import java.util.HashMap;

/**
 * excommunication effect that don't let you to take victory points from some resources
 */
public class LoseFinalPointsFromResources implements Effects, Serializable {


    private String effectDescription;
    private String parameter;
    int quantity;
    private HashMap<String, LoseFinalPointsFromResourcesBuilder> parameterEffect;

    public LoseFinalPointsFromResources(String parameter){
        this.parameter = parameter;
        parameterEffect = new HashMap<>(3);
        fillParameterEffect();
    }

    @Override
    public BonusInteraction doEffect(PlayerHandler player) {
        return parameterEffect.get(parameter).realEffect(player);
    }

    @Override
    public String toScreen() {
        return null;
    }

    private void fillParameterEffect() {
        parameterEffect.put(EffectsConstants.VICTORY_POINTS,this::loseFinalPointsEveryFiveVictory);
        parameterEffect.put(EffectsConstants.MILITARY_POINTS,this::loseFinalPointsEveryMilitaryPoints);
        parameterEffect.put(EffectsConstants.WOOD_STONE,this::loseFinalPointsEveryWoodStone);
        parameterEffect.put(EffectsConstants.ALL_RESOURCES,this::loseFinalPointsEveryResources);
    }

    private BonusInteraction loseFinalPointsEveryFiveVictory(PlayerHandler player){
        player.getRoom().setMySupportFunction(new LoseFinalPointsEveryFive(player.getRoom().getMySupportFunction(player)),player);
        return new OkOrNo();
    }

    private BonusInteraction loseFinalPointsEveryMilitaryPoints(PlayerHandler player){
        player.getRoom().setMySupportFunction(new LoseFinalPointsEveryMilitary(player.getRoom().getMySupportFunction(player)),player);
        return new OkOrNo();
    }

    private BonusInteraction loseFinalPointsEveryWoodStone(PlayerHandler player){
        player.getRoom().setMySupportFunction(new LoseFinalPointsEveryWS(player.getRoom().getMySupportFunction(player)),player);
        return new OkOrNo();
    }

    private BonusInteraction loseFinalPointsEveryResources(PlayerHandler player){
        player.getRoom().setMySupportFunction(new LoseFinalPointsEveryResources(player.getRoom().getMySupportFunction(player)),player);
        return new OkOrNo();
    }


    @FunctionalInterface
    private interface LoseFinalPointsFromResourcesBuilder{
        BonusInteraction realEffect(PlayerHandler player);
    }

}
