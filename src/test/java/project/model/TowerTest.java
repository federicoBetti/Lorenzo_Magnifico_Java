package project.model;

import org.junit.Test;
import project.controller.cardsfactory.TerritoryCard;
import project.controller.cardsfactory.TerritoryCost;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * model test
 */
public class TowerTest {
    private Tower test = new Tower("green" , 1, 0, null);

    @Test
    public void getCardOnThisFloor() throws Exception {
        TerritoryCard territoryCard = new TerritoryCard("prova", 1, false, new TerritoryCost(5, 5, 5), new ArrayList<>(), new ArrayList<>());
        test.setCardOnThisFloor(territoryCard);
        assertEquals(territoryCard, test.getCardOnThisFloor());
    }

    @Test
    public void setCardOnThisFloor() throws Exception {
        TerritoryCard territoryCard = new TerritoryCard("prova", 1, false, new TerritoryCost(5, 5, 5), new ArrayList<>(), new ArrayList<>());
        test.setCardOnThisFloor(territoryCard);
        assertEquals(territoryCard, test.getCardOnThisFloor());
    }

    @Test
    public void getDiceValueOfThisFloor() throws Exception {
        assertEquals(1, test.getDiceValueOfThisFloor());
    }

    @Test
    public void getTowerZoneEffect() throws Exception {
        assertEquals("", test.getTowerZoneEffect().toScreen());
    }

    @Test
    public void getColour() throws Exception {
        assertEquals("green", test.getColour());
    }

}