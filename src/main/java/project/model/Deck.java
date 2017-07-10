package project.model;

import project.controller.cardsfactory.ExcommunicationTile;
import project.controller.cardsfactory.LeaderCard;
import project.controller.Constants;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents all the deck in the game
 */
public class Deck {


    private DevelopmentCard[][][] developmentDeck; // va cotruito con le carte: colore, periodo, carte

    private List<LeaderCard> leaderCardDeck; //va costruito con le LeaderCard

    private ExcommunicationTile[][] excommunicationCard;

    private Tile[] prodHarvTiles;

    /**
     * Constructor
     */
    Deck() {
       developmentDeck = new DevelopmentCard[Constants.CARD_TYPE_NUMBER][Constants.PERIOD_NUMBER][Constants.CARD_FOR_EACH_PERIOD];
       excommunicationCard = new ExcommunicationTile[Constants.PERIOD_NUMBER][Constants.EXCOMMUNICATION_CARD_NUMBER_PER_PERIOD];
       prodHarvTiles = new Tile[Constants.NUMBER_OF_TILES];
    }

    /**
     * Get leaderCardDeck
     *
     * @return leaderCardDeck
     */
    public ArrayList<LeaderCard> getLeaderCardDeck() {
        return (ArrayList<LeaderCard>) leaderCardDeck;
    }

    /**
     * Set leaderCardDeck
     *
     * @param leaderCardDeck leaderCardDeck
     */
    public void setLeaderCardDeck(List<LeaderCard> leaderCardDeck) {
        this.leaderCardDeck = leaderCardDeck;
    }

    /**
     * Get developmentDeck
     *
     * @return developmentDeck
     */
    public DevelopmentCard[][][] getDevelopmentDeck() {
        return developmentDeck;
    }

    /**
     * Get excommunicationCard
     *
     * @return excommunicationCard
     */
    public ExcommunicationTile[][] getExcommunicationCard() {
        return excommunicationCard;
    }

    /**
     * Get prodHarvTiles
     *
     * @return prodHarvTiles
     */
    public Tile[] getProdHarvTiles() {
        return prodHarvTiles;
    }

    /**
     * Set the bonus tile in the Tile's deck
     *
     * @param tileNumber tile's number
     * @param tile tile's reference
     */
    public void setProdHaarvTile( int tileNumber, Tile tile ){
        prodHarvTiles[tileNumber] = tile;
    }

    /**
     * Set developmentDeck
     *
     * @param developmentDeck developmentDeck
     */
    public void setDevelopmentDeck(DevelopmentCard[][][] developmentDeck) {
        this.developmentDeck = developmentDeck;
    }

    /**
     * Set excommunicationCard
     *
     * @param excommunicationCard excommunicationCard
     */
    void setExcommunicationCard(ExcommunicationTile[][] excommunicationCard) {
        this.excommunicationCard = excommunicationCard;
    }
}