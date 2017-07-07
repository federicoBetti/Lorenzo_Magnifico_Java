package project.controller.effects.realeffects;

import org.junit.Before;
import org.junit.Test;
import project.controller.cardsfactory.TotalCost;
import project.server.network.PlayerHandler;
import project.server.network.rmi.RMIPlayerHandler;

import static org.junit.Assert.*;

/**
 * test
 */
public class ExchangeServantsForTest {
    TotalCost tc = new TotalCost();
    private ExchangeServantsFor test;
    private PlayerHandler p = new RMIPlayerHandler();

    @Before
    public void before(){

        tc.setServantsRequired(2);
        test = new ExchangeServantsFor(1, tc, "wood");
        p.getPersonalBoardReference().setServants(5);
        p.getPersonalBoardReference().setWood(5);
        p.getPersonalBoardReference().getBonusOnActions().setCoinsBonus(-1);
    }
    @Test
    public void doEffect() throws Exception {
        test.doEffect(p);

        assertEquals(3, p.getPersonalBoardReference().getServants());
        assertEquals(6, p.getPersonalBoardReference().getWood());
    }

    @Test
    public void toScreen() throws Exception {
        String s = "Exchange " + 2 + " servants" + " for taking " + 1 + " of " + "wood";
        assertEquals(s, test.toScreen());
    }

    @Test
    public void addResourceRequested() throws Exception {
        tc.setServantsRequired(2);
        test.addResourceRequested(tc);
        assertEquals(4,tc.getServantsRequired());
    }


}