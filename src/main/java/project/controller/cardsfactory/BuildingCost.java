package project.controller.cardsfactory;

import java.io.Serializable;

/**
 * 
 */
public class BuildingCost extends TerritoryCost implements Serializable {

    private int coinsRequired;

    public BuildingCost() {
        //TODO
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
}