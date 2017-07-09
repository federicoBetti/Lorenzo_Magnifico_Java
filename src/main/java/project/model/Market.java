package project.model;

import project.controller.effects.effectsfactory.BuildImmediateEffects;
import project.controller.effects.effectsfactory.TrisIE;
import project.controller.effects.realeffects.Effects;

import java.io.Serializable;

/**
 * This class represents the Market's zone
 */
public class Market extends Position implements Serializable{

    private Effects effect;

    /**
     * Constructoor
     *
     * @param trisIE object for representing the immediate efferct with 3 parameters
     */
    public Market(TrisIE trisIE ){
        BuildImmediateEffects BuildImmediateEffects = new BuildImmediateEffects();
        effect = BuildImmediateEffects.searchImmediateEffects(trisIE.getType(), trisIE.getParameter(), trisIE.getQuantity());
    }

    /**
     * Get effect
     *
     * @return effect
     */
    public Effects getEffect() {
        return effect;
    }
}