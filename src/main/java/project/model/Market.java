package project.model;

import project.controller.effects.effectsfactory.BuildImmediateEffects;
import project.controller.effects.effectsfactory.TrisIE;
import project.controller.effects.realeffects.Effects;

/**
 * 
 */
public class Market extends Position {

    Effects effect; //todo costruire con il tris: ogni posto ha sui effetti

    public Market(TrisIE trisIE ){
        BuildImmediateEffects BuildImmediateEffects = new BuildImmediateEffects();
        effect = BuildImmediateEffects.searchImmediateEffects(trisIE.getType(), trisIE.getParameter(), trisIE.getQuantity());
    }
    public void makeEffect(int position) {
        //to do implements
    }

}