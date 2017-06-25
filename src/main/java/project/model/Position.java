package project.model;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 */
public abstract class Position {


    private boolean occupied;

    private FamilyMember familiarOnThisPosition;

    private List<FamilyMember> ludovicoAriostoPosition;

    Position (){
        //familiarOnThisPosition = new FamilyMember();
        ludovicoAriostoPosition = new ArrayList<>();
        this.occupied = false;
    }

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

    public void ludovicoAriosto() {
        ludovicoAriostoPosition = new ArrayList<>();
        ludovicoAriostoPosition.add(familiarOnThisPosition);
    }

    public void addFamiliar(FamilyMember familyMember) {
        ludovicoAriostoPosition.add(familyMember);
    }
}