package project.controller.effects.realeffects;

import org.junit.Test;
import project.controller.Constants;
import project.messages.TowerAction;
import project.model.Tower;
import project.server.network.PlayerHandler;
import project.server.network.rmi.RMIPlayerHandler;

import static org.junit.Assert.*;

/**
 * test
 */
public class BonusTowerActionTest {
    private BonusTowerAction test = new BonusTowerAction("ciao" , 1);
    private PlayerHandler p = new RMIPlayerHandler();
    TowerAction t = new TowerAction("ciao" , 1);

    @Test
    public void doEffect() throws Exception {
        TowerAction t = new TowerAction("ciao" , 1);
        assertEquals(TowerAction.class, test.doEffect(p).getClass());
    }

    @Test
    public void toScreen() throws Exception {
        String s = "Do a bonus tower action with the parameters that follows:\n" + t.printBonusAction();
        assertEquals(s, test.toScreen());
    }

}