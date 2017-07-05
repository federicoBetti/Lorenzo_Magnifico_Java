package project.controller.supportfunctions;

import com.google.gson.Gson;
import org.junit.Test;
import project.controller.cardsfactory.BuildingCard;
import project.controller.cardsfactory.CharacterCard;
import project.controller.cardsfactory.TerritoryCard;
import project.controller.cardsfactory.VenturesCard;
import project.controller.effects.effectsfactory.PokerPE;
import project.model.PersonalBoard;
import project.server.network.PlayerHandler;
import project.server.network.socket.SocketPlayerHandler;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

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
    }

    @Test
    public void towerZoneEffect() throws Exception {
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
    public void applyEffects() throws Exception {
    }

    @Test
    public void setFamiliar() throws Exception {
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
    }

    @Test
    public void setFamiliarInTheCouncilPalace() throws Exception {
    }

    @Test
    public void takeMarketAction() throws Exception {
    }

    @Test
    public void payCard() throws Exception {
    }

    @Test
    public void payVenturesCard() throws Exception {
    }

    @Test
    public void pray() throws Exception {
    }

}