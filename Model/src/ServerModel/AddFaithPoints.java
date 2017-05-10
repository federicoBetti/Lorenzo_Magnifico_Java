package ServerModel;

/**
 * Created by Rita1 on 09/05/2017.
 */
public class AddFaithPoints implements Effects{
    private int quantity;

    public AddFaithPoints (int quantity){
        this.quantity = quantity;
    }


    public BonusInteraction doEffect(Player player) {
        player.getScore().setFaithPoints(player.getScore().getFaithPoints() + quantity);
        OkOrNo Ok = new OkOrNo();
        Ok.setOk(true);
        return Ok;
    }
}
