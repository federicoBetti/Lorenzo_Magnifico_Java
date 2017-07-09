package project.model;

import java.io.Serializable;

/**
 * class that contains the bonus for taking building card
 */
public class BonusForTakingBuilding implements Serializable{

    private int diceBonus;
    private int woodBonus;
    private int stoneBonus;

    public int getDiceBonus() {
        return diceBonus;
    }

    public void setDiceBonus(int diceBonus) {
        this.diceBonus = diceBonus;
    }

    public int getWoodBonus() {
        return woodBonus;
    }

    public void setWoodBonus(int woodBonus) {
        this.woodBonus = woodBonus;
    }

    public int getStoneBonus() {
        return stoneBonus;
    }

    public void setStoneBonus(int stoneBonus) {
        this.stoneBonus = stoneBonus;
    }
}
