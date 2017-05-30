package Project.Controller.Effects.RealEffects;

import Project.Messages.BonusInteraction;
import Project.Messages.OkOrNo;
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
