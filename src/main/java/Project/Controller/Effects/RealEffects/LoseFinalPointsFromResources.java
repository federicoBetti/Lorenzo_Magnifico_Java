package Project.Controller.Effects.RealEffects;

import Project.Controller.Effects.EffectsFactory.EffectsConstants;
import Project.Controller.SupportFunctions.*;
import Project.Server.Network.PlayerHandler;
import Project.Messages.BonusInteraction;
import Project.Messages.OkOrNo;

import java.util.HashMap;

/**
 * Created by federico on 26/05/17.
 */
public class LoseFinalPointsFromResources implements Effects {


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

    private void fillParameterEffect() {
        parameterEffect.put(EffectsConstants.VICTORY_POINTS,this::loseFinalPointsEveryFiveVictory);
        parameterEffect.put(EffectsConstants.MILITARY_POINTS,this::loseFinalPointsEveryMilitaryPoints);
        parameterEffect.put(EffectsConstants.WOOD_STONE,this::loseFinalPointsEveryWoodStone);
        parameterEffect.put(EffectsConstants.ALL_RESOURCES,this::loseFinalPointsEveryResources);
    }

    private BonusInteraction loseFinalPointsEveryFiveVictory(PlayerHandler player){
        player.getRoom().setMySupportFunction(new LoseFinalPointsEveryFive(player.getRoom().getMySupportFunction(player)),player);
        return new OkOrNo(true);
    }

    private BonusInteraction loseFinalPointsEveryMilitaryPoints(PlayerHandler player){
        player.getRoom().setMySupportFunction(new LoseFinalPointsEveryMilitary(player.getRoom().getMySupportFunction(player)),player);
        return new OkOrNo(true);
    }

    private BonusInteraction loseFinalPointsEveryWoodStone(PlayerHandler player){
        player.getRoom().setMySupportFunction(new LoseFinalPointsEveryWS(player.getRoom().getMySupportFunction(player)),player);
        return new OkOrNo(true);
    }

    private BonusInteraction loseFinalPointsEveryResources(PlayerHandler player){
        player.getRoom().setMySupportFunction(new LoseFinalPointsEveryResources(player.getRoom().getMySupportFunction(player)),player);
        return new OkOrNo(true);
    }


    private interface LoseFinalPointsFromResourcesBuilder{
        BonusInteraction realEffect(PlayerHandler player);
    }

}
