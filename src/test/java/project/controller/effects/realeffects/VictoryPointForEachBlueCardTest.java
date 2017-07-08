package project.controller.effects.realeffects;

import org.junit.Test;
import project.controller.cardsfactory.CharacterCard;
import project.controller.cardsfactory.CharactersCost;
import project.controller.cardsfactory.TerritoryCard;
import project.controller.cardsfactory.TerritoryCost;
import project.server.network.PlayerHandler;
import project.server.network.rmi.RMIPlayerHandler;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * effect test
 */
public class VictoryPointForEachBlueCardTest {

    VictoryPointForEachBlueCard test = new VictoryPointForEachBlueCard(2);
    @Test
    public void doEffect() throws Exception {
        PlayerHandler p = new RMIPlayerHandler();
        CharacterCard characterCard = new CharacterCard("ciao",1,false,new CharactersCost(1), new ArrayList<>(), new ArrayList<>());
        p.getPersonalBoardReference().getCharacters().add(characterCard);
        p.getPersonalBoardReference().getCharacters().add(characterCard);

        p.getScore().setVictoryPoints(10);

        test.doEffect(p);
        assertEquals(14, p.getScore().getVictoryPoints());
    }

    @Test
    public void toScreen() throws Exception {
        String s = "Take 2 victory points for each blue card";
        assertEquals(s, test.toScreen());
    }

}