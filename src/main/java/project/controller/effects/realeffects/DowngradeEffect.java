package project.controller.effects.realeffects;

import project.controller.Constants;
import project.controller.effects.effectsfactory.EffectsConstants;
import project.model.FamilyMember;
import project.server.network.PlayerHandler;
import project.messages.BonusInteraction;
import project.messages.OkOrNo;

import java.util.HashMap;

/**
 * Created by federico on 26/05/17.
 */
public class DowngradeEffect implements Effects {

    private String parameter;
    private int quantity;
    private HashMap<String, DowngradeEffectBuilder> parameterEffect;

    public DowngradeEffect(String parameter, int quantity){
        this.parameter = parameter;
        this.quantity = quantity;
        parameterEffect = new HashMap<>(4);
        fillParameterEffect();
    }

    private void fillParameterEffect() {
        parameterEffect.put(EffectsConstants.PRODUCTION,this::downgradeOnProduction);
        parameterEffect.put(EffectsConstants.HARVESTER,this::downgradeOnHarvester);
        parameterEffect.put(EffectsConstants.DICES,this::downgradeOnDices);
        parameterEffect.put(EffectsConstants.TERRITORY_CARD,this::downgradeOnTerritoryCard);
        parameterEffect.put(EffectsConstants.BUILDING_CARD,this::downgradeOnBuildingCard);
        parameterEffect.put(EffectsConstants.CHARACTER_CARD,this::downgradeOnCharacterCard);
        parameterEffect.put(EffectsConstants.VENTURES_CARD,this::downgradeOnVenturesCard);
    }

    private BonusInteraction downgradeOnProduction(PlayerHandler player) {
        player.getPersonalBoardReference().getBonusOnActions().setProductionBonus(player.getPersonalBoardReference().getBonusOnActions().getProductionBonus() - quantity);
        return new OkOrNo();
    }

    private BonusInteraction downgradeOnHarvester(PlayerHandler player) {
        player.getPersonalBoardReference().getBonusOnActions().setHarvesterBonus(player.getPersonalBoardReference().getBonusOnActions().getHarvesterBonus() - quantity);
        return new OkOrNo();
    }

    private BonusInteraction downgradeOnTerritoryCard(PlayerHandler player) {
        player.getPersonalBoardReference().getBonusOnActions().setTerritoryBonus(player.getPersonalBoardReference().getBonusOnActions().getTerritoryBonus() - quantity);
        return new OkOrNo();
    }

    private BonusInteraction downgradeOnVenturesCard(PlayerHandler player) {
        player.getPersonalBoardReference().getBonusOnActions().setVenturesBonus(player.getPersonalBoardReference().getBonusOnActions().getVenturesBonus() - quantity);
        return new OkOrNo();
    }

    private BonusInteraction downgradeOnCharacterCard(PlayerHandler player) {
        player.getPersonalBoardReference().getBonusOnActions().getCharactersBonus().setDiceBonus(player.getPersonalBoardReference().getBonusOnActions().getCharactersBonus().getDiceBonus() - quantity);
        return new OkOrNo();
    }

    private BonusInteraction downgradeOnBuildingCard(PlayerHandler player) {
        player.getPersonalBoardReference().getBonusOnActions().getBuildingsBonus().setDiceBonus(player.getPersonalBoardReference().getBonusOnActions().getBuildingsBonus().getDiceBonus() - quantity);
        return new OkOrNo();
    }

    private BonusInteraction downgradeOnDices(PlayerHandler player){
        for (FamilyMember familyMember: player.getAllFamilyMembers()){
            if (familyMember.getMyColour() != Constants.FAMILY_MEMBER_COLOUR_NEUTRAL)
                familyMember.setFixedBonus(familyMember.getFixedBonus() - quantity);
        }
        return new OkOrNo();
    }

    @Override
    public BonusInteraction doEffect(PlayerHandler player) {
        return parameterEffect.get(parameter).realEffect(player);
    }

    @FunctionalInterface
    private interface DowngradeEffectBuilder{
        BonusInteraction realEffect(PlayerHandler player);
    }
}
