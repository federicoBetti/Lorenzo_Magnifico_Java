package project.model;

import project.controller.effects.effectsfactory.BuildImmediateEffects;
import project.controller.effects.effectsfactory.TrisIE;
import project.controller.effects.realeffects.Effects;

import java.io.Serializable;

/**
 * 
 */
public class Market extends Position implements Serializable{

    private Effects effect; 

    public Market(TrisIE trisIE ){
        BuildImmediateEffects BuildImmediateEffects = new BuildImmediateEffects();
        effect = BuildImmediateEffects.searchImmediateEffects(trisIE.getType(), trisIE.getParameter(), trisIE.getQuantity());
    }

    public Effects getEffect() {
        return effect;
    }
}