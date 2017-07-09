package project.controller.cardsfactory;

import java.io.Serializable;

/**
 * This class represent the specific Building card's cost
 */
public class BuildingCost extends TerritoryCost implements Serializable {

    private int coinsRequired;

    private int servantRequired;

    public BuildingCost() {
    }

    public BuildingCost(int coinsRequired, int servantRequired, int woodRequired, int stoneRequired, int diceCost) {
        super(woodRequired,stoneRequired,diceCost);
        this.coinsRequired = coinsRequired;
        this.servantRequired = servantRequired;
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
     * Set coinsRequired
     *
     * @param coinsRequired coinsRequire
     */
    void setCoinsRequired(int coinsRequired) {
        this.coinsRequired = coinsRequired;
    }

    /**
     * This method subtract 3 to coinsRequired
     */
    @Override
    public void picoDellaMirandolaDowngrade() {
        coinsRequired -= 3;
    }

    /**
     * This method generate a cost's copy
     *
     * @return cost's copy
     */
    @Override
    public Cost copyOf() {
        return new BuildingCost(coinsRequired, servantRequired, woodRequired, stoneRequired, diceCost);
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
     * Add stone
     *
     * @param i stones to add
     */
    @Override
    public void addStone(int i) {
        stoneRequired +=i;
        if (stoneRequired < 0)
            stoneRequired = 0;
    }

    /**
     * Add wood
     *
     * @param i wood to add
     */
    @Override
    public void addWood(int i) {
        woodRequired += i;
        if (woodRequired<0)
            woodRequired = 0;
    }

    public int getServantsRequired() {
        return servantRequired;
    }
}