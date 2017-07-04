package project.controller.cardsfactory;

import java.io.Serializable;

/**
 * 
 */
public class TerritoryCost implements Cost, Serializable {

    private int woodRequired;

    private int stoneRequired;

    private int diceCost;


    TerritoryCost() {
        //da fare
    }

    @Override
    public String toScreen() {
        return "Wood required: " + woodRequired +
                " Stone required: " + stoneRequired +
                " Dice value required: " + diceCost;
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




    @Override
    public void addCoin(int i) {

    }

    @Override
    public void addStone(int i) {
        stoneRequired += i;
    }

    @Override
    public void addWood(int i) {
        woodRequired += i;
    }

}