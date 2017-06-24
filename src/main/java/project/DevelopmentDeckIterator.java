package project;

import project.controller.Constants;

/**
 * TODO cambiarlo perchè bisgpa randomizzare le carte
 */
public class DevelopmentDeckIterator implements IteratorInterface {

    int color;
    int period;
    int cards;


    public DevelopmentDeckIterator(){
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
        if ( cards == Constants.CARD_FOR_EACH_PERIOD - 1  ) {
            cards = 0;
            period++;
        }
        else {
            cards++;
        }

        if ( period == Constants.PERIOD_NUMBER ) {
            color++;
            period = 0;
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
