package Project.MODEL;

/**
 * 
 */
public class FamilyMember {

    /**
     * Default constructor
     */
    public FamilyMember() {
    }

    /**
     * 
     */
    private String FamilyColour;



    /**
     * 
     */
    private int FixedBonus;
    private int fixedValue = 0;
    /**
     * 
     */
    private int MyColour;

    /**
     * 
     */
    private int MyValue;

    /**
     * 
     */
    private boolean isPlayed;


    /**
     * @return
     */
    public void TakeValueFromDIce() {
        // TODO implement here
    }

    public String  getFamilyColour() {
        return FamilyColour;
    }

    public void setFamilyColour(String  familyColour) {
        FamilyColour = familyColour;
    }

    public int getFixedBonus() {
        return FixedBonus;
    }

    public void setFixedBonus(int fixedBonus) {
        FixedBonus = fixedBonus;
    }

    public int getMyColour() {
        return MyColour;
    }

    public void setMyColour(int myColour) {
        MyColour = myColour;
    }

    public int getMyValue() {
        if (fixedValue == 0)
            return MyValue;
        else
            return fixedValue;
    }

    public void setMyValue(int myValue) {
        MyValue = myValue;
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
}