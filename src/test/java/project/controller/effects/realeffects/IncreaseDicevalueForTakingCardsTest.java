package project.controller.effects.realeffects;

import org.junit.Test;
import project.server.network.PlayerHandler;
import project.server.network.rmi.RMIPlayerHandler;

import static org.junit.Assert.*;

/**
 * effect test
 */
public class IncreaseDicevalueForTakingCardsTest {
    IncreaseDicevalueForTakingCards test = new IncreaseDicevalueForTakingCards(1, "green");
    PlayerHandler p = new RMIPlayerHandler();

    @Test
    public void doEffect() throws Exception {
        p.getPersonalBoardReference().getBonusOnActions().setTerritoryBonus(3);
        test.doEffect(p);
        assertEquals(4, p.getPersonalBoardReference().getBonusOnActions().getTerritoryBonus());
    }

    @Test
    public void toScreen() throws Exception {
        String s = "Increase dice value for taking " + "green" + " card of " + 1;
        assertEquals(s, test.toScreen());
    }


}