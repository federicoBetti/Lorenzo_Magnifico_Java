package project.controller.supportfunctions;

import org.junit.Test;
import project.model.Score;
import project.server.network.PlayerHandler;
import project.server.network.socket.SocketPlayerHandler;

import static org.junit.Assert.*;

/**
 * Created by raffaelebongo on 05/07/17.
 */
public class LoseFinalPointsEveryMilitaryTest {
    @Test
    public void extraLostOfPoints() throws Exception {
        PlayerHandler player = new SocketPlayerHandler();
        Score score = new Score();
        score.setMilitaryPoints(5);
        player.setScore(score);

        BasicSupportFunctions bsf = new BasicSupportFunctions(player);
        LoseFinalPointsEveryMilitary lfp = new LoseFinalPointsEveryMilitary(bsf);

        lfp.extraLostOfPoints(player);

        assertEquals(5, player.getScore().getMilitaryPoints());
    }

}