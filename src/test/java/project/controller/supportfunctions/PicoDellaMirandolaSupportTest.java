package project.controller.supportfunctions;

import org.junit.Test;
import project.controller.cardsfactory.*;
import project.model.DevelopmentCard;
import project.model.PersonalBoard;
import project.model.Score;
import project.server.network.PlayerHandler;
import project.server.network.rmi.RMIPlayerHandler;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by raffaelebongo on 05/07/17.
 */
public class PicoDellaMirandolaSupportTest {
    @Test
    public void payCard() throws Exception {
        PlayerHandler p = new RMIPlayerHandler();
        DevelopmentCard card = new CharacterCard("prova",1,false,new CharactersCost(4), new ArrayList<>(),new ArrayList<>());
        boolean coinsFee = false;
        int zoneDiceCost = 5;
        int valueOfFM = 3;

        p.getPersonalBoardReference().setCoins(10);

        BasicSupportFunctions bsf = new BasicSupportFunctions(p);
        PicoDellaMirandolaSupport pico = new PicoDellaMirandolaSupport(bsf);

        pico.payCard(card, coinsFee, zoneDiceCost, valueOfFM);

        assertEquals(9, p.getPersonalBoardReference().getCoins());
    }

    @Test
    public void payVenturesCard() throws Exception {
        PlayerHandler p = new RMIPlayerHandler();
        PersonalBoard personalBoard = new PersonalBoard();
        personalBoard.setWood(10);
        personalBoard.setStone(10);
        personalBoard.setCoins(10);
        Score score = new Score();
        score.setMilitaryPoints(10);

        p.setPersonalBoardReference(personalBoard);
        p.setScore(score);
        List<VenturesCost> costs = new ArrayList<>();
        VenturesCost venturesCost = new VenturesCost(3, 2, 3, 0, 0);
        VenturesCost venturesCost1 = new VenturesCost(0, 0, 0, 6, 3);
        costs.add(venturesCost);
        costs.add(venturesCost1);
        VenturesCard card = new VenturesCard("prova", 1, false, costs, new ArrayList<>(), new ArrayList<>());
        boolean coinsFee = false;
        int zoneDiceCost = 5;
        int valueOfFM = 3;
        int paymentChoosen = 0;

        BasicSupportFunctions bsf = new BasicSupportFunctions(p);
        PicoDellaMirandolaSupport pico = new PicoDellaMirandolaSupport(bsf);

        pico.payVenturesCard(card, p, coinsFee, zoneDiceCost, valueOfFM, paymentChoosen);

        assertEquals(10, p.getPersonalBoardReference().getCoins());
        assertEquals(7, p.getPersonalBoardReference().getStone());
        assertEquals(8, p.getPersonalBoardReference().getWood());

    }

}