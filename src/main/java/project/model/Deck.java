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

    private List<LeaderCard> leaderCardeck; //va costruito con le LeaderCard

    private ExcommunicationTile[][] excomunicationCard;

    private Tile[] prodHaarvTiles;

    public Deck() {
       developmentDeck = new DevelopmentCard[Constants.CARD_TYPE_NUMBER][Constants.PERIOD_NUMBER][Constants.CARD_FOR_EACH_PERIOD];
       excomunicationCard = new ExcommunicationTile[Constants.PERIOD_NUMBER][Constants.EXCOMMUNICATION_CARD_NUMBER_PER_PERIOD];
       prodHaarvTiles = new Tile[Constants.NUMBER_OF_TILES];
    }

    public List<LeaderCard> getLeaderCardeck() {
        return leaderCardeck;
    }

    public void setLeaderCardeck(List<LeaderCard> leaderCardeck) {
        this.leaderCardeck = leaderCardeck;
    }

    public DevelopmentCard[][][] getDevelopmentDeck() {
        return developmentDeck;
    }


    public ExcommunicationTile[][] getExcomunicationCard() {
        return excomunicationCard;
    }

    public Tile[] getProdHaarvTiles() {
        return prodHaarvTiles;
    }

    public void setProdHaarvTile( int tileNumber, Tile tile ){
        prodHaarvTiles[tileNumber] = tile;
    }
}