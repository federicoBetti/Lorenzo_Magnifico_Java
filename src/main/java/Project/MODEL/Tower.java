package Project.MODEL;

/**
 * 
 */
public class Tower extends Position {

    /**
     * Default constructor
     */
    public Tower() {
        setOccupied(false);
    }

    /**
     * 
     */
    private String colour;

    /**
     * 
     */
    private int diceValueOfThisFloor;

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

    public int getDiceValueOfThisFloor() {
        return diceValueOfThisFloor;
    }
}