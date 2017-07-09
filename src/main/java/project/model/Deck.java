package project.model;

import project.controller.cardsfactory.ExcommunicationTile;
import project.controller.cardsfactory.LeaderCard;
import project.controller.Constants;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 */
public class Deck {


    private DevelopmentCard[][][] developmentDeck; // va cotruito con le carte: colore, periodo, carte

    private List<LeaderCard> leaderCardDeck; //va costruito con le LeaderCard

    private ExcommunicationTile[][] excommunicationCard;

    private Tile[] prodHarvTiles;

    Deck() {
       developmentDeck = new DevelopmentCard[Constants.CARD_TYPE_NUMBER][Constants.PERIOD_NUMBER][Constants.CARD_FOR_EACH_PERIOD];
       excommunicationCard = new ExcommunicationTile[Constants.PERIOD_NUMBER][Constants.EXCOMMUNICATION_CARD_NUMBER_PER_PERIOD];
       prodHarvTiles = new Tile[Constants.NUMBER_OF_TILES];
    }

    public ArrayList<LeaderCard> getLeaderCardDeck() {
        return (ArrayList<LeaderCard>) leaderCardDeck;
    }

    public void setLeaderCardDeck(List<LeaderCard> leaderCardDeck) {
        this.leaderCardDeck = leaderCardDeck;
    }

    public DevelopmentCard[][][] getDevelopmentDeck() {
        return developmentDeck;
    }


    public ExcommunicationTile[][] getExcommunicationCard() {
        return excommunicationCard;
    }

    public Tile[] getProdHarvTiles() {
        return prodHarvTiles;
    }

    public void setProdHaarvTile( int tileNumber, Tile tile ){
        prodHarvTiles[tileNumber] = tile;
        System.out.println( prodHarvTiles[tileNumber] + "\n" +  prodHarvTiles[tileNumber].getTileNumber());
    }

    public void setDevelopmentDeck(DevelopmentCard[][][] developmentDeck) {
        this.developmentDeck = developmentDeck;
    }

    public void setExcommunicationCard(ExcommunicationTile[][] excommunicationCard) {
        this.excommunicationCard = excommunicationCard;
    }
}