package project.controller.effects.realeffects;

import org.junit.Test;
import project.server.network.PlayerHandler;
import project.server.network.rmi.RMIPlayerHandler;

import static org.junit.Assert.*;

/**
 * effect test
 */
public class IncreaseDiceValueForHoPTest {
    IncreaseDiceValueForHoP test = new IncreaseDiceValueForHoP(1, "harvester");
    PlayerHandler p = new RMIPlayerHandler();

    @Test
    public void doEffect() throws Exception {
        p.getPersonalBoardReference().getBonusOnActions().setHarvesterBonus(3);
        test.doEffect(p);
        assertEquals(4, p.getPersonalBoardReference().getBonusOnActions().getHarvesterBonus());
    }

    @Test
    public void toScreen() throws Exception {
        String s = "Increase the dice value for perfoming " + "harvester" + " of " + 1;
        assertEquals(s, test.toScreen());
    }

}