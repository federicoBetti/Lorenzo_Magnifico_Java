package project.model;

import project.controller.cardsfactory.ExcommunicationTile;
import project.controller.cardsfactory.LeaderCard;
import project.controller.Constants;

/**
 * 
 */
public class Deck {


    private DevelopmentCard[][][] developmentDeck; // va cotruito con le carte: colore, periodo, carte

    private LeaderCard [] leaderCardeck; //va costruito con le LeaderCard

    private ExcommunicationTile[][] excomunicationCard;

    private Tile[] prodHaarvTiles;

    public Deck() {

        DevelopmentCard[][][] deck = new DevelopmentCard[Constants.CARD_TYPE_NUMBER][Constants.PERIOD_NUMBER][Constants.CARD_FOR_EACH_PERIOD];
    }

    public DevelopmentCard[][][] getDevelopmentDeck() {
        return developmentDeck;
    }

    public LeaderCard[] getLeaderCardeck() {
        return leaderCardeck;
    }

    public ExcommunicationTile[][] getExcomunicationCard() {
        return excomunicationCard;
    }

    public Tile[] getProdHaarvTiles() {
        return prodHaarvTiles;
    }
}