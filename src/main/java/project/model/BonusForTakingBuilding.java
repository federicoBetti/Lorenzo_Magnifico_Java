package project.model;

import java.io.Serializable;

/**
 * This class represent the bonus for taking building cards
 */
public class BonusForTakingBuilding implements Serializable{

    private int diceBonus;
    private int woodBonus;
    private int stoneBonus;

    /**
     * get diceBonus
     *
     * @return diceBonus
     */
    public int getDiceBonus() {
        return diceBonus;
    }

    /**
     * Set diceBonus
     *
     * @param diceBonus diceBonus
     */
    public void setDiceBonus(int diceBonus) {
        this.diceBonus = diceBonus;
    }

    /**
     * get woodBonus
     *
     * @return woodBonus
     */
    public int getWoodBonus() {
        return woodBonus;
    }

    /**
     * Set woodBonus
     *
     * @param woodBonus woodBonus
     */
    public void setWoodBonus(int woodBonus) {
        this.woodBonus = woodBonus;
    }

    /**
     * get stoneBonus
     *
     * @return stoneBonus
     */
    public int getStoneBonus() {
        return stoneBonus;
    }

    /**
     * Set stoneBonus
     *
     * @param stoneBonus stoneBonus
     */
    public void setStoneBonus(int stoneBonus) {
        this.stoneBonus = stoneBonus;
    }
}
