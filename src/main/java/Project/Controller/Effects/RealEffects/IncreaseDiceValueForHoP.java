package Project.Controller.Effects.RealEffects;

import Project.Messages.BonusInteraction;
import Project.Messages.OkOrNo;
import Project.MODEL.Player;

/**
 * Created by raffaelebongo on 10/05/17.
 */
public class IncreaseDiceValueForHoP implements Effects {

    String kindOfAction;
    int increasingDiceValue;

    public IncreaseDiceValueForHoP(int quantity, String kindOfAction) {
        this.increasingDiceValue = quantity;
        this.kindOfAction = kindOfAction;
    }

    @Override
    public BonusInteraction doEffect(Player player) {

        OkOrNo Ok = new OkOrNo(false);
        Ok.setOk(true);
        return Ok;
    }
}
