package Project.Controller.Effects.RealEffects;

import Project.Controller.CheckFunctions.CantPlaceFamiliarInMarket;
import Project.Controller.CheckFunctions.DoubleServantsValue;
import Project.Controller.Effects.EffectsFactory.EffectsConstants;
import Project.Controller.SupportFunctions.DoubleServantsPayment;
import Project.Server.Network.PlayerHandler;
import Project.toDelete.BonusInteraction;
import Project.toDelete.OkOrNo;

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
        parameterEffect = new HashMap<String, SpecialExcommunicationEffectsBuilder>(3);
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
        return new OkOrNo(true);
    }
    private BonusInteraction doubleServantsValue(PlayerHandler player) { //ho decorato sia la check sia la support function
        player.setCheckFunctions(new DoubleServantsValue(player.getCheckFunctions()));
        player.getRoom().setMySupportFunction(new DoubleServantsPayment(player.getRoom().getMySupportFunction(player)),player);
        return new OkOrNo(true);
    }

    private BonusInteraction cantPlaceFamiliarInMarket(PlayerHandler player){
        player.setCheckFunctions(new CantPlaceFamiliarInMarket(player.getCheckFunctions()));
        return new OkOrNo(true);
    }


    private interface SpecialExcommunicationEffectsBuilder{
        BonusInteraction realEffect(PlayerHandler player);
    }


}
