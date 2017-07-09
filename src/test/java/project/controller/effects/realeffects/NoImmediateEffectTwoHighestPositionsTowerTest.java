package project.controller.effects.realeffects;

import org.junit.Test;
import project.messages.OkOrNo;
import project.server.Room;
import project.server.Server;
import project.server.network.PlayerHandler;
import project.server.network.rmi.RMIPlayerHandler;

import static org.junit.Assert.*;

/**
 * effect test
 */
public class NoImmediateEffectTwoHighestPositionsTowerTest {
    NoImmediateEffectTwoHighestPositionsTower test = new NoImmediateEffectTwoHighestPositionsTower();

    @Test
    public void doEffect() throws Exception {
        PlayerHandler p = new RMIPlayerHandler();
        Room room = new Room(new Server());
        p.setRoom(room);
        assertEquals(OkOrNo.class, test.doEffect(p).getClass());
    }

    @Test
    public void toScreen() throws Exception {
        String s = "You will not take more the two highest places in each tower";
        assertEquals(s, test.toScreen());
    }

}