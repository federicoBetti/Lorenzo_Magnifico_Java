package project.controller.effects.realeffects;

import project.controller.cardsfactory.TotalCost;

/**
 * Created by federico on 05/07/17.
 */
public interface ExchangeEffects extends Effects {

    void addResourceRequested(TotalCost cost);
}
