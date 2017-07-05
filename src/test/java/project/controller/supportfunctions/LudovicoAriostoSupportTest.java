package project.controller.supportfunctions;

import org.junit.Test;
import project.model.FamilyMember;
import project.model.PersonalBoard;
import project.model.Score;
import project.model.Tower;
import project.server.network.PlayerHandler;
import project.server.network.socket.SocketPlayerHandler;

import static org.junit.Assert.*;

/**
 * Created by raffaelebongo on 05/07/17.
 */
public class LudovicoAriostoSupportTest {
    @Test
    public void setFamiliar() throws Exception {
        PlayerHandler player = new SocketPlayerHandler();
        Score score = new Score();
        score.setVictoryPoints(20);
        PersonalBoard personalBoard = new PersonalBoard();
        personalBoard.setCoins(5);
        personalBoard.setServants(5);
        personalBoard.setWood(5);
        personalBoard.setStone(5);
        player.setPersonalBoardReference(personalBoard);
        Tower position = new Tower();
        FamilyMember familyMember = new FamilyMember();

        BasicSupportFunctions bsf = new BasicSupportFunctions(player);
        LudovicoAriostoSupport ludovicoAriostoSupport = new LudovicoAriostoSupport(bsf);

        ludovicoAriostoSupport.setFamiliar(position,familyMember);

        assertEquals(familyMember, position.getLudovicoAriostoPosition().get(0));
    }

}