package project.model;

import java.io.Serializable;

/**
 * This class represent the family member
 */
public class FamilyMember implements Serializable{


    private String familyColour;

    private String myColour;

    private int fixedBonus;

    private int fixedValue;

    private int myValue;

    private boolean isPlayed;

    /**
     * Constructor
     */
    public FamilyMember(){
        this.fixedValue = 0;
        this.fixedBonus = 0;
        this.myValue = 0;
        this.isPlayed = false;
    }

    /**
     * Get familyColour
     *
     * @return familyColour
     */
    public String  getFamilyColour() {
        return familyColour;
    }

    /**
     * Set familyColour
     *
     * @param familyColour familyColour
     */
    public void setFamilyColour(String  familyColour) {
        this.familyColour = familyColour;
    }

    /**
     * Get fixedBonus
     *
     * @return fixedBonus
     */
    public int getFixedBonus() {
        return fixedBonus;
    }

    /**
     * Set fixedBonus
     *
     * @param fixedBonus fixedBonus
     */
    public void setFixedBonus(int fixedBonus) {
        this.fixedBonus = fixedBonus;
    }

    /**
     * Get myColour
     *
     * @return myColour
     */
    public String getMyColour() {
        return myColour;
    }

    /**
     * Set myColour
     *
     * @param myColour myColour
     */
    public void setMyColour(String myColour) {
        this.myColour = myColour;
    }

    /**
     * Get my value
     *
     * @return my value
     */
    public int getMyValue() {
        if (fixedValue == 0)
            return myValue + fixedBonus;
        else
            return fixedValue + fixedBonus;
    }

    /**
     * Set myValue
     *
     * @param myValue myValue
     */
    public void setMyValue(int myValue) {
        this.myValue = myValue;
    }

    /**
     * Get isPlayed
     *
     * @return isPlayed
     */
    public boolean isPlayed() {
        return isPlayed;
    }

    /**
     * Set played
     *
     * @param played played
     */
    public void setPlayed(boolean played) {
        isPlayed = played;
    }

    /**
     * Set fixedValue
     *
     * @param fixedValue fixedValue
     */
    public void setFixedValue(int fixedValue) {
        this.fixedValue = fixedValue;
    }

    /**
     * to string method
     *
     * @return the string of family colour and familiar colour
     */
    public String toString(){
       return (familyColour + myColour).toLowerCase();
    }
}