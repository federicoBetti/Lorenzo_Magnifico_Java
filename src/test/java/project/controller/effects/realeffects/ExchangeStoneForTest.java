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
public class ExchangeStoneForTest {
    TotalCost tc = new TotalCost();
    private ExchangeStoneFor test;
    private PlayerHandler p = new RMIPlayerHandler();

    @Before
    public void before(){
        tc.setStoneRequired(3);
        test = new ExchangeStoneFor(1, tc, "wood");
        p.getPersonalBoardReference().setStone(5);
        p.getPersonalBoardReference().setWood(5);
        p.getPersonalBoardReference().getBonusOnActions().setCoinsBonus(-1);
    }
    @Test
    public void doEffect() throws Exception {
        test.doEffect(p);

        assertEquals(2, p.getPersonalBoardReference().getStone());
        assertEquals(6, p.getPersonalBoardReference().getWood());
    }

    @Test
    public void toScreen() throws Exception {
        String s = "Exchange " + 3 + " stones" + " for taking " + 1 + " of " + "wood";
        assertEquals(s, test.toScreen());
    }

    @Test
    public void addResourceRequested() throws Exception {
        test.addResourceRequested(tc);
        assertEquals(6,tc.getStoneRequired());
    }

}