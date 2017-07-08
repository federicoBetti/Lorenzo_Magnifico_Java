package project.controller.effects.realeffects;

import org.junit.Test;
import project.server.network.PlayerHandler;
import project.server.network.rmi.RMIPlayerHandler;

import static org.junit.Assert.*;

/**
 * effect test
 */
public class DiscountForTakingBlueCardsTest {

    DiscountForTakingBlueCards test = new DiscountForTakingBlueCards(1, "coins");
    PlayerHandler p = new RMIPlayerHandler();
    @Test
    public void doEffect() throws Exception {
        p.getPersonalBoardReference().getBonusOnActions().getCharactersBonus().setCoinsBonus(3);
        test.doEffect(p);
        assertEquals(4, p.getPersonalBoardReference().getBonusOnActions().getCharactersBonus().getCoinsBonus());
    }

    @Test
    public void toScreen() throws Exception {
        String s = "You have a discount of " + 1 + " coin for taking blue cards";
        assertEquals(s, test.toScreen());
    }

}