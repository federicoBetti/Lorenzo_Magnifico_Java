package Project.Controller.Effects.RealEffects;


import Project.Controller.MessageObjects.BonusInteraction;
import Project.Controller.MessageObjects.OkOrNo;
import Project.MODEL.Player;

public class AddVicotryPoints implements Effects {

    private int quantity;

    public AddVicotryPoints (int quantity){
        this.quantity = quantity;
    }

    public BonusInteraction doEffect(Player player) {
        player.getScore().setVictoryPoints(player.getScore().getVictoryPoints() + quantity);
        OkOrNo Ok = new OkOrNo();
        Ok.setOk(true);
        return Ok;
    }
}
