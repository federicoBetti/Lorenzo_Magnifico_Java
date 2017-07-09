package project.controller.cardsfactory;

import project.controller.effects.effectsfactory.TrisIE;

import java.util.ArrayList;

/**
 * This class represent a bonus tile loaded from Json
 */
public class TileBonusFromJson {
    private int tileNumber;
    private ArrayList<TrisIE> harvesterBonus;
    private ArrayList<TrisIE> productionBonus;

    /**
     * Get tile Number
     *
     * @return tileNumber
     */
    public int getTileNumber() {
        return tileNumber;
    }

    /**
     * Get list of harvester's bonus
     *
     * @return harvesterBonus
     */
    public ArrayList<TrisIE> getHarvesterBonus() {
        return harvesterBonus;
    }

    /**
     * Get list of production's bonus
     *
     * @return productionBonus
     */
    public ArrayList<TrisIE> getProductionBonus() {
        return productionBonus;
    }
}
