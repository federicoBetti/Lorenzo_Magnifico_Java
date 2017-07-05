package project.controller.cardsfactory;

import org.junit.Test;
import project.model.PersonalBoard;

import static org.junit.Assert.*;

/**
 * Created by raffaelebongo on 04/07/17.
 */
public class BuildingCardTest {
    @Test
    public void addToPersonalBoard() throws Exception {
        PersonalBoard personalBoard = new PersonalBoard();
        BuildingCard card = new BuildingCard();
        card.setName("hello");

        card.addToPersonalBoard(personalBoard);

        assertEquals("hello", personalBoard.getBuildings().get(personalBoard.getBuildings().size() - 1).getName());
    }

    @Test
    public void getCost() throws Exception {
        BuildingCard card = new BuildingCard();
        BuildingCost cost = new BuildingCost();
        cost.setCoinsRequired(5);

        card.setCardCost(cost);

        BuildingCost costOut = card.getCost();
        assertEquals(5, costOut.getCoinsRequired());
    }

    @Test
    public void isChoicePe() throws Exception {
        BuildingCard card = new BuildingCard();
        card.setChoicePe(true);

        boolean res = card.isChoicePe();

        assertEquals(true, res);
    }

    @Test
    public void getPermanentCardEffects() throws Exception {
    }

    @Test
    public void getImmediateCardEffects() throws Exception {
    }

    @Test
    public void getName() throws Exception {
        BuildingCard card = new BuildingCard();
        card.setName("hello");

        String output = card.getName();

        assertEquals("hello", output);
    }
    
}