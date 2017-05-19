package Project.Controller.Effects.RealEffects;

import Project.Controller.MessageObjects.BonusInteraction;
import Project.Controller.MessageObjects.OkOrNo;
import Project.MODEL.*;


public class AddFaithPoints implements TakeRoPEffects {
    private int quantity;

    public AddFaithPoints (int quantity){
        this.quantity = quantity;
    }


    public BonusInteraction doEffect(Player player) {
        player.getScore().setFaithPoints(player.getScore().getFaithPoints() + quantity);
        OkOrNo Ok = new OkOrNo();
        Ok.setOk(true);
        return Ok;
    }
}
