package project.controller.effects.realeffects;

import org.junit.Before;
import org.junit.Test;
import project.server.network.PlayerHandler;
import project.server.network.rmi.RMIPlayerHandler;

import static org.junit.Assert.*;

/**
 * Created by federico on 07/07/17.
 */
public class AddCoinTest {
    private AddCoin test = new AddCoin(1);
    private PlayerHandler p = new RMIPlayerHandler();

    @Before
    public void before(){
        p.getPersonalBoardReference().setCoins(5);
        p.getPersonalBoardReference().getBonusOnActions().setCoinsBonus(-3);
    }
    @Test
    public void doEffect() throws Exception {
        test.doEffect(p);

        assertEquals(5, p.getPersonalBoardReference().getCoins());
    }

    @Test
    public void toScreen() throws Exception {
        String s = "Add " + 1 + " coins";
        assertEquals(s, test.toScreen());
    }

}