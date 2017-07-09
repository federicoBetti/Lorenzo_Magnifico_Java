package project.controller.cardsfactory;

import project.controller.effects.effectsfactory.TrisIE;

/**
 * This class represent the market position loaded from Json
 */
public class MarketFromJson {
    private int position;
    private TrisIE trisIE;

    /**
     * Get position
     *
     * @return position
     */
    public int getPosition() {
        return position;
    }

    /**
     * Get tris
     *
     * @return tris
     */
    public TrisIE getTrisIE() {
        return trisIE;
    }
}
