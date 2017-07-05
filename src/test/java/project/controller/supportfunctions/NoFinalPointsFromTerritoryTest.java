package project.controller.supportfunctions;

import org.junit.Test;
import project.server.network.PlayerHandler;
import project.server.network.socket.SocketPlayerHandler;

import static org.junit.Assert.*;

/**
 * Created by raffaelebongo on 05/07/17.
 */
public class NoFinalPointsFromTerritoryTest {
    @Test
    public void finalPointsFromTerritoryCard() throws Exception {
        PlayerHandler player = new SocketPlayerHandler();
        BasicSupportFunctions bsf = new BasicSupportFunctions(player);
        NoFinalPointsFromTerritory nof = new NoFinalPointsFromTerritory(bsf);
        int[] victoryPoints = new int[4];
        int res = nof.finalPointsFromTerritoryCard( victoryPoints );

        assertEquals(0, res);
    }

}