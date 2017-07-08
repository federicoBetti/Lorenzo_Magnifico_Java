package project.controller.effects.effectsfactory;

import org.junit.Test;
import project.controller.effects.realeffects.LoseResourceEffect;

import static org.junit.Assert.*;

/**
 * Created by federico on 07/07/17.
 */
public class BuildExcommunicationEffectsTest {

    BuildExcommunicationEffects test = new BuildExcommunicationEffects();



    @Test
    public void searchEffectType() throws Exception {
        assertEquals(LoseResourceEffect.class, test.searchEffectType(EffectsConstants.LOSE_RESOURCE,"", 3).getClass());
    }

}