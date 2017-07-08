package project.controller.effects.realeffects;

import org.junit.Test;
import project.controller.cardsfactory.CharacterCard;
import project.controller.cardsfactory.CharactersCost;
import project.controller.cardsfactory.VenturesCard;
import project.model.PersonalBoard;
import project.server.network.PlayerHandler;
import project.server.network.rmi.RMIPlayerHandler;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * effect test
 */
public class VictoryPointsForEachPurpleCardTest {
    VictoryPointsForEachPurpleCard test = new VictoryPointsForEachPurpleCard(2);
    @Test
    public void doEffect() throws Exception {
        PlayerHandler p = new RMIPlayerHandler();
        PersonalBoard personalBoard = p.getPersonalBoardReference();
        VenturesCard venturesCard = new VenturesCard("c",0,false,new ArrayList<>(), new ArrayList<>(), new ArrayList<>());

        personalBoard.getVentures().add(venturesCard);
        personalBoard.getVentures().add(venturesCard);
        personalBoard.getVentures().add(venturesCard);
        personalBoard.getVentures().add(venturesCard);
        personalBoard.getVentures().add(venturesCard);
        personalBoard.getVentures().add(venturesCard);
        p.getScore().setVictoryPoints(10);

        test.doEffect(p);
        assertEquals(22, p.getScore().getVictoryPoints());
    }

    @Test
    public void toScreen() throws Exception {
        String s = "Take 2 victory points for each purple card";
        assertEquals(s, test.toScreen());
    }
}