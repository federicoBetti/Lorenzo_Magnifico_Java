package Project.MODEL;

import Project.Controller.CardsFactory.ExcommunitationTile;
import Project.Controller.CardsFactory.LeaderCard;
import Project.Controller.Constants;
import Project.MODEL.Card;

/**
 * 
 */
public class Deck {


    private Card[][][] developmentDeck; // va cotruito con le carte

    private LeaderCard [] leaderCardeck; //va costruito con le LeaderCard

    private ExcommunitationTile[][] excomunicationCard;

    public Deck() {

        Card[][][] deck = new Card[Constants.CARD_TYPE_NUMBER][Constants.PERIOD_NUMBER][Constants.CARD_FOR_EACH_PERIOD];
    }

    public Card[][][] getDevelopmentdeck() {
        return developmentDeck;
    }


}