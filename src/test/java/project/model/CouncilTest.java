package project.model;

import org.junit.Before;
import org.junit.Test;
import project.controller.Constants;
import project.server.network.PlayerHandler;
import project.server.network.rmi.RMIPlayerHandler;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by federico on 04/07/17.
 */
public class CouncilTest {
    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void findPlayer() throws Exception {
        FamilyMember familiar = new FamilyMember();
        familiar.setFamilyColour(Constants.RED);

        List<PlayerHandler> playerList = new ArrayList<>();

        PlayerHandler p1 = new RMIPlayerHandler();
        p1.setFamilyColour(Constants.RED);
        PlayerHandler p2 = new RMIPlayerHandler();
        p2.setFamilyColour(Constants.BLUE);

        playerList.add(p1);
        playerList.add(p2);

        Council test = new Council(familiar);

        PlayerHandler outPlayer = test.findPlayer(playerList);

        assertEquals(p1,outPlayer);
    }

}