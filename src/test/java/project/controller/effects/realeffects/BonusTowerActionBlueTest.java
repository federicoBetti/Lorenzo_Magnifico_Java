package project.controller.effects.realeffects;

import org.junit.Test;
import project.messages.TowerAction;
import project.server.network.PlayerHandler;
import project.server.network.rmi.RMIPlayerHandler;

import static org.junit.Assert.*;

/**
 * effect test
 */
public class BonusTowerActionBlueTest {
    private BonusTowerActionBlue test = new BonusTowerActionBlue("ciao" , 1);
    private PlayerHandler p = new RMIPlayerHandler();
    TowerAction t = new TowerAction("coins" , 1);

    @Test
    public void doEffect() throws Exception {
        TowerAction t =new TowerAction("blue", 6, "coins", 1 );
        assertEquals(TowerAction.class, test.doEffect(p).getClass());
    }

    @Test
    public void toScreen() throws Exception {
        String s = "Do a bonus tower action with the parameters that follows:\n" + t.printBonusAction();
        assertEquals(null, test.toScreen());
    }


}