package Project.MODEL;

/**
 * 
 */
public class Tower extends Position {

    /**
     * Default constructor
     */
    public Tower() {
    }

    /**
     * 
     */
    private String colour;

    /**
     * 
     */
    private int floor;

    /**
     * 
     */
    private DevelopmentCard cardOnThisFloor;

    /**
     * 
     */
    private boolean used;

    public DevelopmentCard getCardOnThisFloor() {
        return cardOnThisFloor;
    }

    public void setCardOnThisFloor(DevelopmentCard cardOnThisFloor) {
        this.cardOnThisFloor = cardOnThisFloor;
    }
}