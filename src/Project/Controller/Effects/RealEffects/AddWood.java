package Project.Controller.Effects.RealEffects;

import Project.toDelete.BonusInteraction;
import Project.toDelete.OkOrNo;
import Project.MODEL.Player;

public class AddWood implements TakeRoPEffects {
    private int quantity;

    public AddWood (int quantity){
        this.quantity = quantity;
    }


    @Override
    public BonusInteraction doEffect(Player player) {
        player.getPersonalBoardReference().setWood(player.getPersonalBoardReference().getWood() + quantity);
        OkOrNo Ok = new OkOrNo(false);
        Ok.setOk(true);
        return Ok;
    }
}
