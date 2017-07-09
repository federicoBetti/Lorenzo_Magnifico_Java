package project.model;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * bonus test
 */
public class BonusTest {

    private Bonus test = new Bonus();

    @Test
    public void getVenturesBonus() throws Exception {
        int bonus = 2;
        test.setVenturesBonus(bonus);
        assertEquals(bonus, test.getVenturesBonus());
    }

    @Test
    public void setVenturesBonus() throws Exception {
        int bonus = 2;
        test.setVenturesBonus(bonus);
        assertEquals(bonus, test.getVenturesBonus());
    }

    @Test
    public void getTerritoryBonus() throws Exception {
        int bonus = 2;
        test.setTerritoryBonus(bonus);
        assertEquals(bonus, test.getTerritoryBonus());
    }

    @Test
    public void setTerritoryBonus() throws Exception {
        int bonus = 2;
        test.setTerritoryBonus(bonus);
        assertEquals(bonus, test.getTerritoryBonus());
    }

    @Test
    public void getProductionBonus() throws Exception {
        int bonus = 2;
        test.setProductionBonus(bonus);
        assertEquals(bonus, test.getProductionBonus());
    }

    @Test
    public void setProductionBonus() throws Exception {
        int bonus = 2;
        test.setProductionBonus(bonus);
        assertEquals(bonus, test.getProductionBonus());
    }

    @Test
    public void getHarvesterBonus() throws Exception {
        int bonus = 2;
        test.setHarvesterBonus(bonus);
        assertEquals(bonus, test.getHarvesterBonus());
    }

    @Test
    public void setHarvesterBonus() throws Exception {
        int bonus = 2;
        test.setHarvesterBonus(bonus);
        assertEquals(bonus, test.getHarvesterBonus());
    }

    @Test
    public void setCharactersBonus() throws Exception {
        BonusForTakingCharacter charaterBonus = new BonusForTakingCharacter();
        test.setCharactersBonus(charaterBonus);
        assertEquals(charaterBonus, test.getCharactersBonus());
    }

    @Test
    public void getBuildingsBonus() throws Exception {
        BonusForTakingBuilding buildingBonus = new BonusForTakingBuilding();
        test.setBuildingsBonus(buildingBonus);
        assertEquals(buildingBonus, test.getBuildingsBonus());
    }

    @Test
    public void setBuildingsBonus() throws Exception {
    }

    @Test
    public void getCharactersBonus() throws Exception {
        BonusForTakingCharacter charaterBonus = new BonusForTakingCharacter();
        test.setCharactersBonus(charaterBonus);
        assertEquals(charaterBonus, test.getCharactersBonus());
    }

    @Test
    public void getMilitaryPointsBonus() throws Exception {
        int bonus = 2;
        test.setMilitaryPointsBonus(bonus);
        assertEquals(bonus, test.getMilitaryPointsBonus());
    }

    @Test
    public void setMilitaryPointsBonus() throws Exception {
        int bonus = 2;
        test.setMilitaryPointsBonus(bonus);
        assertEquals(bonus, test.getMilitaryPointsBonus());
    }

    @Test
    public void getCoinsBonus() throws Exception {
        int bonus = 2;
        test.setCoinsBonus(bonus);
        assertEquals(bonus, test.getCoinsBonus());
    }

    @Test
    public void setCoinsBonus() throws Exception {
        int bonus = 2;
        test.setCoinsBonus(bonus);
        assertEquals(bonus, test.getCoinsBonus());
    }

    @Test
    public void getServantsBonus() throws Exception {
        int bonus = 2;
        test.setServantsBonus(bonus);
        assertEquals(bonus, test.getServantsBonus());
    }

    @Test
    public void setServantsBonus() throws Exception {
        int bonus = 2;
        test.setServantsBonus(bonus);
        assertEquals(bonus, test.getServantsBonus());
    }

    @Test
    public void getStoneBonus() throws Exception {
        int bonus = 2;
        test.setStoneBonus(bonus);
        assertEquals(bonus, test.getStoneBonus());
    }

    @Test
    public void setStoneBonus() throws Exception {
        int bonus = 2;
        test.setStoneBonus(bonus);
        assertEquals(bonus, test.getStoneBonus());
    }

    @Test
    public void getWoodBonus() throws Exception {
        int bonus = 2;
        test.setWoodBonus(bonus);
        assertEquals(bonus, test.getWoodBonus());
    }

    @Test
    public void setWoodBonus() throws Exception {
        int bonus = 2;
        test.setWoodBonus(bonus);
        assertEquals(bonus, test.getWoodBonus());
    }

}