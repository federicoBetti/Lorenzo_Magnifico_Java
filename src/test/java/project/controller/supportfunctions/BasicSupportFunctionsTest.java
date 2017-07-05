package project.controller.supportfunctions;

import com.google.gson.Gson;
import org.junit.Test;
import project.controller.cardsfactory.*;
import project.controller.effects.effectsfactory.PokerPE;
import project.controller.effects.effectsfactory.TrisIE;
import project.model.*;
import project.model.PersonalBoard;
import project.server.network.PlayerHandler;
import project.server.network.rmi.RMIPlayerHandler;
import project.server.network.socket.SocketPlayerHandler;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.*;

import static org.junit.Assert.*;

/**
 * Created by raffaelebongo on 04/07/17.
 */
public class BasicSupportFunctionsTest {
    @Test
    public void payServants() throws Exception {
        int cost = 10;
        int value = 4;

        BasicSupportFunctions bsf = new BasicSupportFunctions();
        int res = bsf.payServants(cost,value);

        assertEquals(6, res);
    }

    @Test
    public void finalPointsFromTerritoryCard() throws Exception {
        Gson gson = new Gson();
        BasicSupportFunctions bsf = new BasicSupportFunctions();
        InputStream is = getClass().getResourceAsStream("/fileJson/finalPointsFromTerritoryCards.json");
        Reader reader = new InputStreamReader(is);

        PlayerHandler player = new SocketPlayerHandler();
        int[] array = gson.fromJson(reader, int[].class );
        PersonalBoard personalBoard = new PersonalBoard();
        personalBoard.getTerritories().add(new TerritoryCard());
        personalBoard.getTerritories().add(new TerritoryCard());
        personalBoard.getTerritories().add(new TerritoryCard());
        personalBoard.getTerritories().add(new TerritoryCard());
        personalBoard.getTerritories().add(new TerritoryCard());
        personalBoard.getTerritories().add(new TerritoryCard());
        player.setPersonalBoardReference(personalBoard);

        bsf.setPlayer(player);
        int res = bsf.finalPointsFromTerritoryCard(array);

        assertEquals(20, res);
    }

    @Test
    public void finalPointsFromCharacterCard() throws Exception {
        Gson gson = new Gson();
        BasicSupportFunctions bsf = new BasicSupportFunctions();
        InputStream is = getClass().getResourceAsStream("/fileJson/finalPointsFromCharacterCards.json");
        Reader reader = new InputStreamReader(is);
        int[] victoryPoints = gson.fromJson(reader, int[].class);

        PlayerHandler player = new SocketPlayerHandler();
        PersonalBoard personalBoard = new PersonalBoard();
        personalBoard.getCharacters().add(new CharacterCard());
        personalBoard.getCharacters().add(new CharacterCard());
        personalBoard.getCharacters().add(new CharacterCard());
        personalBoard.getCharacters().add(new CharacterCard());
        personalBoard.getCharacters().add(new CharacterCard());
        personalBoard.getCharacters().add(new CharacterCard());

        player.setPersonalBoardReference(personalBoard);

        bsf.setPlayer(player);
        int res = bsf.finalPointsFromCharacterCard(victoryPoints);

        assertEquals(21, res);
    }

    @Test
    public void extraLostOfPoints() throws Exception {
        BasicSupportFunctions bsf = new BasicSupportFunctions();
        int res = bsf.extraLostOfPoints(new SocketPlayerHandler());
        assertEquals(0, res );
    }

    @Test
    public void finalPointsFromVenturesCard() throws Exception {
        List<VenturesCard> venturesCards;

        List<PokerPE> pokerPE = new ArrayList<>();
        PlayerHandler player = new SocketPlayerHandler();
        PersonalBoard personalBoard = new PersonalBoard();
        pokerPE.add(new PokerPE("takeRop", "victoryPoint", 5));
        personalBoard.getVentures().add(new VenturesCard("hello", 1, false, new ArrayList<>(), new ArrayList<>(),  pokerPE));
        personalBoard.getVentures().add(new VenturesCard("hello", 1, false, new ArrayList<>(), new ArrayList<>(),  pokerPE));
        personalBoard.getVentures().add(new VenturesCard("hello",1, false, new ArrayList<>(), new ArrayList<>(),  pokerPE));

        player.setPersonalBoardReference(personalBoard);
        BasicSupportFunctions bsf = new BasicSupportFunctions();

        bsf.setPlayer(player);

        bsf.finalPointsFromVenturesCard();

        assertEquals(15, player.getScore().getVictoryPoints());

    }


    @Test
    public void setFamiliar() throws Exception {
        Tower tower = new Tower();
        FamilyMember familyMember = new FamilyMember();

        BasicSupportFunctions bsf = new BasicSupportFunctions();
        bsf.setFamiliar(tower, familyMember );

        assertEquals(true, tower.isOccupied());
    }

    @Test
    public void placeCardInPersonalBoard() throws Exception {

        BuildingCard card = new BuildingCard();
        BasicSupportFunctions bsf = new BasicSupportFunctions();
        PlayerHandler player = new SocketPlayerHandler();
        PersonalBoard personalBoard = new PersonalBoard();
        player.setPersonalBoardReference(personalBoard);
        bsf.setPlayer(player);
        bsf.placeCardInPersonalBoard(card);

        assertEquals(card, personalBoard.getBuildings().get(personalBoard.getBuildings().size() - 1 ));

        VenturesCard venturesCard = new VenturesCard();
        bsf.placeCardInPersonalBoard(venturesCard);

        assertEquals(venturesCard, personalBoard.getVentures().get(personalBoard.getBuildings().size() - 1 ));

        CharacterCard characterCard = new CharacterCard();
        bsf.placeCardInPersonalBoard(characterCard);
        assertEquals(characterCard, personalBoard.getCharacters().get(personalBoard.getBuildings().size() - 1 ));

        TerritoryCard territoryCard = new TerritoryCard();
        bsf.placeCardInPersonalBoard(territoryCard);
        assertEquals(territoryCard, personalBoard.getTerritories().get(personalBoard.getBuildings().size() - 1 ));
    }


    @Test
    public void setDicesValue() throws Exception {
        int[] diceValue = new int[3];
        diceValue[0] = 1;
        diceValue[1] = 2;
        diceValue[2] = 3;

        PlayerHandler player = new SocketPlayerHandler();
        FamilyMember[] familyMembers = new FamilyMember[4];
        familyMembers[0] = new FamilyMember();
        familyMembers[1] = new FamilyMember();
        familyMembers[2] = new FamilyMember();
        familyMembers[3] = new FamilyMember();
        player.setAllFamilyMembers(familyMembers);
        BasicSupportFunctions bsf = new BasicSupportFunctions();
        bsf.setDicesValue(diceValue, player);

        assertEquals(1, player.getAllFamilyMembers()[1].getMyValue());
        assertEquals(2, player.getAllFamilyMembers()[2].getMyValue());
        assertEquals(3, player.getAllFamilyMembers()[3].getMyValue());
    }

    @Test
    public void setFamiliarInTheCouncilPalace() throws Exception {
        List<Council> councils = new ArrayList<>();
        FamilyMember familyMember = new FamilyMember();

        BasicSupportFunctions bsf = new BasicSupportFunctions();
        bsf.setFamiliarInTheCouncilPalace(councils, familyMember);

        assertEquals(true, councils.get(0).getFamiliarOnThisPosition().isPlayed());
    }


    @Test
    public void payCard() throws Exception {
        PlayerHandler p = new RMIPlayerHandler();
        DevelopmentCard card = new TerritoryCard("prova",1,false,new TerritoryCost(3,3,3), new ArrayList<>(),new ArrayList<>());
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

        BasicSupportFunctions bsf = new BasicSupportFunctions(p);
        bsf.payCard(card,coinsFee,zoneDiceCost,valueOfFM);

        assertEquals(0, p.getPersonalBoardReference().getWood());
        assertEquals(1, p.getPersonalBoardReference().getStone());

    }

    @Test
    public void payVenturesCard() throws Exception {

        PlayerHandler p = new RMIPlayerHandler();
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

        VenturesCard card = new VenturesCard("prova", 1, false, costs, new ArrayList<>(), new ArrayList<>());
        boolean coinsFee = false;
        int zoneDiceCost = 5;
        int valueOfFM = 3;

        p.getPersonalBoardReference().setServants(10);
        p.getPersonalBoardReference().setWood(10);
        p.getPersonalBoardReference().setStone(10);
        p.getPersonalBoardReference().setCoins(10);
        p.getScore().setMilitaryPoints(5);

        p.getPersonalBoardReference().getTerritories().add(new TerritoryCard());
        p.getPersonalBoardReference().getTerritories().add(new TerritoryCard());
        p.getPersonalBoardReference().getTerritories().add(new TerritoryCard());
        p.getPersonalBoardReference().getTerritories().add(new TerritoryCard());

        BasicSupportFunctions basicSupportFunctions = new BasicSupportFunctions(p);
        int paymentChoosen = 0;
        basicSupportFunctions.payVenturesCard(card,p, coinsFee, zoneDiceCost,valueOfFM, paymentChoosen);

        assertEquals(5, p.getPersonalBoardReference().getWood());
        assertEquals(5, p.getPersonalBoardReference().getStone());
        assertEquals(5, p.getPersonalBoardReference().getCoins());

        coinsFee = true;

        basicSupportFunctions.payVenturesCard(card, p, coinsFee,zoneDiceCost,valueOfFM, paymentChoosen);
        assertEquals(-3, p.getPersonalBoardReference().getCoins());

        paymentChoosen = 1;

        basicSupportFunctions.payVenturesCard(card,p,coinsFee, zoneDiceCost, valueOfFM, paymentChoosen);
        assertEquals(3, p.getScore().getMilitaryPoints());
        assertEquals(-6, p.getPersonalBoardReference().getCoins());

    }

    @Test
    public void pray() throws Exception {

        PlayerHandler player = new SocketPlayerHandler();
        Score score = new Score();
        score.setFaithPoints(10);
        score.setVictoryPoints(0);
        player.setScore(score);
        int victoryPointToAdd = 5;

        BasicSupportFunctions bsf = new BasicSupportFunctions(player);
        bsf.pray(victoryPointToAdd);

        assertEquals(0, player.getScore().getFaithPoints());
        assertEquals(5, player.getScore().getVictoryPoints());


    }

}