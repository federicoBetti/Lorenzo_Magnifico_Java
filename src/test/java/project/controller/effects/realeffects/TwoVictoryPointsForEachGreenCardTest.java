package project.controller.effects.realeffects;

import org.junit.Test;
import project.controller.cardsfactory.TerritoryCard;
import project.controller.cardsfactory.TerritoryCost;
import project.server.network.PlayerHandler;
import project.server.network.rmi.RMIPlayerHandler;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * effect test
 */
public class TwoVictoryPointsForEachGreenCardTest {

    TwoVictoryPointsForEachGreenCard test = new TwoVictoryPointsForEachGreenCard();
    @Test
    public void doEffect() throws Exception {
        PlayerHandler p = new RMIPlayerHandler();
        TerritoryCard territoryCard = new TerritoryCard("ciao",1,false,new TerritoryCost(1,1,1), new ArrayList<>(), new ArrayList<>());
        p.getPersonalBoardReference().getTerritories().add(territoryCard);

        p.getScore().setVictoryPoints(10);

        test.doEffect(p);
        assertEquals(12, p.getScore().getVictoryPoints());
    }

    @Test
    public void toScreen() throws Exception {
        String s = "Take 2 victory points for each green card";
        assertEquals(s, test.toScreen());
    }

}