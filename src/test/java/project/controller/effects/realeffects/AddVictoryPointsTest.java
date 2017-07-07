package project.controller.effects.realeffects;

import org.junit.Before;
import org.junit.Test;
import project.server.network.PlayerHandler;
import project.server.network.rmi.RMIPlayerHandler;

import static org.junit.Assert.*;

/**
 * test
 *
 */

public class AddVictoryPointsTest {
    private AddVictoryPoints test = new AddVictoryPoints(1);
    private PlayerHandler p = new RMIPlayerHandler();

    @Before
    public void before(){
        p.getScore().setVictoryPoints(10);
    }
    @Test
    public void doEffect() throws Exception {
        test.doEffect(p);

        assertEquals(11, p.getScore().getVictoryPoints());
    }

    @Test
    public void toScreen() throws Exception {
        String s = "Add " + 1 + " victory points";
        assertEquals(s, test.toScreen());
    }


}