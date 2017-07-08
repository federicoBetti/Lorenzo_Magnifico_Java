package project.controller.effects.realeffects;

import org.junit.Test;
import project.controller.cardsfactory.BuildingCard;
import project.controller.cardsfactory.BuildingCost;
import project.controller.cardsfactory.TerritoryCard;
import project.controller.cardsfactory.TerritoryCost;
import project.server.network.PlayerHandler;
import project.server.network.rmi.RMIPlayerHandler;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * effect test
 */
public class TwoVictoryPointsForEachYellowCardTest {

    TwoVictoryPointsForEachYellowCard test = new TwoVictoryPointsForEachYellowCard();
    @Test
    public void doEffect() throws Exception {
        PlayerHandler p = new RMIPlayerHandler();
        BuildingCard buildingCard = new BuildingCard("ciao",1,false,new BuildingCost(1,1,1,1,1), new ArrayList<>(), new ArrayList<>());
        p.getPersonalBoardReference().getBuildings().add(buildingCard);

        p.getScore().setVictoryPoints(10);

        test.doEffect(p);
        assertEquals(12, p.getScore().getVictoryPoints());
    }

    @Test
    public void toScreen() throws Exception {
        String s = "Take 2 victory points for each yellow card";
        assertEquals(s, test.toScreen());
    }


}