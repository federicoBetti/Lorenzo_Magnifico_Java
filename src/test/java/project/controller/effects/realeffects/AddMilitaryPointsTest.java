package project.controller.effects.realeffects;

import org.junit.Before;
import org.junit.Test;
import project.server.network.PlayerHandler;
import project.server.network.rmi.RMIPlayerHandler;

import static org.junit.Assert.*;

/**
 * test
 */
public class AddMilitaryPointsTest {
    private AddMilitaryPoints test = new AddMilitaryPoints(1);
    private PlayerHandler p = new RMIPlayerHandler();

    @Before
    public void before(){
        p.getScore().setMilitaryPoints(10);
        p.getPersonalBoardReference().getBonusOnActions().setMilitaryPointsBonus(-1);
    }
    @Test
    public void doEffect() throws Exception {
        test.doEffect(p);

        assertEquals(10, p.getScore().getMilitaryPoints());
    }

    @Test
    public void toScreen() throws Exception {
        String s = "Add " + 1 + " military points";
        assertEquals(s, test.toScreen());
    }
}