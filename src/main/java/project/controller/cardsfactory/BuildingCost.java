package project.controller.cardsfactory;

import java.io.Serializable;

/**
 * 
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
     * 
     */


    public int getCoinsRequired() {
        return coinsRequired;
    }

    public void setCoinsRequired(int coinsRequired) {
        this.coinsRequired = coinsRequired;
    }

    @Override
    public void picoDellaMirandolaDowngrade() {
        coinsRequired -= 3;
    }

    @Override
    public Cost copyOf() {
        return new BuildingCost(coinsRequired, servantRequired, woodRequired, stoneRequired, diceCost);
    }


    @Override
    public void addCoin(int i) {
        coinsRequired += i;
    }

    @Override
    public void addStone(int i) {
    }

    @Override
    public void addWood(int i) {
    }

}