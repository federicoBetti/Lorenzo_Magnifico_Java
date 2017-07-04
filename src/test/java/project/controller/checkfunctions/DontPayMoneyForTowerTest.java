package project.controller.checkfunctions;

import org.junit.Before;
import org.junit.Test;
import project.model.Tower;

import static org.junit.Assert.*;

/**
 * Created by federico on 04/07/17.
 */
public class DontPayMoneyForTowerTest {
    AllCheckFunctions AC;
    @Before
    public void setUp() throws Exception {
        AC = new BasicCheckFunctions();
        AC = new DontPayMoneyForTower(AC);
    }

    @Test
    public void checkTowerOccupied() throws Exception {
        assertEquals(false,AC.checkTowerOccupied(new Tower[3]));
    }

}