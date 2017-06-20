package project.controller.cardsfactory;

import java.io.Serializable;

/**
 * 
 */
public class TotalCost implements Cost, Serializable {

    private int stoneRequired;

    private int woodRequired;

    private int servantsRequired;

    private int coinsRequired;

    private int militaryRequired;

    private int militaryCost;



    public TotalCost() {
        //da fare
    }



    public int getStoneRequired() {
        return stoneRequired;
    }

    public void setStoneRequired(int stoneRequired) {
        this.stoneRequired = stoneRequired;
    }

    public int getWoodRequired() {
        return woodRequired;
    }

    public void setWoodRequired(int woodRequired) {
        this.woodRequired = woodRequired;
    }

    public int getServantsRequired() {
        return servantsRequired;
    }

    public void setServantsRequired(int servantsRequired) {
        this.servantsRequired = servantsRequired;
    }

    public int getCoinsRequired() {
        return coinsRequired;
    }

    public void setCoinsRequired(int coinsRequired) {
        this.coinsRequired = coinsRequired;
    }

    public int getMilitaryRequired() {
        return militaryRequired;
    }

    public void setMilitaryRequired(int militaryRequired) {
        this.militaryRequired = militaryRequired;
    }

    public int getMilitaryCost() {
        return militaryCost;
    }

    public void setMilitaryCost(int militaryCost) {
        this.militaryCost = militaryCost;
    }

    @Override
    public void picoDellaMirandolaDowngrade() {

        coinsRequired -= 3;
    }



    @Override
    public void addCoin(int i) {
        coinsRequired += i;
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