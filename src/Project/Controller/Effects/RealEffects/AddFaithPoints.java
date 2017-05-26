package Project.Controller.Effects.RealEffects;

import Project.toDelete.BonusInteraction;
import Project.toDelete.OkOrNo;
import Project.MODEL.*;


public class AddFaithPoints implements TakeRoPEffects {
    private int quantity;

    public AddFaithPoints (int quantity){
        this.quantity = quantity;
    }


    public BonusInteraction doEffect(Player player) {
        player.getScore().setFaithPoints(player.getScore().getFaithPoints() + quantity);
        OkOrNo Ok = new OkOrNo(false);
        Ok.setOk(true);
        return Ok;
    }
}
