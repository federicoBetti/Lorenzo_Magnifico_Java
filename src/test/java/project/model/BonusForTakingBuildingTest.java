package project.model;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * bonus test
 */
public class BonusForTakingBuildingTest {
    private BonusForTakingBuilding test = new BonusForTakingBuilding();

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
    public void getWoodBonus() throws Exception {
        int bonus = 3;
        test.setWoodBonus(bonus);
        assertEquals(bonus, test.getWoodBonus());
    }

    @Test
    public void setWoodBonus() throws Exception {
        int bonus = 3;
        test.setWoodBonus(bonus);
        assertEquals(bonus, test.getWoodBonus());
    }

    @Test
    public void getStoneBonus() throws Exception {
        int bonus = 3;
        test.setStoneBonus(bonus);
        assertEquals(bonus, test.getStoneBonus());
    }

    @Test
    public void setStoneBonus() throws Exception {
        int bonus = 3;
        test.setStoneBonus(bonus);
        assertEquals(bonus, test.getStoneBonus());
    }

}