package project.controller.effects.realeffects;

import org.junit.Before;
import org.junit.Test;
import project.controller.checkfunctions.BasicCheckFunctions;
import project.controller.checkfunctions.CantPlaceFamiliarInMarket;
import project.controller.checkfunctions.DoubleServantsValue;
import project.controller.effects.effectsfactory.EffectsConstants;
import project.controller.supportfunctions.DoubleServantsPayment;
import project.model.Board;
import project.server.Room;
import project.server.Server;
import project.server.network.PlayerHandler;
import project.server.network.rmi.RMIPlayerHandler;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Created by federico on 07/07/17.
 */
public class SpecialExcommunicationEffectsTest {

    SpecialExcommunicationEffects test;

    SpecialExcommunicationEffects testScreen = new SpecialExcommunicationEffects(EffectsConstants.MARKET);


    PlayerHandler p = new RMIPlayerHandler();
    Board board = null;
    private Room room;

    @Before
    public void before(){
        room = new Room(new Server());
        try {
            board = new Board(3);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        room.setBoard(board);
        p.setRoom(room);
        p.setCheckFunctions(new BasicCheckFunctions());
    }


    @Test
    public void doEffect() throws Exception {
        test = new SpecialExcommunicationEffects(EffectsConstants.MARKET);

        test.doEffect(p);
        assertEquals(CantPlaceFamiliarInMarket.class, p.getCheckFunctions().getClass());

        test = new SpecialExcommunicationEffects(EffectsConstants.SERVANTS);

        test.doEffect(p);
        assertEquals(DoubleServantsValue.class, p.getCheckFunctions().getClass());
        assertEquals(DoubleServantsPayment.class, p.getRoom().getMySupportFunction(p).getClass());

        test = new SpecialExcommunicationEffects(EffectsConstants.TURN);

        test.doEffect(p);
        ArrayList<PlayerHandler> excomm = new ArrayList<>();
        excomm.add(p);
        assertEquals(excomm , p.getRoom().getBoard().getTurn().getSkipTurnForExcommunication());
    }

    @Test
    public void toScreen() throws Exception {
        assertEquals(null, testScreen.toScreen());
    }

}