package project.controller.cardsfactory;

import project.controller.effects.effectsfactory.TrisIE;

/**
 * This class represent the excommunication tile loaded from Json
 */
public class ExTileFromJson {
    private int period;
    private int idCard;
    private TrisIE excomunicationEffectsFromJson;
    private String effectDescription;

    /**
     * Get Period
     *
     * @return period
     */
    public int getPeriod() {
        return period;
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
     * Get excomunicationEffectsFromJson
     *
     * @return excomunicationEffectsFromJson
     */
    public TrisIE getExcomunicationEffectsFromJson() {
        return excomunicationEffectsFromJson;
    }

    /**
     * Get effectDescription
     *
     * @return effectDescription
     */
    public String getEffectDescription() {
        return effectDescription;
    }
}
