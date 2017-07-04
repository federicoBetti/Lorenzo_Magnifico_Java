package project.controller.cardsfactory;

import java.io.Serializable;

/**
 * 
 */
public class VenturesCost implements Cost, Serializable {


    private int stoneRequired;

    private int woodRequired;

    private int coinsRequired;

    private int militaryRequired;

    private int militaryCost;

    private boolean toPaid = false;


    public VenturesCost() {
        //da fare
    }


    @Override
    public String toScreen() {
        return "Stone required: " + stoneRequired +
                "Wood required: " + woodRequired +
                "Coins required: " + coinsRequired +
                "Military points required: " + militaryRequired +
                "Military points to pay: " + militaryCost;
    }

    @Override
    public Cost copyOf() {
        return new VenturesCost();
    }

    public boolean isToPaid() {
        return toPaid;
    }

    public void setToPaid(boolean toPaid) {
        this.toPaid = toPaid;
    }

    public int getStoneRequired() {
        return stoneRequired;
    }

    public int getWoodRequired() {
        return woodRequired;
    }

    public int getCoinsRequired() {
        return coinsRequired;
    }

    public int getMilitaryRequired() {
        return militaryRequired;
    }

    public int getMilitaryCost() {
        return militaryCost;
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