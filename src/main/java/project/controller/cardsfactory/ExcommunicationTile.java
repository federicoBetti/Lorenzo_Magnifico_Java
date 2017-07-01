package project.controller.cardsfactory;

import project.controller.effects.effectsfactory.BuildExcommunicationEffects;
import project.controller.effects.effectsfactory.TrisIE;
import project.controller.effects.realeffects.Effects;
import project.server.network.PlayerHandler;
import project.messages.BonusInteraction;

import java.io.Serializable;

/**
 * 
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


    public BonusInteraction makeEffect(PlayerHandler player){
        return excommunicationEffect.doEffect(player);
    }

    public int getIdCard() {
        return idCard;
    }

    public Effects getExcommunicationEffect() {
        return excommunicationEffect;
    }

    public String getEffectDescription() {
        return effectDescription;
    }
}