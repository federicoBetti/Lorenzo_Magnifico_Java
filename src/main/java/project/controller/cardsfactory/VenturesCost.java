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

    }


    @Override
    public String toScreen() {
        String toScreen = "";

        if (stoneRequired != 0)
            toScreen += "Stone required: " + stoneRequired;

        if (woodRequired != 0)
            toScreen += " Wood required: " + woodRequired;

        if (coinsRequired != 0)
            toScreen += " Coins required: " + coinsRequired;

        if (militaryRequired == 0)
            return toScreen;

            toScreen += " or you can pay Military points required: " + militaryRequired;

        if (militaryCost != 0)
            toScreen += " Military points to pay: " + militaryCost;

        return toScreen;
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