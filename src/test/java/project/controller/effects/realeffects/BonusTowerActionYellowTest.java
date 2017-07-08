package project.controller.effects.realeffects;

import org.junit.Test;
import project.messages.TowerAction;
import project.server.network.PlayerHandler;
import project.server.network.rmi.RMIPlayerHandler;

import static org.junit.Assert.*;

/**
 * effect test
 */
public class BonusTowerActionYellowTest {
    private BonusTowerActionYellow test = new BonusTowerActionYellow("wood-stone" , 1);
    private PlayerHandler p = new RMIPlayerHandler();
    TowerAction t = new TowerAction("coins" , 1);

    @Test
    public void doEffect() throws Exception {
        TowerAction t = new TowerAction("yellow", 6, "wood", 1 , "stone" ,1);
        assertEquals(TowerAction.class, test.doEffect(p).getClass());
    }

    @Test
    public void toScreen() throws Exception {
        assertEquals(null, test.toScreen());
    }

}