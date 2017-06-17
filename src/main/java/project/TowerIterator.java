package project;

/**
 * Created by raffaelebongo on 17/06/17.
 */
public class TowerIterator implements IteratorInterface {

    //todo
    int towerNumber;
    int floor;

    @Override
    public boolean hasNext() {
        return false;
    }

    @Override
    public void next() {

    }

    public int getTowerNumber() {
        return towerNumber;
    }

    public int getFloor() {
        return floor;
    }
}
