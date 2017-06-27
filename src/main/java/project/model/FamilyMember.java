package project.model;

import java.io.Serializable;

/**
 * 
 */
public class FamilyMember implements Serializable{


    private String familyColour;

    private String myColour;

    private int fixedBonus;

    private int fixedValue;

    private int myValue;

    private boolean isPlayed;


    protected FamilyMember(){
        this.fixedValue = 0;
        this.isPlayed = false;
    }

    public void takeValueFromDIce() {
        // to do implements
    }

    public String  getFamilyColour() {
        return familyColour;
    }

    public void setFamilyColour(String  familyColour) {
        this.familyColour = familyColour;
    }

    public int getFixedBonus() {
        return fixedBonus;
    }

    public void setFixedBonus(int fixedBonus) {
        this.fixedBonus = fixedBonus;
    }

    public String getMyColour() {
        return myColour;
    }

    public void setMyColour(String myColour) {
        this.myColour = myColour;
    }

    public int getMyValue() {
        if (fixedValue == 0)
            return myValue + fixedBonus;
        else
            return fixedValue + fixedBonus;
    }

    public void setMyValue(int myValue) {
        this.myValue = myValue;
    }

    public boolean isPlayed() {
        return isPlayed;
    }

    public void setPlayed(boolean played) {
        isPlayed = played;
    }

    public void setFixedValue(int fixedValue) {
        this.fixedValue = fixedValue;
    }

    public String toString(){
       return (familyColour + myColour).toLowerCase();
    }
}