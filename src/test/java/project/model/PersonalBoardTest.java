package project.model;

import org.junit.Test;
import project.controller.cardsfactory.BuildingCard;
import project.controller.effects.effectsfactory.TrisIE;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * model test
 */
public class PersonalBoardTest {

    private PersonalBoard test = new PersonalBoard();

    @Test
    public void toScreen() throws Exception {
        String s = "Building Cards: \n " +
                "\nCharacter Cards: \n " +
                "\nVenture Cards: \n " +
                "\nTerritory Cards: \n " +
                "\nWood: " + 0 + "\n" +
                "Servants: " +0 +  "\n" +
                "Stone: " + 0 +  "\n" +
                "Coins: " + 0;
        assertEquals(s, test.toScreen());
    }

    @Test
    public void getMyLeaderCard() throws Exception {
        assertEquals(0, test.getMyLeaderCard().size());
    }

    @Test
    public void getTerritories() throws Exception {
        assertEquals(0, test.getTerritories().size());

    }

    @Test
    public void getBuildings() throws Exception {
        assertEquals(0, test.getBuildings().size());
    }

    @Test
    public void getVentures() throws Exception {
        assertEquals(0, test.getVentures().size());
    }

    @Test
    public void getCharacters() throws Exception {
        assertEquals(0, test.getCharacters().size());
    }

    @Test
    public void getBonusOnActions() throws Exception {
        Bonus bonus = new Bonus();
        test.setBonusOnActions(bonus);
        assertEquals(bonus, test.getBonusOnActions());
    }

    @Test
    public void setBonusOnActions() throws Exception {
        Bonus bonus = new Bonus();
        test.setBonusOnActions(bonus);
        assertEquals(bonus, test.getBonusOnActions());
    }

    @Test
    public void getMyTile() throws Exception {
        Tile tile = new Tile(new TrisIE("takeRop", "coin", 5) , new TrisIE("takeRop", "coin", 5));
        test.setMyTile(tile);
        assertEquals(tile.takeHarvesterResource(), test.getMyTile().takeHarvesterResource());
    }

    @Test
    public void setMyTile() throws Exception {
        Tile tile = new Tile(new TrisIE("takeRop", "coin", 5) , new TrisIE("takeRop", "coin", 5));
        test.setMyTile(tile);
        assertEquals(tile.takeHarvesterResource(), test.getMyTile().takeHarvesterResource());
    }

    @Test
    public void getStone() throws Exception {
        int valueOfResource = 3;
        test.setStone(valueOfResource);
        assertEquals(3, test.getStone());
    }

    @Test
    public void setStone() throws Exception {
        int valueOfResource = 3;
        test.setStone(valueOfResource);
        assertEquals(3, test.getStone());
    }

    @Test
    public void getWood() throws Exception {
        int valueOfResource = 3;
        test.setWood(valueOfResource);
        assertEquals(3, test.getWood());
    }

    @Test
    public void setWood() throws Exception {
        int valueOfResource = 3;
        test.setWood(valueOfResource);
        assertEquals(3, test.getWood());
    }

    @Test
    public void getCoins() throws Exception {
        int valueOfResource = 3;
        test.setCoins(valueOfResource);
        assertEquals(3, test.getCoins());
    }

    @Test
    public void setCoins() throws Exception {
        int valueOfResource = 3;
        test.setCoins(valueOfResource);
        assertEquals(3, test.getCoins());
    }

    @Test
    public void getServants() throws Exception {
        int valueOfResource = 3;
        test.setServants(valueOfResource);
        assertEquals(3, test.getServants());
    }

    @Test
    public void setServants() throws Exception {
        int valueOfResource = 3;
        test.setServants(valueOfResource);
        assertEquals(3, test.getServants());
    }

    @Test
    public void setBuildings() throws Exception {
        List<BuildingCard> buildings = new ArrayList<>();
        test.setBuildings(buildings);
        assertEquals(0, test.getBuildings().size());

    }

    @Test
    public void addCoins() throws Exception {
        test.setCoins(1);
        test.getBonusOnActions().setCoinsBonus(-1);
        test.addCoins(1);
        assertEquals(1, test.getCoins());
        test.addCoins(-1);
        assertEquals(0, test.getCoins());
        test.addCoins(-1);
        assertEquals(0, test.getCoins());

    }

    @Test
    public void addStone() throws Exception {
        test.setStone(1);
        test.getBonusOnActions().setStoneBonus(-1);
        test.addStone(1);
        assertEquals(1, test.getStone());
        test.addStone(-1);
        assertEquals(0, test.getStone());
        test.addStone(-1);
        assertEquals(0, test.getStone());
    }

    @Test
    public void addWood() throws Exception {
        test.setWood(1);
        test.getBonusOnActions().setWoodBonus(-1);
        test.addWood(1);
        assertEquals(1, test.getWood());
        test.addWood(-1);
        assertEquals(0, test.getWood());
        test.addWood(-1);
        assertEquals(0, test.getWood());
    }

    @Test
    public void addServants() throws Exception {
        test.setServants(1);
        test.getBonusOnActions().setServantsBonus(-1);
        test.addServants(1);
        assertEquals(1, test.getServants());
        test.addServants(-1);
        assertEquals(0, test.getServants());
        test.addServants(-1);
        assertEquals(0, test.getServants());
    }

}