package Project.Controller.Effects.RealEffects;


import Project.Controller.MessageObjects.BonusInteraction;
import Project.Controller.MessageObjects.OkOrNo;
import Project.MODEL.Player;

public class AddServants implements Effects {
    private int quantity;

    public AddServants (int quantity){
        this.quantity = quantity;
    }


    @Override
    public BonusInteraction doEffect(Player player) {
        player.getPersonalBoardReference().setServants(player.getPersonalBoardReference().getServants() + quantity);
        OkOrNo Ok = new OkOrNo();
        Ok.setOk(true);
        return Ok;
    }
}
