package project.model;

import org.junit.Test;
import project.controller.effects.effectsfactory.TrisIE;
import project.controller.effects.realeffects.AddCoin;

import static org.junit.Assert.*;

/**
 * model test
 */
public class TileTest {

    Tile test = new Tile(new TrisIE("takeRop", "coin", 5) , new TrisIE("takeRop", "coin", 5));
    @Test
    public void takeProductionResource() throws Exception {
        assertEquals(AddCoin.class, test.takeProductionResource().get(0).getClass());
    }

    @Test
    public void takeHarvesterResource() throws Exception {
        assertEquals(AddCoin.class, test.takeHarvesterResource().get(0).getClass());
    }

    @Test
    public void getTileNumber() throws Exception {
        assertEquals(0, test.getTileNumber());
    }

}