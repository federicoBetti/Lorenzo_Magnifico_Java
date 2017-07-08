package project.controller.effects.effectsfactory;

import org.junit.Before;
import org.junit.Test;
import project.controller.Constants;
import project.controller.checkfunctions.BasicCheckFunctions;
import project.messages.BonusInteraction;
import project.messages.BonusProductionOrHarvesterAction;
import project.messages.OkOrNo;
import project.messages.TakePrivilegesAction;
import project.model.Board;
import project.model.FamilyMember;
import project.model.PersonalBoard;
import project.server.Room;
import project.server.Server;
import project.server.network.PlayerHandler;
import project.server.network.rmi.RMIPlayerHandler;

import java.io.FileNotFoundException;

import static org.junit.Assert.*;

/**
 * Created by federico on 07/07/17.
 */
public class LeaderCardsEffectsTest {
    LeaderCardsEffects test = new LeaderCardsEffects();
    PlayerHandler p = new RMIPlayerHandler();
    PersonalBoard personalBoard = p.getPersonalBoardReference();

    private Board board;
    private Room room = new Room(new Server());

    @Before
    public void before(){
        p.setRoom(room);
        p.setCheckFunctions(new BasicCheckFunctions());
        try {
            board = new Board(3);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        room.setBoard(board);


        FamilyMember familyMember0 = new FamilyMember();
        familyMember0.setMyColour("neutral");
        familyMember0.setMyValue(6);
        FamilyMember familyMember1 = new FamilyMember();
        familyMember1.setMyColour("orange");
        familyMember1.setMyValue(6);
        FamilyMember familyMember2 = new FamilyMember();
        familyMember2.setMyValue(6);
        familyMember2.setMyColour("white");
        FamilyMember familyMember3 = new FamilyMember();
        familyMember3.setMyValue(6);
        familyMember3.setMyColour("black");

        FamilyMember[] all = new FamilyMember[4];
        all[0] = familyMember0;
        all[1] = familyMember1;
        all[2] = familyMember2;
        all[3] = familyMember3;
        p.setAllFamilyMembers(all);
    }

    @Test
    public void doEffect() throws Exception {
        BonusInteraction result = test.doEffect(Constants.FRANCESCO_SFORZA, p);
        assertEquals(BonusProductionOrHarvesterAction.class, result.getClass());

        result = test.doEffect(Constants.LUDVICO_ARIOSTO, p);
        assertEquals(OkOrNo.class, result.getClass());


        result = test.doEffect(Constants.FILIPPO_BRUNELLESCHI, p);
        assertEquals(OkOrNo.class, result.getClass());

        result = test.doEffect(Constants.SIGISMONDO_MALATESTA, p);
        assertEquals(OkOrNo.class, result.getClass());
        assertEquals(9 , p.getAllFamilyMembers()[0].getMyValue());

        result = test.doEffect(Constants.GIROLAMO_SAVONAROLA, p);
        assertEquals(OkOrNo.class, result.getClass());

        result = test.doEffect(Constants.MICHELANGELO_BUONARROTI, p);
        assertEquals(OkOrNo.class, result.getClass());

        result = test.doEffect(Constants.GIOVANNI_DALLE_BANDE_NERE, p);
        assertEquals(OkOrNo.class, result.getClass());

        result = test.doEffect(Constants.LEONARDO_DA_VINCI, p);
        assertEquals(BonusProductionOrHarvesterAction.class, result.getClass());

        result = test.doEffect(Constants.SANDRO_BOTTICELLI, p);
        assertEquals(OkOrNo.class, result.getClass());

        result = test.doEffect(Constants.LUDOVICO_IL_MORO, p);
        assertEquals(OkOrNo.class, result.getClass());
        assertEquals(5, p.getAllFamilyMembers()[1].getMyValue());

        result = test.doEffect(Constants.LUCREZIA_BORGIA, p);
        assertEquals(OkOrNo.class, result.getClass());
        assertEquals(7,p.getAllFamilyMembers()[1].getMyValue());

        result = test.doEffect(Constants.FEDERICO_DA_MONTEFELTRO, p);
        assertEquals(OkOrNo.class, result.getClass());

        result = test.doEffect(Constants.SISTO_IV, p);
        assertEquals(OkOrNo.class, result.getClass());

        result = test.doEffect(Constants.CESARE_BORGIA, p);
        assertEquals(OkOrNo.class, result.getClass());

        result = test.doEffect(Constants.SANDRO_BOTTICELLI, p);
        assertEquals(OkOrNo.class, result.getClass());

        result = test.doEffect(Constants.COSIMO_DE_MEDICI, p);
        assertEquals(OkOrNo.class, result.getClass());

        result = test.doEffect(Constants.BARTOLOMEO_CORLEONI, p);
        assertEquals(OkOrNo.class, result.getClass());

        result = test.doEffect(Constants.LUDOVICO_III_GONZAGA, p);
        assertEquals(TakePrivilegesAction.class, result.getClass());

        result = test.doEffect(Constants.PICO_DELLA_MIRANDOLA, p);
        assertEquals(OkOrNo.class, result.getClass());
    }



}