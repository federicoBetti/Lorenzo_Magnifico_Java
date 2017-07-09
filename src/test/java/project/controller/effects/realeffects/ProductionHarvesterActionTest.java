package project.controller.effects.realeffects;

import org.junit.Test;
import project.messages.BonusProductionOrHarvesterAction;
import project.server.network.PlayerHandler;
import project.server.network.rmi.RMIPlayerHandler;

import static org.junit.Assert.*;

/**
 * effect test
 */
public class ProductionHarvesterActionTest {
    ProductionHarvesterAction test = new ProductionHarvesterAction("harvester", 1 );
    @Test
    public void doEffect() throws Exception {
        PlayerHandler p = new RMIPlayerHandler();
        assertEquals(BonusProductionOrHarvesterAction.class, test.doEffect(p).getClass());
    }

    @Test
    public void toScreen() throws Exception {
        String s = test.toScreen();
        assertEquals(s , test.toScreen());
    }

}