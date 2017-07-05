package project.controller.supportfunctions;

import org.junit.Test;
import project.controller.cardsfactory.BuildingCard;
import project.controller.cardsfactory.BuildingCost;
import project.model.PersonalBoard;
import project.model.Score;
import project.server.network.PlayerHandler;
import project.server.network.socket.SocketPlayerHandler;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by raffaelebongo on 05/07/17.
 */
public class LoseFinalPointsEveryWSTest {
    @Test
    public void extraLostOfPoints() throws Exception {
        PlayerHandler player = new SocketPlayerHandler();
        PersonalBoard personalBoard = new PersonalBoard();
        List<BuildingCard> buildingCards = new ArrayList<>();
        BuildingCard buildingCard1 = new BuildingCard();
        BuildingCost cost = new BuildingCost();
        cost.setStoneRequired(5);
        cost.setWoodRequired(3);
        buildingCard1.setCardCost(cost);

        BuildingCost cost1 = new BuildingCost();
        cost1.setWoodRequired(2);
        cost1.setStoneRequired(3);

        BuildingCard buildingCard2 = new BuildingCard();
        buildingCard2.setCardCost(cost1);

        buildingCards.add(buildingCard1);
        buildingCards.add(buildingCard2);
        personalBoard.setBuildings(buildingCards);
        player.setPersonalBoardReference(personalBoard);

        BasicSupportFunctions bsf = new BasicSupportFunctions(player);
        LoseFinalPointsEveryWS lp = new LoseFinalPointsEveryWS(bsf);

        int res = lp.extraLostOfPoints(player);

        assertEquals(13, res );
    }

}