package Project.Controller.CardsFactory;

import Project.Controller.Effects.EffectsFactory.BuildExcommunicationEffects;
import Project.Controller.Effects.EffectsFactory.TrisIE;
import Project.Controller.Effects.RealEffects.Effects;
import Project.MODEL.Player;
import Project.Server.Network.PlayerHandler;
import Project.toDelete.BonusInteraction;

/**
 * 
 */
public class ExcommunitationTile {

    /**
     * Default constructor
     */
    public ExcommunitationTile(TrisIE myEffect) {
        BuildExcommunicationEffects buildExcommunicationEffects = new BuildExcommunicationEffects();
        this.excommunicationEffect = buildExcommunicationEffects.searchEffectType(myEffect.getType(),myEffect.getParameter(),myEffect.getQuantity());
    }

    /**
     * period of this excommunication
     */
    private int Period;

    /**
     * identificativo della carta
     */
    private int idCard;


    private Effects excommunicationEffect;

    public BonusInteraction makeEffect(PlayerHandler player){
        return excommunicationEffect.doEffect(player);
    }

    public int getIdCard() {
        return idCard;
    }
}