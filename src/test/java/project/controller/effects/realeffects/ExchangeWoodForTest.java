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
public class ExchangeWoodForTest {
    TotalCost tc = new TotalCost();
    private ExchangeWoodFor test;
    private PlayerHandler p = new RMIPlayerHandler();

    @Before
    public void before() {
        tc.setWoodRequired(3);
        test = new ExchangeWoodFor(1, tc, "stone");
        p.getPersonalBoardReference().setStone(5);
        p.getPersonalBoardReference().setWood(5);
        p.getPersonalBoardReference().getBonusOnActions().setCoinsBonus(-1);
    }

    @Test
    public void doEffect() throws Exception {
        test.doEffect(p);

        assertEquals(2, p.getPersonalBoardReference().getWood());
        assertEquals(6, p.getPersonalBoardReference().getStone());
    }

    @Test
    public void toScreen() throws Exception {
        String s = "Exchange " + 3 + " wood" + " for taking " + 1 + " of " + "stone";
        assertEquals(s, test.toScreen());
    }

    @Test
    public void addResourceRequested() throws Exception {
        test.addResourceRequested(tc);
        assertEquals(6, tc.getWoodRequired());
    }
}
