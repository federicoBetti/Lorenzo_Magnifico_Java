package project.controller.checkfunctions;

import org.junit.Before;
import org.junit.Test;
import project.controller.Constants;
import project.model.FamilyMember;
import project.model.Position;
import project.model.Tower;

import static org.junit.Assert.*;

/**
 * class to test LudovicoAriostoCheck
 */
public class LudovicoAriostoCheckTest {
    AllCheckFunctions test;

    @Before
    public void start(){
        test = new BasicCheckFunctions();
        test = new LudovicoAriostoCheck(test);
    }

    @Test
    public void checkPosition() throws Exception {
        Position[] zone = new Position[3];
        int posiition = 0;

        Position p0 = new Tower();
        zone[0] = p0;


        Position p1 = new Tower();
        zone[1] = p1;
        FamilyMember f1 = new FamilyMember();
        f1.setFamilyColour(Constants.RED);
        p1.setFamiliarOnThisPosition(f1);

        Position p2 = new Tower();
        zone[2] = p2;
        FamilyMember f2 = new FamilyMember();
        f2.setFamilyColour(Constants.BLUE);
        p2.setFamiliarOnThisPosition(f2);

        FamilyMember familyMember = new FamilyMember();
        familyMember.setFamilyColour(Constants.BLUE);

        boolean ret = test.checkPosition(posiition,zone,familyMember);

        assertEquals(false,ret);

        posiition = 1;

        ret = test.checkPosition(posiition,zone,familyMember);
        assertEquals(false,ret);

        f2.setFamilyColour(Constants.RED);
        ret = test.checkPosition(posiition,zone,familyMember);
        assertEquals(true,ret);
    }

}