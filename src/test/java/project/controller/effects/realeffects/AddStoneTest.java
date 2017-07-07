package project.controller.effects.realeffects;

import org.junit.Before;
import org.junit.Test;
import project.server.network.PlayerHandler;
import project.server.network.rmi.RMIPlayerHandler;

import static org.junit.Assert.*;

/**
 * Created by federico on 07/07/17.
 */
public class AddStoneTest {
    AddStone test = new AddStone(1);
    PlayerHandler p = new RMIPlayerHandler();

    @Before
    public void before(){
        p.getPersonalBoardReference().setStone(5);
        p.getPersonalBoardReference().getBonusOnActions().setStoneBonus(-3);
    }
    @Test
    public void doEffect() throws Exception {
        test.doEffect(p);

        assertEquals(5, p.getPersonalBoardReference().getStone());
    }

    @Test
    public void toScreen() throws Exception {
        String s = "Add " + 1 + " stones";
        assertEquals(s, test.toScreen());
    }

}