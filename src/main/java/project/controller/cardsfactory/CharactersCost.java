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

    public int getCoinsRequired() {
        return coinsRequired;
    }

    @Override
    public void picoDellaMirandolaDowngrade() {
        coinsRequired -= 3;
    }
}