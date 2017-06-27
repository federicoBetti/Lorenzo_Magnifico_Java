package project;

import project.model.Tower;

/**
 * Created by raffaelebongo on 17/06/17.
 */
public class TowerIterator implements IteratorInterface {

    private Tower[][] towers;
    //todo
    private int towerNumber;
    private int floor;

    public TowerIterator(Tower[][] towers){
        this();
        this.towers = towers;
    }

    private TowerIterator() {
        towerNumber = 0;
        floor = 0;
    }

    @Override
    public boolean hasNext() {
        if (towerNumber < 4){
            return true;
        }
        return false;
    }

    @Override
    public void next() {
        if (floor< 3){
            floor++;
        }
        else if (towerNumber<3) {
            towerNumber++;
            floor = 0;
        }
        return ;

    }

    public Tower getZone(){
        return towers[floor][towerNumber];
    }
    public int getTowerNumber() {
        return towerNumber;
    }

    public int getFloor() {
        return floor;
    }
}
