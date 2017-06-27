package project.controller.cardsfactory;

import project.controller.effects.effectsfactory.TrisIE;

import java.util.ArrayList;

/**
 * Created by raffaelebongo on 17/06/17.
 */
public class TileBonusFromJson {
    private int tileNumber;
    private ArrayList<TrisIE> harvesterBonus;
    private ArrayList<TrisIE> productionBonus;

    public int getTileNumber() {
        return tileNumber;
    }

    public ArrayList<TrisIE> getHarvesterBonus() {
        return harvesterBonus;
    }

    public ArrayList<TrisIE> getProductionBonus() {
        return productionBonus;
    }
}
