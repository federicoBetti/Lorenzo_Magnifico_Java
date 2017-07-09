package project.controller.effects.realeffects;

import project.controller.Constants;
import project.controller.effects.effectsfactory.EffectsConstants;
import project.model.FamilyMember;
import project.server.network.PlayerHandler;
import project.messages.BonusInteraction;
import project.messages.OkOrNo;

import java.io.Serializable;
import java.util.HashMap;

/**
 * This class is used to apply excommunication downgrade type's effect
 */
public class DowngradeEffect implements Effects, Serializable {

    private String parameter;
    private int quantity;
    private HashMap<String, DowngradeEffectBuilder> parameterEffect;

    /**
     * Constructor
     *
     * @param parameter kind of action downgraded
     * @param quantity
     */
    public DowngradeEffect(String parameter, int quantity){
        this.parameter = parameter;
        this.quantity = quantity;
        parameterEffect = new HashMap<>(4);
        fillParameterEffect();
    }

    /**
     * This method is used for filling the HashMap
     */
    private void fillParameterEffect() {
        parameterEffect.put(EffectsConstants.PRODUCTION,this::downgradeOnProduction);
        parameterEffect.put(EffectsConstants.HARVESTER,this::downgradeOnHarvester);
        parameterEffect.put(EffectsConstants.DICES,this::downgradeOnDices);
        parameterEffect.put(EffectsConstants.TERRITORY_CARD,this::downgradeOnTerritoryCard);
        parameterEffect.put(EffectsConstants.BUILDING_CARD,this::downgradeOnBuildingCard);
        parameterEffect.put(EffectsConstants.CHARACTER_CARD,this::downgradeOnCharacterCard);
        parameterEffect.put(EffectsConstants.VENTURES_CARD,this::downgradeOnVenturesCard);
    }

    /**
     * Perform the downgradeOnProduction effect
     *
     * @param player playerHandler's reference
     * @return okOrNo instance for saying that the effect has been applied correctly
     */
    private BonusInteraction downgradeOnProduction(PlayerHandler player) {
        player.getPersonalBoardReference().getBonusOnActions().setProductionBonus(player.getPersonalBoardReference().getBonusOnActions().getProductionBonus() - quantity);
        return new OkOrNo();
    }

    /**
     * Perform the downgradeOnHarvester effect
     *
     * @param player playerHandler's reference
     * @return okOrNo instance for saying that the effect has been applied correctly
     */
    private BonusInteraction downgradeOnHarvester(PlayerHandler player) {
        player.getPersonalBoardReference().getBonusOnActions().setHarvesterBonus(player.getPersonalBoardReference().getBonusOnActions().getHarvesterBonus() - quantity);
        return new OkOrNo();
    }

    /**
     * Perform the downgradeOnTerritoryCard effect
     *
     * @param player playerHandler's reference
     * @return okOrNo instance for saying that the effect has been applied correctly
     */
    private BonusInteraction downgradeOnTerritoryCard(PlayerHandler player) {
        player.getPersonalBoardReference().getBonusOnActions().setTerritoryBonus(player.getPersonalBoardReference().getBonusOnActions().getTerritoryBonus() - quantity);
        return new OkOrNo();
    }

    /**
     * Perform the downgradeOnVenturesCard effect
     *
     * @param player playerHandler's reference
     * @return okOrNo instance for saying that the effect has been applied correctly
     */
    private BonusInteraction downgradeOnVenturesCard(PlayerHandler player) {
        player.getPersonalBoardReference().getBonusOnActions().setVenturesBonus(player.getPersonalBoardReference().getBonusOnActions().getVenturesBonus() - quantity);
        return new OkOrNo();
    }

    /**
     * Perform the downgradeOnCharacterCard effect
     *
     * @param player playerHandler's reference
     * @return okOrNo instance for saying that the effect has been applied correctly
     */
    private BonusInteraction downgradeOnCharacterCard(PlayerHandler player) {
        player.getPersonalBoardReference().getBonusOnActions().getCharactersBonus().setDiceBonus(player.getPersonalBoardReference().getBonusOnActions().getCharactersBonus().getDiceBonus() - quantity);
        return new OkOrNo();
    }

    /**
     * Perform the downgradeOnBuildingCard effect
     *
     * @param player playerHandler's reference
     * @return okOrNo instance for saying that the effect has been applied correctly
     */
    private BonusInteraction downgradeOnBuildingCard(PlayerHandler player) {
        player.getPersonalBoardReference().getBonusOnActions().getBuildingsBonus().setDiceBonus(player.getPersonalBoardReference().getBonusOnActions().getBuildingsBonus().getDiceBonus() - quantity);
        return new OkOrNo();
    }

    /**
     * Perform the downgradeOnDices effect
     *
     * @param player playerHandler's reference
     * @return okOrNo instance for saying that the effect has been applied correctly
     */
    private BonusInteraction downgradeOnDices(PlayerHandler player){
        for (FamilyMember familyMember: player.getAllFamilyMembers()){
            if (!familyMember.getMyColour().equals(Constants.FAMILY_MEMBER_COLOUR_NEUTRAL));
                familyMember.setFixedBonus(familyMember.getFixedBonus() - quantity);
        }
        return new OkOrNo();
    }

    /**
     * Perform the effect the right effect according to the parameter
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
        return "The family members'value for doing " + parameter + " is downgraded of " + quantity + " points";
    }

    /**
     * Functional interface used for calling the right effect method
     */
    @FunctionalInterface
    private interface DowngradeEffectBuilder{
        BonusInteraction realEffect(PlayerHandler player);
    }
}
