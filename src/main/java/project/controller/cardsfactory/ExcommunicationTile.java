package project.controller.cardsfactory;

import project.controller.effects.effectsfactory.BuildExcommunicationEffects;
import project.controller.effects.effectsfactory.TrisIE;
import project.controller.effects.realeffects.Effects;
import project.server.network.PlayerHandler;
import project.messages.BonusInteraction;

/**
 * 
 */
public class ExcommunicationTile {

    private int period;
    private int idCard;
    private Effects excommunicationEffect;

    public ExcommunicationTile(int idCard, int period, TrisIE myEffect) {
        this.idCard = idCard;
        this.period = period;
        BuildExcommunicationEffects buildExcommunicationEffects = new BuildExcommunicationEffects();
        this.excommunicationEffect = buildExcommunicationEffects.searchEffectType(myEffect.getType(),myEffect.getParameter(),myEffect.getQuantity());
    }


    public BonusInteraction makeEffect(PlayerHandler player){
        return excommunicationEffect.doEffect(player);
    }

    public int getIdCard() {
        return idCard;
    }
}