package ServerModel;

public class AddWood implements Effects {
    private int quantity;

    public AddWood (int quantity){
        this.quantity = quantity;
    }


    @Override
    public BonusInteraction doEffect(Player player) {
        player.getPersonalBoardReference().setWood(player.getPersonalBoardReference().getWood() + quantity);
        OkOrNo Ok = new OkOrNo();
        Ok.setOk(true);
        return Ok;
    }
}
