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
public class FaithPointForTwoCoinsAndTwoVictoryPointsTest {
    TotalCost tc = new TotalCost();
    private FaithPointForTwoCoinsAndTwoVictoryPoints test;
    private PlayerHandler p = new RMIPlayerHandler();

    @Before
    public void before(){
        tc.setFaithPonts(5);
        p.getScore().setFaithPoints(10);
        test = new FaithPointForTwoCoinsAndTwoVictoryPoints();
        p.getPersonalBoardReference().setCoins(5);
        p.getScore().setVictoryPoints(10);
    }
    @Test
    public void doEffect() throws Exception {
        test.doEffect(p);

        assertEquals(12, p.getScore().getVictoryPoints());
        assertEquals(7, p.getPersonalBoardReference().getCoins());
    }

    @Test
    public void toScreen() throws Exception {
        String s =  "Exchange " + 1 + " faith points" + " for taking " + 2 + " of " + "coins\n" +
                "and " + 2 + " of victory points";
        assertEquals(s, test.toScreen());
    }

    @Test
    public void addResourceRequested() throws Exception {
        test.addResourceRequested(tc);
        assertEquals(6,tc.getFaithPoints());
    }

}