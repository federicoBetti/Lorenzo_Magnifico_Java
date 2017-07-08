package project.controller.effects.realeffects;

import org.junit.Test;
import project.server.network.PlayerHandler;
import project.server.network.rmi.RMIPlayerHandler;

import static org.junit.Assert.*;

/**
 * effect test
 */
public class VictoryPointsForEachTwoMilitaryPointsTest {
    VictoryPointsForEachTwoMilitaryPoints test = new VictoryPointsForEachTwoMilitaryPoints();
    @Test
    public void doEffect() throws Exception {
        PlayerHandler p = new RMIPlayerHandler();

        p.getScore().setVictoryPoints(10);
        p.getScore().setMilitaryPoints(8);

        test.doEffect(p);
        assertEquals(14, p.getScore().getVictoryPoints());
    }

    @Test
    public void toScreen() throws Exception {
        String s = "Take 1 military points for every 2 military points";
        assertEquals(s, test.toScreen());
    }

}