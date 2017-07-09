package project.model;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * model test
 */
public class PlayerTest {

    private Player test = new Player();

    @Test
    public void getName() throws Exception {
        String name = "fede";
        test.setName(name);
        assertEquals(name, test.getName());
    }

    @Test
    public void isOn() throws Exception {
        assertEquals(false, test.isOn());
    }

    @Test
    public void setOn() throws Exception {
        test.setOn(true);
        assertEquals(true, test.isOn());
    }

    @Test
    public void setName() throws Exception {
        String name = "fede";
        test.setName(name);
        assertEquals(name, test.getName());
    }

    @Test
    public void getPersonalBoardReference() throws Exception {
        PersonalBoard pB = new PersonalBoard();
        test.setPersonalBoardReference(pB);
        assertEquals(pB, test.getPersonalBoardReference());
    }

    @Test
    public void setPersonalBoardReference() throws Exception {
        PersonalBoard pB = new PersonalBoard();
        test.setPersonalBoardReference(pB);
        assertEquals(pB, test.getPersonalBoardReference());
    }

    @Test
    public void getScore() throws Exception {
        Score score = new Score();
        test.setScore(score);
        assertEquals(score, test.getScore());
    }

    @Test
    public void setScore() throws Exception {
        Score score = new Score();
        test.setScore(score);
        assertEquals(score, test.getScore());
    }

    @Test
    public void getAllFamilyMembers() throws Exception {
        FamilyMember[] familiars = new FamilyMember[4];
        test.setAllFamilyMembers(familiars);
        assertEquals(4 , test.getAllFamilyMembers().length);
    }

    @Test
    public void setAllFamilyMembers() throws Exception {
        FamilyMember[] familiars = new FamilyMember[4];
        test.setAllFamilyMembers(familiars);
        assertEquals(4 , test.getAllFamilyMembers().length);
    }

    @Test
    public void getFamilyColour() throws Exception {
        String color = "red";
        test.setFamilyColour(color);
        assertEquals(color, test.getFamilyColour());
    }

    @Test
    public void setFamilyColour() throws Exception {
        String color = "red";
        test.setFamilyColour(color);
        assertEquals(color, test.getFamilyColour());
    }

    @Test
    public void setFamilyColourInFamilyMembers() throws Exception {
        String color = "red";
        test.setFamilyColour(color);
        test.setFamilyColourInFamilyMembers();
        assertEquals(color, test.getAllFamilyMembers()[1].getFamilyColour());
    }

}