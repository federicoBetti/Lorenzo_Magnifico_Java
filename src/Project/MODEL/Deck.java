package Project.MODEL;

import Project.Controller.Constants;
import Project.MODEL.Card;

/**
 * 
 */
public class Deck {


    private Card[][][] Developmentdeck; // va cotruito con le carte

    private Card [] LeaderCarddeck; //va costruito con le LeaderCard

    public Deck() {
        Constants constants = new Constants();

        Card[][][] deck = new Card[constants.CARD_FOR_EACH_PERIOD][constants.CARD_FOR_EACH_PERIOD][constants.CARD_FOR_EACH_PERIOD];
    }

    public Card[][][] getDevelopmentdeck() {
        return Developmentdeck;
    }

    public void setDevelopmentdeck(Card[][][] developmentdeck) {
        Developmentdeck = developmentdeck;
    }

    public Card[] getLeaderCarddeck() {
        return LeaderCarddeck;
    }

    public void setLeaderCarddeck(Card[] leaderCarddeck) {
        LeaderCarddeck = leaderCarddeck;
    }
}