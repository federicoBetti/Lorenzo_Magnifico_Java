package project.controller.supportfunctions;

import org.junit.Test;
import project.model.Score;
import project.server.network.PlayerHandler;
import project.server.network.socket.SocketPlayerHandler;

import static org.junit.Assert.*;

/**
 * Created by raffaelebongo on 05/07/17.
 */
public class FivePointsMoreForPrayTest {
    @Test
    public void pray() throws Exception {
        PlayerHandler player = new SocketPlayerHandler();
        Score score = new Score();
        score.setFaithPoints(3);
        score.setVictoryPoints(0);
        player.setScore(score);

        AllSupportFunctions bsf = new BasicSupportFunctions(player);
        FivePointsMoreForPray dsp = new FivePointsMoreForPray(bsf);

        int victoryPoints = 5;

        dsp.pray(victoryPoints);

        assertEquals(0, player.getScore().getFaithPoints());
        assertEquals(10, player.getScore().getVictoryPoints());


    }

}