package Project.Controller.Effects.RealEffects;


import Project.Messages.BonusInteraction;
import Project.Messages.OkOrNo;
import Project.MODEL.Player;

public class AddStone implements TakeRoPEffects {
    private int quantity;

    public AddStone (int quantity){
        this.quantity = quantity;
    }


    @Override
    public BonusInteraction doEffect(Player player) {
        player.getPersonalBoardReference().setStone(player.getPersonalBoardReference().getStone() + quantity);
        OkOrNo Ok = new OkOrNo(false);
        Ok.setOk(true);
        return Ok;
    }
}
