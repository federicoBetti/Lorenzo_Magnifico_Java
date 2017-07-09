package project.model;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * bonus test
 */
public class BonusForTakingCharacterTest {

    private BonusForTakingCharacter test = new BonusForTakingCharacter();

    @Test
    public void getDiceBonus() throws Exception {
        int bonus = 3;
        test.setDiceBonus(bonus);
        assertEquals(bonus, test.getDiceBonus());
    }

    @Test
    public void setDiceBonus() throws Exception {
        int bonus = 3;
        test.setDiceBonus(bonus);
        assertEquals(bonus, test.getDiceBonus());
    }

    @Test
    public void getCoinsBonus() throws Exception {
        int bonus = 3;
        test.setCoinsBonus(bonus);
        assertEquals(bonus, test.getCoinsBonus());
    }

    @Test
    public void setCoinsBonus() throws Exception {
        int bonus = 3;
        test.setCoinsBonus(bonus);
        assertEquals(bonus, test.getCoinsBonus());
    }

}