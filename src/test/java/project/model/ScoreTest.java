package project.model;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * model test
 */
public class ScoreTest {

    private Score test = new Score();

    @Test
    public void toScreen() throws Exception {
        String s =
         "Victory points: " + 0 + "\n" +
                "Faith points: " + 0 + "\n" +
                "Military Points: " + 0;
        assertEquals(s, test.toScreen());
    }

    @Test
    public void getVictoryPoints() throws Exception {
        int points = 3;
        test.setVictoryPoints(points);
        assertEquals(3, test.getVictoryPoints());
    }

    @Test
    public void setVictoryPoints() throws Exception {
        int points = 3;
        test.setVictoryPoints(points);
        assertEquals(3, test.getVictoryPoints());
    }

    @Test
    public void getMilitaryPoints() throws Exception {
        int points = 3;
        test.setMilitaryPoints(points);
        assertEquals(3, test.getMilitaryPoints());
    }

    @Test
    public void setMilitaryPoints() throws Exception {
        int points = 3;
        test.setMilitaryPoints(points);
        assertEquals(3, test.getMilitaryPoints());
    }

    @Test
    public void getFaithPoints() throws Exception {
        int points = 3;
        test.setFaithPoints(points);
        assertEquals(3, test.getFaithPoints());
    }

    @Test
    public void setFaithPoints() throws Exception {
        int points = 3;
        test.setFaithPoints(points);
        assertEquals(3, test.getFaithPoints());
    }

}