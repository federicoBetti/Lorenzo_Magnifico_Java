package project.model;

import java.io.Serializable;

/**
 * This class represent the bonus for taking building cards
 */
public class BonusForTakingCharacter implements Serializable {
    private int diceBonus;
    private int coinsBonus;

    /**
     * Get diceBonus
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
     * Get coinsBonus
     *
     * @return coinsBonus
     */
    public int getCoinsBonus() {
        return coinsBonus;
    }

    /**
     * Set coinsBonus
     *
     * @param coinsBonus coinsBonus
     */
    public void setCoinsBonus(int coinsBonus) {
        this.coinsBonus = coinsBonus;
    }
}
