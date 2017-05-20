package Project.MODEL;

import Project.toDelete.BonusInteraction;

/**
 * Created by raffaelebongo on 10/05/17.
 */
public class BonusForTakingCharacter extends BonusInteraction {
    int diceBonus;
    int coinsBonus;

    public int getDiceBonus() {
        return diceBonus;
    }

    public void setDiceBonus(int diceBonus) {
        this.diceBonus = diceBonus;
    }

    public int getCoinsBonus() {
        return coinsBonus;
    }

    public void setCoinsBonus(int coinsBonus) {
        this.coinsBonus = coinsBonus;
    }
}
