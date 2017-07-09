package project.controller.cardsfactory;

import project.controller.effects.effectsfactory.BuildExcommunicationEffects;
import project.controller.effects.effectsfactory.TrisIE;
import project.controller.effects.realeffects.Effects;
import project.server.network.PlayerHandler;
import project.messages.BonusInteraction;

import java.io.Serializable;

/**
 * This class represents the excommunication's tile
 */
public class ExcommunicationTile implements Serializable{

    private int period;
    private String effectDescription;
    private int idCard;
    transient private Effects excommunicationEffect;

    public ExcommunicationTile(int idCard, int period, TrisIE myEffect, String effectDescription ) {
        this.idCard = idCard;
        this.period = period;
        this.effectDescription = effectDescription;
        BuildExcommunicationEffects buildExcommunicationEffects = new BuildExcommunicationEffects();
        this.excommunicationEffect = buildExcommunicationEffects.searchEffectType(myEffect.getType(),myEffect.getParameter(),myEffect.getQuantity());
    }

    /**
     * This method perform the excommunication's effect
     *
     * @param player palyerHandler's reference
     * @return bonusInteraction returned by the effect performed
     */
    public BonusInteraction makeEffect(PlayerHandler player){
        return excommunicationEffect.doEffect(player);
    }

    /**
     * Get idCard
     *
     * @return idCard
     */
    public int getIdCard() {
        return idCard;
    }

    /**
     * Get effect description
     *
     * @return effects description
     */
    public String getEffectDescription() {
        return effectDescription;
    }
}