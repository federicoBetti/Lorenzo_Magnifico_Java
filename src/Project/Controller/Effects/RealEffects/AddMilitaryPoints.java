package Project.Controller.Effects.RealEffects;

import Project.toDelete.BonusInteraction;
import Project.toDelete.OkOrNo;
import Project.MODEL.*;

/**
 * Created by Rita1 on 09/05/2017.
 */
public class AddMilitaryPoints implements TakeRoPEffects {

    private int quantity;

    public AddMilitaryPoints (int quantity){
        this.quantity = quantity;
    }


    @Override
    public BonusInteraction doEffect(Player player) {
        player.getScore().setMilitaryPoints(player.getScore().getMilitaryPoints() + quantity);
        OkOrNo Ok = new OkOrNo();
        Ok.setOk(true);
        return Ok;
    }
}
