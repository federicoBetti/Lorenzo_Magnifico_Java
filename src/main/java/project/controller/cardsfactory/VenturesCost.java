package project.controller.cardsfactory;

import java.io.Serializable;

/**
 * This class represent the specific Venture card's cost
 */
public class VenturesCost implements Cost, Serializable {


    private int stoneRequired;

    private int woodRequired;

    private int coinsRequired;

    private int militaryRequired;

    private int militaryCost;

    private boolean toPaid = false;


    public VenturesCost() {

    }

    public VenturesCost(int stoneRequired, int woodRequired, int coinsRequired, int militaryRequired, int militaryCost) {
        this.stoneRequired = stoneRequired;
        this.woodRequired = woodRequired;
        this.coinsRequired = coinsRequired;
        this.militaryCost = militaryCost;
        this.militaryRequired = militaryRequired;
    }

    /**
     * This method creates the string that describes the venture's cost
     *
     * @return
     */
    @Override
    public String toScreen() {
        String toScreen = "";

        if (stoneRequired != 0)
            toScreen += "Stone required: " + stoneRequired;

        if (woodRequired != 0)
            toScreen += " Wood required: " + woodRequired;

        if (coinsRequired != 0)
            toScreen += " Coins required: " + coinsRequired;

        if (militaryRequired == 0)
            return toScreen;

            toScreen += " or you can pay Military points required: " + militaryRequired;

        if (militaryCost != 0)
            toScreen += " Military points to pay: " + militaryCost;

        return toScreen;
    }

    /**
     * This method creates a copy of the cost
     *
     * @return cost's copy
     */
    @Override
    public Cost copyOf() {
        return new VenturesCost(stoneRequired,woodRequired,coinsRequired,militaryRequired,militaryCost);
    }

    /**
     * Set toPaid
     *
     * @param toPaid toPaid
     */
    public void setToPaid(boolean toPaid) {
        this.toPaid = toPaid;
    }

    /**
     * Get stone required
     *
     * @return stoneRequired
     */
    public int getStoneRequired() {
        return stoneRequired;
    }

    /**
     * Get wood required
     *
     * @return woodRequired
     */
    public int getWoodRequired() {
        return woodRequired;
    }

    /**
     * Get coins required
     *
     * @return coinsRequired
     */
    public int getCoinsRequired() {
        return coinsRequired;
    }

    /**
     * Get military required
     *
     * @return militaryRequired
     */
    public int getMilitaryRequired() {
        return militaryRequired;
    }

    /**
     * Get military cost
     *
     * @return militaryCost
     */
    public int getMilitaryCost() {
        return militaryCost;
    }

    /**
     * Set stone required
     *
     * @param stoneRequired stoneRequired
     */
    public void setStoneRequired(int stoneRequired) {
        this.stoneRequired = stoneRequired;
    }

    /**
     * Set wood required
     *
     * @param woodRequired woodRequired
     */
    public void setWoodRequired(int woodRequired) {
        this.woodRequired = woodRequired;
    }

    /**
     * Set coins required
     *
     * @param coinsRequired coinsRequired
     */
    public void setCoinsRequired(int coinsRequired) {
        this.coinsRequired = coinsRequired;
    }

    /**
     * Set military required
     *
     * @param militaryRequired militaryRequired
     */
    public void setMilitaryRequired(int militaryRequired) {
        this.militaryRequired = militaryRequired;
    }

    /**
     * Set military cost
     *
     * @param militaryCost militaryCost
     */
    public void setMilitaryCost(int militaryCost) {
        this.militaryCost = militaryCost;
    }

    /**
     * This methos reduces the coinsRequires value of 3
     */
    @Override
    public void picoDellaMirandolaDowngrade() {
        coinsRequired -= 3;
    }

    /**
     * This method adds coins to coinsRequired
     *
     * @param i coins to add
     */
    @Override
    public void addCoin(int i) {
        coinsRequired += i;
    }

    /**
     * This method adds stones to stoneRequired
     *
     * @param i stoneRequired
     */
    @Override
    public void addStone(int i) {
        stoneRequired += i;
    }

    /**
     * This method add woods to woodRequired
     *
     * @param i woodRequired
     */
    @Override
    public void addWood(int i) {
        woodRequired += i;
    }


}