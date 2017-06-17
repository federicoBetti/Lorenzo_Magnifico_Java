package project.controller.cardsfactory;

import project.controller.effects.effectsfactory.TrisIE;
import project.controller.effects.realeffects.Effects;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by raffaelebongo on 17/06/17.
 */
public class TileBonusFromJson {
    int tileNumber;
    ArrayList<TrisIE> harvesterBonus;
    ArrayList<TrisIE> productionBonus;

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
