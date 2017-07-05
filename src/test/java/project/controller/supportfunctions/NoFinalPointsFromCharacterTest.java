package project.controller.supportfunctions;

import org.junit.Test;
import project.server.network.PlayerHandler;
import project.server.network.socket.SocketPlayerHandler;

import static org.junit.Assert.*;

/**
 * Created by raffaelebongo on 05/07/17.
 */
public class NoFinalPointsFromCharacterTest {
    @Test
    public void finalPointsFromCharacterCard() throws Exception {

        PlayerHandler player = new SocketPlayerHandler();
        BasicSupportFunctions bsf = new BasicSupportFunctions(player);
        NoFinalPointsFromCharacter nof = new NoFinalPointsFromCharacter(bsf);
        int[] victoryPoints = new int[4];
        int res = nof.finalPointsFromCharacterCard( victoryPoints );

        assertEquals(0, res);
    }

}