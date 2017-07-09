package project.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Abstract position
 */
public abstract class Position implements Serializable{


    private boolean occupied;

    private FamilyMember familiarOnThisPosition;

    private List<FamilyMember> ludovicoAriostoPosition;

    /**
     * Constructor
     */
    Position (){
        //familiarOnThisPosition = new FamilyMember();
        ludovicoAriostoPosition = new ArrayList<>();
        this.occupied = false;
    }

    /**
     * Get occupied
     *
     * @return occupied
     */
    public boolean isOccupied() {
        return occupied;
    }

    /**
     * Set occupied
     *
     * @param occupied occupied
     */
    public void setOccupied(boolean occupied) {
        this.occupied = occupied;
    }

    /**
     * Get familiarOnThisPosition
     *
     * @return familiarOnThisPosition
     */
    public FamilyMember getFamiliarOnThisPosition() {
        return familiarOnThisPosition;
    }

    /**
     * Set familiarOnThisPosition
     *
     * @param familiarOnThisPosition familiarOnThisPosition
     */
    public void setFamiliarOnThisPosition(FamilyMember familiarOnThisPosition) {
        this.familiarOnThisPosition = familiarOnThisPosition;
    }

    /**
     * Add the familiar to this positon
     */
    public void ludovicoAriosto() {
        ludovicoAriostoPosition = new ArrayList<>();
        if (familiarOnThisPosition!=null)
            ludovicoAriostoPosition.add(familiarOnThisPosition);
    }

    public void addFamiliar(FamilyMember familyMember) {
        ludovicoAriostoPosition.add(familyMember);
    }

    /**
     * Get ludovicoAriostoPosition
     *
     * @return ludovicoAriostoPosition
     */
    public List<FamilyMember> getLudovicoAriostoPosition() {
        return ludovicoAriostoPosition;
    }

}