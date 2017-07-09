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
public class ExchangeCoinsForTest {
    TotalCost tc = new TotalCost();
    private ExchangeCoinsFor test = new ExchangeCoinsFor(1, tc, "wood");
    private PlayerHandler p = new RMIPlayerHandler();

    @Before
    public void before(){

        p.getPersonalBoardReference().setCoins(5);
        p.getPersonalBoardReference().setWood(5);
        p.getPersonalBoardReference().getBonusOnActions().setCoinsBonus(-1);
    }
    @Test
    public void doEffect() throws Exception {
        test.doEffect(p);

        assertEquals(5, p.getPersonalBoardReference().getCoins());
        assertEquals(6, p.getPersonalBoardReference().getWood());
    }

    @Test
    public void toScreen() throws Exception {
        String s = "Exchange " + 0 + " coins" + " for taking " + 1 + " of " + "wood";
        assertEquals(s, test.toScreen());
    }

    @Test
    public void addResourceRequested() throws Exception {
        tc.setCoinsRequired(2);
        test.addResourceRequested(tc);
        assertEquals(2,tc.getCoinsRequired());
    }

}