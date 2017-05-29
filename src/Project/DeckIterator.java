package Project;

import Project.Controller.Constants;
import Project.MODEL.Deck;

/**
 * TODO cambiarlo perchè bisgpa randomizzare le carte
 */
public class DeckIterator implements IteratorInterface {

    int period1;
    int period2;
    int period3;
    Constants constants;

    public DeckIterator(){
        this.period1 = 0;
        this.period2 = 0;
        this.period3 = 0;
        this.constants = new Constants();

    }
    @Override
    public boolean hasNext() {
        if ( period3 < constants.CARD_FOR_EACH_PERIOD ){
            return true;
        }
        else
            return false;
    }

    @Override
    public void next( ) {
        if ( period1 < constants.CARD_FOR_EACH_PERIOD ){
            period1++;
            return;
        }
        if ( period2 < constants.CARD_FOR_EACH_PERIOD ){
            period2++;
            return;
        }
        if ( period3 < constants.CARD_FOR_EACH_PERIOD ){
            period3++;
            return;
        }
    }

    public int getPeriod1() {
        return period1;
    }

    public void setPeriod1(int period1) {
        this.period1 = period1;
    }

    public int getPeriod2() {
        return period2;
    }

    public void setPeriod2(int period2) {
        this.period2 = period2;
    }

    public int getPeriod3() {
        return period3;
    }

    public void setPeriod3(int period3) {
        this.period3 = period3;
    }
}
