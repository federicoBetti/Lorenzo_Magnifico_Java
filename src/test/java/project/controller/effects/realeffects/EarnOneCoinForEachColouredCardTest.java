package project.controller.effects.realeffects;

import org.junit.Test;
import project.messages.OkOrNo;
import project.server.network.PlayerHandler;
import project.server.network.rmi.RMIPlayerHandler;

import static org.junit.Assert.*;

/**
 * test
 */
public class EarnOneCoinForEachColouredCardTest {

    EarnOneCoinForEachColouredCard test = new EarnOneCoinForEachColouredCard("blue");
    PlayerHandler p = new RMIPlayerHandler();

    @Test
    public void doEffect() throws Exception {
        assertEquals(OkOrNo.class, test.doEffect(p).getClass());
    }

    @Test
    public void toScreen() throws Exception {
        String s = "Earn one coin for each " + "blue" + " card";;
        assertEquals(s, test.toScreen());
    }
}