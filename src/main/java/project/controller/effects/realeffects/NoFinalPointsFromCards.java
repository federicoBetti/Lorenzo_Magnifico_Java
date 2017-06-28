package project.controller.effects.realeffects;

import project.controller.effects.effectsfactory.EffectsConstants;
import project.controller.supportfunctions.NoFinalPointsFromCharacter;
import project.controller.supportfunctions.NoFinalPointsFromTerritory;
import project.controller.supportfunctions.NoFinalPointsFromVentures;
import project.server.network.PlayerHandler;
import project.messages.BonusInteraction;
import project.messages.OkOrNo;

import java.util.HashMap;

/**
 * Created by federico on 26/05/17.
 */
public class NoFinalPointsFromCards implements Effects {

    private String parameter;
    int quantity;
    private HashMap<String, NoFinalPointsFromCardsBuilder> parameterEffect;

    public NoFinalPointsFromCards(String parameter){
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
        parameterEffect.put(EffectsConstants.CHARACTER_CARD,this::noPointsFromCharacter);
        parameterEffect.put(EffectsConstants.VENTURES_CARD,this::noPointsFromVentures);
        parameterEffect.put(EffectsConstants.TERRITORY_CARD,this::noPointsFromTerritory);
    }

    private BonusInteraction noPointsFromCharacter(PlayerHandler player){
        player.getRoom().setMySupportFunction(new NoFinalPointsFromCharacter(player.getRoom().getMySupportFunction(player)),player);
        return new OkOrNo();
    }

    private BonusInteraction noPointsFromVentures(PlayerHandler player){
        player.getRoom().setMySupportFunction(new NoFinalPointsFromVentures(player.getRoom().getMySupportFunction(player)),player);
        return new OkOrNo();
    }

    private BonusInteraction noPointsFromTerritory(PlayerHandler player){
        player.getRoom().setMySupportFunction(new NoFinalPointsFromTerritory(player.getRoom().getMySupportFunction(player)),player);
        return new OkOrNo();
    }


    @FunctionalInterface
    private interface NoFinalPointsFromCardsBuilder{
        BonusInteraction realEffect(PlayerHandler player);
    }

}
