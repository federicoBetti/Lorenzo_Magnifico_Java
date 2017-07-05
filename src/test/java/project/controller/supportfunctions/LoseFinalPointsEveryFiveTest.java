package project.controller.supportfunctions;

import org.junit.Test;
import project.model.Score;
import project.server.network.PlayerHandler;
import project.server.network.socket.SocketPlayerHandler;

import static org.junit.Assert.*;

/**
 * Created by raffaelebongo on 05/07/17.
 */
public class LoseFinalPointsEveryFiveTest {
    @Test
    public void extraLostOfPoints() throws Exception {
        PlayerHandler player = new SocketPlayerHandler();
        Score score = new Score();
        score.setVictoryPoints(5);
        player.setScore(score);

        BasicSupportFunctions bsf = new BasicSupportFunctions(player);
        LoseFinalPointsEveryFive lfp = new LoseFinalPointsEveryFive(bsf);

        int res = lfp.extraLostOfPoints(player);

        assertEquals(1, res);
    }

}