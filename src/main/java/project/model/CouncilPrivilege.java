package project.model;

import project.controller.effects.effectsfactory.BuildImmediateEffects;
import project.controller.effects.effectsfactory.TrisIE;
import project.controller.effects.realeffects.Effects;

/**
 * This class represent a council privilege releted to a council privilege's number
 */
public class CouncilPrivilege {

    private int privilegeNumber;
    private Effects effect;

    /**
     * Constructor
     *
     * @param trisIE obeject that represent an effect
     * @param privilegeNumber privilege number
     */
    public CouncilPrivilege(TrisIE trisIE, int privilegeNumber){
        this.privilegeNumber = privilegeNumber;
        BuildImmediateEffects buildImmediateEffects = new BuildImmediateEffects();
        effect = buildImmediateEffects.searchImmediateEffects(trisIE.getType(), trisIE.getParameter(), trisIE.getQuantity());
    }

    /**
     * Get effect
     *
     * @return effect
     */
    public Effects getEffect() {
        return effect;
    }

    /**
     * Set effect
     *
     * @param effect effect
     */
    public void setEffect(Effects effect) {
        this.effect = effect;
    }
}
