package project.controller.checkfunctions;

import org.junit.Before;
import org.junit.Test;
import project.controller.Constants;
import project.controller.effects.effectsfactory.TrisIE;
import project.model.FamilyMember;
import project.model.Market;
import project.model.Position;
import project.model.Tower;

import static org.junit.Assert.*;

/**
 * Created by federico on 04/07/17.
 */
public class CantPlaceFamiliarInMarketTest {
    AllCheckFunctions test;

    @Before
    public void start(){
        test = new BasicCheckFunctions();
        test = new CantPlaceFamiliarInMarket(test);
    }

    //public boolean checkPosition(int position, Position[] zone, FamilyMember familyMember) {
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
        f2.setFamilyColour(Constants.GREEN);
        p2.setFamiliarOnThisPosition(f2);

        FamilyMember familyMember = new FamilyMember();
        familyMember.setFamilyColour(Constants.BLUE);

        boolean ret = test.checkPosition(posiition,zone,familyMember);

        assertEquals(true,ret);
        zone = new Market[3];
        p0 = new Market(new TrisIE("takeRop","coin",0));
        p2 = new Market(new TrisIE("takeRop","coin",0));
        p1 = new Market(new TrisIE("takeRop","coin",0));

        zone[0] = p0;
        zone[1] = p1;
        zone[2] = p2;

        ret = test.checkPosition(posiition,zone,familyMember);
        assertEquals(false,ret);
    }

}