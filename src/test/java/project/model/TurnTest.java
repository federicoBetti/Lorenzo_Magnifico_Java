package project.model;

import org.junit.Test;
import project.server.network.PlayerHandler;
import project.server.network.rmi.RMIPlayerHandler;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * model test
 */
public class TurnTest {
    private Turn test = new Turn();

    @Test
    public void getPlayerTurn() throws Exception {
        List<PlayerHandler> list = new ArrayList<>();
        test.setPlayerTurn(list);
        assertEquals(0, test.getPlayerTurn().size());
    }

    @Test
    public void setPlayerTurn() throws Exception {
        List<PlayerHandler> list = new ArrayList<>();
        test.setPlayerTurn(list);
        assertEquals(0, test.getPlayerTurn().size());
    }

    @Test
    public void getRotation() throws Exception {
        int rotation = 1;
        test.setRotation(rotation);
        assertEquals(1, test.getRotation());
    }

    @Test
    public void setRotation() throws Exception {
        int rotation = 1;
        test.setRotation(rotation);
        assertEquals(1, test.getRotation());
    }

    @Test
    public void fillLists() throws Exception {
        test.getPlayerTurn().add(new RMIPlayerHandler());
        test.getPlayerTurn().add(new RMIPlayerHandler());
        test.fillLists();
    }

    @Test
    public void getPlayersColor() throws Exception {
        assertEquals(null, test.getPlayersColor());
    }

    @Test
    public void getPlayerName() throws Exception {
        List<String> list = new ArrayList<>();
        test.setPlayerName(list);
        assertEquals(0, test.getPlayerName().size());
    }

    @Test
    public void setPlayerName() throws Exception {
        List<String> list = new ArrayList<>();
        test.setPlayerName(list);
        assertEquals(0, test.getPlayerName().size());
    }

    @Test
    public void addSkipTurn() throws Exception {
        test.addSkipTurn(new RMIPlayerHandler());
        assertEquals(1, test.getSkipTurnForExcommunication().size());
    }

    @Test
    public void getSkipTurnForExcommunication() throws Exception {
        assertEquals(0, test.getSkipTurnForExcommunication().size());
    }

}