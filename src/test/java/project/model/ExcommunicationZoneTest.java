package project.model;

import org.junit.Before;
import org.junit.Test;
import project.controller.cardsfactory.ExcommunicationTile;
import project.controller.effects.effectsfactory.EffectsConstants;
import project.controller.effects.effectsfactory.TrisIE;

import static org.junit.Assert.*;

/**
 * model test
 */
public class ExcommunicationZoneTest {

    private ExcommunicationZone test;
    private ExcommunicationTile tile;

    @Before
    public void before(){
        tile = new ExcommunicationTile(1,2,new TrisIE(EffectsConstants.LOSE_RESOURCE, EffectsConstants.COINS, 1), "decription");
        test = new ExcommunicationZone(tile);
    }
    @Test
    public void getCardForThisPeriod() throws Exception {
        assertEquals(tile, test.getCardForThisPeriod());
    }

}