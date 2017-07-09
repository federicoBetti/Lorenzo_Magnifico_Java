package project.controller.effects.effectsfactory;


import com.google.gson.Gson;
import project.controller.effects.realeffects.*;
import java.util.HashMap;

/**
 * This class is used to build excommunication effect
 */
public class BuildExcommunicationEffects {

    private HashMap<String,ExcommunicationBuilder> typeEffect;
    private Gson gson;
    ExcommunicationBuilder excommunicationBuilder;

    /**
     * Class builder
     */
    public BuildExcommunicationEffects(){
        typeEffect = new HashMap<>(5);
        gson = new Gson();
        fillHashMapTypeEffects();
    }

    /**
     * This method fill the hashMap withe the entries
     */
    private void fillHashMapTypeEffects() {
        typeEffect.put(EffectsConstants.LOSE_RESOURCE,              this::loseResource);
        typeEffect.put(EffectsConstants.DOWNGRADE,                  this::downgrade);
        typeEffect.put(EffectsConstants.SPECIAL,                    this::special);
        typeEffect.put(EffectsConstants.NO_FINAL_POINTS_FROM_CARDS, this::noFinalPointsFromCards);
        typeEffect.put(EffectsConstants.LOSE_FINAL_POINTS,          this::loseFinalPoints);
    }

    /**
     * Effect loseFinalPoints
     *
     * @param parameter string that specify for what resource
     * @param quantity useless here
     * @return the right effects
     */
    private Effects loseFinalPoints(String parameter, int quantity) {
        return new LoseFinalPointsFromResources(parameter);
    }

    /**
     * Effect noFinalPointsFromCards
     *
     * @param parameter string that specify the kind of card
     * @param quantity useless here
     * @return the right effect
     */
    private Effects noFinalPointsFromCards(String parameter, int quantity) {
        return new NoFinalPointsFromCards(parameter);
    }

    /**
     * Effect loseResource
     *
     * @param parameter the kind of resource
     * @param quantity of resource lost
     * @return the right effetc
     */
    private Effects loseResource(String parameter, int quantity) {
        return new LoseResourceEffect(parameter,quantity);
    }

    /**
     * Effect downgrade
     *
     * @param parameter kind of downgrade
     * @param quantity downgraded
     * @return the right effect
     */
    private Effects downgrade(String parameter, int quantity) {
        return new DowngradeEffect(parameter,quantity);
    }

    /**
     * Effect special
     *
     * @param parameter kind of special effect
     * @param quantity useless
     * @return the right effect
     */
    private Effects special(String parameter, int quantity){
        return new SpecialExcommunicationEffects(parameter);
    }

    /**
     * Return call the right method according the type, the parameter and the quantity
     *
     * @param type type of effect
     * @param parameter that makes the effect specific
     * @param quantity quantity used for specify the effect
     * @return the right method
     */
    public Effects searchEffectType(String type, String parameter, int quantity){
        return typeEffect.get(type).searchEffectParameter(parameter,quantity);
    }

    /**
     * Functional interface for building permanent effects
     */
    @FunctionalInterface
    private interface ExcommunicationBuilder{
        Effects searchEffectParameter(String parameter, int quantity);
    }


}
