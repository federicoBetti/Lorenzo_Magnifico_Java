package Project.Controller.Effects.EffectsFactory;

import Project.Controller.Effects.RealEffects.*;
import Project.MODEL.Player;
import Project.toDelete.BonusInteraction;

import java.util.HashMap;

/**
 * class used to build excommunication effect
 */
public class BuildExcommunicationEffects {

    //mi servono type, parameter, quantity
    //type sono: loseResource, downgrade, special, noEndPointsFromCard, loseFinalPoints
    private HashMap<String,ExcommunicationBuilder> typeEffect;

    public BuildExcommunicationEffects(){
        typeEffect = new HashMap<>(5);
        fillHashMapTypeEffects();
    }

    private void fillHashMapTypeEffects() {
        typeEffect.put(EffectsConstants.LOSE_RESOURCE,this::loseResource);
        typeEffect.put(EffectsConstants.DOWNGRADE,this::downgrade);
        typeEffect.put(EffectsConstants.SPECIAL,this::special);
        typeEffect.put(EffectsConstants.NO_FINAL_POINTS_FROM_CARDS,this::noFinalPointsFromCards);
        typeEffect.put(EffectsConstants.LOSE_FINAL_POINTS,this::loseFinalPoints);
    }

    private Effects loseFinalPoints(String parameter, int quantity) {
        return new LoseFinalPointsFromResources(parameter);
    }

    private Effects noFinalPointsFromCards(String parameter, int quantity) {
        return new NoFinalPointsFromCards(parameter);
    }

    private Effects loseResource(String parameter, int quantity) {
        return new LoseResourceEffect(parameter,quantity);
    }

    private Effects downgrade(String parameter, int quantity) {
        return new DowngradeEffect(parameter,quantity);
    }

    private Effects special(String parameter, int quantity){
        return new SpecialExcommunicationEffects(parameter);
    }

    public Effects searchEffectType(String type, String parameter, int quantity){
        return typeEffect.get(type).searchEffectParameter(parameter,quantity);
    }
    private interface ExcommunicationBuilder{
        Effects searchEffectParameter(String parameter, int quantity);
    }

    //TODO qua cominciano gli effetti, ne comincio a implementare uno a uno







}
