package Project.Controller.Effects.RealEffects;

import Project.toDelete.BonusInteraction;
import Project.toDelete.OkOrNo;
import Project.MODEL.Player;

/**
 * Created by Rita1 on 09/05/2017.
 */
public class AddCoin implements TakeRoPEffects {
    private int quantity;

    public AddCoin (int quantity){
        this.quantity = quantity;
    }


    @Override
    public BonusInteraction doEffect(Player player) {
        player.getPersonalBoardReference().setCoins(player.getPersonalBoardReference().getCoins() + quantity);
        OkOrNo Ok = new OkOrNo(false);
        Ok.setOk(true);
        return Ok;
    }
}
