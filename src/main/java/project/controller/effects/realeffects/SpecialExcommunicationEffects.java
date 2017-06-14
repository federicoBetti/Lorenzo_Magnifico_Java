package project.controller.effects.realeffects;

import project.controller.checkfunctions.CantPlaceFamiliarInMarket;
import project.controller.checkfunctions.DoubleServantsValue;
import project.controller.effects.effectsfactory.EffectsConstants;
import project.controller.supportfunctions.DoubleServantsPayment;
import project.server.network.PlayerHandler;
import project.messages.BonusInteraction;
import project.messages.OkOrNo;

import java.util.HashMap;

/**
 * Created by federico on 26/05/17.
 */
public class SpecialExcommunicationEffects implements Effects {
    private String parameter;
    int quantity;
    private HashMap<String, SpecialExcommunicationEffectsBuilder> parameterEffect;

    public SpecialExcommunicationEffects(String parameter){
        this.parameter = parameter;
        parameterEffect = new HashMap<>(3);
        fillParameterEffect();
    }

    @Override
    public BonusInteraction doEffect(PlayerHandler player) {
        return parameterEffect.get(parameter).realEffect(player);
    }

    private void fillParameterEffect() {
        parameterEffect.put(EffectsConstants.MARKET,this::cantPlaceFamiliarInMarket);
        parameterEffect.put(EffectsConstants.SERVANTS,this::doubleServantsValue);
        parameterEffect.put(EffectsConstants.TURN,this::skipMyFirstTurn);
    }

    private BonusInteraction skipMyFirstTurn (PlayerHandler player){

        //TODO
        return new OkOrNo();
    }
    private BonusInteraction doubleServantsValue(PlayerHandler player) { //ho decorato sia la check sia la support function
        player.setCheckFunctions(new DoubleServantsValue(player.getCheckFunctions()));
        player.getRoom().setMySupportFunction(new DoubleServantsPayment(player.getRoom().getMySupportFunction(player)),player);
        return new OkOrNo();
    }

    private BonusInteraction cantPlaceFamiliarInMarket(PlayerHandler player){
        player.setCheckFunctions(new CantPlaceFamiliarInMarket(player.getCheckFunctions()));
        return new OkOrNo();
    }


    @FunctionalInterface
    private interface SpecialExcommunicationEffectsBuilder{
        BonusInteraction realEffect(PlayerHandler player);
    }


}
