package project.controller.effects.realeffects;

import org.junit.Before;
import org.junit.Test;
import project.server.network.PlayerHandler;
import project.server.network.rmi.RMIPlayerHandler;

import static org.junit.Assert.*;

/**
 * test
 */
public class AddFaithPointsTest {
    private AddFaithPoints test = new AddFaithPoints(1);
    private PlayerHandler p = new RMIPlayerHandler();

    @Before
    public void before(){
        p.getScore().setFaithPoints(10);
    }
    @Test
    public void doEffect() throws Exception {
        test.doEffect(p);

        assertEquals(11, p.getScore().getFaithPoints());
    }

    @Test
    public void toScreen() throws Exception {
        String s = "Add " + 1 + " faith points";
        assertEquals(s, test.toScreen());
    }

}