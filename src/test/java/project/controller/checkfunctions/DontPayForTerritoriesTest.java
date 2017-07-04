package project.controller.checkfunctions;

import org.junit.Before;
import org.junit.Test;
import project.model.Board;
import project.server.Room;
import project.server.Server;
import project.server.network.PlayerHandler;
import project.server.network.rmi.RMIPlayerHandler;

import static org.junit.Assert.*;

/**
 * Created by federico on 04/07/17.
 */
public class DontPayForTerritoriesTest {
    AllCheckFunctions AC;
    @Before
    public void setUp() throws Exception {
        AC = new BasicCheckFunctions();
        AC = new DontPayForTerritories(AC);
    }

    @Test
    public void checkMilitaryPointsForTerritory() throws Exception {
        PlayerHandler p1 = new RMIPlayerHandler();
        Room r = new Room(new Server());

        Board b = new Board(2);
        r.setBoard(b);
        p1.setRoom(r);
        p1.getScore().setMilitaryPoints(-1);
        int len = 2;
        boolean ret = AC.checkMilitaryPointsForTerritory(p1,len);

        assertEquals(true,ret);

        len = 4;
        ret = AC.checkMilitaryPointsForTerritory(p1,len);

        assertEquals(true,ret);
    }

}