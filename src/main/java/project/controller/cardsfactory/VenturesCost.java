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
}