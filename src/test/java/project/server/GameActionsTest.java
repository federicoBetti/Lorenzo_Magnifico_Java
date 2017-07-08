package project.server;

import org.junit.Before;
import org.junit.Test;
import project.controller.cardsfactory.*;
import project.controller.effects.effectsfactory.EffectsConstants;
import project.controller.effects.effectsfactory.PokerPE;
import project.controller.effects.effectsfactory.TrisIE;
import project.controller.supportfunctions.AllSupportFunctions;
import project.controller.supportfunctions.BasicSupportFunctions;
import project.model.*;
import project.server.network.PlayerHandler;
import project.server.network.rmi.RMIPlayerHandler;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * game action test
 * the nullPointerException exceptions are thrown by RMI and Socket Connection that cannot be tested
 */
public class GameActionsTest {
    private Room room = new Room(new Server());
    private GameActions test = new GameActions(room);
    private PlayerHandler p = new RMIPlayerHandler();
    private PlayerHandler playerHandler2 = new RMIPlayerHandler();
    private PlayerHandler playerHandler3 = new RMIPlayerHandler();
    private PlayerHandler playerHandler4 = new RMIPlayerHandler();
    private Tower towerZone = new Tower("blue", 7, 2 , new TrisIE("takeRop","coin",1));
    private FamilyMember familyMember =  new FamilyMember();
    private boolean towerIsOccupied;
    private AllSupportFunctions supportFunctions = new BasicSupportFunctions(p);
    private VenturesCard venturesCard;
    private CharacterCard characterCard;
    private Board board;
    private TerritoryCard territoryCard;
    private BuildingCard buildingCard;

    @Before
    public void before(){
        try {
            board = new Board(4);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        room.setBoard(board);
        p.setRoom(room);
        test.setBoard(board);
        familyMember.setMyValue(6);
        familyMember.setFixedBonus(-1);
        towerIsOccupied = true;
        room.setMySupportFunction(supportFunctions,p);

        p.getPersonalBoardReference().setCoins(10);
        p.getPersonalBoardReference().setServants(10);
        p.getPersonalBoardReference().setWood(10);
        p.getPersonalBoardReference().setStone(10);
        p.getScore().setMilitaryPoints(10);
        p.getScore().setVictoryPoints(10);
        p.getScore().setFaithPoints(10);


        FamilyMember[] familyMembers = new FamilyMember[4];
        familyMembers[0] = familyMember;
        familyMembers[1] = new FamilyMember();
        familyMembers[2] = new FamilyMember();
        familyMembers[3] = new FamilyMember();
        p.setAllFamilyMembers(familyMembers);


        characterCard = new CharacterCard("prova",1,false,new CharactersCost(7), new ArrayList<>(), new ArrayList<>());
        List<PokerPE> harvester = new ArrayList<>();
        harvester.add(new PokerPE("takeRop", "coin" , 1));
        territoryCard = new TerritoryCard("prova",1,false,new TerritoryCost(5,5,5), new ArrayList<>(),harvester);
        p.getPersonalBoardReference().getTerritories().add(territoryCard);

        buildingCard = new BuildingCard("prova", 1, false, new BuildingCost(1,1,1,1,6), new ArrayList<>(), harvester);
        p.getPersonalBoardReference().getBuildings().add(buildingCard);


        p.getPersonalBoardReference().setMyTile(new Tile(new TrisIE("takeRop" , "coin" , 1), new TrisIE("takeRop" , "coin" , 1)));

        List<VenturesCost> costs = new ArrayList<>();
        VenturesCost venturesCost1 = new VenturesCost();
        venturesCost1.setWoodRequired(5);
        venturesCost1.setCoinsRequired(5);
        venturesCost1.setStoneRequired(5);
        venturesCost1.setMilitaryRequired(0);
        venturesCost1.setMilitaryCost(0);

        VenturesCost venturesCost2 = new VenturesCost();
        venturesCost2.setMilitaryCost(2);
        venturesCost2.setMilitaryRequired(5);
        venturesCost2.setStoneRequired(0);
        venturesCost2.setWoodRequired(0);
        venturesCost2.setCoinsRequired(0);

        costs.add(venturesCost1);
        costs.add(venturesCost2);

        venturesCard = new VenturesCard("prova", 1, false, costs, new ArrayList<>(), new ArrayList<>());


        room.nicknamePlayersMap.put("fede", p);
        room.nicknamePlayersMap.put("fede", playerHandler2);
        room.nicknamePlayersMap.put("fede", playerHandler3);
        room.nicknamePlayersMap.put("fede", playerHandler4);
        room.setMySupportFunction(new BasicSupportFunctions(playerHandler2), playerHandler2);
        room.setMySupportFunction(new BasicSupportFunctions(playerHandler3), playerHandler3);
        room.setMySupportFunction(new BasicSupportFunctions(playerHandler4), playerHandler4);
        List<PlayerHandler> playerTurn = new ArrayList<>();
        playerTurn.add(p);
        playerTurn.add(playerHandler2);
        playerTurn.add(playerHandler3);
        playerTurn.add(playerHandler4);


        playerHandler2.setAllFamilyMembers(familyMembers);
        playerHandler3.setAllFamilyMembers(familyMembers);
        playerHandler4.setAllFamilyMembers(familyMembers);

        p.getRoom().getBoard().getTurn().setPlayerTurn(playerTurn);


    }

    @Test
    public void takeNoVenturesCard() throws Exception {

        towerZone.setCardOnThisFloor(characterCard);
        test.takeNoVenturesCard(towerZone,familyMember,p,towerIsOccupied);

        assertEquals(1, p.getPersonalBoardReference().getCharacters().size());
        assertEquals(1, p.getPersonalBoardReference().getCoins());
        assertEquals(8, p.getPersonalBoardReference().getServants());
    }

    @Test
    public void takeNoVenturesCard1() throws Exception {

        towerZone.setCardOnThisFloor(characterCard);
        test.takeNoVenturesCard(towerZone,p,towerIsOccupied,familyMember.getMyValue());


        assertEquals(1, p.getPersonalBoardReference().getCharacters().size());
        assertEquals(1, p.getPersonalBoardReference().getCoins());
        assertEquals(8, p.getPersonalBoardReference().getServants());
    }

    @Test
    public void takeVenturesCard() throws Exception {
        towerZone.setCardOnThisFloor(venturesCard);

        test.takeVenturesCard(towerZone,familyMember,p,towerIsOccupied,0);

        assertEquals(1, p.getPersonalBoardReference().getVentures().size());
        assertEquals(3, p.getPersonalBoardReference().getCoins());
        assertEquals(8, p.getPersonalBoardReference().getServants());
        assertEquals(5, p.getPersonalBoardReference().getStone());
        assertEquals(5, p.getPersonalBoardReference().getWood());
    }

    @Test
    public void takeVenturesCard1() throws Exception {

        towerZone.setCardOnThisFloor(venturesCard);

        test.takeVenturesCard(towerZone,p,towerIsOccupied,1,familyMember.getMyValue());

        assertEquals(1, p.getPersonalBoardReference().getVentures().size());
        assertEquals(8, p.getScore().getMilitaryPoints());

    }

    @Test
    public void nextTurn() throws Exception {
        test.nextTurn(p);
    }

    @Test
    public void firstPlayerTurn() throws Exception {
        test.firstPlayerTurn();
    }

    @Test
    public void setBoard() throws Exception {
        test.setBoard(board);
        assertEquals(board, room.getBoard());
    }


    @Test
    public void harvester() throws Exception {
        test.harvester(2,familyMember,3,p);

        assertEquals(12, p.getPersonalBoardReference().getCoins());
        assertEquals(7, p.getPersonalBoardReference().getServants());
    }

    @Test
    public void harvesterBonus() throws Exception {
        test.harvesterBonus(5, 3, p);
        assertEquals(12, p.getPersonalBoardReference().getCoins());
        assertEquals(7, p.getPersonalBoardReference().getServants());
    }

    @Test
    public void production() throws Exception {
        List<BuildingCard> buildingCards = new ArrayList<>();
        buildingCards.add(buildingCard);

        assertEquals(0, board.getProductionZone().size());

        test.production(3, familyMember, buildingCards, 0, new ArrayList<>() , p);

        assertEquals(12 , p.getPersonalBoardReference().getCoins());
        assertEquals(10, p.getPersonalBoardReference().getServants());

        test.production(3, familyMember, buildingCards, 1, new ArrayList<>() , p);

        assertEquals(14 , p.getPersonalBoardReference().getCoins());
        assertEquals(9, p.getPersonalBoardReference().getServants());

        assertEquals(2, board.getProductionZone().size());
    }

    @Test
    public void productionBonus() throws Exception {

        List<BuildingCard> buildingCards = new ArrayList<>();
        buildingCards.add(buildingCard);

        test.productionBonus(buildingCards,0,new ArrayList<>(), p);

        assertEquals(12 , p.getPersonalBoardReference().getCoins());
        assertEquals(10, p.getPersonalBoardReference().getServants());

        test.productionBonus(buildingCards, 1, new ArrayList<>() , p);

        assertEquals(14 , p.getPersonalBoardReference().getCoins());
        assertEquals(9, p.getPersonalBoardReference().getServants());
    }

    @Test
    public void goToMarket() throws Exception {
        Market market = new Market(new TrisIE("takeRop" , "coin" , 5));
        Market[] markets = new Market[1];
        markets[0] = market;
        board.setMarketZone(markets);

        test.goToMarket(0,familyMember,p);

        assertEquals(15, p.getPersonalBoardReference().getCoins());
    }

    @Test
    public void playLeaderCard() throws Exception {
        LeaderCard l = new LeaderCard();
        l.setName("michelangeloBuonarroti");
        p.getPersonalBoardReference().getMyLeaderCard().add(l);
        test.playLeaderCard("michelangeloBuonarroti", p);
        assertEquals(13, p.getPersonalBoardReference().getCoins());
    }

    @Test
    public void discardLeaderCard() throws Exception {
        LeaderCard l = new LeaderCard();
        l.setName("michelangeloBuonarroti");
        p.getPersonalBoardReference().getMyLeaderCard().add(l);
        test.discardLeaderCard("michelangeloBuonarroti", p);
        assertEquals(0, p.getPersonalBoardReference().getMyLeaderCard().size());
    }

    @Test
    public void rollDice() throws Exception {
        test.rollDice();
        int resultFirst = p.getAllFamilyMembers()[0].getMyValue();
        for (PlayerHandler p: board.getTurn().getPlayerTurn()){
            assertEquals(resultFirst, p.getAllFamilyMembers()[0].getMyValue());
        }
    }

    @Test
    public void goToCouncilPalace() throws Exception {
        test.goToCouncilPalace(1,familyMember,p);

        assertEquals(11, p.getPersonalBoardReference().getCoins());
        assertEquals(10, p.getPersonalBoardReference().getStone());
        assertEquals(10, p.getPersonalBoardReference().getWood());
    }

    @Test
    public void takeCouncilPrivilege() throws Exception {
        CouncilPrivilege privilege = new CouncilPrivilege(new TrisIE("takeRop", "servant",  2), 0 );
        CouncilPrivilege[] privileges = new CouncilPrivilege[1];
        privileges[0] = privilege;
        board.setCouncilPrivileges(privileges);
        test.takeCouncilPrivilege(0,p);

        assertEquals(10, p.getPersonalBoardReference().getCoins());
        assertEquals(12, p.getPersonalBoardReference().getServants());
    }

    @Test
    public void pray() throws Exception {
        int[] faithPoints = new int[20];
        faithPoints[p.getScore().getFaithPoints()] = 3;
        board.setVictoryPointsInFaithTrack(faithPoints);
        test.pray(p);

        assertEquals(13, p.getScore().getVictoryPoints());
        assertEquals(0, p.getScore().getFaithPoints());
    }

    @Test
    public void takeExcommunication() throws Exception {
        ExcommunicationZone[] excomm = new ExcommunicationZone[1];
        excomm[0] = new ExcommunicationZone(new ExcommunicationTile(1,1,new TrisIE(EffectsConstants.LOSE_RESOURCE, EffectsConstants.COINS , 1), "ciao"));
        board.setExcommunicationZone(excomm);

        test.takeExcommunication(p);

        assertEquals(-1, p.getPersonalBoardReference().getBonusOnActions().getCoinsBonus());
        assertEquals(10, p.getScore().getFaithPoints());
    }

    @Test
    public void myTimerSkipTurn() throws Exception {
    }

    @Test
    public void myTimerActions() throws Exception {
    }

}