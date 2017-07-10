package project.controller.cardsfactory;

import java.io.Serializable;

/**
 * This class represent the specific Territory card's cost
 */
public class TerritoryCost implements Cost, Serializable {

    int woodRequired;

    int stoneRequired;

    int diceCost;

    /**
     * Constructor
     */
    TerritoryCost() {
        //da fare
    }

    /**
     * Constructor
     *
     * @param woodRequired woodRequired
     * @param stoneRequired stoneRequired
     * @param diceCost diceCost
     */
    public TerritoryCost(int woodRequired, int stoneRequired, int diceCost) {
        this.woodRequired = woodRequired;
        this.stoneRequired = stoneRequired;
        this.diceCost = diceCost;
    }

    /**
     * This method is used to create a string that represents the territory's card cost
     *
     * @return
     */
    @Override
    public String toScreen() {
        return "Wood required: " + woodRequired +
                " Stone required: " + stoneRequired +
                " Dice value required: " + diceCost;
    }

    /**
     * This method creates a cost's copy
     *
     * @return cost's copy
     */
    @Override
    public Cost copyOf() {
        return new TerritoryCost(woodRequired,stoneRequired,diceCost);
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
     * Get stoneRequired
     *
     * @return stoneRequired
     */
    public int getStoneRequired() {
        return stoneRequired;
    }

    /**
     * Get diceCost
     *
     * @return diceCost
     */
    public int getDiceCost() {
        return diceCost;
    }

    /**
     * Set wood required
     *
     */
    public void setWoodRequired(int woodRequired) {
        this.woodRequired = woodRequired;
    }

    /**
     * Set stone required
     *
     */
    public void setStoneRequired(int stoneRequired) {
        this.stoneRequired = stoneRequired;
    }

    /**
     * Method
     */
    @Override
    public void picoDellaMirandolaDowngrade() {
    }

    /**
     * method
     *
     */
    @Override
    public void addCoin(int i) {

    }

    /**
     * This method add stones to the stoneRequired
     *
     * @param i stones to add
     */
    @Override
    public void addStone(int i) {
        stoneRequired += i;
    }

    /**
     * This method add wood to the woodRequired
     *
     * @param i wood to add
     */
    @Override
    public void addWood(int i) {
        woodRequired += i;
    }

}