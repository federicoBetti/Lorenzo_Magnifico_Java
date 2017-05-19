package Project.Controller.Effects.RealEffects;

import Project.Controller.MessageObjects.BonusInteraction;
import Project.Controller.MessageObjects.OkOrNo;
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

        OkOrNo Ok = new OkOrNo();
        Ok.setOk(true);
        return Ok;
    }
}
