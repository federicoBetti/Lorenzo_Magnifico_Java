package project.model;

import project.controller.effects.effectsfactory.BuildImmediateEffects;
import project.controller.effects.effectsfactory.TrisIE;
import project.controller.effects.realeffects.Effects;

/**
 * Created by raffaelebongo on 17/06/17.
 */
public class CouncilPrivilege {

    private int priviledgeNumber;
    private Effects effect;

    public CouncilPrivilege(TrisIE trisIE, int priviledgeNumber ){
        this.priviledgeNumber = priviledgeNumber;
        BuildImmediateEffects buildImmediateEffects = new BuildImmediateEffects();
        effect = buildImmediateEffects.searchImmediateEffects(trisIE.getType(), trisIE.getParameter(), trisIE.getQuantity());
    }

    public int getPriviledgeNumber() {
        return priviledgeNumber;
    }

    public void setPriviledgeNumber(int priviledgeNumber) {
        this.priviledgeNumber = priviledgeNumber;
    }

    public Effects getEffect() {
        return effect;
    }

    public void setEffect(Effects effect) {
        this.effect = effect;
    }
}
