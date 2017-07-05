package project.controller.supportfunctions;

import org.junit.Test;
import project.model.PersonalBoard;
import project.model.Score;
import project.server.network.PlayerHandler;
import project.server.network.socket.SocketPlayerHandler;

import static org.junit.Assert.*;

/**
 * Created by raffaelebongo on 05/07/17.
 */
public class LoseFinalPointsEveryResourcesTest {
    @Test
    public void extraLostOfPoints() throws Exception {
        PlayerHandler player = new SocketPlayerHandler();
        Score score = new Score();
        score.setVictoryPoints(20);
        PersonalBoard personalBoard = new PersonalBoard();
        personalBoard.setCoins(5);
        personalBoard.setServants(5);
        personalBoard.setWood(5);
        personalBoard.setStone(5);
        player.setPersonalBoardReference(personalBoard);

        BasicSupportFunctions bsf = new BasicSupportFunctions(player);
        LoseFinalPointsEveryResources lfp = new LoseFinalPointsEveryResources(bsf);

        lfp.extraLostOfPoints(player);

        assertEquals(0, player.getScore().getVictoryPoints());


    }

}