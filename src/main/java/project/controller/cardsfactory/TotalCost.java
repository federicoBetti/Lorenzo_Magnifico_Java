package project.controller.cardsfactory;

import java.io.Serializable;

/**
 * This class represents all kinds of possible costs
 */
public class TotalCost implements Cost, Serializable {

    private int stoneRequired;

    private int woodRequired;

    private int servantsRequired;

    private int coinsRequired;

    private int militaryRequired;

    private int militaryCost;

    private int faithPonts;

    /**
     * This method build a string that describes the cost
     *
     * @return string that describes the cost
     */
    @Override
    public String toScreen() {
        return  "Stone required: " + stoneRequired +
                "Wood required: " + woodRequired +
                "Servants required: " + servantsRequired +
                "Coins required: " + coinsRequired +
                "Military points Required: " + militaryRequired +
                "Military points to pay: " + militaryCost;
    }

    /**
     * This method creates a cost's copy
     *
     * @return cost's copy
     */
    @Override
    public Cost copyOf() {
        return null;
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
     * Set stones required
     *
     * @param stoneRequired stoneRequired
     */
    void setStoneRequired(int stoneRequired) {
        this.stoneRequired = stoneRequired;
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
     * Set wood required
     *
     * @param woodRequired woodRequired
     */
    void setWoodRequired(int woodRequired) {
        this.woodRequired = woodRequired;
    }

    /**
     * Get servants required
     *
     * @return servantsRequired
     */
    public int getServantsRequired() {
        return servantsRequired;
    }

    /**
     * Set servants required
     *
     * @param servantsRequired servantsRequired
     */
    void setServantsRequired(int servantsRequired) {
        this.servantsRequired = servantsRequired;
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
     * Set coins required
     *
     * @param coinsRequired
     */
    void setCoinsRequired(int coinsRequired) {
        this.coinsRequired = coinsRequired;
    }

    /**
     * Get faith points required
     *
     * @return faithPonts
     */
    public int getFaithPoints() {
        return faithPonts;
    }

    /**
     * This method reduce the coins required of 3
     */
    @Override
    public void picoDellaMirandolaDowngrade() {

        coinsRequired -= 3;
    }

    /**
     * Add coins
     *
     * @param i coins to add
     */
    @Override
    public void addCoin(int i) {
        coinsRequired += i;
    }

    /**
     * Add stones
     *
     * @param i stones to add
     */
    @Override
    public void addStone(int i) {
        stoneRequired += i;
    }

    /**
     * Add wood required
     *
     * @param i wood to add
     */
    @Override
    public void addWood(int i) {
        woodRequired += i;
    }

    /**
     * Add servants Required
     *
     * @param i servantsRequired to add
     */
    public void addServants(int i) {
        servantsRequired += i;
    }

    /**
     * Add faith points
     *
     * @param faithPointsrequired faith points to add
     */
    public void addFaithPoints(int faithPointsrequired) {
        faithPonts += faithPointsrequired;
    }



    void setFaithPonts(int faithPonts) {
        this.faithPonts = faithPonts;
    }
}