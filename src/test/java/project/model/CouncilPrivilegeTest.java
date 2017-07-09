package project.model;

import org.junit.Test;
import project.controller.effects.effectsfactory.TrisIE;
import project.controller.effects.realeffects.AddCoin;
import project.controller.effects.realeffects.Effects;

import static org.junit.Assert.*;

/**
 * bonus test
 */
public class CouncilPrivilegeTest {

    private CouncilPrivilege test = new CouncilPrivilege(new TrisIE("takeRop", "coin",2), 2);

    @Test
    public void getEffect() throws Exception {
        Effects effect = new AddCoin(3);
        test.setEffect(effect);
        assertEquals(effect.toScreen(), test.getEffect().toScreen());
    }

    @Test
    public void setEffect() throws Exception {
        Effects effect = new AddCoin(3);
        test.setEffect(effect);
        assertEquals(effect.toScreen(), test.getEffect().toScreen());
    }

}