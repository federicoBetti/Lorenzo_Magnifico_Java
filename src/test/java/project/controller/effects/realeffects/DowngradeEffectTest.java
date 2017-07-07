package project.controller.effects.realeffects;

import org.junit.Before;
import org.junit.Test;
import project.controller.effects.effectsfactory.EffectsConstants;
import project.model.*;
import project.server.network.PlayerHandler;
import project.server.network.rmi.RMIPlayerHandler;

import static org.junit.Assert.*;

/**
 * Created by federico on 07/07/17.
 */
public class DowngradeEffectTest {

    DowngradeEffect test;
    PlayerHandler p = new RMIPlayerHandler();
    PersonalBoard personalBoard = p.getPersonalBoardReference();
    Bonus bonus = personalBoard.getBonusOnActions();
    DowngradeEffect testScreen = new DowngradeEffect(EffectsConstants.DICES, 1);

    @Before
    public void before(){

        bonus.setProductionBonus(10);
        bonus.setHarvesterBonus(10);

        bonus.setTerritoryBonus(10);

        BonusForTakingBuilding bonusForTakingBuilding = new BonusForTakingBuilding();
        bonusForTakingBuilding.setDiceBonus(10);
        bonus.setBuildingsBonus(bonusForTakingBuilding);


        BonusForTakingCharacter bonusForTakingCharacter = new BonusForTakingCharacter();
        bonusForTakingCharacter.setDiceBonus(10);
        bonus.setCharactersBonus(bonusForTakingCharacter);

        bonus.setVenturesBonus(10);

        FamilyMember familyMember1 = new FamilyMember();
        familyMember1.setMyColour("orange");
        familyMember1.setMyValue(6);
        FamilyMember familyMember2 = new FamilyMember();
        familyMember2.setMyValue(6);
        familyMember2.setMyColour("white");
        FamilyMember familyMember3 = new FamilyMember();
        familyMember3.setMyValue(6);
        familyMember3.setMyColour("black");

        FamilyMember[] all = new FamilyMember[3];
        all[0] = familyMember1;
        all[1] = familyMember2;
        all[2] = familyMember3;
        p.setAllFamilyMembers(all);

    }

    @Test
    public void doEffect() throws Exception {


        test = new DowngradeEffect(EffectsConstants.PRODUCTION, 7);

        test.doEffect(p);
        assertEquals(3, bonus.getProductionBonus());


        test = new DowngradeEffect(EffectsConstants.HARVESTER, 7);

        test.doEffect(p);
        assertEquals(3, bonus.getHarvesterBonus());


        test = new DowngradeEffect(EffectsConstants.TERRITORY_CARD, 7);

        test.doEffect(p);
        assertEquals(3, bonus.getTerritoryBonus());


        test = new DowngradeEffect(EffectsConstants.BUILDING_CARD, 7);

        test.doEffect(p);
        assertEquals(3, bonus.getBuildingsBonus().getDiceBonus());


        test = new DowngradeEffect(EffectsConstants.CHARACTER_CARD, 7);

        test.doEffect(p);
        assertEquals(3, bonus.getCharactersBonus().getDiceBonus());


        test = new DowngradeEffect(EffectsConstants.VENTURES_CARD, 7);

        test.doEffect(p);
        assertEquals(3, bonus.getVenturesBonus());


        test = new DowngradeEffect(EffectsConstants.DICES, 1);

        test.doEffect(p);
        for (FamilyMember f: p.getAllFamilyMembers()){
            assertEquals(5, f.getMyValue());
        }
    }

    @Test
    public void toScreen() throws Exception {
        String s ="The family members'value for doing " + EffectsConstants.DICES + " is downgraded of " + 1 + " points";

        assertEquals(s,testScreen.toScreen());
    }

}