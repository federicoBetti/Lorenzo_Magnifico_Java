package project.controller.cardsfactory;

import java.io.Serializable;

/**
 * 
 */
public class TerritoryCost implements Cost, Serializable {

    private int woodRequired;

    private int stoneRequired;

    private int diceCost;


    public TerritoryCost() {
        //da fare
    }

    public int getWoodRequired() {
        return woodRequired;
    }

    public int getStoneRequired() {
        return stoneRequired;
    }

    public int getDiceCost() {
        return diceCost;
    }

    public void setWoodRequired(int woodRequired) {
        this.woodRequired = woodRequired;
    }

    public void setStoneRequired(int stoneRequired) {
        this.stoneRequired = stoneRequired;
    }

    @Override
    public void picoDellaMirandolaDowngrade() {
    }
}