package project.controller.cardsfactory;

/**
 * 
 */
public class CharactersCost implements Cost {

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