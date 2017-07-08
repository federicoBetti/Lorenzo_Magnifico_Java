package project.controller.effects.realeffects;

import org.junit.Before;
import org.junit.Test;
import project.controller.checkfunctions.BasicCheckFunctions;
import project.controller.effects.effectsfactory.EffectsConstants;
import project.controller.supportfunctions.NoFinalPointsFromCharacter;
import project.controller.supportfunctions.NoFinalPointsFromTerritory;
import project.controller.supportfunctions.NoFinalPointsFromVentures;
import project.model.Board;
import project.server.Room;
import project.server.Server;
import project.server.network.PlayerHandler;
import project.server.network.rmi.RMIPlayerHandler;

import java.io.FileNotFoundException;

import static org.junit.Assert.*;

/**
 * Created by federico on 07/07/17.
 */
public class NoFinalPointsFromCardsTest {

    private NoFinalPointsFromCards testScreen = new NoFinalPointsFromCards(EffectsConstants.CHARACTER_CARD);


    private PlayerHandler p = new RMIPlayerHandler();
    private Board board;

    @Before
    public void before(){
        Room room = new Room(new Server());
        p.setCheckFunctions(new BasicCheckFunctions());
        try {
            board = new Board(3);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        room.setBoard(board);
        p.setRoom(room);
    }

    @Test
    public void doEffect() throws Exception {
        NoFinalPointsFromCards test = new NoFinalPointsFromCards(EffectsConstants.CHARACTER_CARD);

        test.doEffect(p);
        assertEquals(NoFinalPointsFromCharacter.class, p.getRoom().getMySupportFunction(p).getClass());

        test = new NoFinalPointsFromCards(EffectsConstants.VENTURES_CARD);

        test.doEffect(p);
        assertEquals(NoFinalPointsFromVentures.class, p.getRoom().getMySupportFunction(p).getClass());

        test = new NoFinalPointsFromCards(EffectsConstants.TERRITORY_CARD);

        test.doEffect(p);
        assertEquals(NoFinalPointsFromTerritory.class, p.getRoom().getMySupportFunction(p).getClass());
    }

    @Test
    public void toScreen() throws Exception {
        assertEquals(null, testScreen.toScreen());
    }

}