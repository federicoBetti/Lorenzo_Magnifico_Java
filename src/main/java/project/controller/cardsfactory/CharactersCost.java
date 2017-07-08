package project.controller.cardsfactory;

import java.io.Serializable;

/**
 * 
 */
public class CharactersCost implements Cost, Serializable {

    private int coinsRequired;

    public CharactersCost() {
        //da fare
    }

    public CharactersCost(int coinsRequired) {
        this.coinsRequired = coinsRequired;
    }

    public int getCoinsRequired() {
        return coinsRequired;
    }

    @Override
    public void picoDellaMirandolaDowngrade() {
        coinsRequired -= 3;
    }

    @Override
    public String toScreen() {
        return "Coins required: " + coinsRequired;
    }

    @Override
    public Cost copyOf() {
        return new CharactersCost(coinsRequired);
    }


    @Override
    public void addCoin(int i) {
        coinsRequired += i;
        if (coinsRequired < 0)
            coinsRequired = 0;
    }

    @Override
    public void addStone(int i) {

    }

    @Override
    public void addWood(int i) {

    }

}