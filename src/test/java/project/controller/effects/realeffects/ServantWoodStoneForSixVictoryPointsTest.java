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
public class ServantWoodStoneForSixVictoryPointsTest {
    ServantWoodStoneForSixVictoryPoints test = new ServantWoodStoneForSixVictoryPoints();

    TotalCost tc = new TotalCost();
    private PlayerHandler p = new RMIPlayerHandler();

    @Before
    public void before(){

        p.getPersonalBoardReference().setStone(1);
        p.getPersonalBoardReference().setWood(5);
    }
    @Test
    public void doEffect() throws Exception {
        test.doEffect(p);

        assertEquals(0, p.getPersonalBoardReference().getStone());
        assertEquals(4, p.getPersonalBoardReference().getWood());
    }

    @Test
    public void toScreen() throws Exception {
        String s = "Exchange 1 servant, 1 wood and 1 stone for 6 victory points.";
        assertEquals(s, test.toScreen());
    }

    @Test
    public void addResourceRequested() throws Exception {
        tc.setWoodRequired(2);
        test.addResourceRequested(tc);
        assertEquals(3,tc.getWoodRequired());
    }


}