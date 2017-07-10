package project.configurations;

import project.controller.Constants;

/**
 * Development deck iterator
 */
public class DevelopmentDeckIterator  {

    private int color;
    private int period;
    private int cards;

    /**
     * Constructor
     */
    public DevelopmentDeckIterator(){
        this.color = 0;
        this.period = 0;
        this.cards = 0;


    }


    /**
     * Increase the indexes for pointing the next element of the deck
     */
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

    /**
     * Get color
     *
     * @return color
     */
    public int getColor() {
        return color;
    }

    /**
     * Get period
     *
     * @return period
     */
    public int getPeriod() {
        return period;
    }

    /**
     * Get cards
     *
     * @return cards
     */
    public int getCards() {
        return cards;
    }
}

