package project.controller.effects.realeffects;

import org.junit.Before;
import org.junit.Test;
import project.controller.Constants;
import project.controller.effects.effectsfactory.EffectsConstants;
import project.model.Bonus;
import project.model.PersonalBoard;
import project.model.Score;
import project.server.network.PlayerHandler;
import project.server.network.rmi.RMIPlayerHandler;

import static org.junit.Assert.*;

/**
 * Created by federico on 07/07/17.
 */
public class LoseResourceEffectTest {
    LoseResourceEffect test;
    PlayerHandler p = new RMIPlayerHandler();
    PersonalBoard personalBoard = p.getPersonalBoardReference();
    Bonus bonus = personalBoard.getBonusOnActions();

    @Before
    public void before(){
        personalBoard.setStone(10);
        personalBoard.setWood(10);
        personalBoard.setServants(10);
        personalBoard.setCoins(10);
        p.setScore(new Score());
        p.getScore().setMilitaryPoints(10);

        bonus.setMilitaryPointsBonus(10);
        bonus.setWoodBonus(10);
        bonus.setStoneBonus(10);
        bonus.setCoinsBonus(10);
        bonus.setServantsBonus(10);
    }

    @Test
    public void doEffect() throws Exception {
        test = new LoseResourceEffect(EffectsConstants.MILITARY_POINTS, 7);

        test.doEffect(p);
        assertEquals(3, bonus.getMilitaryPointsBonus());


        test = new LoseResourceEffect(EffectsConstants.WOOD_STONE, 7);

        test.doEffect(p);
        assertEquals(3, bonus.getWoodBonus());
        assertEquals(3, bonus.getStoneBonus());


        test = new LoseResourceEffect(EffectsConstants.COINS, 7);

        test.doEffect(p);
        assertEquals(3, bonus.getCoinsBonus());


        test = new LoseResourceEffect(EffectsConstants.SERVANTS, 7);

        test.doEffect(p);
        assertEquals(3, bonus.getServantsBonus());
    }

}