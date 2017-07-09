package project.controller.effects.realeffects;

import org.junit.Before;
import org.junit.Test;
import project.server.network.PlayerHandler;
import project.server.network.rmi.RMIPlayerHandler;

import static org.junit.Assert.*;

/**
 * Created by federico on 07/07/17.
 */
public class AddServantsTest {
    AddServants test = new AddServants(1);
    PlayerHandler p = new RMIPlayerHandler();

    @Before
    public void before(){
        p.getPersonalBoardReference().setServants(5);
        p.getPersonalBoardReference().getBonusOnActions().setServantsBonus(0);
    }
    @Test
    public void doEffect() throws Exception {
        test.doEffect(p);

        assertEquals(6, p.getPersonalBoardReference().getServants());
    }

    @Test
    public void toScreen() throws Exception {
        String s = "Add " + 1 + " servants";
        assertEquals(s, test.toScreen());
    }

}