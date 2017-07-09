package project.controller.cardsfactory;

import java.io.Serializable;

/**
 * This class represent the specific Character card's cost
 */
public class CharactersCost implements Cost, Serializable {

    private int coinsRequired;

    public CharactersCost(int coinsRequired) {
        this.coinsRequired = coinsRequired;
    }


    /**
     * Get coins required
     *
     * @return coins required
     */
    public int getCoinsRequired() {
        return coinsRequired;
    }

    /**
     * This method subtract 3 to the coinsRequired
     */
    @Override
    public void picoDellaMirandolaDowngrade() {
        coinsRequired -= 3;
    }

    /**
     * This method returns the coins'requires string
     *
     * @return coins required string
     */
    @Override
    public String toScreen() {
        return "Coins required: " + coinsRequired;
    }

    /**
     * This method returns the cost's copy
     *
     * @return cost's copy
     */
    @Override
    public Cost copyOf() {
        return new CharactersCost(coinsRequired);
    }

    /**
     * This method add coins
     *
     * @param i coins to add
     */
    @Override
    public void addCoin(int i) {
        coinsRequired += i;
        if (coinsRequired < 0)
            coinsRequired = 0;
    }

    /**
     * This methos add stones
     *
     * @param i stones to add
     */
    @Override
    public void addStone(int i) {

    }

    /**
     * This method adds wood
     *
     * @param i wood to add
     */
    @Override
    public void addWood(int i) {

    }

}