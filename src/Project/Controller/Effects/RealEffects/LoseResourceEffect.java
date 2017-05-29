package Project.Controller.Effects.RealEffects;

import Project.Controller.Effects.EffectsFactory.EffectsConstants;
import Project.Server.Network.PlayerHandler;
import Project.Messages.BonusInteraction;
import Project.Messages.OkOrNo;

import java.util.HashMap;

/**
 * class for excommunication effect
 */
public class LoseResourceEffect implements Effects {
    String parameter;
    int quantity;
    private HashMap<String, LoseResourceEffectBuilder> parameterEffect;
    
    public LoseResourceEffect(String parameter, int quantity){
        this.parameter = parameter;
        this.quantity = quantity;
        parameterEffect = new HashMap<>(4);
        fillParameterEffect();
    }

    @Override
    public BonusInteraction doEffect(PlayerHandler player) {
        return parameterEffect.get(parameter).realEffect(player);
    }

    private void fillParameterEffect() {
        parameterEffect.put(EffectsConstants.MILITARY_POINTS,this::loseMilitaryPoints);
        parameterEffect.put(EffectsConstants.COINS,this::loseCoins);
        parameterEffect.put(EffectsConstants.SERVANTS,this::loseServants);
        parameterEffect.put(EffectsConstants.WOOD_STONE,this::loseWoodStone);
    }

    private BonusInteraction loseMilitaryPoints(PlayerHandler player) {
        player.getPersonalBoardReference().getBonusOnActions().setMilitaryPointsBonus(player.getPersonalBoardReference().getBonusOnActions().getMilitaryPointsBonus() - quantity);
        return new OkOrNo(true);
    }

    private BonusInteraction loseCoins(PlayerHandler player) {
        player.getPersonalBoardReference().getBonusOnActions().setCoinsBonus(player.getPersonalBoardReference().getBonusOnActions().getCoinsBonus() - quantity);
        return new OkOrNo(true);
    }

    private BonusInteraction loseServants(PlayerHandler player) {
        player.getPersonalBoardReference().getBonusOnActions().setServantsBonus(player.getPersonalBoardReference().getBonusOnActions().getServantsBonus() - quantity);
        return new OkOrNo(true);
    }
    
    private BonusInteraction loseWoodStone(PlayerHandler player) {
        player.getPersonalBoardReference().getBonusOnActions().setWoodBonus(player.getPersonalBoardReference().getBonusOnActions().getWoodBonus() - quantity);
        player.getPersonalBoardReference().getBonusOnActions().setStoneBonus(player.getPersonalBoardReference().getBonusOnActions().getStoneBonus() - quantity);
        return new OkOrNo(true);
    }


    private interface LoseResourceEffectBuilder{
        BonusInteraction realEffect(PlayerHandler player);
    }
}
