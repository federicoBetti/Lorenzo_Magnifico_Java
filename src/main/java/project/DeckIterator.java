package project;

import project.controller.Constants;

/**
 * TODO cambiarlo perchè bisgpa randomizzare le carte
 */
public class DeckIterator implements IteratorInterface {

    int color;
    int period;
    int cards;


    public DeckIterator(){
        this.color = 0;
        this.period = 0;
        this.cards = 0;


    }
    @Override
    public boolean hasNext() {
        if (color < Constants.CARD_TYPE_NUMBER && period < 2 && cards < Constants.CARD_FOR_EACH_PERIOD )
            return true;
        return false;
    }


    @Override
    public void next( ) {
        if ( cards < Constants.CARD_FOR_EACH_PERIOD )
            cards++;

        else if ( period < 2 ){
            cards = 0;
            period++;
        }
        else if ( color < Constants.CARD_TYPE_NUMBER ){
            color++;
            period = 0;
            cards = 0;
        }
    }

    public int getColor() {
        return color;
    }

    public int getPeriod() {
        return period;
    }

    public int getCards() {
        return cards;
    }
}
