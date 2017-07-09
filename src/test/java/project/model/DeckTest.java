package project.model;

import org.junit.Test;
import project.controller.cardsfactory.ExcommunicationTile;
import project.controller.cardsfactory.LeaderCard;
import project.controller.effects.effectsfactory.TrisIE;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * bonus test
 */
public class DeckTest {

    private Deck test = new Deck();

    @Test
    public void getLeaderCardDeck() throws Exception {
        List<LeaderCard> leaders = new ArrayList<>();
        test.setLeaderCardDeck(leaders);
        assertEquals(leaders, test.getLeaderCardDeck());
    }

    @Test
    public void setLeaderCardDeck() throws Exception {
        List<LeaderCard> leaders = new ArrayList<>();
        test.setLeaderCardDeck(leaders);
        assertEquals(leaders, test.getLeaderCardDeck());
    }

    @Test
    public void getDevelopmentDeck() throws Exception {
        DevelopmentCard[][][] developementDeck = new DevelopmentCard[4][4][4];
        test.setDevelopmentDeck(developementDeck);
        assertEquals(developementDeck, test.getDevelopmentDeck());
    }

    @Test
    public void getExcommunicationCard() throws Exception {
        ExcommunicationTile[][] excommunicationCard = new ExcommunicationTile[7][3];
        test.setExcommunicationCard(excommunicationCard);
        assertEquals(excommunicationCard, test.getExcommunicationCard());
    }

    @Test
    public void setExcommunicationCard() throws Exception {
        ExcommunicationTile[][] excommunicationCard = new ExcommunicationTile[7][3];
        test.setExcommunicationCard(excommunicationCard);
        assertEquals(excommunicationCard, test.getExcommunicationCard());
    }

    @Test
    public void getProdHarvTiles() throws Exception {
        Tile tile = new Tile(new TrisIE("takeRop", "coin" ,1 ), new TrisIE("takeRop", "coin" ,1 ));
        test.setProdHaarvTile(1, tile);
        assertEquals(tile.getTileNumber(), test.getProdHarvTiles()[1].getTileNumber());
    }

    @Test
    public void setProdHaarvTile() throws Exception {
        Tile tile = new Tile(new TrisIE("takeRop", "coin" ,1 ), new TrisIE("takeRop", "coin" ,1 ));
        test.setProdHaarvTile(1, tile);
        assertEquals(tile.getTileNumber(), test.getProdHarvTiles()[1].getTileNumber());
    }

    @Test
    public void setDevelopmentDeck() throws Exception {
        DevelopmentCard[][][] developementDeck = new DevelopmentCard[4][4][4];
        test.setDevelopmentDeck(developementDeck);
        assertEquals(developementDeck, test.getDevelopmentDeck());
    }

}