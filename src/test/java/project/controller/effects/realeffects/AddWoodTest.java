package project.controller.effects.realeffects;

import org.junit.Before;
import org.junit.Test;
import project.server.network.PlayerHandler;
import project.server.network.rmi.RMIPlayerHandler;

import static org.junit.Assert.*;

/**
 * Created by federico on 07/07/17.
 */
public class AddWoodTest {
    AddWood test = new AddWood(1);
    PlayerHandler p = new RMIPlayerHandler();

    @Before
    public void before(){
        p.getPersonalBoardReference().setWood(5);
        p.getPersonalBoardReference().getBonusOnActions().setWoodBonus(1);
    }
    @Test
    public void doEffect() throws Exception {
        test.doEffect(p);

        assertEquals(7, p.getPersonalBoardReference().getWood());
    }

    @Test
    public void toScreen() throws Exception {
        String s = "Add " + 1 + " wood";
        assertEquals(s, test.toScreen());
    }

}