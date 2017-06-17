package project.controller.cardsfactory;

import project.controller.effects.effectsfactory.TrisIE;
import project.controller.effects.realeffects.Effects;

/**
 * Created by raffaelebongo on 17/06/17.
 */
public class ExTileFromJson {
    private int period;
    private int idCard;
    TrisIE excomunicationEffectsFromJson;

    public int getPeriod() {
        return period;
    }

    public int getIdCard() {
        return idCard;
    }

    public TrisIE getExcomunicationEffectsFromJson() {
        return excomunicationEffectsFromJson;
    }
}
