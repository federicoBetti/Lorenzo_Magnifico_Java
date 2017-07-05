package project.controller.checkfunctions;

import org.junit.Before;
import org.junit.Test;
import project.controller.Constants;
import project.controller.cardsfactory.*;
import project.controller.effects.effectsfactory.TrisIE;
import project.model.*;
import project.server.Room;
import project.server.Server;
import project.server.network.PlayerHandler;
import project.server.network.rmi.RMIPlayerHandler;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by federico on 04/07/17.
 */
public class BasicCheckFunctionsTest {

    BasicCheckFunctions BC;
    Position[] zone;
    Tower[] towerZone;

    @Before
    public void createParameters(){
        BC = new BasicCheckFunctions();
        zone = new Position[3];
        towerZone = new Tower[3];
    }
    @Test
    public void checkPosition() throws Exception {
        int posiition = 1;

        Position p0 = new Tower();
        zone[0] = p0;


        Position p1 = new Tower();
        zone[1] = p1;
        FamilyMember f1 = new FamilyMember();
        f1.setFamilyColour(Constants.RED);
        p1.setFamiliarOnThisPosition(f1);

        Position p2 = new Tower();
        zone[2] = p2;
        FamilyMember f2 = new FamilyMember();
        f2.setFamilyColour(Constants.GREEN);
        p2.setFamiliarOnThisPosition(f2);

        FamilyMember familyMember = new FamilyMember();
        familyMember.setFamilyColour(Constants.BLUE);

        boolean ret = BC.checkPosition(posiition,zone,familyMember);

        assertEquals(true,ret);
    }

    @Test
    public void checkTowerOccupied() throws Exception {
        Tower p0 = new Tower();
        towerZone[0] = p0;
        p0.setOccupied(false);


        Tower p1 = new Tower();
        towerZone[1] = p1;
        p1.setOccupied(false);

        Tower p2 = new Tower();
        towerZone[2] = p2;
        p2.setOccupied(false);

        assertEquals(false,BC.checkTowerOccupied(towerZone));

        p2.setOccupied(true);

        assertEquals(true,BC.checkTowerOccupied(towerZone));
    }



    @Test
    public void checkCardCost() throws Exception {
        DevelopmentCard card = new TerritoryCard("prova",1,false,new TerritoryCost(3,3,3), new ArrayList<TrisIE>(),new ArrayList<>());
        PlayerHandler p = new RMIPlayerHandler();
        boolean coinsFee = true;
        int zoneDiceCost = 5;
        int valueOfFM = 3;

        p.getPersonalBoardReference().setServants(1);
        p.getPersonalBoardReference().setWood(3);
        p.getPersonalBoardReference().setStone(4);
        p.getPersonalBoardReference().setCoins(3);

        p.getPersonalBoardReference().getTerritories().add(new TerritoryCard());
        p.getPersonalBoardReference().getTerritories().add(new TerritoryCard());
        p.getPersonalBoardReference().getTerritories().add(new TerritoryCard());
        p.getPersonalBoardReference().getTerritories().add(new TerritoryCard());


        Room r = new Room(new Server());
        Board b = new Board(2);
        r.setBoard(b);
        p.setRoom(r);
        p.getScore().setMilitaryPoints(-1);
        int len = 2;

        boolean ret = BC.checkCardCost(card,p,coinsFee,zoneDiceCost,valueOfFM);
        assertEquals(false,ret);

        p.getPersonalBoardReference().setServants(2);
        ret = BC.checkCardCost(card,p,coinsFee,zoneDiceCost,valueOfFM);
        assertEquals(false,ret);

        p.getScore().setMilitaryPoints(7);
        ret = BC.checkCardCost(card,p,coinsFee,zoneDiceCost,valueOfFM);
        assertEquals(true,ret);

        card = new BuildingCard("test",1,true,new BuildingCost(2,1,0,0,0), new ArrayList<>(), new ArrayList<>());
        ret = BC.checkCardCost(card,p,coinsFee,zoneDiceCost,valueOfFM);
        assertEquals(false,ret);


        p.getPersonalBoardReference().setServants(3);
        ret = BC.checkCardCost(card,p,coinsFee,zoneDiceCost,valueOfFM);
        assertEquals(false,ret);

        p.getPersonalBoardReference().setCoins(3+2);
        ret = BC.checkCardCost(card,p,coinsFee,zoneDiceCost,valueOfFM);
        assertEquals(true,ret);
    }

    @Test
    public void getServants() throws Exception {
        PlayerHandler p = new RMIPlayerHandler();

        p.getPersonalBoardReference().setServants(1);
        assertEquals(1,p.getPersonalBoardReference().getServants());
    }

    //public int checkCardCostVentures(VenturesCard card, PlayerHandler player, boolean coinsFee, int zoneDiceCost, int valueOfFamilyMember){
    @Test
    public void checkCardCostVentures() throws Exception {
        List<VenturesCost> venturesCost = new ArrayList<>();
        VenturesCost cost1 = new VenturesCost(3,3,4,0,0);
        VenturesCost cost2 = new VenturesCost(0,0,0,10,5);
        venturesCost.add(cost1);
        venturesCost.add(cost2);

        VenturesCard card = new VenturesCard("prova",1,false,venturesCost, new ArrayList<TrisIE>(),new ArrayList<>());

        PlayerHandler p = new RMIPlayerHandler();
        boolean coinsFee = true;
        int zoneDiceCost = 5;
        int valueOfFM = 3;

        p.getPersonalBoardReference().setServants(2);
        p.getPersonalBoardReference().setWood(3);
        p.getPersonalBoardReference().setStone(4);
        p.getPersonalBoardReference().setCoins(4 + 3);
        p.getScore().setMilitaryPoints(6);


        int ret = BC.checkCardCostVentures(card,p,coinsFee,zoneDiceCost,valueOfFM);
        assertEquals(1,ret);


        p.getScore().setMilitaryPoints(10);
        ret = BC.checkCardCostVentures(card,p,coinsFee,zoneDiceCost,valueOfFM);
        assertEquals(3,ret);
    }


   // public boolean checkMilitaryPointsForTerritory(PlayerHandler player, int length) {
    @Test
    public void checkMilitaryPointsForTerritory() throws Exception {
        PlayerHandler p1 = new RMIPlayerHandler();
        Room r = new Room(new Server());

        Board b = new Board(2);
        r.setBoard(b);
        p1.setRoom(r);
        p1.getScore().setMilitaryPoints(-1);
        int len = 2;
        boolean ret = BC.checkMilitaryPointsForTerritory(p1,len);

        assertEquals(false,ret);
    }

}