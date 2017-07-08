package project.controller.effects.realeffects;

import org.junit.Before;
import org.junit.Test;
import project.controller.cardsfactory.TotalCost;
import project.server.network.PlayerHandler;
import project.server.network.rmi.RMIPlayerHandler;

import static org.junit.Assert.*;

/**
 * effect test
 */
public class ExchangeServantsForThreeMilitaryPointsAndOneVictoryPointsTest {
    TotalCost tc = new TotalCost();
    private ExchangeServantsForThreeMilitaryPointsAndOneVictoryPoints test = new ExchangeServantsForThreeMilitaryPointsAndOneVictoryPoints();
    private PlayerHandler p = new RMIPlayerHandler();

    @Before
    public void before(){

        p.getPersonalBoardReference().setServants(5);
        p.getScore().setMilitaryPoints(10);
        p.getScore().setVictoryPoints(10);
    }
    @Test
    public void doEffect() throws Exception {
        test.doEffect(p);

        assertEquals(4, p.getPersonalBoardReference().getServants());
        assertEquals(13, p.getScore().getMilitaryPoints());
        assertEquals(11, p.getScore().getVictoryPoints());
    }

    @Test
    public void toScreen() throws Exception {
        String s = "Exchange " + 1 + " servants" + " for taking " + 1 + " of " + " victory points\n" +
                "and " +  3 + " of military points";
        assertEquals(s, test.toScreen());
    }

    @Test
    public void addResourceRequested() throws Exception {
        tc.setServantsRequired(2);
        test.addResourceRequested(tc);
        assertEquals(3,tc.getServantsRequired());
    }

}