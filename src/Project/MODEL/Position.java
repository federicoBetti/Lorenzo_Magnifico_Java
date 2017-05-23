package Project.MODEL;

/**
 * 
 */
public  abstract class Position {



    private boolean occupied = false;

    private FamilyMember familiarOnThisPosition;

    public boolean isOccupied() {
        return occupied;
    }

    public void setOccupied(boolean occupied) {
        this.occupied = occupied;
    }

    public FamilyMember getFamiliarOnThisPosition() {
        return familiarOnThisPosition;
    }

    public void setFamiliarOnThisPosition(FamilyMember familiarOnThisPosition) {
        this.familiarOnThisPosition = familiarOnThisPosition;
    }

}