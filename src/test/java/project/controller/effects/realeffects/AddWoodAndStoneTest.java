package project.controller.effects.realeffects;

import org.junit.Before;
import org.junit.Test;
import project.server.network.PlayerHandler;
import project.server.network.rmi.RMIPlayerHandler;

import static org.junit.Assert.*;

/**
 * Created by federico on 07/07/17.
 */
public class AddWoodAndStoneTest {
    AddWoodAndStone test = new AddWoodAndStone(1);
    PlayerHandler p = new RMIPlayerHandler();

    @Before
    public void before(){
        p.getPersonalBoardReference().setWood(5);
        p.getPersonalBoardReference().getBonusOnActions().setWoodBonus(-1);
        p.getPersonalBoardReference().setStone(5);
        p.getPersonalBoardReference().getBonusOnActions().setStoneBonus(0);
    }
    @Test
    public void doEffect() throws Exception {
        test.doEffect(p);

        assertEquals(5, p.getPersonalBoardReference().getWood());
        assertEquals(6, p.getPersonalBoardReference().getStone());
    }

    @Test
    public void toScreen() throws Exception {
        String s = "Add " + 1 + " stones and " + 1 + " wood";
        assertEquals(s, test.toScreen());
    }
}