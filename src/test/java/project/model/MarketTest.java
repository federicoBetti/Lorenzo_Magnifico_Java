package project.model;

import org.junit.Test;
import project.controller.effects.effectsfactory.TrisIE;
import project.controller.effects.realeffects.AddCoin;

import static org.junit.Assert.*;

/**
 * model test
 */
public class MarketTest {
    private Market test = new Market(new TrisIE("takeRop", "coin", 5));

    @Test
    public void getEffect() throws Exception {
        assertEquals(AddCoin.class, test.getEffect().getClass());
    }

}