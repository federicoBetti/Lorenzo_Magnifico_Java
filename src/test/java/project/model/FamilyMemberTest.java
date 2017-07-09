package project.model;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * model test
 */
public class FamilyMemberTest {

    private FamilyMember test = new FamilyMember();

    @Test
    public void getFamilyColour() throws Exception {
        String colour = "blue";
        test.setFamilyColour(colour);
        assertEquals(colour, test.getFamilyColour());
    }

    @Test
    public void setFamilyColour() throws Exception {
        String colour = "blue";
        test.setFamilyColour(colour);
        assertEquals(colour, test.getFamilyColour());
    }

    @Test
    public void getFixedBonus() throws Exception {
        test.setFixedBonus(3);
        assertEquals(3, test.getFixedBonus());
    }

    @Test
    public void setFixedBonus() throws Exception {
        test.setMyValue(5);
        test.setFixedBonus(3);
        assertEquals(8, test.getMyValue());
    }

    @Test
    public void getMyColour() throws Exception {
        String colour = "orange";
        test.setMyColour(colour);
        assertEquals(colour, test.getMyColour());
    }

    @Test
    public void setMyColour() throws Exception {
        String colour = "orange";
        test.setMyColour(colour);
        assertEquals(colour, test.getMyColour());
    }

    @Test
    public void getMyValue() throws Exception {
        test.setMyValue(5);
        test.setFixedBonus(3);
        assertEquals(8, test.getMyValue());
    }

    @Test
    public void setMyValue() throws Exception {
        test.setMyValue(5);
        test.setFixedValue(2);
        assertEquals(2, test.getMyValue());
    }

    @Test
    public void isPlayed() throws Exception {
        assertEquals(false, test.isPlayed());
    }

    @Test
    public void setPlayed() throws Exception {
        test.setPlayed(true);
        assertEquals(true, test.isPlayed());
    }

    @Test
    public void setFixedValue() throws Exception {
        test.setFixedValue(2);
        assertEquals(2, test.getMyValue());

    }


}