package Project.Controller.Effects.RealEffects;

import Project.Controller.Effects.EffectsFactory.EffectsConstants;
import Project.Controller.SupportFunctions.NoFinalPointsFromCharacter;
import Project.Controller.SupportFunctions.NoFinalPointsFromTerritory;
import Project.Controller.SupportFunctions.NoFinalPointsFromVentures;
import Project.Server.Network.PlayerHandler;
import Project.Messages.BonusInteraction;
import Project.Messages.OkOrNo;

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

    private void fillParameterEffect() {
        parameterEffect.put(EffectsConstants.CHARACTER_CARD,this::noPointsFromCharacter);
        parameterEffect.put(EffectsConstants.VENTURES_CARD,this::noPointsFromVentures);
        parameterEffect.put(EffectsConstants.TERRITORY_CARD,this::noPointsFromTerritory);
    }

    private BonusInteraction noPointsFromCharacter(PlayerHandler player){
        player.getRoom().setMySupportFunction(new NoFinalPointsFromCharacter(player.getRoom().getMySupportFunction(player)),player);
        return new OkOrNo(true);
    }

    private BonusInteraction noPointsFromVentures(PlayerHandler player){
        player.getRoom().setMySupportFunction(new NoFinalPointsFromVentures(player.getRoom().getMySupportFunction(player)),player);
        return new OkOrNo(true);
    }

    private BonusInteraction noPointsFromTerritory(PlayerHandler player){
        player.getRoom().setMySupportFunction(new NoFinalPointsFromTerritory(player.getRoom().getMySupportFunction(player)),player);
        return new OkOrNo(true);
    }


    private interface NoFinalPointsFromCardsBuilder{
        BonusInteraction realEffect(PlayerHandler player);
    }

}
