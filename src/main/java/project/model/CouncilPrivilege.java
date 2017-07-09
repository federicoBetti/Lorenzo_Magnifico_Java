package project.model;

import project.controller.effects.effectsfactory.BuildImmediateEffects;
import project.controller.effects.effectsfactory.TrisIE;
import project.controller.effects.realeffects.Effects;

/**
 * Created by raffaelebongo on 17/06/17.
 */
public class CouncilPrivilege {

    private int privilegeNumber;
    private Effects effect;

    public CouncilPrivilege(TrisIE trisIE, int privilegeNumber){
        this.privilegeNumber = privilegeNumber;
        BuildImmediateEffects buildImmediateEffects = new BuildImmediateEffects();
        effect = buildImmediateEffects.searchImmediateEffects(trisIE.getType(), trisIE.getParameter(), trisIE.getQuantity());
    }

    public Effects getEffect() {
        return effect;
    }

    public void setEffect(Effects effect) {
        this.effect = effect;
    }
}
