package Project.Controller.Effects.RealEffects;


import Project.Messages.BonusInteraction;
import Project.Messages.OkOrNo;
import Project.MODEL.Player;

public class AddServants implements TakeRoPEffects {
    private int quantity;

    public AddServants (int quantity){
        this.quantity = quantity;
    }


    @Override
    public BonusInteraction doEffect(Player player) {
        player.getPersonalBoardReference().setServants(player.getPersonalBoardReference().getServants() + quantity);
        OkOrNo Ok = new OkOrNo(false);
        Ok.setOk(true);
        return Ok;
    }
}
