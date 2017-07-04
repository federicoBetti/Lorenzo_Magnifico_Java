package project.controller.checkfunctions;

import org.junit.Before;
import org.junit.Test;
import project.server.network.PlayerHandler;
import project.server.network.rmi.RMIPlayerHandler;

import static org.junit.Assert.*;

/**
 * Created by federico on 04/07/17.
 */
public class DoubleServantsValueTest {
    AllCheckFunctions AC;
    @Before
    public void setUp() throws Exception {
        AC = new BasicCheckFunctions();
        AC = new DoubleServantsValue(AC);
    }
    @Test
    public void getServants() throws Exception {
        PlayerHandler p = new RMIPlayerHandler();
        p.getPersonalBoardReference().setServants(5);
        assertEquals(2,AC.getServants(p));
    }

}