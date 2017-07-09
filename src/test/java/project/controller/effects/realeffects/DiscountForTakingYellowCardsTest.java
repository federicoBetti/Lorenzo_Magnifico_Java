package project.controller.effects.realeffects;

import org.junit.Test;
import project.messages.OkOrNo;
import project.server.network.PlayerHandler;
import project.server.network.rmi.RMIPlayerHandler;

import static org.junit.Assert.*;

/**
 * test
 */
public class DiscountForTakingYellowCardsTest {
    DiscountForTakingYellowCards test = new DiscountForTakingYellowCards(1, "wood");
    PlayerHandler p = new RMIPlayerHandler();
    @Test
    public void doEffect() throws Exception {
        assertEquals(OkOrNo.class, test.doEffect(p).getClass());
    }

    @Test
    public void toScreen() throws Exception {
        String s = "Discount of " + 1 +  " units of " +"wood" + " for taking yellow cards";
        assertEquals(s, test.toScreen());
    }

}