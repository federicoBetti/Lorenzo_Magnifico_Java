package Project.Controller.Effects.RealEffects;


import Project.Messages.BonusInteraction;
import Project.Messages.OkOrNo;
import Project.MODEL.Player;

public class AddVicotryPoints implements TakeRoPEffects {

    private int quantity;

    public AddVicotryPoints (int quantity){
        this.quantity = quantity;
    }

    public BonusInteraction doEffect(Player player) {
        player.getScore().setVictoryPoints(player.getScore().getVictoryPoints() + quantity);
        OkOrNo Ok = new OkOrNo(false);
        Ok.setOk(true);
        return Ok;
    }
}