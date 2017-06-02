package project.model;

import project.controller.cardsfactory.ExcommunitationTile;
import project.controller.cardsfactory.LeaderCard;
import project.controller.Constants;

/**
 * 
 */
public class Deck {


    private DevelopmentCard[][][] developmentDeck; // va cotruito con le carte

    private LeaderCard [] leaderCardeck; //va costruito con le LeaderCard

    private ExcommunitationTile[][] excomunicationCard;

    public Deck() {

        DevelopmentCard[][][] deck = new DevelopmentCard[Constants.CARD_TYPE_NUMBER][Constants.PERIOD_NUMBER][Constants.CARD_FOR_EACH_PERIOD];
    }

    public DevelopmentCard[][][] getDevelopmentdeck() {
        return developmentDeck;
    }


}