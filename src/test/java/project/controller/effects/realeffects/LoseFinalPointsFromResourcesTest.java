package project.controller.effects.realeffects;

import org.junit.Before;
import org.junit.Test;
import project.controller.checkfunctions.BasicCheckFunctions;
import project.controller.effects.effectsfactory.EffectsConstants;
import project.controller.supportfunctions.*;
import project.model.Board;
import project.server.Room;
import project.server.Server;
import project.server.network.PlayerHandler;
import project.server.network.rmi.RMIPlayerHandler;

import java.io.FileNotFoundException;

import static org.junit.Assert.*;


public class LoseFinalPointsFromResourcesTest {

    private LoseFinalPointsFromResources test;

    private LoseFinalPointsFromResources testScreen = new LoseFinalPointsFromResources(EffectsConstants.CHARACTER_CARD);


    private PlayerHandler p = new RMIPlayerHandler();
    private Board board;

    @Before
    public void before(){
        Room room = new Room(new Server());
        p.setRoom(room);
        p.setCheckFunctions(new BasicCheckFunctions());
        try {
            board = new Board(3);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        room.setBoard(board);
    }

    @Test
    public void doEffect() throws Exception {
        test = new LoseFinalPointsFromResources(EffectsConstants.VICTORY_POINTS);

        test.doEffect(p);
        assertEquals(LoseFinalPointsEveryFive.class, p.getRoom().getMySupportFunction(p).getClass());

        test = new LoseFinalPointsFromResources(EffectsConstants.MILITARY_POINTS);

        test.doEffect(p);
        assertEquals(LoseFinalPointsEveryMilitary.class, p.getRoom().getMySupportFunction(p).getClass());

        test = new LoseFinalPointsFromResources(EffectsConstants.WOOD_STONE);

        test.doEffect(p);
        assertEquals(LoseFinalPointsEveryWS.class, p.getRoom().getMySupportFunction(p).getClass());

        test = new LoseFinalPointsFromResources(EffectsConstants.ALL_RESOURCES);

        test.doEffect(p);
        assertEquals(LoseFinalPointsEveryResources.class, p.getRoom().getMySupportFunction(p).getClass());
    }

    @Test
    public void toScreen() throws Exception {
        assertEquals(null, testScreen.toScreen());
    }


}