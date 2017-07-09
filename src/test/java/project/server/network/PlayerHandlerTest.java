package project.server.network;

import org.junit.Before;
import org.junit.Test;
import project.controller.Constants;
import project.controller.cardsfactory.*;
import project.controller.checkfunctions.BasicCheckFunctions;
import project.controller.effects.effectsfactory.EffectsConstants;
import project.controller.effects.effectsfactory.PokerPE;
import project.controller.effects.effectsfactory.TrisIE;
import project.controller.supportfunctions.AllSupportFunctions;
import project.controller.supportfunctions.BasicSupportFunctions;
import project.messages.BonusProductionOrHarvesterAction;
import project.messages.TowerAction;
import project.model.*;
import project.server.Room;
import project.server.Server;
import project.server.network.exception.CantDoActionException;
import project.server.network.rmi.RMIPlayerHandler;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * player handler gameActions class
 */
public class PlayerHandlerTest {


    private Room room = new Room(new Server());
    private PlayerHandler test = new RMIPlayerHandler();
    private PlayerHandler playerHandler2 = new RMIPlayerHandler();
    private PlayerHandler playerHandler3 = new RMIPlayerHandler();
    private PlayerHandler playerHandler4 = new RMIPlayerHandler();
    private FamilyMember familyMember =  new FamilyMember();
    private AllSupportFunctions supportFunctions = new BasicSupportFunctions(test);
    private VenturesCard venturesCard;
    private Board board;
    private BuildingCard buildingCard;
    private Tower[][] towers;
    private BasicCheckFunctions checkFunctions;

    @Before
    public void before(){
        try {
            board = new Board(4);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        test.setName("fede");
        room.setBoard(board);
        room.getGameActions().setBoard(board);
        test.setRoom(room);
        checkFunctions = new BasicCheckFunctions();
        test.setCheckFunctions(checkFunctions);

        familyMember.setMyValue(6);
        familyMember.setFixedBonus(-1);
        familyMember.setMyColour("neutral");
        room.setMySupportFunction(supportFunctions, test);

        test.getPersonalBoardReference().setCoins(10);
        test.getPersonalBoardReference().setServants(10);
        test.getPersonalBoardReference().setWood(10);
        test.getPersonalBoardReference().setStone(10);
        test.getScore().setMilitaryPoints(10);
        test.getScore().setVictoryPoints(10);
        test.getScore().setFaithPoints(10);


        FamilyMember[] familyMembers = new FamilyMember[4];
        familyMembers[0] = familyMember;
        familyMembers[1] = new FamilyMember();
        familyMembers[1].setMyColour("orange");
        familyMembers[2] = new FamilyMember();
        familyMembers[2].setMyColour("black");
        familyMembers[3] = new FamilyMember();
        familyMembers[3].setMyColour("white");
        test.setAllFamilyMembers(familyMembers);


        CharacterCard characterCard = new CharacterCard("prova", 1, false, new CharactersCost(7), new ArrayList<>(), new ArrayList<>());
        List<PokerPE> harvester = new ArrayList<>();
        harvester.add(new PokerPE("takeRop", "coin" , 1));
        TerritoryCard territoryCard = new TerritoryCard("prova", 1, false, new TerritoryCost(5, 5, 5), new ArrayList<>(), harvester);
        test.getPersonalBoardReference().getTerritories().add(territoryCard);

        buildingCard = new BuildingCard("prova", 1, false, new BuildingCost(1,1,1,1,6), new ArrayList<>(), harvester);
        test.getPersonalBoardReference().getBuildings().add(buildingCard);


        test.getPersonalBoardReference().setMyTile(new Tile(new TrisIE("takeRop" , "coin" , 1), new TrisIE("takeRop" , "coin" , 1)));

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


        room.getNicknamePlayersMap().put("fede", test);
        room.getNicknamePlayersMap().put("fede", playerHandler2);
        room.getNicknamePlayersMap().put("fede", playerHandler3);
        room.getNicknamePlayersMap().put("fede", playerHandler4);
        room.setMySupportFunction(new BasicSupportFunctions(playerHandler2), playerHandler2);
        room.setMySupportFunction(new BasicSupportFunctions(playerHandler3), playerHandler3);
        room.setMySupportFunction(new BasicSupportFunctions(playerHandler4), playerHandler4);
        List<PlayerHandler> playerTurn = new ArrayList<>();
        playerTurn.add(test);
        playerTurn.add(playerHandler2);
        playerTurn.add(playerHandler3);
        playerTurn.add(playerHandler4);


        playerHandler2.setAllFamilyMembers(familyMembers);
        playerHandler3.setAllFamilyMembers(familyMembers);
        playerHandler4.setAllFamilyMembers(familyMembers);

        test.getRoom().getBoard().getTurn().setPlayerTurn(playerTurn);

        towers = board.getAllTowers();
        towers[0][1].setCardOnThisFloor(territoryCard);
        towers[1][1].setCardOnThisFloor(characterCard);
        towers[2][1].setCardOnThisFloor(buildingCard);
        towers[3][1].setCardOnThisFloor(venturesCard);


    }

    @Test
    public void clientTakeDevelopmentCard() throws Exception {

        test.clientTakeDevelopmentCard("green", 1, familyMember);
        assertEquals(2, test.getPersonalBoardReference().getTerritories().size());
        assertEquals(5, test.getPersonalBoardReference().getWood());
        assertEquals(5, test.getPersonalBoardReference().getStone());
        assertEquals(10, test.getPersonalBoardReference().getServants());

        test.clientTakeDevelopmentCard("blue", 1, familyMember);
        assertEquals(1, test.getPersonalBoardReference().getCharacters().size());
        assertEquals(5, test.getPersonalBoardReference().getWood());
        assertEquals(5, test.getPersonalBoardReference().getStone());
        assertEquals(3, test.getPersonalBoardReference().getCoins());
        assertEquals(10, test.getPersonalBoardReference().getServants());

        test.clientTakeDevelopmentCard("yellow", 1, familyMember);
        assertEquals(2, test.getPersonalBoardReference().getBuildings().size());
        assertEquals(4, test.getPersonalBoardReference().getWood());
        assertEquals(4, test.getPersonalBoardReference().getStone());
        assertEquals(2, test.getPersonalBoardReference().getCoins());
        assertEquals(9, test.getPersonalBoardReference().getServants());


        test.clientTakeDevelopmentCard("purple", 1, familyMember);
        assertEquals(1, test.getPersonalBoardReference().getVentures().size());
        assertEquals(4, test.getPersonalBoardReference().getWood());
        assertEquals(4, test.getPersonalBoardReference().getStone());
        assertEquals(2, test.getPersonalBoardReference().getCoins());
        assertEquals(9, test.getPersonalBoardReference().getServants());
        assertEquals(8, test.getScore().getMilitaryPoints());

    }

    @Test
    public void clientTakeBonusDevelopementCard() throws Exception {

        test.clientTakeBonusDevelopementCard("green", 1, new TowerAction("green" , 1));
        assertEquals(2, test.getPersonalBoardReference().getTerritories().size());
        assertEquals(5, test.getPersonalBoardReference().getWood());
        assertEquals(5, test.getPersonalBoardReference().getStone());
        assertEquals(8, test.getPersonalBoardReference().getServants());
        assertEquals(10, test.getPersonalBoardReference().getCoins());

        test.clientTakeBonusDevelopementCard("blue", 1, new TowerAction("blue" , 3, "coin" , 2));
        assertEquals(1, test.getPersonalBoardReference().getCharacters().size());
        assertEquals(5, test.getPersonalBoardReference().getWood());
        assertEquals(5, test.getPersonalBoardReference().getStone());
        assertEquals(5, test.getPersonalBoardReference().getCoins());
        assertEquals(8, test.getPersonalBoardReference().getServants());


        test.clientTakeBonusDevelopementCard("yellow", 1, new TowerAction("yellow" , 3, "stone" , 2,"wood" , 2));
        assertEquals(2, test.getPersonalBoardReference().getBuildings().size());
        assertEquals(5, test.getPersonalBoardReference().getWood());
        assertEquals(5, test.getPersonalBoardReference().getStone());
        assertEquals(4, test.getPersonalBoardReference().getCoins());
        assertEquals(7, test.getPersonalBoardReference().getServants());

        test.clientTakeBonusDevelopementCard("purple", 1, new TowerAction("purple" , 1));
        assertEquals(2, test.getPersonalBoardReference().getBuildings().size());
        assertEquals(5, test.getPersonalBoardReference().getWood());
        assertEquals(5, test.getPersonalBoardReference().getStone());
        assertEquals(4, test.getPersonalBoardReference().getCoins());
        assertEquals(5, test.getPersonalBoardReference().getServants());
        assertEquals(8, test.getScore().getMilitaryPoints());
    }

    @Test
    public void clientChosenPaymentForVenturesCard() throws Exception {
        test.clientChosenPaymentForVenturesCard(1, familyMember, 0);
        assertEquals(5, test.getPersonalBoardReference().getWood());
        assertEquals(5, test.getPersonalBoardReference().getStone());
        assertEquals(5, test.getPersonalBoardReference().getCoins());

        towers[3][1].setCardOnThisFloor(venturesCard);
        test.clientChosenPaymentForVenturesCard(1, familyMember, 1);
        assertEquals(8, test.getScore().getMilitaryPoints());

    }

    @Test (expected = CantDoActionException.class)
    public void harvester() throws Exception {
        test.harvester(familyMember, 11);

        test.harvester(familyMember,0);
        assertEquals(12, test.getPersonalBoardReference().getCoins());
        assertEquals(10, test.getPersonalBoardReference().getServants());

    }

    @Test
    public void doBonusHarv() throws Exception {
        test.doBonusHarv(new BonusProductionOrHarvesterAction(Constants.HARVESTER, 2), 2);
        assertEquals(11, test.getPersonalBoardReference().getCoins());
        assertEquals(8, test.getPersonalBoardReference().getServants());

        test.doBonusHarv(new BonusProductionOrHarvesterAction(Constants.HARVESTER, 2), 3);
        assertEquals(13, test.getPersonalBoardReference().getCoins());
        assertEquals(5, test.getPersonalBoardReference().getServants());
    }

    @Test (expected = CantDoActionException.class)
    public void production() throws Exception {
        List<BuildingCard> buildingCards = new ArrayList<>();
        buildingCards.add(buildingCard);

        assertEquals(0, board.getProductionZone().size());

        test.getPersonalBoardReference().setServants(0);
        test.production(familyMember, buildingCards);

        test.getPersonalBoardReference().setServants(10);
        test.production(familyMember, buildingCards);
        assertEquals(12, test.getPersonalBoardReference().getCoins());
        assertEquals(9, test.getPersonalBoardReference().getServants());

    }

    @Test
    public void doBonusProduct() throws Exception {
        ArrayList<BuildingCard> buildingCards = new ArrayList<>();
        buildingCards.add(buildingCard);

        test.doBonusProduct(new BonusProductionOrHarvesterAction(Constants.PRODUCTION, 0), buildingCards);
        assertEquals(12, test.getPersonalBoardReference().getCoins());
        assertEquals(4, test.getPersonalBoardReference().getServants());
    }

    @Test
    public void goToMarket() throws Exception {

        Market market = new Market(new TrisIE("takeRop" , "coin" , 5));
        Market[] markets = new Market[1];
        markets[0] = market;
        board.setMarketZone(markets);
        familyMember.setMyValue(1);
        test.goToMarket(0,familyMember);

        assertEquals(15, test.getPersonalBoardReference().getCoins());
        assertEquals(9, test.getPersonalBoardReference().getServants());
    }

    @Test ( expected = NullPointerException.class)
    public void jumpTurn() throws Exception {
        test.jumpTurn();
    }

    @Test
    public void playLeaderCard() throws Exception {
        LeaderCard l = new LeaderCard();
        l.setName("michelangeloBuonarroti");
        test.getPersonalBoardReference().getMyLeaderCard().add(l);
        test.playLeaderCard("michelangeloBuonarroti");
        assertEquals(13, test.getPersonalBoardReference().getCoins());

        l.setPlayed(false);
        test.getPersonalBoardReference().setStone(0);
        test.playLeaderCard("michelangeloBuonarroti");
        assertEquals(16, test.getPersonalBoardReference().getCoins());
    }

    @Test (expected = CantDoActionException.class)
    public void discardLeaderCard() throws Exception {
        LeaderCard l = new LeaderCard();
        l.setName("michelangeloBuonarroti");
        test.getPersonalBoardReference().getMyLeaderCard().add(l);
        test.discardLeaderCard("michelangeloBuonarroti");
        assertEquals(0, test.getPersonalBoardReference().getMyLeaderCard().size());

        test.discardLeaderCard("michelangeloBuonarroti");
    }

    @Test
    public void rollDices() throws Exception {
        test.rollDices();
    }

    @Test (expected = CantDoActionException.class)
    public void goToCouncilPalace() throws Exception {
        familyMember.setMyValue(0);
        test.getPersonalBoardReference().setServants(0);

        test.goToCouncilPalace(0, familyMember);

        test.getPersonalBoardReference().setServants(1);
        test.goToCouncilPalace(0, familyMember);
        assertEquals(0, test.getPersonalBoardReference().getServants());
        assertEquals(11, test.getPersonalBoardReference().getWood());

    }

    @Test
    public void takePrivilege() throws Exception {
        CouncilPrivilege privilege = new CouncilPrivilege(new TrisIE("takeRop", "servant",  2), 0 );
        CouncilPrivilege[] privileges = new CouncilPrivilege[1];
        privileges[0] = privilege;
        board.setCouncilPrivileges(privileges);
        test.takePrivilege(0);

        assertEquals(10, test.getPersonalBoardReference().getCoins());
        assertEquals(12, test.getPersonalBoardReference().getServants());
    }

    @Test
    public void pray() throws Exception {
        test.pray();
    }

    @Test
    public void dontPray() throws Exception {
        ExcommunicationZone[] excomm = new ExcommunicationZone[1];
        excomm[0] = new ExcommunicationZone(new ExcommunicationTile(1,1,new TrisIE(EffectsConstants.LOSE_RESOURCE, EffectsConstants.COINS , 1), "ciao"));
        board.setExcommunicationZone(excomm);

        test.dontPray();

        assertEquals(-1, test.getPersonalBoardReference().getBonusOnActions().getCoinsBonus());
        assertEquals(10, test.getScore().getFaithPoints());
    }

    @Test
    public void getCheckFunctions() throws Exception {
        assertEquals(checkFunctions, test.getCheckFunctions());
    }

    @Test
    public void setCheckFunctions() throws Exception {
        BasicCheckFunctions cf = new BasicCheckFunctions();
        test.setCheckFunctions(cf);
        assertEquals(cf, test.getCheckFunctions());
    }

    @Test
    public void getRoom() throws Exception {
        assertEquals(room, test.getRoom());
    }

    @Test (expected = NullPointerException.class)
    public void reconnectClient() throws Exception {
        assertEquals(false, test.isOn());
        test.reconnectClient();
        assertEquals(true, test.isOn());
    }

    @Test
    public void findFamilyMember() throws Exception {
        assertEquals(test.getAllFamilyMembers()[1], test.findFamilyMember("orange"));
        assertEquals(null, test.findFamilyMember("ciao"));
    }

    @Test (expected = NullPointerException.class)
    public void skipTurn() throws Exception {
        test.skipTurn();
        assertEquals(test, room.getLastPlayer());
    }

    @Test
    public void setRoom() throws Exception {
        Room cf = new Room(new Server());
        test.setRoom(cf);
        assertEquals(cf, test.getRoom());
    }

    @Test
    public void isDisconnectedInDraft() throws Exception {
        test.setDisconnectedInDraft(false);
        assertEquals(false, test.isDisconnectedInDraft());
    }

    @Test
    public void setDisconnectedInDraft() throws Exception {
        test.setDisconnectedInDraft(false);
        assertEquals(false, test.isDisconnectedInDraft());
    }

    @Test
    public void isMatchStartedVar() throws Exception {
        test.setMatchStartedVar(false);
        assertEquals(false, test.isMatchStartedVar());
    }

    @Test
    public void setMatchStartedVar() throws Exception {
        test.setMatchStartedVar(false);
        assertEquals(false, test.isMatchStartedVar());
    }

    @Test
    public void afterMatch() throws Exception {
    }

    @Test
    public void takeRanking() throws Exception {
    }

    @Test
    public void sendRanking() throws Exception {
    }

    @Test
    public void showStatistics() throws Exception {
    }

    @Test
    public void sendStatistic() throws Exception {
    }

    @Test
    public void afterGameIftemporarilyOff() throws Exception {
    }

    @Test
    public void winnerComunication() throws Exception {
    }

}