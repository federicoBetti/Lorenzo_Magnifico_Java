package project.model;

import org.junit.Before;
import org.junit.Test;
import project.controller.effects.effectsfactory.TrisIE;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * board test
 */
public class BoardTest {
    Board test;

    @Before
    public void before(){
        try {
            test = new Board(4);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void nextRound() throws Exception {
        test.nextRound();
        assertEquals(1, test.getRound());
        test.nextRound();
        assertEquals(0, test.getRound());
    }

    @Test
    public void setMarketZone() throws Exception {
        Market[] markets = new Market[3];
        test.setMarketZone(markets);
        assertEquals(markets, test.getMarketZone());

    }

    @Test
    public void getVictoryPointsInFaithTrack() throws Exception {
        int[] vicotryPointsInFaith = new int[3];
        test.setVictoryPointsInFaithTrack(vicotryPointsInFaith);
        assertEquals(vicotryPointsInFaith, test.getVictoryPointsInFaithTrack());
    }

    @Test
    public void getMarketZone() throws Exception {
        Market[] markets = new Market[3];
        test.setMarketZone(markets);
        assertEquals(markets, test.getMarketZone());

    }

    @Test
    public void getCouncilZone() throws Exception {
        List<Council> councilZone = new ArrayList<>();
        test.setCouncilZone(councilZone);
        assertEquals(councilZone, test.getCouncilZone());
    }

    @Test
    public void setCouncilZone() throws Exception {
        List<Council> councilZone = new ArrayList<>();
        test.setCouncilZone(councilZone);
        assertEquals(councilZone, test.getCouncilZone());
    }

    @Test
    public void getExcommunicationZone() throws Exception {
        ExcommunicationZone[] excommunicationZones = new ExcommunicationZone[2];
        test.setExcommunicationZone(excommunicationZones);
        assertEquals(excommunicationZones, test.getExcommunicationZone());
    }

    @Test
    public void getPeriod() throws Exception {
        test.nextPeriod();
        assertEquals(1, test.getPeriod());
    }

    @Test
    public void getRound() throws Exception {
        test.nextRound();
        assertEquals(1, test.getRound());
    }

    @Test
    public void setDiceValue() throws Exception {
        int[] diceValue = new int[3];
        test.setDiceValue(diceValue);
        assertEquals(diceValue, test.getDiceValue());
    }

    @Test
    public void getTrueArrayList() throws Exception {
        assertEquals(test.getAllTowers()[0], test.getTrueArrayList("green"));
        assertEquals(test.getAllTowers()[1], test.getTrueArrayList("blue"));
        assertEquals(test.getAllTowers()[2], test.getTrueArrayList("yellow"));
        assertEquals(test.getAllTowers()[3], test.getTrueArrayList("purple"));
    }

    @Test
    public void getAllTowers() throws Exception {
        Tower[][] towers = new Tower[4][4];
        test.setTowers(towers);
        assertEquals(towers, test.getTowers());
    }

    @Test
    public void nextPeriod() throws Exception {
        test.nextPeriod();
        assertEquals(1, test.getPeriod());
        test.nextPeriod();
        assertEquals(2, test.getPeriod());
    }

    @Test
    public void setTowers() throws Exception {
        Tower[][] towers = new Tower[4][4];
        test.setTowers(towers);
        assertEquals(towers, test.getTowers());
    }

    @Test
    public void getFinalPointsFromTerritoryCards() throws Exception {
        int[] finalPoints = new int[6];
        test.setFinalPointsFromTerritoryCards(finalPoints);
        assertEquals(finalPoints, test.getFinalPointsFromTerritoryCards());
    }

    @Test
    public void getFinalPointsFromCharacterCards() throws Exception {
        int[] finalPoints = new int[6];
        test.setFinalPointsFromCharacterCards(finalPoints);
        assertEquals(finalPoints, test.getFinalPointsFromCharacterCards());
    }

    @Test
    public void getFaithPointsRequiredEveryPeriod() throws Exception {
        int[] finalPoints = new int[3];
        test.setFaithPointsRequiredEveryPeriod(finalPoints);
        assertEquals(finalPoints, test.getFaithPointsRequiredEveryPeriod());
    }

    @Test
    public void getTurn() throws Exception {
        Turn turn = new Turn();
        test.setTurn(turn);
        assertEquals(turn, test.getTurn());
    }

    @Test
    public void getDiceValue() throws Exception {
        int[] diceValue = new int[3];
        test.setDiceValue(diceValue);
        assertEquals(diceValue, test.getDiceValue());
    }

    @Test
    public void getDeckCard() throws Exception {
        Deck deck = new Deck();
        test.setDeckCard(deck);
        assertEquals(deck, test.getDeckCard());
    }
    @Test
    public void setDeckCard() throws Exception {
        Deck deck = new Deck();
        test.setDeckCard(deck);
        assertEquals(deck, test.getDeckCard());
    }

    @Test
    public void setExcommunicationZone() throws Exception {
        ExcommunicationZone[] excommunicationZones = new ExcommunicationZone[2];
        test.setExcommunicationZone(excommunicationZones);
        assertEquals(excommunicationZones, test.getExcommunicationZone());
    }

    @Test
    public void getCouncilPrivileges() throws Exception {
        CouncilPrivilege[] privielges = new CouncilPrivilege[3];
        test.setCouncilPrivileges(privielges);
        assertEquals(privielges, test.getCouncilPrivileges());
    }

    @Test
    public void setCouncilPrivileges() throws Exception {
        CouncilPrivilege[] privielges = new CouncilPrivilege[3];
        test.setCouncilPrivileges(privielges);
        assertEquals(privielges, test.getCouncilPrivileges());
    }

    @Test
    public void setTowerInTowers() throws Exception {
        int tower = 0;
        int floor = 1;
        Tower tower1 = new Tower("",1,1,new TrisIE("takeRop","coin",1));
        test.setTowerInTowers(tower,floor,tower1);
        assertEquals(tower1, test.getTowers()[0][1]);
    }

    @Test
    public void getMilitaryPointsForTerritories() throws Exception {
        int[] finalPoints = new int[6];
        test.setMilitaryPointsForTerritories(finalPoints);
        assertEquals(finalPoints, test.getMilitaryPointsForTerritories());
    }

    @Test
    public void getTowers() throws Exception {
        Tower[][] towers = new Tower[4][4];
        test.setTowers(towers);
        assertEquals(towers, test.getAllTowers());
    }

    @Test
    public void getHarvesterZone() throws Exception {
        List<Harvester> harvesterZone = new ArrayList<>();
        test.setHarvesterZone(harvesterZone);
        assertEquals(harvesterZone, test.getHarvesterZone());
    }

    @Test
    public void getProductionZone() throws Exception {
        List<Production> productionZone = new ArrayList<>();
        test.setProductionZone(productionZone);
        assertEquals(productionZone, test.getProductionZone());
    }

    @Test
    public void getZones() throws Exception {
    }


    @Test
    public void setHarvesterZone() throws Exception {
        List<Harvester> harvesterZone = new ArrayList<>();
        test.setHarvesterZone(harvesterZone);
        assertEquals(harvesterZone, test.getHarvesterZone());
    }

    @Test
    public void setProductionZone() throws Exception {
        List<Production> productionZone = new ArrayList<>();
        test.setProductionZone(productionZone);
        assertEquals(productionZone, test.getProductionZone());
    }

    @Test
    public void setVictoryPointsInFaithTrack() throws Exception {
        int[] vicotryPointsInFaith = new int[3];
        test.setVictoryPointsInFaithTrack(vicotryPointsInFaith);
        assertEquals(vicotryPointsInFaith, test.getVictoryPointsInFaithTrack());
    }


    @Test
    public void setFinalPointsFromTerritoryCards() throws Exception {
        int[] finalPoints = new int[6];
        test.setFinalPointsFromTerritoryCards(finalPoints);
        assertEquals(finalPoints, test.getFinalPointsFromTerritoryCards());
    }

    @Test
    public void setFinalPointsFromCharacterCards() throws Exception {
        int[] finalPoints = new int[6];
        test.setFinalPointsFromCharacterCards(finalPoints);
        assertEquals(finalPoints, test.getFinalPointsFromCharacterCards());
    }

    @Test
    public void setFaithPointsRequiredEveryPeriod() throws Exception {
        int[] finalPoints = new int[3];
        test.setFaithPointsRequiredEveryPeriod(finalPoints);
        assertEquals(finalPoints, test.getFaithPointsRequiredEveryPeriod());
    }

    @Test
    public void setMilitaryPointsForTerritories() throws Exception {
        int[] finalPoints = new int[6];
        test.setMilitaryPointsForTerritories(finalPoints);
        assertEquals(finalPoints, test.getMilitaryPointsForTerritories());
    }

    @Test
    public void setTurn() throws Exception {
        Turn turn = new Turn();
        test.setTurn(turn);
        assertEquals(turn, test.getTurn());
    }

}