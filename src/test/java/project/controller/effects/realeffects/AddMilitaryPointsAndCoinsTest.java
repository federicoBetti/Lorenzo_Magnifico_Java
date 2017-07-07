package project.controller.effects.realeffects;

import org.junit.Before;
import org.junit.Test;
import project.server.network.PlayerHandler;
import project.server.network.rmi.RMIPlayerHandler;

import static org.junit.Assert.*;

/**
 * test
 */
public class AddMilitaryPointsAndCoinsTest {
    private AddMilitaryPointsAndCoins test = new AddMilitaryPointsAndCoins();
    private PlayerHandler p = new RMIPlayerHandler();

    @Before
    public void before(){
        p.getScore().setMilitaryPoints(10);
        p.getPersonalBoardReference().setCoins(10);
        p.getPersonalBoardReference().getBonusOnActions().setCoinsBonus(-1);
    }
    @Test
    public void doEffect() throws Exception {
        test.doEffect(p);

        assertEquals(13, p.getScore().getMilitaryPoints());
        assertEquals(11, p.getPersonalBoardReference().getCoins());
    }

    @Test
    public void toScreen() throws Exception {
        String s = "Add 3 military points and 2 coins";
        assertEquals(s, test.toScreen());
    }


}