package project.controller.effects.realeffects;

import org.junit.Test;
import project.controller.Constants;
import project.messages.TakePrivilegesAction;
import project.server.network.PlayerHandler;
import project.server.network.rmi.RMIPlayerHandler;

import static org.junit.Assert.*;

/**
 * effect test
 */
public class UsePrivilegeTest {

    UsePrivilege test  = new UsePrivilege(3);

    @Test
    public void doEffect() throws Exception {
        TakePrivilegesAction tp = new TakePrivilegesAction(3);
        PlayerHandler p = new RMIPlayerHandler();
        assertEquals(tp.getClass(), test.doEffect(p).getClass());
    }

    @Test
    public void toScreen() throws Exception {
        String s = "Take " + 3 + " different priviledges";
        assertEquals(s, test.toScreen());
    }

}