package project.controller.supportfunctions;

import org.junit.Test;
import project.controller.effects.realeffects.AddCoin;
import project.server.network.PlayerHandler;
import project.server.network.rmi.RMIPlayerHandler;

import static org.junit.Assert.*;

/**
 * Created by raffaelebongo on 05/07/17.
 */
public class SantaRitaTest {
    @Test
    public void applyEffects() throws Exception {

        PlayerHandler p = new RMIPlayerHandler();

        AddCoin addCoin = new AddCoin(5);

        p.getPersonalBoardReference().setCoins(0);

        BasicSupportFunctions bsf = new BasicSupportFunctions(p);
        SantaRita santaRita = new SantaRita(bsf);

        santaRita.applyEffects(addCoin, p);

        assertEquals(10, p.getPersonalBoardReference().getCoins());
    }

}