package Project.Client.MessageObject;

/**
 * Created by raffaelebongo on 20/05/17.
 */

public enum TowerFloor {

    GROUND_FLOOR(0),
    
    FIRST(1),
    
    SECOND(2),
    
    THIRD(3);

    private int floor;

    private TowerFloor(int floor ) {
        this.floor = floor;
    }

    public int getFloor() {
        return floor;
    }
}
